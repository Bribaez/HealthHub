package BLL;

import java.util.LinkedList;

public class Admin {
	private String nombre;
	private String rol;
	private String password;
	private int id;
	private static ControllerAdmin controlador = new ControllerAdmin();
	
	public Admin(String nombre, String rol, String password, int id) {
		super();
		this.nombre = nombre;
		this.rol = rol;
		this.password = password;
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Admin [nombre=" + nombre + ", rol=" + rol + ", password=" + password + ", id=" + id + "]";
	}
	
/*public static Persona Login(String nombre , String password) {
		LinkedList<Persona> usuarios =  controlador.MostrarUsuarios();
		for (Persona persona : usuarios) {
			if (persona.getNombre().equals(nombre) && persona.getPassword().equals(password)) {
				
				return persona;
			} 
		}
		return null;
		
	}
	public static String Registrarse(String nombre , String password) {
		
		if (nombre.isEmpty()) {
			return "No puede tener un nombre vacio";
		} else if(password.isEmpty()){
			return "No puede tener un contraseña vacia";

		}
		
		LinkedList<Persona> usuarios =  controlador.MostrarUsuarios();
		for (Persona persona : usuarios) {
			if (persona.getNombre().equals(nombre) ) {
				
				return "No puede crear un usuario con este nombre, ya existe";
			} 
		}
		controlador.agregarUsuario(new Persona(nombre, "empleado", password));
		
		return "Usuario creado";
	}	*/
	
	
	
}
