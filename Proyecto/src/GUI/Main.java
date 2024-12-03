package GUI;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import BLL.Admin;
import BLL.Cuenta;
import BLL.Medico;
import BLL.Paciente;
import BLL.Usuario;


public class Main {

	public static void main(String[] args) {

		String[] opcionUsuario = {"Iniciar sesión",
				"Registrarse",
		"Salir"};
		int opcionMenu =0 ;
		do {

			opcionMenu= JOptionPane.showOptionDialog(null, "Que desea realizar hoy", "Bienvenido", 0, JOptionPane.DEFAULT_OPTION, new ImageIcon(Main.class.getResource("/img/healthhub.jpg")), opcionUsuario, opcionUsuario[0]);
		

			switch (opcionMenu) {
			case 0:
				String nombre="";
				while (nombre.isEmpty()) {
					nombre = JOptionPane.showInputDialog("Ingrese nombre");
					if (nombre.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Incorrecto");
					}
				}
				String contra="";
				while (contra.isEmpty()) {
					contra = JOptionPane.showInputDialog("Ingrese contraseña");
					if (contra.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Incorrecto");
					}
				}

				Usuario encontrado = Usuario.Login(nombre, contra) ;

				if(encontrado!=null) {
					JOptionPane.showMessageDialog(null, encontrado);
					if (encontrado.getRol().equals("admin")) {
						
						Admin nuevo = new Admin(encontrado.getNombre(),encontrado.getPassword(),encontrado.getRol());
						nuevo.menuPrincipal();
						
					} else if (encontrado.getRol().equals("paciente")) {

						Paciente nuevo = new Paciente(encontrado.getId(),encontrado.getNombre(),encontrado.getPassword(),encontrado.getRol());
						nuevo.menuPrincipal();

					} else if (encontrado.getRol().equals("medico")) {

						Medico nuevo = new Medico(encontrado.getId(),encontrado.getNombre(),encontrado.getPassword(),encontrado.getRol());
						nuevo.menuPrincipal();	
					}


				}else {
					JOptionPane.showMessageDialog(null, "No existe");

				}
				break;
			case 1: 
				//Registrarse 
				nombre = "";
                while (nombre.isEmpty()) {
                    nombre = JOptionPane.showInputDialog("Ingrese nombre");
                    if (nombre.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Incorrecto");
                    }
                }
                contra = "";
                while (contra.isEmpty()) {
                	contra = JOptionPane.showInputDialog("Ingrese contraseña");
                    if (contra.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Incorrecto");
                    }
                }

                String mail = "";
                while (mail.isEmpty()) {
                    mail = JOptionPane.showInputDialog("Ingrese correo");
                    if (mail.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Incorrecto");
                    }
                }
                Cuenta nueva2 = Usuario.Register(nombre, contra, mail);
                JOptionPane.showMessageDialog(null, nueva2);
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

