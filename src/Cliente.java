import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Cliente extends JFrame{
    private JTable table1;
    private JPanel panel1;
    private JTable table2;
    private JComboBox ComboHabitacion;
    private JTextField MostrarEleccion;
    private JLabel ImagenHabitacion;
    private JButton buscarButton;
    private JButton regresarButton;

    //Variable para mi tabla
    public DefaultTableModel tableModel;
    public DefaultTableModel tableModel3;

    public  Cliente(){
        setContentPane(panel1);
        /**
         * Esto es para la parte de habitaciones
         */
        // Inicializar el modelo de la tabla
        String[] columnNamesH = {
                "ID Habitacion",         // 0
                "Tipo",
                "Descripcion"
        };
        tableModel3 = new DefaultTableModel(columnNamesH, 0);
        table1.setModel(tableModel3);


        // Agregar los nombres de los campos como la primera fila
        Object[] headerRowH = {
                "ID Habitacion", "Tipo","Descripcion"
        };
        tableModel3.addRow(headerRowH);

        // Ajustar el ancho de las columnas
        table1.getColumnModel().getColumn(0).setPreferredWidth(150);
        table1.getColumnModel().getColumn(1).setPreferredWidth(400);
        table1.getColumnModel().getColumn(2).setPreferredWidth(600);

        // Cargar datos existentes
        cargarDatosExistentesHabitaciones2();

        //Combobox de elejir a mi habitacion
        ComboHabitacion.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String valor=ComboHabitacion.getSelectedItem().toString();
                MostrarEleccion.setText(valor);
            }
        });

        //Metodo para buscar habitacion
        buscarButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    BuscarHabitacion();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


        /*TODO ESTO SERA PARA QUE ME MUESTRE LA TABLA COMPLETA*/
        /**
         * Esto es para la parte de habitaciones
         */
        // Inicializar el modelo de la tabla
        String[] columnNamesH2 = {

                "Tipo",                  // 1
                "Descripcion",           // 2
                "Precio",                // 3
                "Estado",                // 4
                "Numero de Camas",       // 5
                "Capacidad",             // 6
                "Wifi",                  // 7
                "TV",                    // 8
                "Aire Acondicionado",    // 9
                "MiniBar",               // 10
                "Vista",                 // 11

        };
        tableModel3 = new DefaultTableModel(columnNamesH2, 0);
        table2.setModel(tableModel3);


        // Agregar los nombres de los campos como la primera fila
        Object[] headerRowH2 = {
                "Tipo", "Descripcion", "Precio", "Estado",
                "Numero de Camas", "Capacidad", "Wifi", "TV", "Aire Acondicionado",
                "MiniBar", "Vista",
        };
        tableModel3.addRow(headerRowH2);

        // Cargar datos existentes
        cargarDatosExistentesHabitaciones3();


        regresarButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                PantallaInicio p1 = new PantallaInicio();
                p1.Iniciar();
                dispose();
            }
        });
    }



    // Metodo para cargar los datos anteriores a mi tabla
    private void cargarDatosExistentesHabitaciones2() {
        Conexion c = new Conexion();
        try (Connection conecta = c.conexion();
             PreparedStatement pstmt = conecta.prepareStatement("SELECT * FROM informacion_habitaciones");
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Object[] rowData2 = {
                        rs.getInt("id_habitacion"),
                        rs.getString("tipo"),
                        rs.getString("descripcion")

                };
                tableModel3.addRow(rowData2);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //Metodo para la segunda tabla

    // Metodo para cargar los datos anteriores a mi tabla
    private void cargarDatosExistentesHabitaciones3() {
        String VS3 = MostrarEleccion.getText();

        Conexion c = new Conexion();
        try (Connection conecta = c.conexion();
             PreparedStatement pstmt = conecta.prepareStatement("SELECT * FROM informacion_habitaciones WHERE id_habitacion = ?")) {

            pstmt.setString(1, VS3);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Object[] rowData2 = {

                            rs.getString("tipo"),
                            rs.getString("descripcion"),
                            rs.getDouble("precio"),
                            rs.getString("estado"),
                            rs.getInt("numero_camas"),
                            rs.getInt("capacidad"),
                            rs.getString("tiene_wifi"),
                            rs.getString("tiene_tv"),
                            rs.getString("tiene_aire_acondicionado"),
                            rs.getString("tiene_minibar"),
                            rs.getString("vista"),

                    };
                    tableModel3.addRow(rowData2);
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }





    //Metodo para bucar habitacion

    public void BuscarHabitacion() throws SQLException {
        String VS3 = MostrarEleccion.getText();

        // Establezco la conexión llamando al método de mi clase Conexion
        Conexion c = new Conexion();
        Connection conecta = c.conexion();

        // Creo mi consulta SQL
        String sql = "SELECT * FROM informacion_habitaciones WHERE id_habitacion = ?";

        // Creo mi PreparedStatement
        PreparedStatement pstmt = conecta.prepareStatement(sql);
        pstmt.setString(1, VS3);

        // Creo mi ResultSet
        ResultSet rs = pstmt.executeQuery();

        // Limpiar el modelo de la tabla antes de agregar la fila
        DefaultTableModel model = (DefaultTableModel) table2.getModel();
        model.setRowCount(1);

        if (rs.next()) {
            // Si encontramos un registro, lo mostramos en la tabla
            Object[] rowData2 = {

                    rs.getString("tipo"),
                    rs.getString("descripcion"),
                    rs.getDouble("precio"),
                    rs.getString("estado"),
                    rs.getInt("numero_camas"),
                    rs.getInt("capacidad"),
                    rs.getString("tiene_wifi"),
                    rs.getString("tiene_tv"),
                    rs.getString("tiene_aire_acondicionado"),
                    rs.getString("tiene_minibar"),
                    rs.getString("vista"),

            };
            tableModel3.addRow(rowData2);


            ImagenHabitacion.setText("");
            // Obtener la ruta de la imagen desde la base de datos
            String imagenRuta = rs.getString("imagen");
            if (imagenRuta != null && !imagenRuta.isEmpty()) {
                // Cargar la imagen en el JLabel
                ImageIcon imagenIcono = new ImageIcon(imagenRuta);
                Image imagen = imagenIcono.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH); // Escalar la imagen
                ImagenHabitacion.setIcon(new ImageIcon(imagen));
            } else {
                ImagenHabitacion.setIcon(null); // Si no hay imagen, quitar cualquier imagen previa
            }

        } else {
            // Si no se encuentra el registro
            DialogoPersonalizado.mostrarDialogo2();
        }

        // Cerrar conexiones
        conecta.close();
        pstmt.close();
    }


    //Falta lo ultimo paaa

    //Metodo para mostrar cuando esta pestaña sea llamada desde otro formulario
    public void Iniciar(){
        setVisible(true);
        setTitle("Rol Cliente");
        setSize(1200,200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}
