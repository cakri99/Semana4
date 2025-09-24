import java.awt.*;
import java.sql.*;
import javax.swing.*;

public class SelectorVehiculo extends JPanel {

    private javax.swing.JComboBox<String> comboMarca;
    private javax.swing.JComboBox<String> comboModelo;
    private final Connection conn;

    public SelectorVehiculo() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        // Iniciar conexión
        conn = ConexionDB.conectar();
        if (conn == null) {
            JOptionPane.showMessageDialog(this, "No se pudo conectar a la base de datos para cargar marcas y modelos.");
            return;
        }

        comboMarca = new javax.swing.JComboBox<>();
        comboModelo = new javax.swing.JComboBox<>();
        
        add(new JLabel("Marca:"));
        add(comboMarca);
        add(new JLabel("Modelo:"));
        add(comboModelo);

        cargarMarcas();

        comboMarca.addActionListener(e -> actualizarModelos((String) comboMarca.getSelectedItem()));
    }
    
    private void cargarMarcas() {
        comboMarca.removeAllItems();
        String sql = "SELECT DISTINCT marca FROM vehiculos ORDER BY marca";

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                comboMarca.addItem(rs.getString("marca"));
            }

            if (comboMarca.getItemCount() == 0) {
                comboMarca.addItem("No hay marcas disponibles");
            }

        } catch (SQLException e) {
            System.out.println("Error al cargar marcas: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "Error al cargar marcas desde la base de datos.");
        }
    }

    private void actualizarModelos(String marcaSeleccionada) {
        if (marcaSeleccionada == null || marcaSeleccionada.isEmpty() || marcaSeleccionada.equals("No hay marcas disponibles")) {
            comboModelo.removeAllItems();
            comboModelo.addItem("Selecciona una marca primero");
            return;
        }

        comboModelo.removeAllItems();
        String sql = "SELECT DISTINCT modelo FROM vehiculos WHERE marca = ? ORDER BY modelo";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, marcaSeleccionada);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                comboModelo.addItem(rs.getString("modelo"));
            }

            if (comboModelo.getItemCount() == 0) {
                comboModelo.addItem("No hay modelos para esta marca");
            }

        } catch (SQLException e) {
            System.out.println("Error al cargar modelos: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "Error al cargar modelos desde la base de datos.");
        }
    }
}