package calculator;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.awt.event.ActionEvent;

public class Client extends JFrame {

	private JPanel contentPane;
	private JTextField num1;
	private JTextField num2;
	private JButton addButton;
	private JButton subtractButton;
	private JLabel lblResuilt;
	private JTextField result;
	String serviceName = "rmi://localhost:6789/rmiTest";
	MathService mathService = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client frame = new Client();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Client() {
		initView();
		try {
			mathService = (MathService) Naming.lookup(serviceName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void initView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(4, 2));
		
		JLabel lblNewLabel = new JLabel("Num 1");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel);
		
		num1 = new JTextField();
		contentPane.add(num1);
		num1.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Num 2");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel_1);
		
		num2 = new JTextField();
		contentPane.add(num2);
		num2.setColumns(10);
		
		addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double a = Double.parseDouble(num1.getText());
				double b = Double.parseDouble(num2.getText());
				try {
					double resultNum = mathService.add(a, b);
					result.setText(Double.toString(resultNum));
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
				
			}
		});
		contentPane.add(addButton);
		
		subtractButton = new JButton("Subtract");
		subtractButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double a = Double.parseDouble(num1.getText());
				double b = Double.parseDouble(num2.getText());
				try {
					double resultNum = mathService.subtract(a, b);
					result.setText(Double.toString(resultNum));
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		contentPane.add(subtractButton);
		
		lblResuilt = new JLabel("Resuilt");
		lblResuilt.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblResuilt);
		
		result = new JTextField();
		contentPane.add(result);
		result.setColumns(10);
	}

}
