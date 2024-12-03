package BLL;


import java.util.LinkedList;

import javax.swing.JOptionPane;

import DLL.ControllerCuenta;
import DLL.ControllerUsuario;


public class Usuario {
	private String nombre;
	private String rol;
	private String password;
	private int id;

	public Usuario(String nombre, String rol, String password, int id) {
		super();
		this.nombre = nombre;
		this.rol = rol;
		this.password = password;
		this.id = id;
	}
	public Usuario(String nombre, String rol, String password) {
		super();
		this.nombre = nombre;
		this.rol = rol;
		this.password = password;
	}

	public Usuario(int id) {
		Usuario nuevo = ControllerUsuario.BuscarUsuario(id);
		this.id =id ;
		this.nombre = nuevo.getNombre();
		this.password = nuevo.getPassword();
		this.rol = nuevo.getRol();	
		JOptionPane.showMessageDialog(null, "Usuario que construi " + this);
	}

		
	public Usuario() {
		// TODO Auto-generated constructor stub
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
		return "Usuario [nombre=" + nombre + ", rol=" + rol + ", password=" + password + ", id=" + id + "]";
	}
	public static Usuario Login(String nombre , String password) {
		LinkedList<Usuario> usuarios = ControllerUsuario.MostrarUsuarios();
		for (Usuario usuario : usuarios) {
			if (usuario.getNombre().equals(nombre) && usuario.getPassword().equals(password)) {
				
				return usuario;
			} 
		}
		return null;
		
	}
	public static Cuenta Register(String nombre, String password, String mail) {
	    LinkedList<Usuario> usuarios = ControllerUsuario.MostrarUsuarios();
	    
	    boolean nombreValido = false;
	    while (!nombreValido) {
	        nombreValido = true; 
	        for (Usuario usuario : usuarios) {
	            if (usuario.getNombre().equals(nombre)) {
	                JOptionPane.showMessageDialog(null, "El nombre de usuario ya existe");
	                nombre = JOptionPane.showInputDialog("Ingrese un nuevo nombre");
	                nombreValido = false; 
	                break; 
	            }
	        }
	    }

	  
	    int id_usuario = (int) ControllerUsuario.agregarUsuario(new Usuario(nombre, "paciente", password));
	    
	    if (id_usuario == 0) {
	        JOptionPane.showMessageDialog(null, "Error al crear el usuario");
	        return null; 
	    }
	    
	    JOptionPane.showMessageDialog(null, "Se creó el usuario con id " + id_usuario);
	    
	    
	    int id_cuenta = (int) ControllerCuenta.agregarCuenta(new Cuenta(mail, new Usuario(id_usuario)));
	    
	    if (id_cuenta == 0) {
	        JOptionPane.showMessageDialog(null, "Error al crear la cuenta");
	        return null; 
	    }
	    
	    Cuenta nueva = ControllerCuenta.BuscarCuenta(id_cuenta);
	    
	    return nueva;
	}
	
	public static LinkedList<Usuario> MostrarUsuario(){
		ControllerUsuario controlador = new ControllerUsuario();
		return controlador.MostrarUsuarios();
	}
	
}
