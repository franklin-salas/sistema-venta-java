/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import database.Conector;

import entidades.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

/**
 *
 * @author lanister
 */
public class UsuarioDAO implements CrudPaginadoInterface<Usuario> {

    private final Conector con;
    private PreparedStatement ps;
    private ResultSet rs;

    public UsuarioDAO() {
        this.con = Conector.getInstance();
    }
    
  

    @Override
    public List<Usuario> listar(String texto, int total, int pagina) {

        List<Usuario> registros = new ArrayList<>();
        try {
            ps = con.conectar().prepareStatement(
                     "SELECT u.id, u.rol_id, r.nombre as rol_nombre, u.nombre, u.tipo_documento, u.num_documento, u.direccion, u.telefono, u.email, u.clave, u.activo"
                             + " FROM usuario u inner join rol r ON u.rol_id=r.id"
                             + " WHERE u.nombre LIKE ? ORDER BY u.id ASC LIMIT ?,?");
            ps.setString(1, "%" + texto + "%");
            ps.setInt(2, total* (pagina - 1));
            ps.setInt(3, total );
            rs = ps.executeQuery();
            while (rs.next()) {
                //System.out.println( rs.getString(5));
          
                 
                 registros.add( new Usuario(
                         rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getBoolean(11)
                )
                );
                
                
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

    @Override
    public boolean insertar(Usuario obj) {

        boolean inserto = false;
        try {
            ps = con.conectar().prepareStatement("INSERT INTO usuario (rol_id,nombre,tipo_documento, num_documento, direccion, telefono, email, clave, activo) VALUES (?,?,?,?,?,?,?,?,1)");
           ps.setInt(1,obj.getRolId());
            ps.setString(2, obj.getNombre());
            ps.setString(3, obj.getTipoDocumento());
            ps.setString(4, obj.getNumDocumento());
            ps.setString(5, obj.getDireccion());
            ps.setString(6, obj.getTelefono());
            ps.setString(7, obj.getEmail());
            ps.setString(8, obj.getClave());
            
            if(ps.executeUpdate() > 0) {
                inserto = true;
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            con.desconectar();
        }

        return inserto;

    }

    @Override
    public boolean actualizar(Usuario obj) {
    
        boolean inserto = false;
        try {
            ps = con.conectar().prepareStatement("UPDATE usuario SET rol_id=?, nombre=?, tipo_documento=?, num_documento=?, direccion=?, telefono=?, email=?, clave=? WHERE id=?");
           ps.setInt(1,obj.getRolId());
            ps.setString(2, obj.getNombre());
            ps.setString(3, obj.getTipoDocumento());
            ps.setString(4, obj.getNumDocumento());
            ps.setString(5, obj.getDireccion());
            ps.setString(6, obj.getTelefono());
            ps.setString(7, obj.getEmail());
            ps.setString(8, obj.getClave());
            ps.setInt(9, obj.getId());
            
            if (ps.executeUpdate() > 0) {
                inserto = true;
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            con.desconectar();
        }
        return inserto;
    }

    @Override
    public boolean desactivar(int id) {
    
        boolean inserto = false;
        try {
            ps = con.conectar().prepareStatement("UPDATE usuario SET activo=0 WHERE id=?");
            ps.setInt(1, id);
            if(ps.executeUpdate() > 0) {
                inserto = true;
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            con.desconectar();
        }
        return inserto;
    
    }

    @Override
    public boolean activar(int id) {
    
        boolean inserto = false;
        try {
            ps = con.conectar().prepareStatement("UPDATE usuario SET activo=1 WHERE id=?");
            ps.setInt(1, id);
            if(ps.executeUpdate() > 0) {
                inserto = true;
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            con.desconectar();
        }
        return inserto;
    }

    @Override
    public int total() {

        int totalRegistros = 0;
        try {
            ps = con.conectar().prepareStatement("SELECT COUNT(*) AS total FROM  usuario ");
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

    @Override
    public boolean existe(String texto) {
        
        boolean existe = false;
        try {
            ps = con.conectar().prepareStatement("SELECT email FROM usuario WHERE email=?");
            ps.setString(1,texto);
            rs = ps.executeQuery();
            if (rs.next()) {
                 if(rs.getRow()>0) {
                existe =  true;
            }
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
        return existe;

    }

  
}
