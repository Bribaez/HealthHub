package BLL;

import java.time.LocalDate;

public class RegistroMedico {

    private LocalDate fechaRegistro;
    private String diagnostico;
    private String tratamiento;

    public RegistroMedico(LocalDate fechaRegistro, String diagnostico, String tratamiento) {
        this.fechaRegistro = fechaRegistro;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
    }


    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    @Override
    public String toString() {
        return "RegistroMedico [fechaRegistro=" + fechaRegistro + ", diagnostico=" + diagnostico + ", tratamiento=" + tratamiento + "]";
    }


	public long getId() {
		// TODO Auto-generated method stub
		return 0;
	}
}
