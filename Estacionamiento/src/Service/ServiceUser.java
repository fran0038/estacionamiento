/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Conexion.DatabaseConnection;
import Modelo.TipoUsuario;
import Modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author naxch
 */
public class ServiceUser {
    
    private final DatabaseConnection db;
    private Connection con;
    
    public ServiceUser(){
        db = new DatabaseConnection();
        con = db.getConnection();
        System.out.println("Te estoy observando (+).(+)");
    }
    
    public Usuario login(Usuario usuario) throws SQLException{
        Usuario user = null;
        try(PreparedStatement p = con.prepareStatement("SELECT * from usuario WHERE email=? and password=?")){
            p.setString(1,usuario.getEmail());
            p.setString(2,usuario.getPassword());
            try(ResultSet r = p.executeQuery()){
                if(r.next()){
                    user = new Usuario();
                    TipoUsuario tipoUsuario = new TipoUsuario();
                    user.setId( r.getInt(1) );
                    user.setNombre( r.getString(2) );
                    user.setEmail( r.getString(3) );
                    user.setPassword( r.getString(4) );
                    user.setEstado(r.getBoolean(5));
                    user.setTelefono(r.getString(6) );
                    tipoUsuario.setId(r.getByte(7));
                    user.setTipoUsuario(tipoUsuario);
                }
            }
        }catch(SQLException e){
            System.out.println("Error al traer usuarioLogin\n---> "+e);
        }
        return user;
    } 
    
}
