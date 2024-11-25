import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ventana {
    private JPanel principal;
    private JTabbedPane tabbedPane1;
    private JTextField txtId;
    private JTextField txtNombre;
    private JComboBox cboClase;
    private JTextField txtNivel;
    private JTextField txtPoder;
    private JComboBox cboRareza;
    private JButton btnRegistrar;
    private JTextArea txtPersonajes;
    private JTextField txtBuscarPorNombre;
    private JButton buscarPorNombreButton;
    private JTextField txtBuscarPorID;
    private JButton buscarPorIdButton;
    private JTextArea txtMostrar;
    private JComboBox cboOrden;
    private JButton btnOrdenar;
    private JComboBox cboFiltrarclase;
    private JButton btnFiltrarClase;
    private Juego juego = new Juego();

    public Ventana() {
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(txtId.getText());
                    String nombre = txtNombre.getText();
                    String clase = cboClase.getSelectedItem().toString();
                    int nivel = Integer.parseInt(txtNivel.getText());
                    int poder = Integer.parseInt(txtPoder.getText());
                    String rareza = cboRareza.getSelectedItem().toString();

                    juego.agregarPersonaje(new Personaje(id, nombre, clase, nivel, poder, rareza));
                    JOptionPane.showMessageDialog(null, "Personaje agregado exitosamente.");
                    txtPersonajes.setText(juego.toString());
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, exception.getMessage());
                }
            }
        });

        buscarPorNombreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = txtBuscarPorNombre.getText();
                StringBuilder resultados = new StringBuilder();

                for (Personaje x : juego.listado) {
                    if (x.getNombre().equalsIgnoreCase(nombre)) {
                        resultados.append(x.toString()).append("\n");
                    }
                }

                if (resultados.length() > 0) {
                    txtMostrar.setText(resultados.toString());
                } else {
                    txtMostrar.setText("No se encontró ningún personaje con el nombre: " + nombre);
                }
            }
        });

        buscarPorIdButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(txtBuscarPorID.getText());
                    Personaje personaje = juego.buscarPersonajePorID(id);
                    if (personaje != null) {
                        txtMostrar.setText(personaje.toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontró un personaje con el ID: " + id);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "El ID debe ser un número entero.");
                }
            }
        });

        btnOrdenar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ordenSeleccionado = cboOrden.getSelectedItem().toString();
                boolean ascendente = ordenSeleccionado.equalsIgnoreCase("Ascendente");
                juego.ordenarPorNivel(ascendente);
                txtPersonajes.setText(juego.toString());
                JOptionPane.showMessageDialog(null, "Lista ordenada en orden " + ordenSeleccionado + ".");
            }
        });

        btnFiltrarClase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String claseSeleccionada = cboFiltrarclase.getSelectedItem().toString();
                var personajesFiltrados = juego.filtrarPorClase(claseSeleccionada);

                if (!personajesFiltrados.isEmpty()) {
                    StringBuilder resultados = new StringBuilder();
                    for (Personaje personaje : personajesFiltrados) {
                        resultados.append(personaje.toString()).append("\n");
                    }
                    txtMostrar.setText(resultados.toString());
                } else {
                    txtMostrar.setText("No se encontraron personajes de la clase: " + claseSeleccionada);
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Gestión de Personajes");
        frame.setContentPane(new Ventana().principal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
