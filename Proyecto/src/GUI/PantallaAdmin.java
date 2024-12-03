package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import BLL.Usuario;
import DLL.ControllerUsuario;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;

public class PantallaAdmin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private JLabel elemento;
    private JTextField filtro;

    
	private Usuario seleccionado;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaAdmin frame = new PantallaAdmin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PantallaAdmin() {
		this.setVisible(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 909, 452);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

	
		Usuario seleccionado = new Usuario();


		String[] columnNames = { "ID", "Nombre", "Rol" };
		model = new DefaultTableModel(columnNames, 0);
		table = new JTable(model);
		actualizarTabla();
		contentPane.setLayout(null);


		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(5, 19, 911, 190);
		contentPane.add(scrollPane);


		elemento = new JLabel("Seleccionado:");
		elemento.setBounds(5, 5, 911, 14);
		contentPane.add(elemento);


		
		 filtro = new JTextField();
		 filtro.setBounds(140, 250, 200, 40);		 
	     contentPane.add(filtro);
	     filtro.setVisible(true);
	     
	     JButton Eliminar = new JButton("Eliminar");
	     Eliminar.setBounds(540, 300, 100, 30);
	        contentPane.add(Eliminar);
	        Eliminar.setVisible(true);
	        Eliminar.addActionListener(new ActionListener() {
	        	  public void actionPerformed(ActionEvent e) {
	        		  if (seleccionado.getId()!=0) {
						
	        			  
	        			  ControllerUsuario.EliminarUsuario(seleccionado.getId());
	        			  JOptionPane.showMessageDialog(null, "Se elimino");
	        				actualizarTabla();
	        				seleccionado.setId(0);

	        		  } else {
	        			  JOptionPane.showMessageDialog(null, "No se selecciono nada");

	        		  }
	        		  
	        	  }
	        });
	     
	     JButton filtrarNombre = new JButton("Filtrar nombre");
	     filtrarNombre.setBounds(340, 300, 100, 30);
	        contentPane.add(filtrarNombre);
	        filtrarNombre.setVisible(true);
	        filtrarNombre.addActionListener(new ActionListener() {
	        	  public void actionPerformed(ActionEvent e) {
	        		  FiltrarNombre(filtro.getText());
	        	  }
	        });
		JButton btnRegistrar = new JButton("Filtrar");
		btnRegistrar.setBounds(140, 300, 100, 30);
        contentPane.add(btnRegistrar);
        btnRegistrar.setVisible(true);

        btnRegistrar.addActionListener(new ActionListener() {
        	  public void actionPerformed(ActionEvent e) {
        		  Filtrar(filtro.getText());
        	  }
        });
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(15, 220, 101, 22);
		contentPane.add(menuBar);

		ListSelectionModel selectionModel = table.getSelectionModel();
		selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		
		selectionModel.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					int selectedRow = table.getSelectedRow();
					if (selectedRow != -1) {
						int id = (int) table.getValueAt(selectedRow, 0);
						String nombre = (String) table.getValueAt(selectedRow, 1);
						String rol = (String) table.getValueAt(selectedRow, 2);

						elemento.setText("Seleccionado: ID=" + id + ", Nombre=" + nombre + ", Mail=" + rol);
						seleccionado.setNombre(nombre);
						seleccionado.setId(id);
						seleccionado.setRol(rol);
					}
				}
			}
		});
	}

	private void actualizarTabla() {
		
		model.setRowCount(0);
		List<Usuario> usuarios = ControllerUsuario.MostrarUsuarios();
		for (Usuario usuario : usuarios) {
			model.addRow(new Object[] { usuario.getId(), usuario.getNombre(), usuario.getRol() });
		}
	}
	private void Filtrar(String filtro) {
		model.setRowCount(0);
		List<Usuario> usuarios = ControllerUsuario.MostrarUsuarios();
		for (Usuario usuario : usuarios) {
			if ( usuario.getRol().equals(filtro)) {
				model.addRow(new Object[] { usuario.getId(), 
						usuario.getNombre(), usuario.getRol() });
			}
			
		}
	}
	private void FiltrarNombre(String filtro) {
		model.setRowCount(0);
		List<Usuario> usuarios = ControllerUsuario.MostrarUsuarios();
		for (Usuario usuario : usuarios) {
			if ( usuario.getNombre().toLowerCase().startsWith(filtro.toLowerCase())) {
				model.addRow(new Object[] { usuario.getId(), 
						usuario.getNombre(), usuario.getRol() });
			}
			
		}
	}
}
