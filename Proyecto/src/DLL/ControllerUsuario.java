package DLL;
import java.sql.ResultSet;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;


import BLL.Usuario;


public class ControllerUsuario {
	private static Connection con = (Connection) conexion.getInstance().getConection();

	public static long agregarUsuario(Usuario usuario) {
		try {

			PreparedStatement statement = (PreparedStatement) 
					con.prepareStatement(
							"INSERT INTO `usuario`(`nombre`, `rol`, `password`) VALUES (?,?,?)" 
							,PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setString(1, usuario.getNombre());
			statement.setString(2, usuario.getRol());
			statement.setString(3, usuario.getPassword());
			int filas = statement.executeUpdate();
			if(filas>0) {
				try (ResultSet id = statement.getGeneratedKeys()) {
					if (id.next()) {
						JOptionPane.showMessageDialog(null, "Se agregó usuario id " + id.getLong(1));
						return id.getLong(1);
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Error");
				}
			}				



	} catch (Exception e) {
		System.out.println("No se agregó");		}
	return 0;


}

public static LinkedList<Usuario> MostrarUsuarios() {
	LinkedList<Usuario> usuarios = new  LinkedList<Usuario>();
	try {

		PreparedStatement statement = (PreparedStatement) 
				con.prepareStatement("SELECT * FROM `usuario`");
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {

			usuarios.add(new Usuario(resultSet.getString("nombre"), resultSet.
					getString("rol"),resultSet.getString("password"),
					resultSet.getInt("id")));
		}

	} catch (Exception e) {
		System.out.println("No se agregó");		}


	return usuarios;
}

public static Usuario BuscarUsuario(int id) {
	Usuario nuevo = null;
	try {

		PreparedStatement statement = (PreparedStatement) 
				con.prepareStatement("SELECT * FROM `usuario` WHERE id= ? ");
		statement.setInt(1, id);
		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			nuevo = new Usuario(
					resultSet.getString("nombre"), 
					resultSet.getString("rol")
					,resultSet.getString("password"),
					resultSet.getInt("id") );
		}

	} catch (Exception e) {
		System.out.println("No se agregó");		
	}


	return nuevo;
}

public static void EliminarUsuario(int id) {
	try {

		PreparedStatement statement = (PreparedStatement) 
				con.prepareStatement("DELETE FROM `usuario` WHERE id= ? ");
		statement.setInt(1, id);
		int fila = statement.executeUpdate();
		if (fila>0) {
			JOptionPane.showMessageDialog(null, "Se borró");
		}

	} catch (Exception e) {
		System.out.println("No se borró");		
	}


}

public static  void ActualizarUsuario(Usuario usuario) {

	try {

		PreparedStatement statement = (PreparedStatement) 
				con.prepareStatement("UPDATE `usuario` SET `nombre`=?,`rol`=?,`password`=? WHERE id = ?");
		statement.setString(1, usuario.getNombre());
		statement.setString(2, usuario.getRol());
		statement.setString(3, usuario.getPassword());
		statement.setInt(4, usuario.getId());

		int fila = statement.executeUpdate();
		if (fila>0) {
			JOptionPane.showMessageDialog(null, "Se actualizó");
		}

	} catch (Exception e) {
		System.out.println("No se borró");		
	}


}
public static LinkedList<Usuario> obtenerMedicos() {
    LinkedList<Usuario> medicos = new LinkedList<>();
    try {
        PreparedStatement statement = (PreparedStatement) con.prepareStatement("SELECT * FROM `usuario` WHERE rol = 'medico'");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            medicos.add(new Usuario(
                    resultSet.getString("nombre"),
                    resultSet.getString("rol"),
                    resultSet.getString("password"),
                    resultSet.getInt("id")
            ));
        }
    } catch (Exception e) {
        System.out.println("Error al obtener los médicos");
    }
    return medicos;
}




}
