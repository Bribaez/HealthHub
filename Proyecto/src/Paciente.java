
import java.time.LocalDate;
import java.util.LinkedList;

public class Paciente {
	private String nombre;
	private String apellido;
	private LocalDate fecha_nacimiento;
	private LinkedList<RegistroMedico> historialMedico;
	
	public Paciente(String nombre, String apellido, LocalDate fecha_nacimiento,
			LinkedList<RegistroMedico> historialMedico) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.fecha_nacimiento = fecha_nacimiento;
		this.historialMedico = historialMedico;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public LocalDate getFecha_nacimiento() {
		return fecha_nacimiento;
	}
	public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}
	public LinkedList<RegistroMedico> getHistorialMedico() {
		return historialMedico;
	}
	public void setHistorialMedico(LinkedList<RegistroMedico> historialMedico) {
		this.historialMedico = historialMedico;
	}

	@Override
	public String toString() {
		return "Paciente [nombre=" + nombre + ", apellido=" + apellido + ", fecha_nacimiento=" + fecha_nacimiento
				+ ", historialMedico=" + historialMedico + "]";
	}
	
	
	
	
	
	
}
                           