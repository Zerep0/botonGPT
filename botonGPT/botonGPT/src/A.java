import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;

public class A {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					A window = new A();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public A() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ToggleButtonBean boton = new ToggleButtonBean();
		boton.addToggleListener(new ToggleListener() {
			
			@Override
			public void onToggle(boolean toggled) {
				System.out.println("Has pulsado el toggle1");
			}
		});
		boton.setAuraColor(Color.PINK);
		
		ToggleButtonBean2 boton1 = new ToggleButtonBean2();
		boton1.addToggleListener(new ToggleListener() {
			
			@Override
			public void onToggle(boolean toggled) {
				System.out.println("JOJO");
			}
		});
		boton1.setAuraColor1(Color.GREEN);
		boton1.setAuraColor2(Color.CYAN);
		frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		frame.getContentPane().add(boton);
		frame.getContentPane().add(boton1);
	}

}
