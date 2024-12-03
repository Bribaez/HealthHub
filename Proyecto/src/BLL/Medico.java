package BLL;

import java.sql.Time;
import java.time.LocalDate;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import DLL.ControllerTurno;
import DLL.ControllerUsuario;
import GUI.Main;

public class Medico extends Usuario implements Menu {
	private static int incremental;
	private String nombre;
	private String apellido;
	private LocalDate fechaNacimiento;
	public Medico(int id, String nombre, String password, String rol) {
	    super(nombre,rol, password, id);
	    this.nombre = nombre;
	    this.apellido = apellido; 
	    this.fechaNacimiento = LocalDate.now();
	}

	public LocalDate getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public String getApellido() {
		return this.apellido;
	}

	@Override
	public void menu() {
		JOptionPane.showMessageDialog(null, "Menu médico");
	}

	@Override
	public void menuPrincipal() {
		JOptionPane.showMessageDialog(null, "Menu médico desde interface");

		String[] opciones = {
				"Ver turnos", "Seleccionar turno y agregar registro médico", "Mis pacientes", "Cancelar turno", "Datos personales", "Salir"
		};

		int opcion = 0;
		do {
			opcion = JOptionPane.showOptionDialog(null, "Opciones generales", "Menu Medico", 0, JOptionPane.DEFAULT_OPTION,
					new ImageIcon(Main.class.getResource("/img/paciente.jpg")), opciones, opciones[0]);
			switch (opcion) {
			case 0:
				// Ver turnos
				LinkedList<Turno> turnos = ControllerTurno.MostrarTurnosPorPaciente1(this.getId());
				if (turnos.isEmpty()) {
					JOptionPane.showMessageDialog(null, "No tenes turnos registrados.");
				} else {
					StringBuilder sb = new StringBuilder("Tus turnos:\n");
					for (Turno turno : turnos) {
						sb.append("Paciente ID: " + turno.getPacienteId() + " Fecha: " + turno.getFecha() + 
								" Hora: " + turno.getHora() + " Estado: " + turno.getEstado() + "\n");
					}
					JOptionPane.showMessageDialog(null, sb.toString());
				}
				break;
			case 1:
				// Seleccionar turno y agregar registro médico
				LinkedList<Turno> turnosMedico = ControllerTurno.MostrarTurnosPorPaciente1(this.getId());
				if (turnosMedico.isEmpty()) {
				    JOptionPane.showMessageDialog(null, "No tenes turnos para seleccionar.");
				} else {
				    String[] opcionesTurnos = new String[turnosMedico.size()];
				    for (int i = 0; i < turnosMedico.size(); i++) {
				        Turno turno = turnosMedico.get(i);
				        opcionesTurnos[i] = "Paciente ID: " + turno.getPacienteId() + " Fecha: " + turno.getFecha() + " Hora: " + turno.getHora();
				    }

				    String turnoSeleccionado = (String) JOptionPane.showInputDialog(
				            null,
				            "Selecciona un turno:",
				            "Turno",
				            JOptionPane.PLAIN_MESSAGE,
				            null,
				            opcionesTurnos,
				            opcionesTurnos[0]
				    );

				    if (turnoSeleccionado != null && !turnoSeleccionado.trim().isEmpty()) {
				    	Turno turno = turnosMedico.stream()
				    		    .filter(t -> (("Paciente ID: " + t.getPacienteId() + " Fecha: " + t.getFecha() + " Hora: " + t.getHora()).equals(turnoSeleccionado)))
				    		    .findFirst().orElse(null);
				        
				        if (turno != null) {
				            LocalDate fechaRegistro = LocalDate.now();
				            String diagnostico = JOptionPane.showInputDialog("Ingrese el diagnóstico:");
				            String tratamiento = JOptionPane.showInputDialog("Ingrese el tratamiento:");

				            RegistroMedico registro = new RegistroMedico(fechaRegistro, diagnostico, tratamiento);

				       
				            boolean actualizada = ControllerTurno.actualizarRegistroMedico(registro);
							if (actualizada) {
								JOptionPane.showMessageDialog(null, "Registro actualizado con éxito");
							} else {
								JOptionPane.showMessageDialog(null, "Error al actualizar registro");
							}

				        }
				    }
				}


				break;
			case 2:
				// Mis pacientes
				LinkedList<Turno> turnosDeMedico = ControllerTurno.MostrarTurnosPorPaciente1(this.getId());
				if (turnosDeMedico.isEmpty()) {
					JOptionPane.showMessageDialog(null, "No tenes pacientes registrados.");
				} else {
					StringBuilder sbPacientes = new StringBuilder("Tus pacientes:\n");
					for (Turno turno : turnosDeMedico) {
						Usuario pacienteDeTurno = ControllerUsuario.BuscarUsuario(turno.getPacienteId());
						sbPacientes.append(pacienteDeTurno.getNombre() + "\n");
					}
					JOptionPane.showMessageDialog(null, sbPacientes.toString());
				}
				break;
			case 3:
				// Cancelar turno
				LinkedList<Turno> turnosParaCancelar = ControllerTurno.MostrarTurnosPorPaciente1(this.getId());
				if (turnosParaCancelar.isEmpty()) {
				    JOptionPane.showMessageDialog(null, "No tenes turnos para cancelar.");
				} else {
				    String[] opcionesCancelarTurnos = new String[turnosParaCancelar.size()];
				    for (int i = 0; i < turnosParaCancelar.size(); i++) {
				        Turno turno = turnosParaCancelar.get(i);
				        opcionesCancelarTurnos[i] = "Paciente ID: " + turno.getPacienteId() + " Fecha: " + turno.getFecha() + " Hora: " + turno.getHora();
				    }
				    String turnoSeleccionado = (String) JOptionPane.showInputDialog(
				            null, 
				            "Selecciona un turno para cancelar:", 
				            "Cancelar Turno", 
				            JOptionPane.PLAIN_MESSAGE, 
				            null, 
				            opcionesCancelarTurnos, 
				            opcionesCancelarTurnos[0]
				    );

				    if (turnoSeleccionado != null && !turnoSeleccionado.trim().isEmpty()) {
				       
				        Turno turno = turnosParaCancelar.stream()
				                .filter(t -> (("Paciente ID: " + t.getPacienteId() + " Fecha: " + t.getFecha() + " Hora: " + t.getHora()).equals(turnoSeleccionado)))
				                .findFirst().orElse(null);

				        if (turno != null) {
				            ControllerTurno.eliminarTurno(turno.getId());
				        }
				    }
				}

				break;
			case 4:
				// Datos personales
				
				String datos = "Nombre: " + this.getNombre() + "\n";
				datos += "Apellido: " + this.getApellido() + "\n";  
				datos += "Fecha de Nacimiento: " + this.getFechaNacimiento() + "\n";
				JOptionPane.showMessageDialog(null, datos);
				break;
			case 5: 
				JOptionPane.showMessageDialog(null, "Finalizar");
				break;
			default:
				break;
			}

		} while (opcion != 5); 
	}
}
