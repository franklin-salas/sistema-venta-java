/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import database.Conector;
import entidades.DetalleIngreso;
import entidades.DetalleVenta;
import entidades.Ingreso;
import entidades.Venta;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author frank
 */
public class VentaDAO implements CrudVentanterface<Venta, DetalleVenta> {

    private final Conector con;
    private PreparedStatement ps;
    private ResultSet rs;
    

    public VentaDAO() {
        this.con = Conector.getInstance();
    }

    @Override
    public List<Venta> listar(String texto, int totalPorPagina, int numPagina) {
        List<Venta> registros = new ArrayList<>();
        try {
            ps = con.conectar().prepareStatement("SELECT v.id,v.usuario_id,u.nombre as usuario_nombre,v.persona_id,p.nombre as persona_nombre,v.tipo_comprobante,v.serie_comprobante,v.num_comprobante,v.fecha,v.impuesto,v.total,v.estado"
                    + " FROM venta v INNER JOIN persona p ON v.persona_id=p.id INNER JOIN usuario u ON v.usuario_id=u.id "
                    + "WHERE v.num_comprobante LIKE ? ORDER BY v.id ASC LIMIT ?,?");
            ps.setString(1, "%" + texto + "%");
            ps.setInt(2, (numPagina - 1) * totalPorPagina);
            ps.setInt(3, totalPorPagina);
            rs = ps.executeQuery();
            while (rs.next()) {
                registros.add(
                       new Venta(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getDate(9),rs.getDouble(10),rs.getDouble(11),rs.getString(12))
                );
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            rs = null;
            con.desconectar();
        }
        return registros;
    }

    @Override
    public List<DetalleVenta> listarDetalle(int id) {
        List<DetalleVenta> registros = new ArrayList();
        try {
            ps = con.conectar().prepareStatement("SELECT a.id,a.codigo,a.nombre,a.stock,d.cantidad,d.precio,d.descuento,((d.cantidad*precio)-d.descuento) as sub_total "
                    + "FROM detalle_venta d INNER JOIN articulo a ON d.articulo_id=a.id "
                    + "WHERE d.venta_id=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                registros.add(
                 new DetalleVenta(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getDouble(6),rs.getDouble(7),rs.getDouble(8))       
                );
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            rs = null;
            con.desconectar();
        }
        return registros;
    }

    @Override
    public boolean insertar(Venta obj) {
        boolean inserto = false;
         Connection conectar = con.conectar();
        try {
           
            conectar.setAutoCommit(false);
            String sqlInsertVenta = "INSERT INTO venta (persona_id,usuario_id,fecha,tipo_comprobante,serie_comprobante,num_comprobante,impuesto,total,estado) VALUES (?,?,now(),?,?,?,?,?,?)";

            ps = conectar.prepareStatement(sqlInsertVenta, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1,obj.getPersonaId());
            ps.setInt(2, obj.getUsuarioId());
            ps.setString(3,obj.getTipoComprobante());
            ps.setString(4, obj.getSerieComprobante());
            ps.setString(5, obj.getNumComprobante());
            ps.setDouble(6, obj.getImpuesto());
            ps.setDouble(7, obj.getTotal());
            ps.setString(8, "Aceptado");

            int filasAfectadas = ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            int idGenerado = 0;
            if (rs.next()) {
                idGenerado = rs.getInt(1);
            }

            if (filasAfectadas == 1) {
                String sqlInsertDetalle = "INSERT INTO detalle_venta (venta_id,articulo_id,cantidad,precio,descuento) VALUES (?,?,?,?,?)";
                ps = conectar.prepareStatement(sqlInsertDetalle);
                for (DetalleVenta item : obj.getDetalles()) {
                      ps.setInt(1,idGenerado);
                    ps.setInt(2,item.getArticuloId());
                    ps.setInt(3, item.getCantidad());
                    ps.setDouble(4, item.getPrecio());
                    ps.setDouble(5, item.getDescuento());
                    inserto=ps.executeUpdate()>0;
                }
                conectar.commit();
            } else {
               conectar.rollback();
            }
            rs.close();
            ps.close();

        } catch (SQLException e) {
            try {
                if (conectar != null) {
                   conectar.rollback();
                }
                JOptionPane.showMessageDialog(null, e.getMessage());
            } catch (SQLException ex) {
                Logger.getLogger(VentaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {

            rs = null;
            ps = null;
              con.desconectar();
           conectar = null;

        }
        return inserto;
    }

    @Override
    public boolean anular(int id) {
    
        boolean resp=false;
        try {
            ps= con.conectar().prepareStatement("UPDATE venta SET estado='Anulado' WHERE id=?");
            ps.setInt(1, id);
            if (ps.executeUpdate()>0){
                resp=true;
            }
            ps.close();
        }  catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally{
            ps=null;
            con.desconectar();
        }
        return resp;
    }

    @Override
    public int total() {
     int totalRegistros=0;
        try {
            ps=con.conectar().prepareStatement("SELECT COUNT(id) FROM venta");            
            rs=ps.executeQuery();
            
            while(rs.next()){
                totalRegistros=rs.getInt("COUNT(id)");
            }            
            ps.close();
            rs.close();
        }  catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally{
            ps=null;
            rs=null;
            con.desconectar();
        }
        return totalRegistros;
    }

    @Override
    public boolean existe(String texto1, String texto2) {
    
        boolean resp=false;
        try {
            ps= con.conectar().prepareStatement("SELECT id FROM venta WHERE serie_comprobante=? AND num_comprobante=?");
            ps.setString(1, texto1);
            ps.setString(2, texto2);
            rs=ps.executeQuery();
            if(rs.next()){
                if(rs.getRow()>0){
                resp=true;
            }           
            }
            
            ps.close();
            rs.close();
        }  catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally{
            ps=null;
            rs=null;
            con.desconectar();
        }
        return resp;
    }
    
    public String ultimoSerie(String tipoComprobante) {
        String serieComprobante="";
        try {
            ps=con.conectar().prepareStatement("SELECT serie_comprobante FROM venta where tipo_comprobante=? order by serie_comprobante desc limit 1");            
            ps.setString(1, tipoComprobante);
            rs=ps.executeQuery();
            
            while(rs.next()){
                serieComprobante=rs.getString("serie_comprobante");
            }            
            ps.close();
            rs.close();
        }  catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally{
            ps=null;
            rs=null;
            con.desconectar();
        }
        return serieComprobante;
    }
public String ultimoNumero(String tipoComprobante,String serieComprobante) {
        String numComprobante="";
        try {
            ps=con.conectar().prepareStatement("SELECT num_comprobante FROM venta WHERE tipo_comprobante=? AND serie_comprobante=? order by num_comprobante desc limit 1");            
            ps.setString(1, tipoComprobante);
            ps.setString(2, serieComprobante);
            rs=ps.executeQuery();
            
            while(rs.next()){
                numComprobante=rs.getString("num_comprobante");
            }            
            ps.close();
            rs.close();
        }  catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally{
            ps=null;
            rs=null;
            con.desconectar();
        }
        return numComprobante;
    }

public List<Venta> consultaFechas(Date fechaInicio, Date fechaFin, int totalPorPagina, int numPagina) {
        List<Venta> registros=new ArrayList();
        try {
            ps=con.conectar().prepareStatement("SELECT v.id,v.usuario_id,u.nombre as usuario_nombre,v.persona_id,p.nombre as persona_nombre,v.tipo_comprobante,v.serie_comprobante,v.num_comprobante,v.fecha,v.impuesto,v.total,v.estado "
                    + "FROM venta v INNER JOIN persona p ON v.persona_id=p.id INNER JOIN usuario u ON v.usuario_id=u.id"
                    + " WHERE v.fecha>=? AND v.fecha<=?  LIMIT ?,?");
            ps.setDate(1,fechaInicio);            
            ps.setDate(2,fechaFin);
             ps.setInt(3, (numPagina - 1) * totalPorPagina);
            ps.setInt(4, totalPorPagina);
            rs=ps.executeQuery();
            while(rs.next()){
                registros.add(new Venta(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getDate(9),rs.getDouble(10),rs.getDouble(11),rs.getString(12)));
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
}
