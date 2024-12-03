package BLL;

import java.sql.Time;
import java.time.LocalDate;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import DLL.ControllerTurno;
import DLL.ControllerUsuario;
import GUI.Main;

public class Paciente extends Usuario implements Menu {
    private static int incremental;
    private String nombre;
    private String apellido;
    private LinkedList<RegistroMedico> historialMedico;
    private LocalDate fechaNacimiento;

    public Paciente(int id, String nombre, String password, String rol) {
        super(nombre, password, rol, id);
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = LocalDate.now();
    }

    public Paciente() {
		// TODO Auto-generated constructor stub
	}

	public LocalDate getFechaNacimiento() {
        return this.fechaNacimiento;
    }

    public String getApellido() {
        return this.apellido; 
    }
	public void menu() {
		JOptionPane.showMessageDialog(null, "Menu paciente");
	}

	@Override
	public void menuPrincipal() {
		JOptionPane.showMessageDialog(null, "Menu paciente desde interface");

		String[] opciones = {
				"Ver turnos", "Sacar turno", "Mis médicos", "Datos personales", "Salir"
		};

		int opcion = 0;
		do {
			opcion = JOptionPane.showOptionDialog(null, "Opciones generales", "Menu Paciente", 0, JOptionPane.DEFAULT_OPTION,
					new ImageIcon(Main.class.getResource("/img/paciente.jpg")), opciones, opciones[0]);

			switch (opcion) {
			case 0:
				// Ver turnos
				System.out.println("ID del paciente: " + this.getId());

				LinkedList<Turno> turnos = ControllerTurno.MostrarTurnosPorPaciente(this.getId());
				if (turnos.isEmpty()) {
					JOptionPane.showMessageDialog(null, "No tienes turnos registrados.");
				} else {
					StringBuilder sb = new StringBuilder("Tus turnos:\n");
					for (Turno turno : turnos) {
						sb.append("Fecha: " + turno.getFecha() + " Hora: " + turno.getHora() + " Estado: " + turno.getEstado() + "\n");
					}
					JOptionPane.showMessageDialog(null, sb.toString());
				}
				break;
			case 1:
				// Sacar turno
				// Obtener todos los usuarios
				LinkedList<Usuario> medicos = ControllerUsuario.MostrarUsuarios();
				LinkedList<Usuario> medicosDisponibles = new LinkedList<>();

				// Filtrar los usuarios con rol de médico
				for (Usuario medico : medicos) {
				    if (medico.getRol().equalsIgnoreCase("medico")) {
				        medicosDisponibles.add(medico);
				    }
				}

				// Verifica si hay médicos disponibles antes de mostrar el diálogo
				if (medicosDisponibles.isEmpty()) {
				    JOptionPane.showMessageDialog(null, "No hay médicos disponibles en este momento.");
				} else {
				    // Crear el arreglo de opciones para el diálogo
				    String[] opcionesMedicos = new String[medicosDisponibles.size()];
				    for (int i = 0; i < medicosDisponibles.size(); i++) {
				        opcionesMedicos[i] = medicosDisponibles.get(i).getNombre();
				    }

				    // Mostrar el diálogo para seleccionar médico
				    String medicoSeleccionado = (String) JOptionPane.showInputDialog(
				        null, 
				        "Selecciona un médico:", 
				        "Médico", 
				        JOptionPane.PLAIN_MESSAGE, 
				        null, 
				        opcionesMedicos, 
				        opcionesMedicos[0]
				    );

				    // Validar selección
				    if (medicoSeleccionado != null && !medicoSeleccionado.trim().isEmpty()) {
				        JOptionPane.showMessageDialog(null, "Médico seleccionado: " + medicoSeleccionado);

				        // Solicitar fecha del turno
				        String fechaString = JOptionPane.showInputDialog("Ingrese la fecha del turno (YYYY-MM-DD):");
				        LocalDate fecha = null;
				        try {
				            fecha = LocalDate.parse(fechaString);
				        } catch (Exception e) {
				            JOptionPane.showMessageDialog(null, "Fecha inválida. Por favor, ingrese en formato YYYY-MM-DD.");
				            return;
				        }

				        // Solicitar hora del turno
				        String horaString = JOptionPane.showInputDialog("Ingrese la hora del turno (HH:MM):");
				        Time hora = null;
				        try {
				            hora = Time.valueOf(horaString + ":00");
				        } catch (Exception e) {
				            JOptionPane.showMessageDialog(null, "Hora inválida. Por favor, ingrese en formato HH:MM.");
				            return;
				        }


				        Usuario medico = medicosDisponibles.stream()
				            .filter(m -> m.getNombre().trim().equalsIgnoreCase(medicoSeleccionado.trim()))
				            .findFirst()
				            .orElse(null);

				        if (medico != null) {
				            // Crear el turno
				            Turno nuevoTurno = new Turno(
				                (long) this.getId(),  
				                (int) medico.getId(),  
				                opcion,                
				                fecha,                 
				                hora,                
				                "pendiente",           
				                "Consulta general"     
				            );

				            long exito = ControllerTurno.agregarTurno(nuevoTurno);

				            if (exito > 0) {
				                JOptionPane.showMessageDialog(null, "Turno creado exitosamente con ID: " + exito);
				            } else {
				                JOptionPane.showMessageDialog(null, "No se pudo crear el turno.");
				            }
				        } else {
				            JOptionPane.showMessageDialog(null, "Médico no encontrado.");
				        }
				    } else {
				        JOptionPane.showMessageDialog(null, "No seleccionaste un médico válido.");
				    }
				}


				break;
			case 2:
				// Mis médicos
				LinkedList<Turno> turnosPorPaciente = ControllerTurno.MostrarTurnosPorPaciente(this.getId());
				if (turnosPorPaciente.isEmpty()) {
					JOptionPane.showMessageDialog(null, "No tenes médicos registrados.");
				} else {
					StringBuilder sbMedicos = new StringBuilder("Tus médicos:\n");
					for (Turno turno : turnosPorPaciente) {
						Usuario medicoDeTurno = ControllerUsuario.BuscarUsuario(turno.getMedicoId());
						sbMedicos.append(medicoDeTurno.getNombre() + "\n");
					}
					JOptionPane.showMessageDialog(null, sbMedicos.toString());
				}
				break;
			case 3:
				// Datos personales
				String datos = "Nombre: " + this.getNombre() + "\n";
				datos += "Apellido: " + this.getApellido() + "\n";  
				datos += "Fecha de Nacimiento: " + this.getFechaNacimiento() + "\n";
				JOptionPane.showMessageDialog(null, datos);
				break;
			case 4: 
				JOptionPane.showMessageDialog(null, "Finalizar");
				break;
			default:
				break;
			}

		} while (opcion != 4); 
	}








}
