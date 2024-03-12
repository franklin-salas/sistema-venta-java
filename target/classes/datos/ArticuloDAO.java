/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import database.Conector;
import entidades.Articulo;

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
public class ArticuloDAO implements CrudPaginadoInterface<Articulo> {

    private final Conector con;
    private PreparedStatement ps;
    private ResultSet rs;

    public ArticuloDAO() {
        this.con = Conector.getInstance();
    }
    
  

    @Override
    public List<Articulo> listar(String texto, int total, int pagina) {

        List<Articulo> registros = new ArrayList<>();
        try {
            ps = con.conectar().prepareStatement(
                     "SELECT a.id,a.categoria_id, c.nombre as categoria_nombre, a.codigo, a.nombre, a.precio_venta, a.stock, a.descripcion, a.imagen, a.activo "
                    + "FROM articulo a inner join categoria c ON a.categoria_id=c.id "
                    + "WHERE a.nombre LIKE ? ORDER BY a.id ASC LIMIT ?,?");
            ps.setString(1, "%" + texto + "%");
            ps.setInt(2, total* (pagina - 1));
            ps.setInt(3, total );
            rs = ps.executeQuery();
            while (rs.next()) {
                //System.out.println( rs.getString(5));
          
                 
                 registros.add( new Articulo(
                rs.getInt(1),rs.getInt(2),
                        rs.getString(3),rs.getString(4),rs.getString(5),
                        rs.getDouble(6),rs.getInt(7),rs.getString(8),rs.getString(9),rs.getBoolean(10)
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
    public List<Articulo> listarArticuloVenta(String texto, int total, int pagina) {

        List<Articulo> registros = new ArrayList<>();
        try {
            ps = con.conectar().prepareStatement(
                     "SELECT a.id,a.categoria_id, c.nombre as categoria_nombre, a.codigo, a.nombre, a.precio_venta, a.stock, a.descripcion, a.imagen, a.activo "
                    + "FROM articulo a inner join categoria c ON a.categoria_id=c.id "
                    + "WHERE a.nombre LIKE ? AND a.stock>0 AND a.activo=true ORDER BY a.id ASC LIMIT ?,?");
            ps.setString(1, "%" + texto + "%");
            ps.setInt(2, total* (pagina - 1));
            ps.setInt(3, total );
            rs = ps.executeQuery();
            while (rs.next()) {
                //System.out.println( rs.getString(5));
          
                 
                 registros.add( new Articulo(
                rs.getInt(1),rs.getInt(2),
                        rs.getString(3),rs.getString(4),rs.getString(5),
                        rs.getDouble(6),rs.getInt(7),rs.getString(8),rs.getString(9),rs.getBoolean(10)
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
    public boolean insertar(Articulo obj) {

        boolean inserto = false;
        try {
            ps = con.conectar().prepareStatement("INSERT INTO articulo"
                    + " (categoria_id,codigo,nombre,precio_venta,stock,descripcion,imagen,activo)"
                    + " VALUES (?,?,?,?,?,?,?,1)");
             ps.setInt(1,obj.getCategoriaId());
            ps.setString(2, obj.getCodigo());
            ps.setString(3, obj.getNombre());
            ps.setDouble(4, obj.getPrecio());
            ps.setInt(5, obj.getStock());
            ps.setString(6, obj.getDescripcion());
            ps.setString(7, obj.getImagen());
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
    public boolean actualizar(Articulo obj) {
    
        boolean inserto = false;
        try {
            ps = con.conectar().prepareStatement("UPDATE articulo "
                    + "SET categoria_id=?, codigo=?, nombre=?, precio_venta=?, stock=?, descripcion=?, imagen=? WHERE id=?");
           ps.setInt(1,obj.getCategoriaId());
            ps.setString(2, obj.getCodigo());
            ps.setString(3, obj.getNombre());
            ps.setDouble(4, obj.getPrecio());
            ps.setInt(5, obj.getStock());
            ps.setString(6, obj.getDescripcion());
            ps.setString(7, obj.getImagen());
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
            ps = con.conectar().prepareStatement("UPDATE articulo SET activo=0 WHERE id=?");
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
            ps = con.conectar().prepareStatement("UPDATE articulo SET activo=1 WHERE id=?");
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
            ps = con.conectar().prepareStatement("SELECT COUNT(*) AS total FROM  articulo ");
            rs = ps.executeQuery();
            if (rs.next()) {
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
            ps = con.conectar().prepareStatement("SELECT nombre FROM  articulo WHERE  nombre=?");
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

    public Articulo obtenerArticuloCodigoIngreso(String codigo) {
        Articulo art=null;
        try {
            ps= con.conectar().prepareStatement("SELECT id,codigo,nombre,precio_venta,stock FROM articulo WHERE codigo=?");
            ps.setString(1,codigo);
            rs=ps.executeQuery();
            
            if (rs.next()){
                art=new Articulo(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getInt(5));
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
        return art;
    }
  //obtenerArticuloCodigoVenta
  
        public Articulo obtenerArticuloCodigoVenta(String codigo) {
        Articulo art=null;
        try {
            ps= con.conectar().prepareStatement("SELECT id,codigo,nombre,precio_venta,stock FROM articulo WHERE codigo=?  AND stock > 0 activo=true");
            ps.setString(1,codigo);
            rs=ps.executeQuery();
            
            if (rs.next()){
                art=new Articulo(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getInt(5));
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
        return art;
    }
}
