/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import database.Conector;
import entidades.Categoria;
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
public class CategoriaDAO implements CrudSimpleInterface<Categoria> {

    private final Conector con;
    private PreparedStatement ps;
    private ResultSet rs;

    public CategoriaDAO() {
        this.con = Conector.getInstance();
    }

        public List<Categoria> seleccionar() {
        List<Categoria> registros=new ArrayList();
        try {
            ps= con.conectar().prepareStatement("SELECT id, nombre FROM categoria ORDER BY nombre asc");
            rs=ps.executeQuery();
            while(rs.next()){
                registros.add(new Categoria(rs.getInt(1),rs.getString(2)));
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
    public List<Categoria> listar(String texto) {

        List<Categoria> registros = new ArrayList<>();
        try {
            ps = con.conectar().prepareStatement("SELECT * FROM categoria WHERE nombre Like ?");
            ps.setString(1, "%" + texto + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                registros.add(new Categoria(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getBoolean(4)));
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
    public boolean insertar(Categoria obj) {

        boolean inserto = false;
        try {
            ps = con.conectar().prepareStatement("INSERT INTO categoria (nombre,descripcion) VALUES(?,?)");
            ps.setString(1, obj.getNombre());
            ps.setString(2, obj.getDescripcion());
            if(ps.executeUpdate() > 0) {
                inserto = true;
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage() +"ddddd");
        } finally {
            ps = null;
            con.desconectar();
        }

        return inserto;

    }

    @Override
    public boolean actualizar(Categoria obj) {
    
        boolean inserto = false;
        try {
            ps = con.conectar().prepareStatement("UPDATE categoria SET nombre=?, descripcion=? WHERE id=?");
            ps.setString(1, obj.getNombre());
            ps.setString(2, obj.getDescripcion());
            ps.setInt(3, obj.getId());
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
            ps = con.conectar().prepareStatement("UPDATE categoria SET activo=0 WHERE id=?");
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
            ps = con.conectar().prepareStatement("UPDATE categoria SET activo=1 WHERE id=?");
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
            ps = con.conectar().prepareStatement("SELECT COUNT(*) AS total FROM  categoria ");
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
            ps = con.conectar().prepareStatement("SELECT nombre FROM  categoria WHERE  nombre=?");
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
