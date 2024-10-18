
import java.sql.Time;
import java.time.LocalDate;
import java.util.LinkedList;

public class Cita {
	private LocalDate fecha;
	private Time hora;
	private LinkedList<Paciente> paciente;
	private LinkedList<Medico> medico;
	
	public Cita(LocalDate fecha, Time hora, LinkedList<Paciente> paciente, LinkedList<Medico> medico) {
		super();
		this.fecha = fecha;
		this.hora = hora;
		this.paciente = paciente;
		this.medico = medico;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Time getHora() {
		return hora;
	}

	public void setHora(Time hora) {
		this.hora = hora;
	}

	public LinkedList<Paciente> getPaciente() {
		return paciente;
	}

	public void setPaciente(LinkedList<Paciente> paciente) {
		this.paciente = paciente;
	}

	public LinkedList<Medico> getMedico() {
		return medico;
	}

	public void setMedico(LinkedList<Medico> medico) {
		this.medico = medico;
	}

	@Override
	public String toString() {
		return "Cita [fecha=" + fecha + ", hora=" + hora + ", paciente=" + paciente + ", medico=" + medico + "]";
	}  
	
	
	
	
}
 