import java.util.LinkedList;

public class Medico {
	private String nombre;
	private String especialidad;
	private LinkedList<Paciente> paciente;
	
	public Medico(String nombre, String especialidad, LinkedList<Paciente> paciente) {
		super();
		this.nombre = nombre;
		this.especialidad = especialidad;
		this.paciente = paciente;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEspecialidad() {
		return especialidad;
	}
	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
	public LinkedList<Paciente> getPaciente() {
		return paciente;
	}

	public void setPaciente(LinkedList<Paciente> paciente) {
		this.paciente = paciente;
	}

	@Override
	public String toString() {
		return "Medico [nombre=" + nombre + ", especialidad=" + especialidad + ", paciente=" + paciente + "]";
	}
	
	
	
	

}
           