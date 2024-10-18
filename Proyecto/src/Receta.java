
import java.time.LocalDate;
import java.util.LinkedList;

public class Receta {
	private LocalDate fechaEmision;
	private String medicamento;
	private LinkedList<Medico> medico;
	public Receta(LocalDate fechaEmision, String medicamento, LinkedList<Medico> medico) {
		super();
		this.fechaEmision = fechaEmision;
		this.medicamento = medicamento;
		this.medico = medico;
	}
	public LocalDate getFechaEmision() {
		return fechaEmision;
	}
	public void setFechaEmision(LocalDate fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	public String getMedicamento() {
		return medicamento;
	}
	public void setMedicamento(String medicamento) {
		this.medicamento = medicamento;
	}
	public LinkedList<Medico> getMedico() {
		return medico;
	}
	public void setMedico(LinkedList<Medico> medico) {
		this.medico = medico;
	}
	
	@Override
	public String toString() {
		return "Receta [fechaEmision=" + fechaEmision + ", medicamento=" + medicamento + ", medico=" + medico + "]";
	}  
	
	
}
 