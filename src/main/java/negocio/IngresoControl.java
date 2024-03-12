/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import datos.ArticuloDAO;
import datos.CategoriaDAO;
import datos.IngresoDAO;
import entidades.Articulo;
import entidades.Categoria;
import entidades.DetalleIngreso;
import entidades.Ingreso;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lanister
 */
public class IngresoControl {
  private final ArticuloDAO datosArt;
  private final IngresoDAO datos;
    private final Ingreso obj;
    private DefaultTableModel modeloTabla;
    public int registrosMostrados;
    
    public IngresoControl(){
        this.datos=new IngresoDAO();
        this.datosArt=new ArticuloDAO();
        this.obj=new Ingreso();
        this.registrosMostrados=0;
    }
  /*     public DefaultComboBoxModel seleccionar(){
        DefaultComboBoxModel<Categoria> items = new DefaultComboBoxModel();
        List<Categoria> lista=new ArrayList();
        lista=datosCategoria.seleccionar();
        for (Categoria item: lista){
            items.addElement(new Categoria(item.getId(),item.getNombre()));
        }
        return items;
    }*/
    public DefaultTableModel listar(String texto,int total, int pagina ){
        List<Ingreso> lista=new ArrayList<>();
        lista.addAll(datos.listar(texto,total,pagina));
        
         String[] titulos={"Id","Usuario ID","Usuario","Proveedor ID","Proveedor","Tipo Comprobante","Serie","Número","Fecha","Impuesto","Total","Estado"};
            
        this.modeloTabla=new DefaultTableModel(null,titulos){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // Hacer que todas las celdas no sean editables
                }
            };
  
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
 
        String[] registro = new String[12];
        
        this.registrosMostrados=0;
        for (Ingreso item:lista){
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
    
    public DefaultTableModel listarDetalle(int id){
        List<DetalleIngreso> lista=new ArrayList<>();
        lista.addAll(datos.listarDetalle(id));
        
     String[] titulos={"ID","CODIGO","ARTICULO","CANTIDAD","PRECIO","SUBTOTAL"};
            
        this.modeloTabla=new DefaultTableModel(null,titulos){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // Hacer que todas las celdas no sean editables
                }
            };
        String[] registro = new String[6];
        for (DetalleIngreso item:lista){
             registro[0]=Integer.toString(item.getArticuloId());
            registro[1]=item.getArticuloCodigo();
            registro[2]=item.getArticuloNombre();
            registro[3]=Integer.toString(item.getCantidad());
            registro[4]=Double.toString(item.getPrecio());
            registro[5]=Double.toString(item.getSubTotal());    
           
            this.modeloTabla.addRow(registro);
      
        }
        return this.modeloTabla;
    }
    public Articulo obtenerArticuloCodigoIngreso(String codigo){
        Articulo art= datosArt.obtenerArticuloCodigoIngreso(codigo);
        return art;
    }
    public String insertar(int personaId, String tipoComprobante, String serieComprobante, String numComprobante, double impuesto, double total,DefaultTableModel modeloDetalles){
        if (datos.existe(serieComprobante,numComprobante)){
            return "El registro ya existe.";
        }else{
            obj.setUsuarioId(Session.usuarioId);
            obj.setPersonaId(personaId);
            obj.setTipoComprobante(tipoComprobante);
            obj.setSerieComprobante(serieComprobante);
            obj.setNumComprobante(numComprobante);
            obj.setImpuesto(impuesto);
            obj.setTotal(total);
            
            List<DetalleIngreso> detalles = new ArrayList();
            int articuloId;
            int cantidad;
            double precio;
            
            for (int i=0;i<modeloDetalles.getRowCount();i++){
                articuloId=Integer.parseInt(String.valueOf(modeloDetalles.getValueAt(i, 0)));
                cantidad=Integer.parseInt(String.valueOf(modeloDetalles.getValueAt(i, 3)));
                precio=Double.parseDouble(String.valueOf(modeloDetalles.getValueAt(i, 4)));
                detalles.add(new DetalleIngreso(articuloId,cantidad,precio));
            }
            
            obj.setDetalles(detalles);
            
            if (datos.insertar(obj)){
                return "OK";
            }else{
                return "Error en el registro.";
            }
        }
    }

 
    
    public String anular(int id){
        if (datos.anular(id)){
            return "OK";
        }else{
            return "No se puede anular el registro";
        }
    }
    
    public int total(){
        return datos.total();
    }
    
    public int totalMostrados(){
        return this.registrosMostrados;
    }

 

    
}
