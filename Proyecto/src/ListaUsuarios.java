

import java.util.LinkedList;

public class ListaUsuarios {
	//Atributos
	private static 	LinkedList<Usuario> usuarios;
	//Constructor
	private ListaUsuarios() {
		
	}
	//
	public static LinkedList<Usuario> getInstance(){
		if (usuarios==null) {
			usuarios = new LinkedList<Usuario>();
			rellenarLista();
			
		} else {
			System.out.println("Ya existe");
		}
		
		return usuarios;
	}
	public static void guardar(Usuario usuario) {
		usuarios.add(usuario);
	}
	
	public static void rellenarLista() {
		usuarios.add(new  Usuario("Flor","4321","paciente"));
		usuarios.add(new  Usuario("Andres","4321","admin"));
		usuarios.add(new  Usuario("Angel","4321","paciente"));
		usuarios.add(new  Usuario("Ivan","4321","medico"));
	}
}
