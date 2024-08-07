import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    public Connection conexion() throws SQLException {

        String url="jdbc:mysql://uzccfwu9iaavsioi:eTLuNNNDVMiqhRQQRd6O@byceupr5m7fri2s7ivds-mysql.services.clever-cloud.com:3306/byceupr5m7fri2s7ivds";
        String user="uzccfwu9iaavsioi";
        String password="eTLuNNNDVMiqhRQQRd6O";

        /*String url = "jdbc:mysql://localhost:3306/PROYECTO_POO";
        String user = "root";
        String password = "123456";*/

        return DriverManager.getConnection(url,user,password);
    }
}
