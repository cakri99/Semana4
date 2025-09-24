import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Tabla extends JPanel {

    private final JTable tablaDatos;
    private final DefaultTableModel modeloTabla;
    private JTextField txtBusqueda;
    private final JButton btnBuscar;

    public Tabla() {
        // Se configura el panel
        setLayout(new BorderLayout());

        // Seccion para realizar busqueda
        JPanel panelSuperior = new JPanel(new FlowLayout());
        panelSuperior.add(new JLabel("Buscar por marca:"));
        txtBusqueda = new JTextField(20);
        btnBuscar = new JButton("Buscar");
        panelSuperior.add(txtBusqueda);
        panelSuperior.add(btnBuscar);
        add(panelSuperior, BorderLayout.NORTH);

        //Creacion de la tabla
        modeloTabla = new DefaultTableModel();
        tablaDatos = new JTable(modeloTabla);
        add(new JScrollPane(tablaDatos), BorderLayout.CENTER);

        btnBuscar.addActionListener(e -> cargarDatos(txtBusqueda.getText().trim()));
        
        cargarDatos(null);
    }

    private void cargarDatos(String filtroMarca) {
        modeloTabla.setRowCount(0);
    modeloTabla.setColumnCount(0);

    String sql = "SELECT id, marca, modelo, year FROM vehiculos";
    if (filtroMarca != null && !filtroMarca.isEmpty()) {
        sql += " WHERE marca LIKE ?";
    }

    try (Connection conn = ConexionDB.conectar();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        if (filtroMarca != null && !filtroMarca.isEmpty()) {
            pstmt.setString(1, "%" + filtroMarca + "%");
        }

        ResultSet rs = pstmt.executeQuery();
        ResultSetMetaData metaData = rs.getMetaData();
        int numeroColumnas = metaData.getColumnCount();
        
        for (int i = 1; i <= numeroColumnas; i++) {
            modeloTabla.addColumn(metaData.getColumnLabel(i));
        }

        while (rs.next()) {
            Object[] fila = new Object[numeroColumnas];
            for (int i = 0; i < numeroColumnas; i++) {
                fila[i] = rs.getObject(i + 1);
            }
            modeloTabla.addRow(fila);
        }

        } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al acceder a la base de datos: " + e.getMessage());
        }
    }
}