/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import datos.ArticuloDAO;
import datos.IngresoDAO;
import datos.VentaDAO;
import entidades.Articulo;
import entidades.DetalleIngreso;
import entidades.DetalleVenta;
import entidades.Ingreso;
import entidades.Venta;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lanister
 */
public class VentaControl {

    private final ArticuloDAO datosArt;
    private final VentaDAO datos;
    private final Venta obj;
    private DefaultTableModel modeloTabla;
    public int registrosMostrados;

    public VentaControl() {
        this.datos = new VentaDAO();
        this.datosArt = new ArticuloDAO();
        this.obj = new Venta();
        this.registrosMostrados = 0;
    }

    public DefaultTableModel listar(String texto, int total, int pagina) {
        List<Venta> lista = new ArrayList<>();
        lista.addAll(datos.listar(texto, total, pagina));

        String[] titulos = {"Id", "Usuario ID", "Usuario", "Cliente ID", "Cliente", "Tipo Comprobante", "Serie", "Número", "Fecha", "Impuesto", "Total", "Estado"};
        this.modeloTabla = new DefaultTableModel(null, titulos) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer que todas las celdas no sean editables
            }
        };

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        String[] registro = new String[12];

        this.registrosMostrados = 0;
        for (Venta item : lista) {
            registro[0] = Integer.toString(item.getId());
            registro[1] = Integer.toString(item.getUsuarioId());
            registro[2] = item.getUsuarioNombre();
            registro[3] = Integer.toString(item.getPersonaId());
            registro[4] = item.getPersonaNombre();
            registro[5] = item.getTipoComprobante();
            registro[6] = item.getSerieComprobante();
            registro[7] = item.getNumComprobante();
            registro[8] = sdf.format(item.getFecha());
            registro[9] = Double.toString(item.getImpuesto());
            registro[10] = Double.toString(item.getTotal());
            registro[11] = item.getEstado();

            this.modeloTabla.addRow(registro);
            this.registrosMostrados = this.registrosMostrados + 1;
        }
        return this.modeloTabla;
    }

    public DefaultTableModel listarDetalle(int id) {
        List<DetalleVenta> lista = new ArrayList<>();
        lista.addAll(datos.listarDetalle(id));

        String[] titulos = {"ID", "CODIGO", "ARTICULO", "STOCK", "CANTIDAD", "PRECIO", "DESCUENTO", "SUBTOTAL"};

        this.modeloTabla = new DefaultTableModel(null, titulos) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer que todas las celdas no sean editables
            }
        };
        String[] registro = new String[6];
        for (DetalleVenta item : lista) {
            registro[0] = Integer.toString(item.getArticuloId());
            registro[1] = item.getArticuloCodigo();
            registro[2] = item.getArticuloNombre();
            registro[3] = Integer.toString(item.getArticuloStock());
            registro[4] = Integer.toString(item.getCantidad());
            registro[5] = Double.toString(item.getPrecio());
            registro[6] = Double.toString(item.getDescuento());
            registro[7] = Double.toString(item.getSubTotal());

            this.modeloTabla.addRow(registro);

        }
        return this.modeloTabla;
    }

    public Articulo obtenerArticuloCodigoVenta(String codigo) {
        Articulo art = datosArt.obtenerArticuloCodigoVenta(codigo);
        return art;
    }

    public String insertar(int personaId, String tipoComprobante, String serieComprobante, String numComprobante, double impuesto, double total, DefaultTableModel modeloDetalles) {
        if (datos.existe(serieComprobante, numComprobante)) {
            return "El registro ya existe.";
        } else {

            obj.setUsuarioId(Session.usuarioId);
            obj.setPersonaId(personaId);
            obj.setTipoComprobante(tipoComprobante);
            obj.setSerieComprobante(serieComprobante);
            obj.setNumComprobante(numComprobante);
            obj.setImpuesto(impuesto);
            obj.setTotal(total);

            List<DetalleVenta> detalles = new ArrayList();
            int articuloId;
            int cantidad;
            double precio;
            double descuento;
            for (int i = 0; i < modeloDetalles.getRowCount(); i++) {
                articuloId = Integer.parseInt(String.valueOf(modeloDetalles.getValueAt(i, 0)));
                cantidad = Integer.parseInt(String.valueOf(modeloDetalles.getValueAt(i, 4)));
                precio = Double.parseDouble(String.valueOf(modeloDetalles.getValueAt(i, 5)));
                descuento = Double.parseDouble(String.valueOf(modeloDetalles.getValueAt(i, 6)));
                detalles.add(new DetalleVenta(articuloId, cantidad, precio, descuento));
            }

            obj.setDetalles(detalles);

            if (datos.insertar(obj)) {
                return "OK";
            } else {
                return "Error en el registro.";
            }
        }
    }

    public String anular(int id) {
        if (datos.anular(id)) {
            return "OK";
        } else {
            return "No se puede anular el registro";
        }
    }

    public int total() {
        return datos.total();
    }

    public int totalMostrados() {
        return this.registrosMostrados;
    }
public String ultimoSerie(String tipoComprobante) {
        return this.datos.ultimoSerie(tipoComprobante);
    }
public String ultimoNumero(String tipoComprobante,String serieComprobante) {
        return this.datos.ultimoNumero(tipoComprobante, serieComprobante);
    }

public DefaultTableModel consultaFechas(Date fechaInicio, Date fechaFin, int totalPorPagina, int numPagina){
        List<Venta> lista=new ArrayList();
        lista.addAll(datos.consultaFechas(fechaInicio,fechaFin, totalPorPagina, numPagina));
        
        String[] titulos={"Id","Usuario ID","Usuario","Cliente ID","Cliente","Tipo Comprobante","Serie","Número","Fecha","Impuesto","Total","Estado"};
        this.modeloTabla=new DefaultTableModel(null,titulos);        
        
        String[] registro = new String[12];
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
        
        this.registrosMostrados=0;
        for (Venta item:lista){
            registro[0]=Integer.toString(item.getId());
            registro[1]=Integer.toString(item.getUsuarioId());
            registro[2]=item.getUsuarioNombre();
            registro[3]=Integer.toString(item.getPersonaId());
            registro[4]=item.getPersonaNombre();
            registro[5]=item.getTipoComprobante();
            registro[6]=item.getSerieComprobante();
            registro[7]=item.getNumComprobante();
            registro[8]=sdf.format(item.getFecha());
            registro[9]=Double.toString(item.getImpuesto());
            registro[10]=Double.toString(item.getTotal());
            registro[11]=item.getEstado();
            
            this.modeloTabla.addRow(registro);
            this.registrosMostrados=this.registrosMostrados+1;
        }
        return this.modeloTabla;
    }
}
