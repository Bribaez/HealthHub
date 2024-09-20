import java.util.LinkedList;

import javax.swing.JOptionPane;

public class Medico extends Usuario implements Menu {
	private static int incremental;
	private String nombre;
	private String especialidad;
	private LinkedList<Paciente> paciente;
	
	public Medico(String mail, String contrasena, String rol) {
		super(mail, contrasena, rol);
		this.nombre = nombre;
		this.especialidad = especialidad;
		this.paciente = paciente;
	}

	@Override
	public void menu() {
		JOptionPane.showMessageDialog(null, "Menu medico");
	}

	@Override
	public void menuPrincipal() {
		JOptionPane.showMessageDialog(null, "Menu medico desde interface");

	}
	
	
	
	

}
           