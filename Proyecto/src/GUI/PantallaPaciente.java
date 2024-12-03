package GUI;

import javax.swing.*;
import BLL.Turno;
import BLL.Usuario;
import DLL.ControllerTurno;
import DLL.ControllerUsuario;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;
import java.util.Properties;

public class PantallaPaciente extends JFrame {
    
    private JTextField horaTextField;
    private JButton crearTurnoButton;
    private Usuario medicoSeleccionado; 
    private JSpinner fechaSpinner;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PantallaPaciente frame = new PantallaPaciente();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public PantallaPaciente() {
        setTitle("Crear Turno");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel fechaLabel = new JLabel("Seleccione la fecha del turno:");
        fechaLabel.setBounds(100, 11, 200, 30);
        getContentPane().add(fechaLabel);


        fechaSpinner = new JSpinner(new SpinnerDateModel());
        fechaSpinner.setBounds(81, 50, 200, 30);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(fechaSpinner, "yyyy-MM-dd");
        fechaSpinner.setEditor(dateEditor);
        getContentPane().add(fechaSpinner);


        JLabel horaLabel = new JLabel("Hora del turno (HH:MM):");
        horaLabel.setBounds(119, 90, 150, 30);
        horaTextField = new JTextField();
        horaTextField.setBounds(81, 130, 200, 30);
        getContentPane().add(horaLabel);
        getContentPane().add(horaTextField);

  
        crearTurnoButton = new JButton("Crear Turno");
        crearTurnoButton.setBounds(81, 190, 200, 30);
        getContentPane().add(crearTurnoButton);


        crearTurnoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearTurno();
            }
        });

     
        medicoSeleccionado = obtenerMedicoSeleccionado(); 
        if (medicoSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "No hay médicos disponibles para asignar.");
        }
    }

    private void crearTurno() {

        Date selectedDate = (Date) fechaSpinner.getValue();
        LocalDate fecha = new java.sql.Date(selectedDate.getTime()).toLocalDate();


        String horaString = horaTextField.getText();
        Time hora = null;
        try {
            if (horaString.length() == 5 && horaString.charAt(2) == ':') {
                hora = Time.valueOf(horaString + ":00");
            } else {
                throw new IllegalArgumentException("Hora en formato incorrecto");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Hora inválida. Por favor, ingrese en formato HH:MM.");
            return;
        }

        if (medicoSeleccionado != null) {
            Turno nuevoTurno = new Turno(
                1L, 
                123, 
                medicoSeleccionado.getId(),
                fecha,
                hora,
                "pendiente",
                "Consulta general"
            );

            long exito = ControllerTurno.agregarTurno(nuevoTurno);
            if (exito > 0) {
                JOptionPane.showMessageDialog(this, "Turno creado exitosamente con ID: " + exito);
                fechaSpinner.setValue(new Date()); 
                horaTextField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo crear el turno.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo asignar un médico.");
        }
    }

    private Usuario obtenerMedicoSeleccionado() {
        LinkedList<Usuario> medicos = ControllerUsuario.obtenerMedicos();
        if (!medicos.isEmpty()) {
            return medicos.get(0); 
        }
        return null;
    }
}
