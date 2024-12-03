package repository;

import java.util.LinkedList;
import java.util.List;

import BLL.RegistroMedico;
import BLL.Turno;

public interface TurnoRepository {
	void agregarTurno(Turno turno);
	LinkedList<Turno> MostrarTurnos();
	Turno BuscarTurno(int id);
	void EliminarTurno(int id);
	 void ActualizarTurno(Turno turno);
	 LinkedList<Turno> MostrarTurnosPorPaciente(int pacienteId);
	 List<Turno> obtenerTurnosMedico(int medicoId);
	 void agregarRegistroMedico(long turnoId, RegistroMedico registro);
}
