import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//Estas son de java pero se usaron para el pdf
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.stream.Stream;


//Importaciones para el pdf
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class RolRecepcionista extends JFrame {


    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JTabbedPane tabbedPane2;
    private JTextField IDclienteRecepcionistaC;
    private JTextField NombreRecepcionistaC;
    private JTextField ApellidoRecepcionistaC;
    private JTextField TelefonoRecepcionistaC;
    private JTextField EmailRecepcionistaC;
    private JTextField DireccionRecepcionistaC;
    private JTextField FechaRegistroRecepcionistaC;
    private JTextField CedulaRecepcionistaC;
    private JTextField IDRecepcionistaC;
    private JButton subirImagenDelClienteButton;
    private JButton relizarLaInserccionDelButton;
    private JTable table1;
    private JComboBox CoboBoxCliente;
    private JTextField MostrarComboBoxCliente;
    private JTextField NuevoValorCliente;
    private JSpinner SpinnerIDCliente;
    private JSpinner SpinnerEliminar;
    private JButton eliminarButton;
    private JButton actualizarButton;
    private JButton volverAlMenuRolesButton;
    private JButton refrescarTablaButton;
    private JButton buscarButton;
    private JTextField CedulaBuscarCliente;
    private JTable table2;
    private JLabel ImagenCliente;
    private JTabbedPane tabbedPane3;
    private JTextField IDReservaC;
    private JTextField FReservaReservaC;
    private JTextField FEntradaReservaC;
    private JTextField FSalidaReservaC;
    private JTextField NHuespedesReservaC;
    private JTextField IDHabitacionReservaC;
    private JTextField IDRecepcionistaReservaC;
    private JTextField IDClienteReservaC;
    private JButton realizarInserccionDelRegistroButton;
    private JSpinner NhuespedesReservaC;
    private JTable table3;
    private JTextField MostrarCampoReserva;
    private JComboBox comboBoxActualizarReserva;
    private JTextField NuevoValorReserva;
    private JTextField IDdelareservaCA;
    private JButton actualizarButton1;
    private JSpinner IDReservaEliminar;
    private JButton eliminarButton1;
    private JTextField BuscarFechaReserva;
    private JButton buscarButton1;
    private JButton refresarTablaButton;
    private JButton relizarPdfDeLaButton;

    //Variable para mi tabla
    public DefaultTableModel tableModel3;
    public DefaultTableModel tableModel;

    private File selectedImageFile2;

    public RolRecepcionista(){
        setContentPane(panel1);

        /**
         * Esto es para la parte de la tabla para los clientes
         */
        // Inicializar el modelo de la tabla
        String[] columnNamesH = {
                "ID Cliente",        // 0
                "Nombre",            // 1
                "Apellido",          // 2
                "Teléfono",          // 3
                "Email",             // 4
                "Dirección",         // 5
                "Fecha Registro",    // 6
                "Cédula",            // 7
                "ID Recepcionista"   // 8
        };
        tableModel3 = new DefaultTableModel(columnNamesH, 0);
        table1.setModel(tableModel3);
        table2.setModel(tableModel3);


        // Agregar los nombres de los campos como la primera fila
        Object[] headerRowH = {
                "ID Cliente", "Nombre", "Apellido", "Teléfono", "Email",
                "Dirección", "Fecha Registro", "Cédula", "ID Recepcionista"
        };

        tableModel3.addRow(headerRowH);

        // Cargar datos existentes
        cargarDatosClientes();


        /**
         * Boton para subir la imagen
         */

        subirImagenDelClienteButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    selectedImageFile2 = fileChooser.getSelectedFile();
                    JOptionPane.showMessageDialog(null, "Imagen seleccionada: " + selectedImageFile2.getName());
                }
            }
        });

        relizarLaInserccionDelButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    CrearCliente();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        //  Combobox para actualizar al cliente

        CoboBoxCliente.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String actualizar=CoboBoxCliente.getSelectedItem().toString();
                MostrarComboBoxCliente.setText(actualizar);
            }
        });
        actualizarButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ActualizarCliente();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        //Boton que ejecutara el metodo para eliminar cliente

        eliminarButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    EliminarCliente();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        //Buscara a un cliente por su cedula
        buscarButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    BuscarCliente();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        //boton que llamara al metodo que reseteara la tabla
        refrescarTablaButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                refrescar();
            }
        });

        //Boton para volver al menu roles
        volverAlMenuRolesButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                PantallaInicio pI=new PantallaInicio();
                pI.Iniciar();
                dispose();
            }
        });


        /*TODO LO ANTERIIOE PERTENECIA A EL CLIENTE
        *  DESDE AQUI PARA ABAJO ESTA ENFOCADO A LAS RESERVAS
        * */

        // Inicializar el modelo de la tabla
        String[] columnNamesRes = {
                "ID Reserva",        // 0
                "Fecha reserva",            // 1
                "Fecha entrada",          // 2
                "Fecha salida",          // 3
                "# Huespedes",             // 4
                "ID habitacion",         // 5
                "ID recepcionista",    // 6
                "ID cliente",            // 7

        };
        tableModel = new DefaultTableModel(columnNamesRes, 0);
        table3.setModel(tableModel);

        //table4.setModel(tableModel);



        // Agregar los nombres de los campos como la primera fila
        Object[] headerRowRes = {
                "ID Reserva","Fecha reserva","Fecha entrada","Fecha salida","# Huespedes","ID habitacion","ID recepcionista",
                "ID cliente",
        };

        tableModel.addRow(headerRowRes);

        // Cargar datos existentes
        cargarDatosReserva();


        //Metodo para crear reservas
        realizarInserccionDelRegistroButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    crearReserva();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        //ComBox para actualizar reservas
        comboBoxActualizarReserva.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String ActuReserva=comboBoxActualizarReserva.getSelectedItem().toString();
                MostrarCampoReserva.setText(ActuReserva);
            }
        });

        //Boton para actualizar la reserva
        actualizarButton1.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ActualizarReserva();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        //Metodo para eliminar una reserva
        eliminarButton1.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    EliminarReserva();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        buscarButton1.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    BuscarReserva();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        //Boton para refrescar la tabla de reservas
        refresarTablaButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                refrescar2();
            }
        });

        //Boton para crear el pdf
        relizarPdfDeLaButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String fechaReserva4 = BuscarFechaReserva.getText();
                generarFacturaPDF4(fechaReserva4);
            }
        });
    }





    //Metodo para refrescar la tabla
    public void refrescar(){
        // Limpio el modelo de la tabla antes de llenarlo con nuevos datos
        tableModel3.setRowCount(1);
        cargarDatosClientes(); // Carga los datos actualizados en la tabla
    }

    //Metodo Refrescar2
    public void refrescar2(){
        // Limpio el modelo de la tabla antes de llenarlo con nuevos datos
        tableModel.setRowCount(1);
        cargarDatosReserva(); // Carga los datos actualizados en la tabla
    }

    //David
    public void CrearCliente() throws SQLException {
        String IDcliente = IDclienteRecepcionistaC.getText();
        String  nombreCliente = NombreRecepcionistaC.getText();
        String  ApellidoC = ApellidoRecepcionistaC.getText();
        String  telefonoC = TelefonoRecepcionistaC.getText();
        String  emailC = EmailRecepcionistaC.getText();
        String  DireccionC = DireccionRecepcionistaC.getText();
        String  fechaC = FechaRegistroRecepcionistaC.getText();
        String  cedulaC = CedulaRecepcionistaC.getText();
        String  IDrecepcionistaC = IDRecepcionistaC.getText();

        //aaaaaaaaaaaaaaa
        // establezco la conexion llamando al metodo de mi clase Conexion

        Conexion c = new Conexion();
        Connection conecta=c.conexion();

        // Creo mi consulta mysql
        String sql ="insert into informacion_clientes(id_cliente2,nombre,apellido,telefono,email,direccion,fecha_registro,cedula,id_recepcionista,imagen) values " +
                "(?,?,?,?,?,?,?,?,?,?)";

        PreparedStatement pstmt = conecta.prepareStatement(sql);

        pstmt.setInt(1, Integer.parseInt(IDcliente));
        pstmt.setString(2, nombreCliente);
        pstmt.setString(3, ApellidoC);
        pstmt.setString(4, telefonoC);
        pstmt.setString(5, emailC);
        pstmt.setString(6, DireccionC);
        pstmt.setString(7, fechaC);
        pstmt.setString(8, cedulaC);
        pstmt.setInt(9, Integer.parseInt(IDrecepcionistaC));



        // Asignar la imagen seleccionada al PreparedStatement
        if (selectedImageFile2 != null) {
            pstmt.setString(10, selectedImageFile2.getAbsolutePath());
        } else {
            pstmt.setNull(10, java.sql.Types.VARCHAR);
        }

        int conteoCreacion = pstmt.executeUpdate();

        if (conteoCreacion > 0) {
            DialogoPersonalizado.mostrarDialogo();
            IDclienteRecepcionistaC.setText("");
            NombreRecepcionistaC.setText("");
            ApellidoRecepcionistaC.setText("");
            TelefonoRecepcionistaC.setText("");
            EmailRecepcionistaC.setText("");
            DireccionRecepcionistaC.setText("");
            FechaRegistroRecepcionistaC.setText("");
            CedulaRecepcionistaC.setText("");
            IDRecepcionistaC.setText("");



            // Agregar los datos insertados a la tabla
            Object[] rowData2 = {
                    IDcliente, nombreCliente, ApellidoC, telefonoC, emailC,
                    DireccionC, fechaC, cedulaC, IDrecepcionistaC, selectedImageFile2 != null
            };

            tableModel3.addRow(rowData2);



        } else {
            DialogoPersonalizado.mostrarDialogo2();
        }

        conecta.close();
        pstmt.close();
    }

    //Metodo para actualizar Cliente
    public void ActualizarCliente() throws SQLException {
        //Variables
        String tipo= MostrarComboBoxCliente.getText();
        String valor= NuevoValorCliente.getText();
        int VS = (int) SpinnerIDCliente.getValue();

        // establezco la conexion llamando al metodo de mi clase Conexion
        Conexion c = new Conexion();
        Connection conecta=c.conexion();

        String sql="UPDATE informacion_clientes SET " + tipo + " = ? WHERE id_cliente2 = ?";

        PreparedStatement pstmt=conecta.prepareStatement(sql);

        pstmt.setString(1,valor);
        pstmt.setInt(2,VS);

        int actualizarCliente=pstmt.executeUpdate();
        if (actualizarCliente>0){
            DialogoPersonalizado.mostrarDialogo();
            // Limpio el modelo de la tabla antes de llenarlo con nuevos datos
            tableModel3.setRowCount(1);
            cargarDatosClientes(); // Carga los datos actualizados en la tabla

            MostrarComboBoxCliente.setText("");
            NuevoValorCliente.setText("");
            SpinnerIDCliente.setValue(0);

        }else {
            DialogoPersonalizado.mostrarDialogo2();
        }
        conecta.close();
        pstmt.close();



    }

    public void EliminarCliente() throws SQLException {
        int VS = (int) SpinnerEliminar.getValue();

        // establezco la conexion llamando al metodo de mi clase Conexion
        Conexion c = new Conexion();
        Connection conecta=c.conexion();

        String sql="delete from informacion_clientes  WHERE id_cliente2 = ?";
        PreparedStatement pstmt=conecta.prepareStatement(sql);
        pstmt.setInt(1,VS);

        int eliminarRecepcionista =pstmt.executeUpdate();
        if (eliminarRecepcionista>0){
            DialogoPersonalizado.mostrarDialogo();

            tableModel3.setRowCount(1);
            cargarDatosClientes(); // Carga los datos actualizados en la tabla
            SpinnerEliminar.setValue(0);

        }else {
            DialogoPersonalizado.mostrarDialogo2();
        }

        conecta.close();
        pstmt.close();
    }

    public void BuscarCliente() throws SQLException {
        String ceudlaB=CedulaBuscarCliente.getText();

        // Establezco la conexión llamando al método de mi clase Conexion
        Conexion c = new Conexion();
        Connection conecta = c.conexion();

        // Creo mi consulta SQL
        String sql = "SELECT * FROM informacion_clientes WHERE cedula = ?";

        // Creo mi PreparedStatement
        PreparedStatement pstmt = conecta.prepareStatement(sql);
        pstmt.setString(1,ceudlaB);

        // Creo mi ResultSet
        ResultSet rs = pstmt.executeQuery();

        // Limpiar el modelo de la tabla antes de agregar la fila
        DefaultTableModel model = (DefaultTableModel) table2.getModel();
        model.setRowCount(1);

        if (rs.next()) {
            // Si encontramos un registro, lo mostramos en la tabla
            Object[] rowData2 = {
                    rs.getInt("id_cliente2"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("telefono"),
                    rs.getString("email"),
                    rs.getString("direccion"),
                    rs.getString("fecha_registro"),
                    rs.getString("cedula"),
                    rs.getInt("id_recepcionista")

            };
            tableModel3.addRow(rowData2);

            ImagenCliente.setText("");

            // Obtener la ruta de la imagen desde la base de datos
            String imagenRuta = rs.getString("imagen");
            if (imagenRuta != null && !imagenRuta.isEmpty()) {
                // Cargar la imagen en el JLabel
                ImageIcon imagenIcono = new ImageIcon(imagenRuta);
                Image imagen = imagenIcono.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH); // Escalar la imagen
                ImagenCliente.setIcon(new ImageIcon(imagen));
            } else {
                ImagenCliente.setIcon(null); // Si no hay imagen, quitar cualquier imagen previa
            }

        } else {
            // Si no se encuentra el registro
            DialogoPersonalizado.mostrarDialogo2();
        }

        // Cerrar conexiones
        conecta.close();
        pstmt.close();

    }





    //Metodo para cargar lo satos

    private void cargarDatosClientes() {
        Conexion c = new Conexion();
        try (Connection conecta = c.conexion();
             PreparedStatement pstmt = conecta.prepareStatement("SELECT * FROM informacion_clientes");
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Object[] rowData2 = {
                        rs.getInt("id_cliente2"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("telefono"),
                        rs.getString("email"),
                        rs.getString("direccion"),
                        rs.getString("fecha_registro"),
                        rs.getString("cedula"),
                        rs.getInt("id_recepcionista")

                };
                tableModel3.addRow(rowData2);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /*tODO LO ANTERIOR FUE CREADO PARA REALIZAR LO RESPECTIVO PARA CLIENTES DESDE AQUI COMIENZA
    *  LA PARTE DE RESERVAS*/

    //David

    //Metodo para crear la reserva

    public void crearReserva() throws SQLException {
        String IDReserva = IDReservaC.getText();
        String  freserva = FReservaReservaC.getText();
        String  fentrada = FEntradaReservaC.getText();
        String  fsalida = FSalidaReservaC.getText();
        int VS = (int) NhuespedesReservaC.getValue();
        String  idHabi = IDHabitacionReservaC.getText();
        String  idRecep = IDRecepcionistaReservaC.getText();
        String  idClien = IDClienteReservaC.getText();


        //aaaaaaaaaaaaaaa
        // establezco la conexion llamando al metodo de mi clase Conexion

        Conexion c = new Conexion();
        Connection conecta=c.conexion();

        // Creo mi consulta mysql
        String sql ="insert into informacion_reservas(id_reserva,fecha_reserva,fecha_entrada,fecha_salida,numero_huespedes,id_habitacion,id_recepcionista,id_cliente)" +
                " values (?,?,?,?,?,?,?,?)";

        PreparedStatement pstmt = conecta.prepareStatement(sql);

        pstmt.setInt(1, Integer.parseInt(IDReserva));
        pstmt.setString(2, freserva);
        pstmt.setString(3, fentrada);
        pstmt.setString(4, fsalida);
        pstmt.setInt(5, VS);
        pstmt.setInt(6, Integer.parseInt(idHabi));
        pstmt.setInt(7, Integer.parseInt(idRecep));
        pstmt.setInt(8, Integer.parseInt(idClien));

        int conteoCreacion = pstmt.executeUpdate();

        if (conteoCreacion > 0) {
            DialogoPersonalizado.mostrarDialogo();


            // Agregar los datos insertados a la tabla
            Object[] rowData2 = {
                    IDReserva, freserva, fentrada, fsalida, VS,
                    idHabi, idRecep, idClien
            };

            tableModel.addRow(rowData2);

            //Limpieza
            IDReservaC.setText("");
            FReservaReservaC.setText("");
            FEntradaReservaC.setText("");
            FSalidaReservaC.setText("");
            NhuespedesReservaC.setValue(0);
            IDHabitacionReservaC.setText("");
            IDRecepcionistaReservaC.setText("");
            IDClienteReservaC.setText("");



        } else {
            DialogoPersonalizado.mostrarDialogo2();
        }

        conecta.close();
        pstmt.close();

    }

    //Metodo para actualizar
    public void ActualizarReserva() throws SQLException {
        //Variables
        String tipo= MostrarCampoReserva.getText();
        String valor= NuevoValorReserva.getText();
        String IDRecep= IDdelareservaCA.getText();

        // establezco la conexion llamando al metodo de mi clase Conexion
        Conexion c = new Conexion();
        Connection conecta=c.conexion();

        String sql="UPDATE informacion_reservas SET " + tipo + " = ? WHERE id_reserva = ?";

        PreparedStatement pstmt=conecta.prepareStatement(sql);

        pstmt.setString(1,valor);
        pstmt.setInt(2,Integer.parseInt(IDRecep));

        int actualizarRecepcionista=pstmt.executeUpdate();
        if (actualizarRecepcionista>0){
            DialogoPersonalizado.mostrarDialogo();
            // Limpio el modelo de la tabla antes de llenarlo con nuevos datos
            tableModel.setRowCount(1);
            cargarDatosReserva(); // Carga los datos actualizados en la tabla

            MostrarCampoReserva.setText("");
            NuevoValorReserva.setText("");
            IDdelareservaCA.setText("");

        }else {
            DialogoPersonalizado.mostrarDialogo2();
        }
        conecta.close();
        pstmt.close();
    }

    //Metodo para eliminar una reserva
    public void EliminarReserva() throws SQLException {
        int VS = (int) IDReservaEliminar.getValue();

        // establezco la conexion llamando al metodo de mi clase Conexion
        Conexion c = new Conexion();
        Connection conecta=c.conexion();

        String sql="delete from informacion_reservas  WHERE id_reserva = ?";
        PreparedStatement pstmt=conecta.prepareStatement(sql);
        pstmt.setInt(1,VS);

        int eliminarRecepcionista =pstmt.executeUpdate();
        if (eliminarRecepcionista>0){
            DialogoPersonalizado.mostrarDialogo();

            tableModel.setRowCount(1);
            cargarDatosReserva();  // Carga los datos actualizados en la tabla
            IDReservaEliminar.setValue(0);

        }else {
            DialogoPersonalizado.mostrarDialogo2();
        }

        conecta.close();
        pstmt.close();
    }

    //Metodo para buscar una reserva por su fecha de reserva
    public void BuscarReserva() throws SQLException {
        String reservaFR=BuscarFechaReserva.getText();

        // Establezco la conexión llamando al método de mi clase Conexion
        Conexion c = new Conexion();
        Connection conecta = c.conexion();

        // Creo mi consulta SQL
        String sql = "SELECT * FROM informacion_reservas WHERE fecha_reserva = ?";

        // Creo mi PreparedStatement
        PreparedStatement pstmt = conecta.prepareStatement(sql);
        pstmt.setString(1,reservaFR);

        // Creo mi ResultSet
        ResultSet rs = pstmt.executeQuery();

        // Limpiar el modelo de la tabla antes de agregar la fila
        DefaultTableModel model = (DefaultTableModel) table3.getModel();
        model.setRowCount(1);

        if (rs.next()) {
            // Si encontramos un registro, lo mostramos en la tabla
            Object[] rowData2 = {
                    rs.getInt("id_reserva"),
                    rs.getString("fecha_reserva"),
                    rs.getString("fecha_entrada"),
                    rs.getString("fecha_salida"),
                    rs.getString("numero_huespedes"),
                    rs.getString("id_habitacion"),
                    rs.getString("id_recepcionista"),
                    rs.getString("id_cliente")

            };
            tableModel.addRow(rowData2);



        } else {
            // Si no se encuentra el registro
            DialogoPersonalizado.mostrarDialogo2();
        }

        // Cerrar conexiones
        conecta.close();
        pstmt.close();
    }



    // Metodo para cargar los datos anteriores a mi tabla
    private void cargarDatosReserva() {
        Conexion c = new Conexion();
        try (Connection conecta = c.conexion();
             PreparedStatement pstmt = conecta.prepareStatement("SELECT * FROM informacion_reservas");
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Object[] rowData2 = {
                        rs.getInt("id_reserva"),
                        rs.getString("fecha_reserva"),
                        rs.getString("fecha_entrada"),
                        rs.getString("fecha_salida"),
                        rs.getString("numero_huespedes"),
                        rs.getInt("id_habitacion"),
                        rs.getInt("id_recepcionista"),
                        rs.getString("id_cliente")
                };
                tableModel.addRow(rowData2);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    //Metodo para mostrar cuando esta pestaña sea llamada desde otro formulario
    public void Iniciar(){
        setVisible(true);
        setTitle("Rol Recepcionista");
        setSize(1000,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    //Metrodo para el pdf
    public void generarFacturaPDF4(String fechaReserva4) {
        // Nombre del archivo PDF
        String rutaUsuario4 = System.getProperty("user.home");
        String rutaPDF4 = rutaUsuario4 + "/Desktop/factura_reservas.pdf";

        Document documento4 = new Document();
        try {
            // Crear un PdfWriter
            PdfWriter.getInstance(documento4, new FileOutputStream(rutaPDF4));

            // Abrir el documento
            documento4.open();

            // Añadir un título
            Paragraph titulo4 = new Paragraph("RESERVA HOSTAL COLIBRI", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, BaseColor.RED));
            titulo4.setAlignment(Element.ALIGN_CENTER);
            documento4.add(titulo4);

            // Añadir subtítulos
            Paragraph subtitulo41 = new Paragraph("Gracias por preferirnos", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14));
            subtitulo41.setAlignment(Element.ALIGN_CENTER);
            documento4.add(subtitulo41);

            Paragraph subtitulo42 = new Paragraph("Aquí podrá visualizar la información de su reserva", FontFactory.getFont(FontFactory.HELVETICA, 12));
            subtitulo42.setAlignment(Element.ALIGN_CENTER);
            documento4.add(subtitulo42);

            // Añadir un espacio
            documento4.add(new Paragraph(" "));

            // Crear una tabla con el mismo número de columnas que tu tabla de reservas
            PdfPTable tabla4 = new PdfPTable(8); // 8 columnas
            tabla4.setWidthPercentage(100);

            // Añadir los encabezados de las columnas
            agregarEncabezadoTabla4(tabla4);

            // Añadir los datos de la tabla
            agregarFilas4(tabla4, fechaReserva4);

            // Añadir la tabla al documento
            documento4.add(tabla4);

            // Cerrar el documento
            documento4.close();

            // Mostrar un mensaje de éxito
            JOptionPane.showMessageDialog(null, "Factura PDF generada exitosamente en el escritorio.");

        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al generar la factura PDF.");
        }
    }

    private void agregarEncabezadoTabla4(PdfPTable tabla4) {
        Stream.of("ID Reserva", "Fecha Reserva", "Fecha Entrada", "Fecha Salida", "# Huespedes", "ID Habitacion", "ID Recepcionista", "ID Cliente")
                .forEach(tituloColumna4 -> {
                    PdfPCell encabezado4 = new PdfPCell();
                    encabezado4.setPhrase(new Phrase(tituloColumna4));
                    encabezado4.setHorizontalAlignment(Element.ALIGN_CENTER);
                    tabla4.addCell(encabezado4);
                });
    }

    private void agregarFilas4(PdfPTable tabla4, String fechaReserva4) {
        Conexion c4 = new Conexion();
        try (Connection conexion4 = c4.conexion()) {
            // Consulta SQL
            String sql4 = "SELECT * FROM informacion_reservas WHERE fecha_reserva = ?";
            PreparedStatement pstmt4 = conexion4.prepareStatement(sql4);
            pstmt4.setString(1, fechaReserva4);

            ResultSet rs4 = pstmt4.executeQuery();
            while (rs4.next()) {
                tabla4.addCell(rs4.getString("id_reserva"));
                tabla4.addCell(rs4.getString("fecha_reserva"));
                tabla4.addCell(rs4.getString("fecha_entrada"));
                tabla4.addCell(rs4.getString("fecha_salida"));
                tabla4.addCell(rs4.getString("numero_huespedes"));
                tabla4.addCell(rs4.getString("id_habitacion"));
                tabla4.addCell(rs4.getString("id_recepcionista"));
                tabla4.addCell(rs4.getString("id_cliente"));
            }

        } catch (SQLException ex4) {
            ex4.printStackTrace();
        }
    }

    //Recepcionista LISTO
}
