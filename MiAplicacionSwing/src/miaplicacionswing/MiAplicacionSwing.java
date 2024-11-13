/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package miaplicacionswing;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author PROPIETARIO
 */
public class MiAplicacionSwing extends JFrame {

    private JTextField nombreTextField;
    private JButton agregarButton, eliminarButton;
    private DefaultTableModel tableModel;
    private JTable table;
    private JLabel avisoLabel;

    public MiAplicacionSwing() {
        // Configuración de la ventana principal
        setTitle("Aplicación de Ejemplo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLayout(new BorderLayout());

        // Panel de entrada para nombre
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(new JLabel("Nombre:"));
        nombreTextField = new JTextField(10);
        inputPanel.add(nombreTextField);

        agregarButton = new JButton("Agregar");
        inputPanel.add(agregarButton);

        // Aviso de límite de elementos
        avisoLabel = new JLabel("Solo se admiten 30 elementos");
        avisoLabel.setForeground(Color.RED);
        avisoLabel.setVisible(false);
        inputPanel.add(avisoLabel);

        add(inputPanel, BorderLayout.NORTH);

        // Tabla para mostrar los nombres con tres columnas
        tableModel = new DefaultTableModel(new Object[]{"Columna 1", "Columna 2", "Columna 3"}, 10);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Botón de eliminar
        eliminarButton = new JButton("Eliminar");
        add(eliminarButton, BorderLayout.SOUTH);

        // Acción de botón Agregar
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarNombre();
            }
        });

        // Acción de botón Eliminar
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarNombre();
            }
        });
    }

    private void agregarNombre() {
        String nombre = nombreTextField.getText().trim();
        if (!nombre.isEmpty()) {
            int added = addNameToTable(nombre);
            if (added == 0) {
                nombreTextField.setText("");
                avisoLabel.setVisible(false);
            } else if (added == -1) {
                avisoLabel.setVisible(true);
            }
        }
    }

    private int addNameToTable(String nombre) {
        for (int col = 0; col < 3; col++) {
            int rowCount = 0;
            for (int row = 0; row < tableModel.getRowCount(); row++) {
                if (tableModel.getValueAt(row, col) != null) {
                    rowCount++;
                } else {
                    // Agregar el nombre en la primera celda vacía de la columna
                    tableModel.setValueAt(nombre, row, col);
                    return 0;  // Nombre agregado con éxito
                }
            }
            // Si la columna tiene 10 nombres, pasa a la siguiente
            if (rowCount < 10) {
                break;
            }
        }
        // Si todas las columnas tienen el máximo de 10 nombres, muestra aviso
        return -1;
    }

    private void eliminarNombre() {
        int selectedRow = table.getSelectedRow();
        int selectedCol = table.getSelectedColumn();
        if (selectedRow != -1 && selectedCol != -1 && table.getValueAt(selectedRow, selectedCol) != null) {
            // Elimina el valor en la celda seleccionada
            tableModel.setValueAt(null, selectedRow, selectedCol);
            avisoLabel.setVisible(false);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MiAplicacionSwing frame = new MiAplicacionSwing();
            frame.setVisible(true);
        });
    }
}