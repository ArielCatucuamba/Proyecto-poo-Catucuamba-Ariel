import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RolAdministrador extends JFrame{
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JTabbedPane tabbedPane2;
    private JTextField IDCreacionRecepcionista;
    private JTextField NombreCreacionRecepcionista;
    private JTextField ApellidoCreacionRecepcionista;
    private JTextField NacimientoCreacionRecepcionista;
    private JTextField TelefonoCreacionRecepcionista;
    private JTextField EmailCreacionRecepcionista;
    private JTextField DireccionCreacionRecepcionista;
    private JTextField ContratacionCreacionRecepcionista;
    private JTextField SalarioCreacionRecepcionista;
    private JTextField IDAdmCreacionRecepcionista;
    private JComboBox TurnoCreacionRecepcionista;
    private JButton volverAlMenuRolesButton;
    private JButton realizarInserccionDelRegistroButton;
    private JTextField turno;
    private JButton subirImagenDeLaButton;
    private JTable table1;
    private JComboBox comboBoxActualizarRecepcionista;
    private JTextField ColumnaActualizarRecepcionista;
    private JButton actualizarButton;
    private JTextField NuevoValorRecepcionista;
    private JTextField IDdelRecepcionista;
    private JSpinner spinner1;
    private JButton eliminarButton;
    private JButton buscarRecepcionistaButton;
    private JLabel VisualizarNombre;
    private JLabel VisualizacionRecepcionistaNombre;
    private JSpinner spinner2;
    private JLabel VisualizarImagenRecep;
    private JTable table2;
    private JButton refescarButton;
    private JTabbedPane tabbedPane3;
    private JButton realizarInserccionDeLaButton;
    private JSpinner IDHspinner;
    private JTextField TipoHabitacionCH;
    private JTextField DescripcionCH;
    private JTextField PrecioCreaciojH;
    private JComboBox EstadoCreacionH;
    private JSpinner NcamasHspinner;
    private JSpinner CapacidadHspinner;
    private JComboBox WifiCreacionH;
    private JComboBox TvCreacionH;
    private JComboBox AireCreacionH;
    private JComboBox MinibarCreacionH;
    private JTextField VistaCH;
    private JSpinner IDAdmHspinner;
    private JButton subirUnaImagenDeButton;
    private JButton subirImagenDeLaButton1;
    private JTextField IDGestionarH;
    private JTextField TipoGestionarH;
    private JTextField DescripcionGestionarH;
    private JTextField PrecioGestionarH;
    private JComboBox EstadoGestionarH;
    private JComboBox CamasGestionarH;
    private JComboBox CapacidadGestionarH;
    private JComboBox WifiGestionarH;
    private JComboBox TvGestionarH;
    private JComboBox AireGestionarH;
    private JComboBox MiniGestionarH;
    private JTextField VistaGestionarH;
    private JTextField IDADMGestionarH;
    private JTextField estado;
    private JTextField Ncamas;
    private JTextField Capacidad;
    private JTextField Wifi;
    private JTextField Tv;
    private JTextField Aire;
    private JTextField Minibar;
    private JTable table3;
    private JTextField MostrarEstado;
    private JTextField MostrarWifi;
    private JTextField MostrarTv;
    private JTextField MostrarAire;
    private JTextField MostrarMiniBar;
    private JButton actualizarButton1;
    private JComboBox comboBoxActualizarHabitacion;
    private JTextField MostrarCampoH;
    private JTextField NuevoValorH;
    private JTextField IDHabitacion;
    private JSpinner spinerIDHa;
    private JButton eliminarHabitacionButton;
    private JButton refrescarTablaButton;
    private JTable table4;
    private JButton refrescarButton;
    private JButton buscarHabitacionButton;
    private JSpinner spinner3;
    private JLabel imagenHabi;


    //variables que no son del swing
    private File selectedImageFile;  // Variable para almacenar la imagen seleccionada de recepcionista
    private File selectedImageFile2;  // Variable para almacenar la imagen seleccionada de recepcionista


    //Variable para mi tabla
    public DefaultTableModel tableModel;
    public DefaultTableModel tableModel3;


    public RolAdministrador(){
        setContentPane(panel1);
        /*-----------------------------------------------------------------------------------------------------------------------
         * ------------------------------------------------------------------------------------------------------------------------
         * -----------------------------------------------------------------------------------------------------------------------
         * -----------------------------------------------------------------------------------------------------------------------
         * -----------------------------------------------------------------------------------------------------------------------*/

        // TODO ESTO PERTENECE A GESTIONAR RECEPCIONISTA
        // Inicializar el modelo de la tabla
        String[] columnNames = {
                "ID Recepcionista",     // 0
                "Nombre",               // 1
                "Apellido",             // 2
                "Fecha de Nacimiento",  // 3
                "Teléfono",             // 4
                "Email",                // 5
                "Dirección",            // 6
                "Fecha de Contratación",// 7
                "Salario",              // 8
                "Turno",                // 9
                "ID Administrador",     // 10

        };
        tableModel = new DefaultTableModel(columnNames, 0);
        table1.setModel(tableModel);
        table2.setModel(tableModel);


        // Agregar los nombres de los campos como la primera fila
        Object[] headerRow = {
                "ID Recepcionista", "Nombre", "Apellido", "Fecha de Nacimiento",
                "Teléfono", "Email", "Dirección", "Fecha de Contratación",
                "Salario", "Turno", "ID Administrador"
                };
        tableModel.addRow(headerRow);

        // Cargar datos existentes
        cargarDatosExistentes();



        // Esto es para el combobox , convertir lo que se elija en texto
        TurnoCreacionRecepcionista.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String valor=TurnoCreacionRecepcionista.getSelectedItem().toString();
                turno.setText(valor);
            }
        });

        //Esto es para subir la imagen a la base de datos
        subirImagenDeLaButton.addActionListener(new ActionListener() {
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
                    selectedImageFile = fileChooser.getSelectedFile();
                    JOptionPane.showMessageDialog(null, "Imagen seleccionada: " + selectedImageFile.getName());
                }
            }
        });
        //Boton para volver al menu de roles
        volverAlMenuRolesButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                PantallaInicio pI=new PantallaInicio();
                pI.Iniciar();
                dispose();
            }
        });

        //Boton para crear el registro de recepcionista
        realizarInserccionDelRegistroButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    CrearRecepcionista();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        // Es el comboBox que tiene las opciones para actualizar
        comboBoxActualizarRecepcionista.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String valor=comboBoxActualizarRecepcionista.getSelectedItem().toString();
                ColumnaActualizarRecepcionista.setText(valor);
            }
        });

        //Actualizar un recepcionista
        actualizarButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ActualizarRecepcionista();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


        /**
         * Boton para eliminar un registro de un recepcionista y objetener el valor de un JSpiner
         */
        eliminarButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    EliminarRegitroRecepcionita();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        // boton para realziar el metodo de ver a los recepcionistas
        buscarRecepcionistaButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    BuscarRecepcionista();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        // boton para refrescar el registro
        refescarButton.addActionListener(new ActionListener() {
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
        /*-----------------------------------------------------------------------------------------------------------------------
         * ------------------------------------------------------------------------------------------------------------------------
         * -----------------------------------------------------------------------------------------------------------------------
         * -----------------------------------------------------------------------------------------------------------------------
         * -----------------------------------------------------------------------------------------------------------------------*/
        /**
         * Esto es para la parte de habitaciones
         */
        // Inicializar el modelo de la tabla
        String[] columnNamesH = {
                "ID Habitacion",         // 0
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
                "ID Administrador"       // 12
        };
        tableModel3 = new DefaultTableModel(columnNamesH, 0);
        table3.setModel(tableModel3);
        table4.setModel(tableModel3);

        // Agregar los nombres de los campos como la primera fila
        Object[] headerRowH = {
                "ID Habitacion", "Tipo", "Descripcion", "Precio", "Estado",
                "Numero de Camas", "Capacidad", "Wifi", "TV", "Aire Acondicionado",
                "MiniBar", "Vista", "ID Administrador"
        };
        tableModel3.addRow(headerRowH);

        // Cargar datos existentes
        cargarDatosExistentesHabitaciones2();




        // Subir una imagen de habitaciones
        subirUnaImagenDeButton.addActionListener(new ActionListener() {
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
        //llamo al metodo crear habitaciones
        realizarInserccionDeLaButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    CrearHabitaciones();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        //Comboboxs

        EstadoCreacionH.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String estado=EstadoCreacionH.getSelectedItem().toString();
                MostrarEstado.setText(estado);
            }
        });

        WifiCreacionH.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String wifi=WifiCreacionH.getSelectedItem().toString();
                MostrarWifi.setText(wifi);
            }
        });


        TvCreacionH.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String tv=TvCreacionH.getSelectedItem().toString();
                MostrarTv.setText(tv);
            }
        });
        AireCreacionH.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {

                String aire=AireCreacionH.getSelectedItem().toString();
                MostrarAire.setText(aire);
            }
        });

        MinibarCreacionH.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String mbar=MinibarCreacionH.getSelectedItem().toString();
                MostrarMiniBar.setText(mbar);
            }
        });

        //comboBox para actualizar
        comboBoxActualizarHabitacion.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String ActualizarH=comboBoxActualizarHabitacion.getSelectedItem().toString();
                MostrarCampoH.setText(ActualizarH);
            }
        });

        //Relizar el metodo de actualizar habitacion
        actualizarButton1.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ActualizarHabitacion();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        eliminarHabitacionButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    EliminarHabitacion();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        refrescarTablaButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                refrescarHabitacion();
            }
        });

        //Metodo para buscar y mostrar mi imagen

        buscarHabitacionButton.addActionListener(new ActionListener() {
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
        // Refrescar
        refrescarButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                refrescarHabitacion();
            }
        });
    }




    //Metodo para refrescar la tabla
    public void refrescar(){
        // Limpio el modelo de la tabla antes de llenarlo con nuevos datos
        tableModel.setRowCount(1);
        cargarDatosExistentes(); // Carga los datos actualizados en la tabla
    }

    //Mishell
    public void CrearRecepcionista() throws SQLException {
        // Guardo todos los JTextField en variables
        String CreacionRecepcionistaID=IDCreacionRecepcionista.getText();
        String turnoRecepcionista=turno.getText();
        String CreacionRNombre=NombreCreacionRecepcionista.getText();
        String CreacionRApellido=ApellidoCreacionRecepcionista.getText();
        String CreacionRNacimiento=NacimientoCreacionRecepcionista.getText();
        String CreacionRTelefono=TelefonoCreacionRecepcionista.getText();
        String CreacionREmail=EmailCreacionRecepcionista.getText();
        String CreacionRDireccion=DireccionCreacionRecepcionista.getText();
        String CreacionRContratacion=ContratacionCreacionRecepcionista.getText();
        String CreacionRSalario=SalarioCreacionRecepcionista.getText();
        String CreacionRIDADM=IDAdmCreacionRecepcionista.getText();

        // establezco la conexion llamando al metodo de mi clase Conexion

        Conexion c = new Conexion();
        Connection conecta=c.conexion();

        // Creo mi consulta mysql
        String sql="insert into informacion_recepcionista(id_recepcionista2,nombre,apellido,fecha_nacimiento,telefono,email," +
                "direccion,fecha_contratacion,salario,turno,id_admin,imagen)" +
                "values (?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt=conecta.prepareStatement(sql);
        pstmt.setInt(1,Integer.parseInt(CreacionRecepcionistaID));
        pstmt.setString(2,CreacionRNombre);
        pstmt.setString(3,CreacionRApellido);
        pstmt.setString(4,CreacionRNacimiento);
        pstmt.setString(5,CreacionRTelefono);
        pstmt.setString(6,CreacionREmail);
        pstmt.setString(7,CreacionRDireccion);
        pstmt.setString(8,CreacionRContratacion);
        pstmt.setDouble(9,Double.parseDouble(CreacionRSalario));
        pstmt.setString(10,turnoRecepcionista);
        pstmt.setInt(11,Integer.parseInt(CreacionRIDADM));

        // Asignar la imagen seleccionada al PreparedStatement
        if (selectedImageFile != null) {
            pstmt.setString(12, selectedImageFile.getAbsolutePath());
        } else {
            pstmt.setNull(12, java.sql.Types.VARCHAR);
        }


        int Creacionconteo= pstmt.executeUpdate();

        if (Creacionconteo>0){
            DialogoPersonalizado.mostrarDialogo();
            IDCreacionRecepcionista.setText("");
            turno.setText("");
            NombreCreacionRecepcionista.setText("");
            ApellidoCreacionRecepcionista.setText("");
            NacimientoCreacionRecepcionista.setText("");
            TelefonoCreacionRecepcionista.setText("");
            EmailCreacionRecepcionista.setText("");
            DireccionCreacionRecepcionista.setText("");
            ContratacionCreacionRecepcionista.setText("");
            SalarioCreacionRecepcionista.setText("");
            IDAdmCreacionRecepcionista.setText("");

            // Agregar los datos insertados a la tabla
            Object[] rowData = {CreacionRecepcionistaID, CreacionRNombre, CreacionRApellido, CreacionRNacimiento, CreacionRTelefono,
                    CreacionREmail, CreacionRDireccion, CreacionRContratacion, CreacionRSalario, turnoRecepcionista,
                    CreacionRIDADM, selectedImageFile2 != null };
            tableModel.addRow(rowData);

        }
        else {
            DialogoPersonalizado.mostrarDialogo2();
            IDCreacionRecepcionista.setText("");
            turno.setText("");
            NombreCreacionRecepcionista.setText("");
            ApellidoCreacionRecepcionista.setText("");
            NacimientoCreacionRecepcionista.setText("");
            TelefonoCreacionRecepcionista.setText("");
            EmailCreacionRecepcionista.setText("");
            DireccionCreacionRecepcionista.setText("");
            ContratacionCreacionRecepcionista.setText("");
            SalarioCreacionRecepcionista.setText("");
            IDAdmCreacionRecepcionista.setText("");
        }
        conecta.close();
        pstmt.close();


    }


    //metodo para actualizar al recepcionista
    public void ActualizarRecepcionista() throws SQLException {

        String tipo= ColumnaActualizarRecepcionista.getText();
        String valor= NuevoValorRecepcionista.getText();
        String IDRecep= IDdelRecepcionista.getText();

        // establezco la conexion llamando al metodo de mi clase Conexion
        Conexion c = new Conexion();
        Connection conecta=c.conexion();

        String sql="UPDATE informacion_recepcionista SET " + tipo + " = ? WHERE id_recepcionista2 = ?";

        PreparedStatement pstmt=conecta.prepareStatement(sql);

        pstmt.setString(1,valor);
        pstmt.setInt(2,Integer.parseInt(IDRecep));

        int actualizarRecepcionista=pstmt.executeUpdate();
        if (actualizarRecepcionista>0){
            DialogoPersonalizado.mostrarDialogo();
            ColumnaActualizarRecepcionista.setText("");
            NuevoValorRecepcionista.setText("");
            IDdelRecepcionista.setText("");

            // Limpio el modelo de la tabla antes de llenarlo con nuevos datos
            tableModel.setRowCount(1);
            cargarDatosExistentes(); // Carga los datos actualizados en la tabla

        }else {
            DialogoPersonalizado.mostrarDialogo2();
        }
        conecta.close();
        pstmt.close();

    }



    //Metodo para eliminar un registro
    public void EliminarRegitroRecepcionita() throws SQLException {
        int VS = (int) spinner1.getValue();

        // establezco la conexion llamando al metodo de mi clase Conexion
        Conexion c = new Conexion();
        Connection conecta=c.conexion();

        String sql="delete from informacion_recepcionista  WHERE id_recepcionista2 = ?";
        PreparedStatement pstmt=conecta.prepareStatement(sql);
        pstmt.setInt(1,VS);

        int eliminarRecepcionista =pstmt.executeUpdate();
        if (eliminarRecepcionista>0){
            DialogoPersonalizado.mostrarDialogo();

            tableModel.setRowCount(1);
            cargarDatosExistentes(); // Carga los datos actualizados en la tabla


        }

        conecta.close();
        pstmt.close();
    }

    //arielv

    //Metodo para buscar a un recepcionista

    public void BuscarRecepcionista() throws SQLException {
        int VS2 = (int) spinner2.getValue();

        // Establezco la conexión llamando al método de mi clase Conexion
        Conexion c = new Conexion();
        Connection conecta = c.conexion();

        // Creo mi consulta SQL
        String sql = "SELECT * FROM informacion_recepcionista WHERE id_recepcionista2 = ?";

        // Creo mi PreparedStatement
        PreparedStatement pstmt = conecta.prepareStatement(sql);
        pstmt.setInt(1, VS2);

        // Creo mi ResultSet
        ResultSet rs = pstmt.executeQuery();

        // Limpiar el modelo de la tabla antes de agregar la fila
        DefaultTableModel model = (DefaultTableModel) table2.getModel();
        model.setRowCount(1);

        if (rs.next()) {
            // Si encontramos un registro, lo mostramos en la tabla
            Object[] rowData = {
                    rs.getInt("id_recepcionista2"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("fecha_nacimiento"),
                    rs.getString("telefono"),
                    rs.getString("email"),
                    rs.getString("direccion"),
                    rs.getString("fecha_contratacion"),
                    rs.getDouble("salario"),
                    rs.getString("turno"),
                    rs.getInt("id_admin")
            };
            model.addRow(rowData);



            // Obtener la ruta de la imagen desde la base de datos
            String imagenRuta = rs.getString("imagen");
            if (imagenRuta != null && !imagenRuta.isEmpty()) {
                // Cargar la imagen en el JLabel
                ImageIcon imagenIcono = new ImageIcon(imagenRuta);
                Image imagen = imagenIcono.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH); // Escalar la imagen
                VisualizarImagenRecep.setIcon(new ImageIcon(imagen));
            } else {
                VisualizarImagenRecep.setIcon(null); // Si no hay imagen, quitar cualquier imagen previa
            }

        } else {
            // Si no se encuentra el registro
            DialogoPersonalizado.mostrarDialogo2();
        }

        // Cerrar conexiones
        conecta.close();
        pstmt.close();
    }




    //Metodo para cargar los datos anteriores a mi tabla
    private void cargarDatosExistentes() {
        Conexion c = new Conexion();
        try (Connection conecta = c.conexion();
             PreparedStatement pstmt = conecta.prepareStatement("SELECT * FROM informacion_recepcionista");
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Object[] rowData = {
                        rs.getInt("id_recepcionista2"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("fecha_nacimiento"),
                        rs.getString("telefono"),
                        rs.getString("email"),
                        rs.getString("direccion"),
                        rs.getString("fecha_contratacion"),
                        rs.getDouble("salario"),
                        rs.getString("turno"),
                        rs.getInt("id_admin"),

                };
                tableModel.addRow(rowData);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    //TODO HASTA AQUI LLEGA LA PARTE DE RECEPCIONISTA
    /*-----------------------------------------------------------------------------------------------------------------------
    * ------------------------------------------------------------------------------------------------------------------------
    * -----------------------------------------------------------------------------------------------------------------------
    * -----------------------------------------------------------------------------------------------------------------------
    * -----------------------------------------------------------------------------------------------------------------------*/
    //Mishell

    public void CrearHabitaciones() throws SQLException {
        int idHabitacion = (int) IDHspinner.getValue();


        String tipoH=TipoHabitacionCH.getText();
        String descriiH=DescripcionCH.getText();
        String precioH=PrecioCreaciojH.getText();

        String estadoH=MostrarEstado.getText();

        int numerocamasH = (int) NcamasHspinner.getValue();
        int capacidadH = (int) CapacidadHspinner.getValue();

        String wifiH=MostrarWifi.getText();

        String tvH=MostrarTv.getText();
        String aireH=MostrarAire.getText();
        String miniH=MostrarMiniBar.getText();

        String vistaH=VistaCH.getText();

        int idAMD = (int) IDAdmHspinner.getValue();




        // establezco la conexion llamando al metodo de mi clase Conexion

        Conexion c = new Conexion();
        Connection conecta=c.conexion();

        // Creo mi consulta mysql
        String sql ="insert into informacion_habitaciones(id_habitacion,tipo,descripcion,precio,estado,numero_camas,capacidad,tiene_wifi,tiene_tv," +
                "tiene_aire_acondicionado,tiene_minibar,vista,id_administrador,imagen) " +
                "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt = conecta.prepareStatement(sql);
        pstmt.setInt(1, idHabitacion);
        pstmt.setString(2, tipoH);
        pstmt.setString(3, descriiH);
        pstmt.setDouble(4, Double.parseDouble(precioH));
        pstmt.setString(5, estadoH);
        pstmt.setInt(6, numerocamasH);
        pstmt.setInt(7, capacidadH);
        pstmt.setString(8, wifiH);
        pstmt.setString(9, tvH);
        pstmt.setString(10, aireH);
        pstmt.setString(11, miniH);
        pstmt.setString(12, vistaH);
        pstmt.setInt(13, idAMD);

        // Asignar la imagen seleccionada al PreparedStatement
        if (selectedImageFile2 != null) {
            pstmt.setString(14, selectedImageFile2.getAbsolutePath());
        } else {
            pstmt.setNull(14, java.sql.Types.VARCHAR);
        }

        int conteoCreacion = pstmt.executeUpdate();

        if (conteoCreacion > 0) {
            DialogoPersonalizado.mostrarDialogo();
            IDHspinner.setValue(0);
            DescripcionCH.setText("");
            PrecioCreaciojH.setText("");
            EstadoCreacionH.setSelectedIndex(0);
            NcamasHspinner.setValue(0);
            CapacidadHspinner.setValue(0);
            WifiCreacionH.setSelectedIndex(0);
            TvCreacionH.setSelectedIndex(0);
            AireCreacionH.setSelectedIndex(0);
            MinibarCreacionH.setSelectedIndex(0);
            VistaCH.setText("");
            IDAdmHspinner.setValue(0);
            MostrarEstado.setText("");
            MostrarWifi.setText("");
            MostrarTv.setText("");
            MostrarAire.setText("");
            MostrarMiniBar.setText("");
            TipoHabitacionCH.setText("");


            // Agregar los datos insertados a la tabla
            Object[] rowData2 = {idHabitacion, tipoH, descriiH, precioH, estadoH, numerocamasH, capacidadH, wifiH, tvH,
                    aireH, miniH, vistaH, idAMD, selectedImageFile2 != null};
            tableModel3.addRow(rowData2);



        } else {
            DialogoPersonalizado.mostrarDialogo2();
        }

        conecta.close();
        pstmt.close();

    }


    public void ActualizarHabitacion() throws SQLException {
        //Variables
        String tipo= MostrarCampoH.getText();
        String valor= NuevoValorH.getText();
        String IDRecep= IDHabitacion.getText();

        // establezco la conexion llamando al metodo de mi clase Conexion
        Conexion c = new Conexion();
        Connection conecta=c.conexion();

        String sql="UPDATE informacion_habitaciones SET " + tipo + " = ? WHERE id_habitacion = ?";

        PreparedStatement pstmt=conecta.prepareStatement(sql);

        pstmt.setString(1,valor);
        pstmt.setInt(2,Integer.parseInt(IDRecep));

        int actualizarRecepcionista=pstmt.executeUpdate();
        if (actualizarRecepcionista>0){
            DialogoPersonalizado.mostrarDialogo();
            // Limpio el modelo de la tabla antes de llenarlo con nuevos datos
            tableModel3.setRowCount(1);
            cargarDatosExistentesHabitaciones2(); // Carga los datos actualizados en la tabla

            MostrarCampoH.setText("");
            NuevoValorH.setText("");
            IDHabitacion.setText("");

        }else {
            DialogoPersonalizado.mostrarDialogo2();
        }
        conecta.close();
        pstmt.close();


    }



    public void EliminarHabitacion() throws SQLException {
        int VS = (int) spinerIDHa.getValue();

        // establezco la conexion llamando al metodo de mi clase Conexion
        Conexion c = new Conexion();
        Connection conecta=c.conexion();

        String sql="delete from informacion_habitaciones  WHERE id_habitacion = ?";
        PreparedStatement pstmt=conecta.prepareStatement(sql);
        pstmt.setInt(1,VS);

        int eliminarRecepcionista =pstmt.executeUpdate();
        if (eliminarRecepcionista>0){
            DialogoPersonalizado.mostrarDialogo();

            tableModel3.setRowCount(1);
            cargarDatosExistentesHabitaciones2(); // Carga los datos actualizados en la tabla
            spinerIDHa.setValue(0);

        }else {
            DialogoPersonalizado.mostrarDialogo2();
        }

        conecta.close();
        pstmt.close();

    }



    //ariel
    public void BuscarHabitacion() throws SQLException {
        int VS3 = (int) spinner3.getValue();

        // Establezco la conexión llamando al método de mi clase Conexion
        Conexion c = new Conexion();
        Connection conecta = c.conexion();

        // Creo mi consulta SQL
        String sql = "SELECT * FROM informacion_habitaciones WHERE id_habitacion = ?";

        // Creo mi PreparedStatement
        PreparedStatement pstmt = conecta.prepareStatement(sql);
        pstmt.setInt(1, VS3);

        // Creo mi ResultSet
        ResultSet rs = pstmt.executeQuery();

        // Limpiar el modelo de la tabla antes de agregar la fila
        DefaultTableModel model = (DefaultTableModel) table4.getModel();
        model.setRowCount(1);

        if (rs.next()) {
            // Si encontramos un registro, lo mostramos en la tabla
            Object[] rowData2 = {
                    rs.getInt("id_habitacion"),
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
                    rs.getInt("id_administrador")
            };
            tableModel3.addRow(rowData2);


            imagenHabi.setText("");
            // Obtener la ruta de la imagen desde la base de datos
            String imagenRuta = rs.getString("imagen");
            if (imagenRuta != null && !imagenRuta.isEmpty()) {
                // Cargar la imagen en el JLabel
                ImageIcon imagenIcono = new ImageIcon(imagenRuta);
                Image imagen = imagenIcono.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH); // Escalar la imagen
                imagenHabi.setIcon(new ImageIcon(imagen));
            } else {
                imagenHabi.setIcon(null); // Si no hay imagen, quitar cualquier imagen previa
            }

        } else {
            // Si no se encuentra el registro
            DialogoPersonalizado.mostrarDialogo2();
        }

        // Cerrar conexiones
        conecta.close();
        pstmt.close();
    }













    //Metodo para refrescar la tabla
    public void refrescarHabitacion(){
        // Limpio el modelo de la tabla antes de llenarlo con nuevos datos
        tableModel3.setRowCount(1);
        cargarDatosExistentesHabitaciones2(); // Carga los datos actualizados en la tabla
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
                        rs.getInt("id_administrador")
                };
                tableModel3.addRow(rowData2);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }








    //Metodo para mostrar cuando esta pestaña sea llamada desde otro formulario
    public void Iniciar(){
        setVisible(true);
        setTitle("Rol administrador");
        setSize(1888,580);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    //DiseADM

}
