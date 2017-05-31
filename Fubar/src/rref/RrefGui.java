package rref;

import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;

public class RrefGui {

	private JFrame frame;
	private JTextField rows;
	private JButton btnUpdate;
	private JPanel panel;
	private JTextField[] arrayCont;
	private int rowNum;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RrefGui window = new RrefGui();
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
	public RrefGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblRows = new JLabel("Rows:");
		lblRows.setBounds(12, 12, 70, 15);
		frame.getContentPane().add(lblRows);
		
		rows = new JTextField();
		rows.setBounds(63, 10, 82, 20);
		frame.getContentPane().add(rows);
		rows.setColumns(10);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(12, 37, 117, 25);
		frame.getContentPane().add(btnUpdate);
		btnUpdate.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try {
					rowNum = Integer.parseInt(rows.getText());
					arrayContSetUp();
					System.out.println(Arrays.toString(arrayCont));
//					createMatrix();//need to figure this out
					JOptionPane.showMessageDialog(null, rowNum);
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "That is not a number. Please try again");
				} catch (HeadlessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}

			private void arrayContSetUp() {
				arrayCont = new JTextField[rowNum * (rowNum + 1)];
				for(int i = 0; i < rowNum * (rowNum + 1); i++) {
					JTextField tmp = new JTextField();
					arrayCont[i] = tmp;
				}
			}
		});
	
	}
	

	
	private void createMatrix(){
		for(int i = 0; i < rowNum; i++){
			for(int j = 0; j < rowNum + 1; j++){
				arrayCont[(i * (rowNum + 1)) + j].setBounds(12 + 20 * j , 12 + i * 20, 15, 15);
				panel.add(arrayCont[(i * (rowNum + 1)) + j]);
			}
			
		}
		
		
			//setBounds(12 + 20 * j , 12 + i * 20, 15, 15);

	}
	
}
