import java.awt.Color;
import java.awt.Font;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

public class Chrono {
	int h = 0, m = 0, s = 0, ms = 0;
	boolean running = false;

	public static void main(String[] args) {
		Chrono crono = new Chrono();
		crono.runChrono();
		}
	
	public void runChrono() {
		ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
		JFrame frame = new JFrame("Chrono");
		frame.setSize(450, 200);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		JLabel time = new JLabel("00:00:00::000");
		time.setFont(new Font(time.getFont().getName(), time.getFont().getStyle(), 55));
		time.setBorder(new LineBorder(Color.BLACK));
		ses.scheduleAtFixedRate(() -> timeFlow(time), 0, 1, TimeUnit.MILLISECONDS);
			
		
		JButton toggleCount = new JButton("start");
		toggleCount.setSize(120, 50);
		toggleCount.setFont(new Font(time.getFont().getName(), time.getFont().getStyle(), 40));
		toggleCount.addActionListener(e -> {
			if(!running) {
				running = true;
				toggleCount.setText("stop");
				}
			else {
				running = false;
				toggleCount.setText("start");
				}
			});
		
		
		JButton reset = new JButton("reset");
		reset.setSize(120, 50);
		reset.setFont(new Font(time.getFont().getName(), time.getFont().getStyle(), 40));
		reset.addActionListener(e -> {
			h = 0;	m = 0; s = 0; ms = 0;
			time.setText(String.format("%02d:%02d:%02d::%03d", h%24, m%60, s%60, ms%1000));
			});
		
		
		panel.add(time);
		panel.add(toggleCount);
		panel.add(reset);
		frame.add(panel);
		frame.setVisible(true);
		}
	
	public void timeFlow(JLabel time) {
		if(running) {
			ms++;
			if(ms > 0 && ms % 1000 == 0) s++;
	        if(s > 0 && s % 60 == 0) m++;
	        if(m > 0 && m % 60 == 0) h++;
	        time.setText(String.format("%02d:%02d:%02d::%03d", h%24, m%60, s%60, ms%1000));
			}
		}
	}