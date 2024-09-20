import java.time.LocalDate;
import java.util.LinkedList;

public class RegistroMedico {
	private LocalDate fecha;
	String descripcion;
	private LinkedList<Medico> medico;
	private String tratamiiento;
	
	public RegistroMedico(LocalDate fecha, String descripcion, LinkedList<Medico> medico, String tratamiiento) {
		super();
		this.fecha = fecha;
		this.descripcion = descripcion;
		this.medico = medico;
		this.tratamiiento = tratamiiento;
	}

	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public LinkedList<Medico> getMedico() {
		return medico;
	}
	public void setMedico(LinkedList<Medico> medico) {
		this.medico = medico;
	}
	public String getTratamiiento() {
		return tratamiiento;
	}
	public void setTratamiiento(String tratamiiento) {
		this.tratamiiento = tratamiiento;
	}

	@Override
	public String toString() {
		return "RegistroMedico [fecha=" + fecha + ", descripcion=" + descripcion + ", medico=" + medico
				+ ", tratamiiento=" + tratamiiento + "]";
	}

	
}
