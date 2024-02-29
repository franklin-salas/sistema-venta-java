/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import database.Conector;
import entidades.Rol;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author frank
 */
public class RolDAO {
    
    
    private final Conector con;
    private PreparedStatement ps;
    private ResultSet rs;

    public RolDAO() {
        this.con = Conector.getInstance();
    }

          public List<Rol> seleccionar() {
        List<Rol> registros=new ArrayList();
        try {
            ps= con.conectar().prepareStatement("SELECT id, nombre FROM rol ORDER BY nombre asc");
            rs=ps.executeQuery();
            while(rs.next()){
                registros.add(new Rol(rs.getInt(1),rs.getString(2)));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally{
            ps=null;
            rs=null;
            con.desconectar();
        }
        return registros;
    }

 
    public List<Rol> listar() {

        List<Rol> registros = new ArrayList<>();
        try {
            ps = con.conectar().prepareStatement("SELECT * FROM rol ");
            
            rs = ps.executeQuery();
            while (rs.next()) {
                registros.add(new Rol(rs.getInt(1), rs.getString(2),
                        rs.getString(3)));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            rs = null;
            ps = null;
            con.desconectar();
        }

        return registros;
    }


    public int total() {

        int totalRegistros = 0;
        try {
            ps = con.conectar().prepareStatement("SELECT COUNT(*) AS total FROM  rol ");
            rs = ps.executeQuery();
            while (rs.next()) {
                totalRegistros = rs.getInt("total");
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            rs= null;
            ps = null;
            con.desconectar();
        }
        return totalRegistros;
    }

   
}
