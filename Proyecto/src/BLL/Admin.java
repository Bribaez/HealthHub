package BLL;

import java.util.LinkedList;

import javax.swing.JOptionPane;

import DLL.ControllerUsuario;



public class Admin extends Usuario implements Menu  {

	private static int incremental ;
	private int nro;
	public Admin(String mail, String contrasena, String rol) {
		super(mail, contrasena, rol);
		incremental++;
		this.nro = nro;
	}
	@Override
	public void menu() {
		JOptionPane.showMessageDialog(null, "Menu admin");

	}
	@Override
	public void menuPrincipal() {
		JOptionPane.showMessageDialog(null, "Menu admin desde interface");
		ControllerUsuario controlador = new ControllerUsuario();

		String[] menu = { 
				"Agregar usuario","Mostrar usuarios","Buscar usuario por id","Editar usuario","Eliminar","Salir"
		};
		int opcionMenu = 0;
		do {

			opcionMenu = JOptionPane.showOptionDialog(null, "Elija una opcion", null, 0, 0, null, menu, menu[0]);


			switch (opcionMenu) {
			case 0:
				//Agregar usuario
				LinkedList<Usuario> usuarios = ControllerUsuario.MostrarUsuarios();
				String nombre = JOptionPane.showInputDialog("Ingrese el nombre ");
				String rol = JOptionPane.showInputDialog("Ingrese el rol");
				String contra = JOptionPane.showInputDialog("Ingrese la contra ");
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

			  
			    int id_usuario = (int) ControllerUsuario.agregarUsuario(new Usuario(nombre, rol, contra));
			    
			    if (id_usuario == 0) {
			        JOptionPane.showMessageDialog(null, "Error al crear el usuario");
			        return; 
			    }
				break;
			case 1:
				//Mostrar usuarios
				JOptionPane.showMessageDialog(null, controlador.MostrarUsuarios());
				break;
			case 2:
				JOptionPane.showMessageDialog(null, Seleccionarusuario(controlador));
				break;
			case 3:
				Usuario nuevo = Seleccionarusuario(controlador);
				String nuevoNombre = JOptionPane.showInputDialog("Ingrese el nombre a cambiar por: " + nuevo.getNombre());
				String nuevoContra = JOptionPane.showInputDialog("Ingrese el email a cambiar por: " + nuevo.getPassword());
				nuevo.setPassword(nuevoContra);
				nuevo.setNombre(nuevoNombre);
				controlador.ActualizarUsuario(nuevo);
				break;
			case 4: 
				Usuario otro = Seleccionarusuario(controlador);
				controlador.EliminarUsuario(otro.getId());
				break;
			case 5: 
				JOptionPane.showMessageDialog(null, "Finalizar");
				break;
			default:
				break;
			}


		} while (opcionMenu!=5);

	}		
	public static Usuario Seleccionarusuario(ControllerUsuario controlador ) {
		String[] lista = new String[controlador.MostrarUsuarios().size()];
		
		for (int i = 0; i < lista.length; i++) {
			lista[i] = Integer.toString( controlador.MostrarUsuarios().get(i).getId());
		}
		String elegido = (String)JOptionPane.showInputDialog(null, "Elija un usuario", null, 0, null, lista, lista[0]);
		
		Usuario seleccionado =  controlador.BuscarUsuario(Integer.parseInt(elegido));
		 return seleccionado;
	}

}



