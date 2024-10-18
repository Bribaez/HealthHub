package BLL;


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
				"Ver turnos","Mis medicos","Mis recetas","Datos personales", "Salir",
		};

		int opcion=0;
		do {
			opcion= JOptionPane.showOptionDialog(null, "Opciones generales", "A", 0, 0
					, null, opcionoesGenerales, opcionoesGenerales[0]);
			switch (opcion) {
			case 0:
				//Ver turnos
				String[] opcionoesTurnos= {
						"Sacar turno","Ver turnos anteriores","Ver turnos proximos","Cancelar turno","Turnos disponibles","Salir"
				};
				int opcionTurno=0;
				do {
					opcionTurno = JOptionPane.showOptionDialog(null, "Opciones turnos", "", 0, 0
							, null, opcionoesTurnos, opcionoesTurnos[0]);
					switch (opcionTurno) {
					case 0:
						//Sacar turno
						break;
					case 1:
						//Ver turnos anteriores
						break;
					case 2:
						//Ver turnos proximos
						break;
					case 3:
						//Cancelar turno
					case 4:
						//Turnos disponibles
						break;
					case 5:
						
						JOptionPane.showMessageDialog(null, "Salir");
						break;
					default:
						break;
					}
				} while (opcionTurno!=5);	



				break;
			case 1:

				break;
			default:	

				break;
			}

		} while (opcion!=4);












	}

}
