package DLL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;

import BLL.Cuenta;
import BLL.Usuario;

public class ControllerCuenta {
    
    private static Connection con = conexion.getInstance().getConection();
    public static long agregarCuenta(Cuenta cuenta) {
        try {
            PreparedStatement statement = (PreparedStatement) 
                con.prepareStatement("INSERT INTO `cuenta`(`mail`, `id_usuario`) VALUES (?, ?)", 
                PreparedStatement.RETURN_GENERATED_KEYS);
            
            statement.setString(1, cuenta.getMail());
            statement.setLong(2, cuenta.getUsuario().getId());
            
            System.out.println("Ejecutando consulta: mail = " + cuenta.getMail() + ", id_usuario = " + cuenta.getUsuario().getId());

            int filas = statement.executeUpdate();
            if (filas > 0) {
                try (ResultSet id = statement.getGeneratedKeys()) {
                    if (id.next()) {
                        JOptionPane.showMessageDialog(null, "Se agregó, id " + id.getLong(1));
                        return id.getLong(1);
                    }
                }
            } else {
                System.out.println("No se agregaron filas.");
            }
            
        } catch (SQLException e) {
            e.printStackTrace(); // Imprime la excepción SQL para entender el error
            JOptionPane.showMessageDialog(null, "Error al agregar cuenta: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // Imprime la excepción genérica
            System.out.println("No se agregó la cuenta.");
        }
        return 0;
    }

    
    public static Cuenta BuscarCuenta(int id_cuenta) {
        Cuenta nuevo = null;
        String sql = "SELECT * FROM `cuenta` WHERE id = ?";
        
        try (PreparedStatement statement = (PreparedStatement) con.prepareStatement(sql)) {
            statement.setInt(1, id_cuenta); // Asegúrate que el tipo coincida con la DB
            
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    nuevo = new Cuenta(
                        resultSet.getInt("id"), 
                        resultSet.getString("mail"),
                        new Usuario(resultSet.getInt("id_usuario")),
                        resultSet.getDate("fecha").toLocalDate() // Asegúrate de que la columna exista
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Imprime el error para entender qué pasó
            JOptionPane.showMessageDialog(null, "Error al buscar la cuenta");
        }
        
        return nuevo;
    }
}
