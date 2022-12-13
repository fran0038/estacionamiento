package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/estacionamiento";
    private static final String USER = "root";
    public static final String CLAVE = "";
    
    public Connection getConnection(){	
        Connection con = null;
        try{
            con = DriverManager.getConnection(URL,USER,CLAVE);
        }catch(SQLException e){
            System.out.println("Error al conectar "+e);
        }
        return con;
    }
}
