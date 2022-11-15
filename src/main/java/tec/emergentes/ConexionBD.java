package tec.emergentes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Author Dreick Lap1
 */
public class ConexionBD {
   
    static String driver = "com.mysql.jdbc.Driver";
    static  String url="jdbc:mysql://localhost:3306/jsp_biblioteca";
    static String usuario="root1";
    static String password ="root12";
    Connection conn =null;
    
    public ConexionBD(){
    try{
        // especificacion del driver
        Class.forName(driver);
        // establecemos la conexion de la base de datos 
        conn= DriverManager.getConnection(url,usuario,password);
        // Verificar  si la conexion fue exitosa
        if(conn != null){
            System.out.println("Conexion OK:"+conn);
        }
    }catch(SQLException ex ){
        System.out.println("Error de SQL"+ex.getMessage());
    }catch(ClassNotFoundException ex){
       Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE,null,ex);
       }
    }
    public Connection conectar()
    {
        return conn;
    }
    public void desconectar()
    {
        try {
            conn.close();
        } catch (SQLException ex){
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
}
