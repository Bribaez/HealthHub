
import java.time.LocalDate;
import java.util.LinkedList;

import javax.swing.JOptionPane;

public class Paciente extends Usuario implements Menu {
	private static int incremental;
	private String nombre;
	private String apellido;
	private LocalDate fechaNacimieto;
	private LinkedList<RegistroMedico> historialMedico;
	
	public Paciente(String mail, String contrasena, String rol) {
		super(mail, contrasena, rol);
		incremental++;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimieto = fechaNacimieto;
		this.historialMedico = historialMedico;	
	}
	
	
	@Override
	public void menu() {
		JOptionPane.showMessageDialog(null, "Menu paciente");
	}

	@Override
	public void menuPrincipal() {
		JOptionPane.showMessageDialog(null, "Menu paciente desde interface");
		
		
		
		String[] opcionoesGenerales = {
				"Ver turnos","Sacar turno","Ver perfil médico","Salir"
		};

		String[] opcionoesTurnos= {
				"Ver turnos anteriores","Ver turnos proximos","Filtrar","Salir"
		};
		  
		
		int elegido = JOptionPane.showOptionDialog(null, "Opciones generales", "", 0, 0, null, opcionoesGenerales, opcionoesGenerales[0]);
		
		
		switch (elegido) {
		case 0:
			
			elegido = JOptionPane.showOptionDialog(null, "", "", 0, 0, null, opcionoesTurnos, opcionoesTurnos[0]);
			
			//Opciones relacionadas con ver turno
			
			
			break;
		case 1:
			
			//Sacar turno
			
			
			break;
		}
			
			








	}

}
