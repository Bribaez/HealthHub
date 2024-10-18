package GUI;
import java.util.LinkedList;

import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {


		String[] opcionUsuario = {"Iniciar sesión",
				"Registrarse",
		"Salir"};
		int opcionMenu =0 ;
		do {

			opcionMenu= JOptionPane.showOptionDialog(null, "Que desea realizar hoy", null, 0, 0, null, opcionUsuario, opcionUsuario[0]);
			switch (opcionMenu) {
			case 0:
				String nombre="";
				while (nombre.isEmpty()) {
					nombre = JOptionPane.showInputDialog("Ingrese nombre");
					if (nombre.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Incorrecto");
					}
				}
				String contrara="";
				while (contrara.isEmpty()) {
					contrara = JOptionPane.showInputDialog("Ingrese contrareña");
					if (contrara.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Incorrecto");
					}
				}

				Usuario encontrado = Usuario.Login(nombre, contra) ;

				if(encontrado!=null) {
					JOptionPane.showMessageDialog(null, encontrado);
					if (encontrado.getRol() == "Admin") {
						JOptionPane.showMessageDialog(null, "Yendo al menú admin");
					} else if (encontrado.getRol() == "Paciente") {

						Paciente nuevo = new Paciente(encontrado.getnombre(),encontrado.getcontrarasena(),encontrado.getRol());
						nuevo.menuPrincipal();

					} else if (encontrado.getRol().equals("medico")) {

						Medico nuevo = new Medico(encontrado.getnombre(),encontrado.getcontrarasena(),encontrado.getRol());
						nuevo.menuPrincipal();	
					}




				}else {
					JOptionPane.showMessageDialog(null, "No existe");

				}
				break;
			case 1: 
				//Registrarse 
				nombre = JOptionPane.showInputDialog("Ingrese nombre");
				contra = JOptionPane.showInputDialog("Ingrese contraraseña");

				ListaUsuarios.getInstance().add(new Usuario(nombre,contra));
				break;
			case 2: 
				JOptionPane.showMessageDialog(null, "Salir");
				break;
			default:
				break;
			}





		} while (opcionMenu!=2);

	}
}

