package BLL;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.LinkedList;

public class Turno {
    private long id; 
    private int pacienteId; 
    private int medicoId;
    private LocalDate fecha; 
    private Time hora; 
    private String estado; 
    private String descripcion; 
    private LinkedList<RegistroMedico> registros;

    public Turno(long id, int pacienteId, int medicoId, LocalDate fecha, Time hora, String estado, String descripcion) {
        this.id = id;
        this.pacienteId = pacienteId;
        this.medicoId = medicoId;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
        this.descripcion = descripcion;
        this.registros = new LinkedList<>();
    }
    public Turno() {
        this.registros = new LinkedList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(int pacienteId) {
        this.pacienteId = pacienteId;
    }

    public int getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(int medicoId) {
        this.medicoId = medicoId;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public LinkedList<RegistroMedico> getRegistro() {
        return registros;
    }

    public void addRegistro(RegistroMedico registro) {
        this.registros.add(registro);
    }


	@Override
	public String toString() {
		return "Turno [id=" + id + ", pacienteId=" + pacienteId + ", medicoId=" + medicoId + ", fecha=" + fecha
				+ ", hora=" + hora + ", estado=" + estado + ", descripcion=" + descripcion + ", registros=" + registros
				+ "]";
	}

}
