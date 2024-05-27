package com.hibernate.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import com.hibernate.dao.PersonaDAO;
import com.hibernate.model.Persona;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class App {

	private JFrame frame;
	private DefaultTableModel model;
	private JTable tabla;
	private Persona p;
	
	PersonaDAO pDAO = new PersonaDAO();
	private JTextField txtId;
	private JTextField txtNombre;
	private JTextField txtCalorias;
	private JButton btnNewButton;
	private JButton btnActualizar;
	private JButton btnEliminar;

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

	/**
	 * Create the application.
	 */
	public App() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 780, 393);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		/*********************************************************
		 *                     Modelo de Usuario                 *
		 *********************************************************/
		model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("Nombre");
		model.addColumn("Correo");
		
		for (Persona p : pDAO.selectAllPerson()) {
			Object[] row = new Object[3];
			row[0] = p.getId();
			row[1] = p.getNombre();
			row[2] = p.getCalorias();

			model.addRow(row);
		}
		frame.getContentPane().setLayout(null);

		/*********************************************************
		 *                     Tabla de Usuario                  *
		 *********************************************************/
		tabla = new JTable(model);
		tabla.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tabla.getSelectedRow();
				TableModel model = tabla.getModel();
				
				txtId.setText(model.getValueAt(index, 0).toString());
				txtNombre.setText(model.getValueAt(index, 1).toString());
				txtCalorias.setText(model.getValueAt(index, 2).toString());
				
			}
		});
		tabla.setToolTipText("");
		
		// Este objeto nos permite personalizar el texto de las celdas
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		//Aplicamos el objeto DefaultTableCellRenderer como renderizador para todas las celdas
		tabla.setDefaultRenderer(Object.class, centerRenderer);
		
		//No dejamos que el usuario pueda editar la tabla
		tabla.setDefaultEditor(Object.class, null);
		tabla.setSelectionBackground(new Color(111, 172, 187));

		tabla.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tabla.setRowHeight(30);
		
		JScrollPane scrollPaneUsuario = new JScrollPane(tabla);
		scrollPaneUsuario.setBounds(12, 26, 358, 155);
		scrollPaneUsuario.setBackground(new Color(32, 104, 122));
		scrollPaneUsuario.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Personas",
				TitledBorder.CENTER, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
		frame.getContentPane().add(scrollPaneUsuario);
		
		txtId = new JTextField();
		txtId.setBounds(463, 26, 114, 19);
		frame.getContentPane().add(txtId);
		txtId.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(463, 87, 114, 19);
		frame.getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		txtCalorias = new JTextField();
		txtCalorias.setBounds(463, 149, 114, 19);
		frame.getContentPane().add(txtCalorias);
		txtCalorias.setColumns(10);
		
		btnNewButton = new JButton("Insertar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String nombre = txtNombre.getText();
				String calorias = txtCalorias.getText();
				
				p = new Persona(nombre, Integer.valueOf(calorias));
				pDAO.insertPersona(p);
				
				
			}
		});
		btnNewButton.setBounds(35, 222, 117, 25);
		frame.getContentPane().add(btnNewButton);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nombre = txtNombre.getText();
				String calorias = txtCalorias.getText();

				p = pDAO.selectPersonaById(Integer.valueOf(txtId.getText()));

				p.setNombre(nombre);
				p.setCalorias(Integer.valueOf(calorias));


				pDAO.updatePersona(p);
			}
		});
		btnActualizar.setBounds(238, 222, 117, 25);
		frame.getContentPane().add(btnActualizar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				
				p =pDAO.selectPersonaById(Integer.valueOf(txtId.getText()));
				pDAO.deletePersona(0);
			}
		});
		btnEliminar.setBounds(431, 222, 117, 25);
		frame.getContentPane().add(btnEliminar);
		
	}

}
