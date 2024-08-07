import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PantallaInicio extends JFrame{
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JTextField usuA;
    private JTextField conA;
    private JButton validacionDeCredencialesButton;
    private JButton validacionDeCredencialesButton1;
    private JTextField usuarioR;
    private JTextField ContraR;
    private JButton habitacionesButton;

    /**
     * Creo el metodo constructor
     */
    public PantallaInicio(){
        setTitle("Acceso segun los roles");
        setSize(600,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(panel1);


        //Boton para validar las credenciales del administrador
        validacionDeCredencialesButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Credenciales_Admin();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        validacionDeCredencialesButton1.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Credenciales_Recep();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        //Demas
        habitacionesButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                Cliente c1 = new Cliente();
                c1.Iniciar();
                dispose();
            }
        });
    }





    /**
     * Metodo para validar las credenciales del administrador
     */
    public void Credenciales_Admin() throws SQLException {
        String usuarioAdministrador=usuA.getText();
        String contraseñaAdministrador=conA.getText();
        //Traigo el establecimiento de la conexion de mi otra clase
        Conexion c = new Conexion();
        Connection conecta=c.conexion();

        String sql="select * from logue_administrador where usuario=? and contrasenia=?";
        PreparedStatement pstmt = conecta.prepareStatement(sql);
        pstmt.setString(1,usuarioAdministrador);
        pstmt.setString(2,contraseñaAdministrador);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()){
            DialogoPersonalizado.mostrarDialogo();

            RolAdministrador r1 = new RolAdministrador();
            r1.Iniciar();
            dispose();

        }else {
            DialogoPersonalizado.mostrarDialogo2();
            usuA.setText("");
            conA.setText("");
        }
        conecta.close();
        pstmt.close();
        rs.close();

    }


    /**
     * Metodo para validar credenciales del recepcionista
     */

    public void Credenciales_Recep() throws SQLException {
        String usuarioRecpcionista=usuarioR.getText();
        String contraseñaRecepcionista=ContraR.getText();
        //Traigo el establecimiento de la conexion de mi otra clase
        Conexion c = new Conexion();
        Connection conecta=c.conexion();

        String sql="select * from logueo_recepcionista where usuario=? and contrasenia=?";
        PreparedStatement pstmt = conecta.prepareStatement(sql);
        pstmt.setString(1,usuarioRecpcionista);
        pstmt.setString(2,contraseñaRecepcionista);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()){
            DialogoPersonalizado.mostrarDialogo();

            RolRecepcionista r1 = new RolRecepcionista();
            r1.Iniciar();
            dispose();

        }else {
            DialogoPersonalizado.mostrarDialogo2();
            usuarioR.setText("");
            ContraR.setText("");
        }
        conecta.close();
        pstmt.close();
        rs.close();
    }

    public void Iniciar(){
        setVisible(true);
        setTitle("Pantalla de Inicio");
        setSize(600,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    //Se acabo
}
