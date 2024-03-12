/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import database.Conector;
import entidades.DetalleIngreso;
import entidades.Ingreso;
import java.sql.Connection;
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
public class IngresoDAO implements CrudIngresoInterface<Ingreso, DetalleIngreso> {

    private final Conector con;
    private PreparedStatement ps;
    private ResultSet rs;
    

    public IngresoDAO() {
        this.con = Conector.getInstance();
    }

    @Override
    public List<Ingreso> listar(String texto, int totalPorPagina, int numPagina) {
        List<Ingreso> registros = new ArrayList<>();
        try {
            ps = con.conectar().prepareStatement("SELECT i.id,i.usuario_id,u.nombre as usuario_nombre,i.persona_id,p.nombre as persona_nombre,i.tipo_comprobante,i.serie_comprobante,i.num_comprobante,i.fecha,i.impuesto,i.total,i.estado "
                    + "FROM ingreso i INNER JOIN persona p ON i.persona_id=p.id INNER JOIN usuario u ON i.usuario_id=u.id "
                    + "WHERE i.num_comprobante LIKE ? ORDER BY i.id ASC LIMIT ?,?");
            ps.setString(1, "%" + texto + "%");
            ps.setInt(2, (numPagina - 1) * totalPorPagina);
            ps.setInt(3, totalPorPagina);
            rs = ps.executeQuery();
            while (rs.next()) {
                registros.add(new Ingreso(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getDate(9), rs.getDouble(10), rs.getDouble(11), rs.getString(12)));
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
    public List<DetalleIngreso> listarDetalle(int id) {
        List<DetalleIngreso> registros = new ArrayList();
        try {
            ps = con.conectar().prepareStatement("SELECT a.id,a.codigo,a.nombre,d.cantidad,d.precio,(d.cantidad*precio) as sub_total "
                    + "FROM detalle_ingreso d INNER JOIN articulo a ON d.articulo_id=a.id "
                    + "WHERE d.ingreso_id=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                registros.add(new DetalleIngreso(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getDouble(5), rs.getDouble(6)));
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
    public boolean insertar(Ingreso obj) {
        boolean inserto = false;
         Connection conectar = con.conectar();
        try {
           
            conectar.setAutoCommit(false);
            String sqlInsertIngreso = "INSERT INTO ingreso (persona_id,usuario_id,fecha,tipo_comprobante,serie_comprobante,num_comprobante,impuesto,total,estado) VALUES (?,?,now(),?,?,?,?,?,?)";

            ps = conectar.prepareStatement(sqlInsertIngreso, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, obj.getPersonaId());
            ps.setInt(2, obj.getUsuarioId());
            ps.setString(3, obj.getTipoComprobante());
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
                String sqlInsertDetalle = "INSERT INTO detalle_ingreso (ingreso_id,articulo_id,cantidad,precio) VALUES (?,?,?,?)";
                ps = conectar.prepareStatement(sqlInsertDetalle);
                for (DetalleIngreso item : obj.getDetalles()) {
                    ps.setInt(1, idGenerado);
                    ps.setInt(2, item.getArticuloId());
                    ps.setInt(3, item.getCantidad());
                    ps.setDouble(4, item.getPrecio());
                    inserto = ps.executeUpdate() > 0;
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
                Logger.getLogger(IngresoDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            ps= con.conectar().prepareStatement("UPDATE ingreso SET estado='Anulado' WHERE id=?");
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
            ps=con.conectar().prepareStatement("SELECT COUNT(id) FROM ingreso");            
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
            ps= con.conectar().prepareStatement("SELECT id FROM ingreso WHERE serie_comprobante=? AND num_comprobante=?");
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

}
