
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
				String mail = JOptionPane.showInputDialog("Ingrese mail");
				String cont = JOptionPane.showInputDialog("Ingrese contraseña");

				Usuario encontrado = Usuario.Login( mail, cont) ;

				if(encontrado!=null) {
					JOptionPane.showMessageDialog(null, encontrado);
					if (encontrado.getRol().equals("admin")) {
						Admin nuevo = new Admin(encontrado.getMail(),encontrado.getContrasena(),encontrado.getRol());
						nuevo.menuPrincipal();
					} else if (encontrado.getRol().equals("paciente")) {

						Paciente nuevo = new Paciente(encontrado.getMail(),encontrado.getContrasena(),encontrado.getRol());
						nuevo.menuPrincipal();

					} else if (encontrado.getRol().equals("medico")) {

						Medico nuevo = new Medico(encontrado.getMail(),encontrado.getContrasena(),encontrado.getRol());
						nuevo.menuPrincipal();	
					}




				}else {
					JOptionPane.showMessageDialog(null, "No existe");

				}
				break;
			case 1: 
				//Registrarse 
				mail = JOptionPane.showInputDialog("Ingrese mail");
				cont = JOptionPane.showInputDialog("Ingrese contraseña");

				ListaUsuarios.getInstance().add(new Usuario(mail,cont));
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
