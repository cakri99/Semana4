import javax.swing.*;

public class Main extends JFrame {

    public Main() {
        //Ventana de inicio
        setTitle("Consultor de vehiculos");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        
        JTabbedPane pestañas = new JTabbedPane();

        JPanel panel1 = new Tabla();
        JPanel panel2 = new SelectorVehiculo();

        pestañas.addTab("Visor BD", panel1);
        pestañas.addTab("Selector Marca/Modelo", panel2);
        
        add(pestañas);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }
}