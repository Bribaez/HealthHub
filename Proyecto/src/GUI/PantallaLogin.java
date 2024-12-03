package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.Admin;
import BLL.Paciente;
import BLL.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;

import javax.print.attribute.standard.JobPrioritySupported;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class PantallaLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField inpNombre;
	private JPasswordField pswContrania;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaLogin frame = new PantallaLogin();
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
	public PantallaLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel titulo = new JLabel("");
	
		titulo.setFont(new Font("Ubuntu Medium", Font.BOLD, 30));
		titulo.setForeground(new Color(255, 0, 0));
		titulo.setBackground(new Color(255, 0, 0));
		titulo.setBounds(353, 193, 71, 57);
		contentPane.add(titulo);
		
		JLabel lblNombre = new JLabel("Nombre de usuario");
		lblNombre.setBounds(148, 27, 148, 14);
		contentPane.add(lblNombre);
		
		inpNombre = new JTextField();
		inpNombre.setBounds(148, 43, 110, 20);
		contentPane.add(inpNombre);
		inpNombre.setColumns(10);
		
		JLabel lblcontrasena = new JLabel("Contraseña");
		lblcontrasena.setBounds(170, 74, 71, 14);
		contentPane.add(lblcontrasena);
		
		pswContrania = new JPasswordField();
		pswContrania.setBounds(148, 98, 110, 20);
		contentPane.add(pswContrania);
		JLabel lblerror = new JLabel("");
		lblerror.setForeground(new Color(255, 0, 0));
		lblerror.setBounds(148, 193, 110, 20);
		contentPane.add(lblerror);
		JButton btnEnviar = new JButton("Login");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				btnEnviar.addActionListener(new ActionListener() {
				    public void actionPerformed(ActionEvent e) {
				        Usuario nueva = Usuario.Login(inpNombre.getText(), pswContrania.getText());
				        if (nueva != null) {
				            lblerror.setVisible(false);
				            if (nueva.getRol().equalsIgnoreCase("admin")) {
				                PantallaAdmin admin = new PantallaAdmin();
				                admin.setVisible(true);
				                dispose();
				            } else if (nueva.getRol().equalsIgnoreCase("paciente")) {
				                PantallaPaciente paciente = new PantallaPaciente();
				                paciente.setVisible(true);
				                dispose();
				            } else if (nueva.getRol().equalsIgnoreCase("medico")) {
				                int medicoId = nueva.getId();  
				                PantallaMedico medico = new PantallaMedico(medicoId); 
				                medico.setVisible(true);
				                dispose();
				            }
				        } else {
				            lblerror.setText("No encontrado");
				        }
				    }
				});


			

				
			}
		});
		btnEnviar.setBounds(50, 161, 89, 23);
		contentPane.add(btnEnviar);
		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				PantallaRegistro registro = new PantallaRegistro();
				registro.setVisible(true);
				dispose();
			}
		});
		btnRegistrarse.setBounds(254, 161, 110, 23);
		contentPane.add(btnRegistrarse);
		
	}
}
