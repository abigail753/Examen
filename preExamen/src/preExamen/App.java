package preExamen;

import java.awt.Color;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class App {

	private JFrame frame;
	private JTable table;
	private JTextField txtId;
	private JTextField txtNombre;
	private JTextField txtCalorias;
	
	ConexionSingleton c = new ConexionSingleton();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection con;
	PreparedStatement pstU;
	ResultSet rsU;
	PreparedStatement pstB;
	ResultSet rsB;
	
	
	public void loadDataU() {
		try {
			con = ConexionSingleton.getConnection();
			pstU = con.prepareStatement("SELECT * FROM persona ");
			rsU = pstU.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rsU));

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void clear() {
		txtId.setText("");
		txtNombre.setText("");
		txtCalorias.setText("");
	}
	

	/**
	 * Create the application.
	 */
	public App() {
		initialize();
		loadDataU();
	}
	
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 712, 288);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		DefaultTableModel model = new DefaultTableModel();
		
		model.addColumn("Código");
		model.addColumn("Nombre");
		model.addColumn("Precio");

		table = new JTable();
		table.setSelectionBackground(new Color(169, 206, 218));
		table.setDefaultEditor(Object.class, null);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow();
				TableModel modelB = table.getModel();

				txtId.setText(modelB.getValueAt(index, 0).toString());
				txtNombre.setText(modelB.getValueAt(index, 1).toString());
				txtCalorias.setText(modelB.getValueAt(index, 2).toString());
			}
		});
		table.setRowHeight(30);
		frame.getContentPane().add(table);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(56, 22, 366, 122);
		frame.getContentPane().add(scrollPane); 
		
		
		
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(463, 21, 114, 19);
		frame.getContentPane().add(txtId);
		txtId.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(463, 70, 170, 19);
		frame.getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		txtCalorias = new JTextField();
		txtCalorias.setBounds(463, 125, 170, 19);
		frame.getContentPane().add(txtCalorias);
		txtCalorias.setColumns(10);
		
		JButton btnNewButton = new JButton("Insertar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nombre = txtNombre.getText();
				String calorias = txtCalorias.getText();
				
				if (txtId.getText().isEmpty()) {
					try {
						String sql = "INSERT INTO persona (nombre, calorias,idpersona) VALUES (?,?,0)";
						pstU = con.prepareStatement(sql);
						pstU.setString(1, nombre);
						pstU.setString(2, calorias);
						pstU.executeUpdate();
						
						JOptionPane.showMessageDialog(null,
								"Se ha creado el nuevo usuario con éxito");
						clear();
						loadDataU();

					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnNewButton.setBounds(42, 184, 117, 25);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Actualizar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nombre = txtNombre.getText();
				String calorias = txtCalorias.getText();


					try {
						String sql = "UPDATE persona SET nombre=?,calorias=? WHERE idpersona=?";
						pstU = con.prepareStatement(sql);
						pstU.setString(1, nombre);
						pstU.setString(2, calorias);
						pstU.setString(3, txtId.getText());
						pstU.executeUpdate();
						
						JOptionPane.showMessageDialog(null, 
								"Se ha actualizado el usuario con exito.");
						clear();
						loadDataU();

					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				
			}
		});
		btnNewButton_1.setBounds(220, 184, 117, 25);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Borrar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String sql = "DELETE FROM persona WHERE idpersona=?";
					pstU = con.prepareStatement(sql);
					pstU.setString(1, txtId.getText());
					pstU.executeUpdate();

					JOptionPane.showMessageDialog(null, "Se ha eliminado el usuario con exito");
					clear();
					loadDataU();

				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(392, 184, 117, 25);
		frame.getContentPane().add(btnNewButton_2);
		
		

	}
}
