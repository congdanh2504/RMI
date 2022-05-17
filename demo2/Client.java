package datastoredinserver;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Client extends JFrame {

	private JPanel contentPane;
	private JTable table;
	Vector header = new Vector();
	Vector data = null;
	EmployeeMngInterface stub = null;
	String serviceName = "rmi://localhost:6789/rmiTest";

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
		header.add("Code");
		header.add("Name");
		header.add("Salary");
		
		try {
			stub = (EmployeeMngInterface) Naming.lookup(serviceName);
			data = stub.getInitData();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		DefaultTableModel dModel = (DefaultTableModel) table.getModel();
		dModel.setDataVector(data, header);
	}
	
	private void initView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				data.remove(selectedRow);
				table.updateUI();
			}
		});
		panel.add(deleteButton);
		
		JButton newButton = new JButton("New");
		newButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddFrame(data, table);
			}
		});
		panel.add(newButton);
		
		JButton saveButton = new JButton("Save");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					boolean isSuccess = stub.saveList(data);
					if (isSuccess) {
						JOptionPane.showMessageDialog(null, "Saved");
					} else {
						JOptionPane.showMessageDialog(null, "Error");
					}
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		panel.add(saveButton);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}

}
