package repository;

import java.util.LinkedList;

import BLL.Usuario;

public interface UsuarioRepository {
	void agregarUsuario(Usuario usuario);
	LinkedList<Usuario> MostrarUsuarios();
	Usuario BuscarUsuario(int id);
	void EliminarUsuario(int id);
	void ActualizarUsuario(Usuario usuario);
	LinkedList<Usuario> obtenerMedicos();

}
