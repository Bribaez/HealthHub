package DLL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.StatementImpl;


import BLL.RegistroMedico;
import BLL.Turno;

public class ControllerTurno {
	private static final StatementImpl ConexionBD = null;
	private static final StatementImpl Conexion = null;
	private static Connection con = (Connection) conexion.getInstance().getConection();

	// Método para agregar un turno
	public static long agregarTurno(Turno turno) {
		try {
			// Insertar el turno en la tabla turno
			PreparedStatement statement = (PreparedStatement) con.prepareStatement(
					"INSERT INTO turno(paciente_id, medico_id, fecha, hora, estado, descripcion) VALUES (?, ?, ?, ?, ?, ?)",
					PreparedStatement.RETURN_GENERATED_KEYS
					);
			statement.setInt(1, turno.getPacienteId());
			statement.setInt(2, turno.getMedicoId());
			statement.setDate(3, java.sql.Date.valueOf(turno.getFecha()));
			statement.setTime(4, turno.getHora());
			statement.setString(5, turno.getEstado());
			statement.setString(6, turno.getDescripcion());

			int filas = statement.executeUpdate();
			if (filas > 0) {

				try (ResultSet rs = statement.getGeneratedKeys()) {
					if (rs.next()) {
						long turnoID = rs.getLong(1); 


						for (RegistroMedico registro : turno.getRegistro()) {
							PreparedStatement detalleStmt = (PreparedStatement) con.prepareStatement(
									"INSERT INTO registro_medico(turno_id, diagnostico, tratamiento) VALUES (?, ?, ?)"
									);
							detalleStmt.setLong(1, turnoID);  
							detalleStmt.setString(2, registro.getDiagnostico());
							detalleStmt.setString(3, registro.getTratamiento());
							detalleStmt.executeUpdate();
						}

						JOptionPane.showMessageDialog(null, "Se agregó turno con ID " + turnoID);
						return turnoID;
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Error al obtener ID del turno");
				}
			}
		} catch (Exception e) {
			System.out.println("No se agregó el turno: " + e.getMessage());
		}
		return 0;
	}



	// Método para buscar un turno por ID
	public static Turno buscarTurno(long id) {
		Turno turno = null;
		try {
			PreparedStatement statement = (PreparedStatement) 
					con.prepareStatement("SELECT * FROM turno WHERE id = ?");
			statement.setLong(1, id);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {

				java.sql.Date fechaSql = resultSet.getDate("fecha");
				LocalDate fecha = fechaSql != null ? fechaSql.toLocalDate() : null;
				if (fechaSql != null)
					fecha = fechaSql.toLocalDate();
				else
					fecha = null;

				turno = new Turno(
						resultSet.getLong("id"),
						resultSet.getInt("paciente_id"),
						resultSet.getInt("medico_id"),
						fecha,  
						resultSet.getTime("hora"),
						resultSet.getString("estado"),
						resultSet.getString("descripcion")
						);
			}
		} catch (Exception e) {
			System.out.println("No se pudo buscar el turno: " + e.getMessage());
		}
		return turno;
	}

	// Método para eliminar un turno
	public static void eliminarTurno(long id) {
		try {
			PreparedStatement statement = (PreparedStatement) 
					con.prepareStatement("DELETE FROM turno WHERE id = ?");
			statement.setLong(1, id);
			int filas = statement.executeUpdate();
			if (filas > 0) {
				JOptionPane.showMessageDialog(null, "Turno eliminado correctamente.");
			}
		} catch (Exception e) {
			System.out.println("No se pudo eliminar el turno: " + e.getMessage());
		}
	}

	// Método para actualizar un turno
	public static boolean actualizarTurno(Turno turno) {
		try {
			PreparedStatement statement = (PreparedStatement) con.prepareStatement(
					"UPDATE turno SET paciente_id = ?, medico_id = ?, fecha = ?, hora = ?, estado = ?, descripcion = ? WHERE id = ?"
					);
			statement.setInt(1, turno.getPacienteId());
			statement.setInt(2, turno.getMedicoId());
			java.sql.Date fechaSql = java.sql.Date.valueOf(turno.getFecha());
			statement.setDate(3, fechaSql);
			statement.setTime(4, turno.getHora());
			statement.setString(5, turno.getEstado());
			statement.setString(6, turno.getDescripcion());
			statement.setLong(7, turno.getId());

			int filas = statement.executeUpdate();
			if (filas > 0) {
				PreparedStatement eliminarRegistrosStmt = (PreparedStatement) con.prepareStatement(
						"DELETE FROM registro_medico WHERE turno_id = ?"
						);
				eliminarRegistrosStmt.setLong(1, turno.getId());
				eliminarRegistrosStmt.executeUpdate();
				for (RegistroMedico registro : turno.getRegistro()) {
					PreparedStatement insertarRegistroStmt = (PreparedStatement) con.prepareStatement(
							"INSERT INTO registro_medico(turno_id, diagnostico, tratamiento) VALUES (?, ?, ?)"
							);
					insertarRegistroStmt.setLong(1, turno.getId());
					insertarRegistroStmt.setString(2, registro.getDiagnostico());
					insertarRegistroStmt.setString(3, registro.getTratamiento());
					insertarRegistroStmt.executeUpdate();
				}

				JOptionPane.showMessageDialog(null, "Turno y registros médicos actualizados exitosamente.");
				return true;
			}
		} catch (Exception e) {
			System.out.println("Error al actualizar el turno: " + e.getMessage());
			return false;
		}
		return false;
	}
	public static boolean actualizarRegistroMedico(RegistroMedico registro) {

	    try {
	        // Consulta SQL para actualizar el registro médico
	        String query = "UPDATE registro_medico SET diagnostico = ?, tratamiento = ?, fecha_registro = ? WHERE turno_id = ?";
	        PreparedStatement stmt = (PreparedStatement) con.prepareStatement(query);
	        
	        // Configuración de los parámetros en el PreparedStatement
	        stmt.setString(1, registro.getDiagnostico());
	        stmt.setString(2, registro.getTratamiento());
	        stmt.setDate(3, java.sql.Date.valueOf(registro.getFechaRegistro())); // Convierte LocalDate a java.sql.Date
	        stmt.setInt(4, (int) registro.getId()); // Aquí debes asegurar que el turnoId esté configurado correctamente

	        // Imprime los valores que se están pasando
	        System.out.println("Diagnóstico: " + registro.getDiagnostico());
	        System.out.println("Tratamiento: " + registro.getTratamiento());
	        System.out.println("Fecha de Registro: " + registro.getFechaRegistro());
	        System.out.println("Turno ID: " + registro.getId());

	        // Ejecuta la actualización
	        int rowsAffected = stmt.executeUpdate();
	        
	        // Verifica si la actualización fue exitosa
	        if (rowsAffected > 0) {
	            System.out.println("Registro médico actualizado correctamente.");
	            return true;
	        } else {
	            System.out.println("No se actualizó ningún registro.");
	            return false;
	        }
	    } catch (SQLException e) {
	        // Manejo de excepciones
	        e.printStackTrace();
	        return false;
	    }
	}










	// Método para mostrar los turnos de un paciente específico
	public static LinkedList<Turno> MostrarTurnosPorPaciente(int pacienteId) {
		LinkedList<Turno> turnos = new LinkedList<>();
		try {

			PreparedStatement statement = (PreparedStatement) 
					con.prepareStatement("SELECT * FROM turno WHERE paciente_id = ?");
			statement.setInt(1, pacienteId);  
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {

				turnos.add(new Turno(
						resultSet.getLong("id"),
						resultSet.getInt("paciente_id"),
						resultSet.getInt("medico_id"),
						resultSet.getDate("fecha").toLocalDate(),
						resultSet.getTime("hora"),
						resultSet.getString("estado"),
						resultSet.getString("descripcion")
						));
			}
		} catch (Exception e) {
			System.out.println("No se pudieron obtener los turnos del paciente: " + e.getMessage());
		}
		return turnos;
	}


	public static void cancelarTurno(long id) {
		try {
			PreparedStatement statement = (PreparedStatement) 
					con.prepareStatement("UPDATE turno SET estado = 'Cancelado' WHERE id = ?");
			statement.setLong(1, id);

			int filas = statement.executeUpdate();
			if (filas > 0) {
				JOptionPane.showMessageDialog(null, "El turno ha sido cancelado.");
			}
		} catch (Exception e) {
			System.out.println("No se pudo cancelar el turno: " + e.getMessage());
		}
	}

	public static LinkedList<Turno> obtenerTurnosMedico(int medicoId) {
		LinkedList<Turno> turnos = new LinkedList<>();
	    try (PreparedStatement stmt = (PreparedStatement) con.prepareStatement("SELECT * FROM turno WHERE medico_id = ?")) {
	        stmt.setInt(1, medicoId);
	        try (ResultSet rs = stmt.executeQuery()) {
	            while (rs.next()) {
	                Turno turno = new Turno(
	                    rs.getLong("id"), 
	                    rs.getInt("paciente_id"), 
	                    rs.getInt("medico_id"), 
	                    rs.getDate("fecha").toLocalDate(),
	                    rs.getTime("hora"),
	                    rs.getString("estado"), 
	                    rs.getString("descripcion")
	                );
	                turnos.add(turno);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return turnos;
	}

	public static LinkedList<Turno> MostrarTurnosPorPaciente1(int medicoId) {
	    LinkedList<Turno> listaTurnos = new LinkedList<>();
	    try {
	        Connection conn = (Connection) conexion.getInstance().getConection();
	        
	        if (conn == null) {
	            System.out.println("La conexión es nula");
	            return listaTurnos;
	        }
	        String query = "SELECT * FROM turno WHERE medico_id = ?";
	        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
	        ps.setInt(1, medicoId);
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            long id = rs.getLong("id");
	            int pacienteId = rs.getInt("paciente_id");
	            LocalDate fecha = rs.getDate("fecha").toLocalDate();
	            Time hora = rs.getTime("hora");
	            String estado = rs.getString("estado");
	            String descripcion = rs.getString("descripcion");


	            Turno turno = new Turno(id, pacienteId, medicoId, fecha, hora, estado, descripcion);
	            listaTurnos.add(turno);
	        }
	        rs.close();
	        ps.close();
	    } catch (SQLException e) {
	        System.out.println("Error al mostrar turnos por médico: " + e.getMessage());
	    }

	    return listaTurnos;
	}






}