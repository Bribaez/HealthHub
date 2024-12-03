package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import BLL.Turno;
import DLL.ControllerTurno;
import java.awt.*;
import java.util.LinkedList;

public class PantallaMedico extends JFrame {

    private int medicoId;
    private JTable tablaTurnos;
    private DefaultTableModel modeloTabla;

    public PantallaMedico(int medicoId) {
        this.medicoId = medicoId;
        inicializarUI();
        cargarTurnos();
    }

    private void inicializarUI() {
        setTitle("Turnos del Médico");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Paciente ID");
        modeloTabla.addColumn("Fecha");
        modeloTabla.addColumn("Hora");
        modeloTabla.addColumn("Estado");
        modeloTabla.addColumn("Descripción");

        tablaTurnos = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaTurnos);
        panel.add(scrollPane, BorderLayout.CENTER);

     
        JButton btnCancelarTurno = new JButton("Cancelar Turno");
        btnCancelarTurno.addActionListener(e -> cancelarTurno());
        panel.add(btnCancelarTurno, BorderLayout.SOUTH);

        add(panel);
    }

    private void cargarTurnos() {
        LinkedList<Turno> turnos = ControllerTurno.obtenerTurnosMedico(medicoId);

        modeloTabla.setRowCount(0);  
        for (Turno turno : turnos) {
            Object[] row = {
                turno.getId(),
                turno.getPacienteId(),
                turno.getFecha(),
                turno.getHora(),
                turno.getEstado(),
                turno.getDescripcion()
            };
            modeloTabla.addRow(row);
        }
    }

    private void cancelarTurno() {
        int selectedRow = tablaTurnos.getSelectedRow();

        if (selectedRow != -1) {
       
            long turnoId = (long) modeloTabla.getValueAt(selectedRow, 0);
            
           
            ControllerTurno.cancelarTurno(turnoId);

           
            cargarTurnos();
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un turno para cancelar.");
        }
    }

    public static void main(String[] args) {
        int medicoId = 1;  
        PantallaMedico pantalla = new PantallaMedico(medicoId);
        pantalla.setVisible(true);
    }
}
