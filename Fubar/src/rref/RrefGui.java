package rref;

import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.table.TableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;

public class RrefGui {

	private JFrame frame;
	private JTextField rows;
	private JButton btnUpdate;
	private JPanel panel;
	private String[] variables;
	private int rowNum;
	private JTable table;
	private JTextField variableTxt;
	private Object[][] data;
	private double[][] values;

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

		JLabel lblvariables = new JLabel("Variables:");
		lblvariables.setBounds(160, 12, 82, 15);
		frame.getContentPane().add(lblvariables);

		variableTxt = new JTextField();
		variableTxt.setBounds(246, 10, 114, 19);
		frame.getContentPane().add(variableTxt);
		variableTxt.setColumns(10);

		JButton btnSolve = new JButton("Solve");
		btnSolve.setBounds(243, 37, 117, 25);
		frame.getContentPane().add(btnSolve);
		frame.getContentPane().setFocusTraversalPolicy(
				new FocusTraversalOnArray(new Component[] { lblRows, rows, variableTxt, btnUpdate, lblvariables }));

		btnSolve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					valuesSetup();
					rref solver = new rref(values);
					solver.solve();
					values = solver.getMatrix();
					setSolvedValues();

				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Something Went Wrong");
					e.printStackTrace();
				}
			}

			private void setSolvedValues() {
				for (int i = 0; i < rowNum; i++) {
					for (int j = 0; j < rowNum + 1; j++) {
						table.setValueAt(values[i][j], i, j);
					}
				}

			}

			private void valuesSetup() {
				values = new double[rowNum][rowNum + 1];
				for (int i = 0; i < rowNum; i++) {
					for (int j = 0; j < rowNum + 1; j++) {
						if (table.getValueAt(i, j).getClass().equals(String.class)) {
							values[i][j] = Double.parseDouble((String) table.getValueAt(i, j));
						} else if(table.getValueAt(i, j).getClass().equals(Integer.class) || table.getValueAt(i, j).getClass().equals(Double.class)){
							values[i][j] = (double) table.getValueAt(i, j); 
						}
					}
				}

			}

		});

		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					rowNum = Integer.parseInt(rows.getText());
					// JOptionPane.showMessageDialog(null, rowNum);
					variablesSetUp();
					if (variables.length != rowNum + 1) {
						JOptionPane.showMessageDialog(null, "Sorry, the dimensions of your matrices are not correct");
						return;
					}
					makeTable();
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "That is not a number. Please try again");
				} catch (HeadlessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			private void makeTable() {
				table = new JTable(data, variables);
				table.setFillsViewportHeight(true);
				JScrollPane scrollPane = new JScrollPane(table);
				scrollPane.setBounds(12, 65, 426, 120);
				frame.getContentPane().add(scrollPane);

			}

			private void variablesSetUp() {
				variables = (variableTxt.getText() + ",Constants").split(",");

				data = new Object[rowNum][rowNum + 1];
				for (int i = 0; i < rowNum; i++) {
					for (int j = 0; j < rowNum + 1; j++) {
						data[i][j] = 0;
					}
				}

			}
		});
	}
}
