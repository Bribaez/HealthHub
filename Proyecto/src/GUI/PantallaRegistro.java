package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import BLL.Cuenta;
import BLL.Usuario;

public class PantallaRegistro extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField inpNombre;
    private JTextField inpCorreo;
    private JPasswordField pswContrania;
    private JLabel lblError;


    public PantallaRegistro() {
        setTitle("Registro de Usuario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("Registro de Usuario");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblTitulo.setBounds(140, 20, 200, 30);
        contentPane.add(lblTitulo);

        // Nombre de usuario
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(30, 70, 100, 20);
        contentPane.add(lblNombre);

        inpNombre = new JTextField();
        inpNombre.setBounds(140, 70, 200, 20);
        contentPane.add(inpNombre);
        inpNombre.setColumns(10);

        // Correo electrónico
        JLabel lblCorreo = new JLabel("Correo:");
        lblCorreo.setBounds(30, 110, 100, 20);
        contentPane.add(lblCorreo);

        inpCorreo = new JTextField();
        inpCorreo.setBounds(140, 110, 200, 20);
        contentPane.add(inpCorreo);
        inpCorreo.setColumns(10);

        // Contraseña
        JLabel lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setBounds(30, 150, 100, 20);
        contentPane.add(lblContrasena);

        pswContrania = new JPasswordField();
        pswContrania.setBounds(140, 150, 200, 20);
        contentPane.add(pswContrania);

        // Mensaje de error
        lblError = new JLabel("");
        lblError.setForeground(Color.RED);
        lblError.setBounds(140, 180, 200, 20);
        contentPane.add(lblError);

        // Botón de registro
        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(140, 210, 100, 30);
        contentPane.add(btnRegistrar);

        // Acción del botón de registro
        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = inpNombre.getText().trim();
                String correo = inpCorreo.getText().trim();
                String contrasena = new String(pswContrania.getPassword()).trim();

                if (nombre.isEmpty() || correo.isEmpty() || contrasena.isEmpty()) {
                    lblError.setText("Todos los campos son requeridos.");
                } else {
                    // Se registra al usuario
                    Cuenta nuevaCuenta = Usuario.Register(nombre, contrasena, correo);
                    if (nuevaCuenta != null) {
                        JOptionPane.showMessageDialog(null, "Registro exitoso: " + nuevaCuenta);
                        // Redirigir a otra pantalla si lo deseas
                        // Ejemplo: new PantallaLogin().setVisible(true);
                        dispose(); // Cerrar la ventana de registro
                    } else {
                        lblError.setText("Error al registrar el usuario.");
                    }
                }
            }
        });
    }
}
