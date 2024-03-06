/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import database.Conector;
import entidades.Persona;

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
public class PersonaDAO implements CrudPaginadoInterface<Persona> {

    protected final Conector con;
    protected PreparedStatement ps;
    protected ResultSet rs;

    public PersonaDAO() {
        this.con = Conector.getInstance();
    }
    
  

    @Override
    public List<Persona> listar(String texto, int total, int pagina) {

        List<Persona> registros = new ArrayList<>();
        try {
            ps = con.conectar().prepareStatement(
                     "SELECT p.id, p.tipo_persona, p.nombre, p.tipo_documento, p.num_documento, p.direccion, p.telefono, p.email, p.activo FROM persona p WHERE p.nombre LIKE ? ORDER BY p.id ASC LIMIT ?,?");
            ps.setString(1, "%" + texto + "%");
            ps.setInt(2, total* (pagina - 1));
            ps.setInt(3, total );
            rs = ps.executeQuery();
            while (rs.next()) {
                 registros.add(
                         new Persona(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8), rs.getBoolean(9)
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
    
     public List<Persona> listar(String texto,int totalPorPagina,int numPagina, String tipoPersona) {
        List<Persona> registros=new ArrayList();
        try {
            ps=con.conectar().prepareStatement("SELECT p.id, p.tipo_persona, p.nombre, p.tipo_documento, p.num_documento, p.direccion, p.telefono, p.email, p.activo FROM persona p WHERE p.nombre LIKE ? AND tipo_persona=? ORDER BY p.id ASC LIMIT ?,?");
            ps.setString(1,"%" + texto +"%");
            ps.setString(2, tipoPersona);
            ps.setInt(3, (numPagina-1)*totalPorPagina);
            ps.setInt(4, totalPorPagina);
            rs=ps.executeQuery();
            while(rs.next()){
                registros.add(new Persona(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8), rs.getBoolean(9)));
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

    @Override
    public boolean insertar(Persona obj) {

        boolean inserto = false;
        try {
            ps = con.conectar().prepareStatement("INSERT INTO persona (tipo_persona,nombre,tipo_documento, num_documento, direccion, telefono, email, activo) VALUES (?,?,?,?,?,?,?,1)"
                    );
          ps.setString(1,obj.getTipoPersona());
            ps.setString(2, obj.getNombre());
            ps.setString(3, obj.getTipoDocumento());
            ps.setString(4, obj.getNumDocumento());
            ps.setString(5, obj.getDireccion());
            ps.setString(6, obj.getTelefono());
            ps.setString(7, obj.getEmail());
            
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
    public boolean actualizar(Persona obj) {
    
        boolean inserto = false;
        try {
            ps = con.conectar().prepareStatement("UPDATE persona SET tipo_persona=?, nombre=?, tipo_documento=?, num_documento=?, direccion=?, telefono=?, email=? WHERE id=?");
            ps.setString(1,obj.getTipoPersona());
            ps.setString(2, obj.getNombre());
            ps.setString(3, obj.getTipoDocumento());
            ps.setString(4, obj.getNumDocumento());
            ps.setString(5, obj.getDireccion());
            ps.setString(6, obj.getTelefono());
            ps.setString(7, obj.getEmail());
            ps.setInt(8, obj.getId());
            
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
            ps = con.conectar().prepareStatement("UPDATE persona SET activo=0 WHERE id=?");
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
            ps = con.conectar().prepareStatement("UPDATE persona SET activo=1 WHERE id=?");
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
            ps = con.conectar().prepareStatement("SELECT COUNT(*) AS total FROM  persona ");
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
 public int total(String tipoPersona) {

        int totalRegistros = 0;
        try {
            ps = con.conectar().prepareStatement("SELECT COUNT(*) AS total FROM  persona WHERE tipo_persona=?");
            ps.setString(1, tipoPersona);
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
            ps = con.conectar().prepareStatement("SELECT nombre FROM persona WHERE nombre=?");
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
