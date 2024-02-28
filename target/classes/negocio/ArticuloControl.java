/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import datos.ArticuloDAO;
import datos.CategoriaDAO;
import entidades.Articulo;
import entidades.Categoria;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lanister
 */
public class ArticuloControl {
  private final ArticuloDAO datos;
  private final CategoriaDAO datosCategoria;
    private  Articulo obj;
    private DefaultTableModel modeloTabla;
    public int registrosMostrados;
    
    public ArticuloControl(){
        this.datos=new ArticuloDAO();
        this.datosCategoria=new CategoriaDAO();
        this.obj=new Articulo();
        this.registrosMostrados=0;
    }
       public DefaultComboBoxModel seleccionar(){
        DefaultComboBoxModel<Categoria> items = new DefaultComboBoxModel();
        List<Categoria> lista=new ArrayList();
        lista=datosCategoria.seleccionar();
        for (Categoria item: lista){
            items.addElement(new Categoria(item.getId(),item.getNombre()));
        }
        return items;
    }
    public DefaultTableModel listar(String texto,int total, int pagina ){
        List<Articulo> lista=new ArrayList<>();
        lista.addAll(datos.listar(texto,total,pagina));
        
        String[] titulos={"Id","Categoria ID","Categoria","Código","Nombre","Precio","Stock","Descripción","Imagen","Estado"};
            
        this.modeloTabla=new DefaultTableModel(null,titulos){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // Hacer que todas las celdas no sean editables
                }
            };
        String estado;
        String[] registro = new String[10];
        
        this.registrosMostrados=0;
        for (Articulo item:lista){
            if (item.isActivo()){
                estado="Activo";
            } else{
                estado="Inactivo";
            }
          
            
             registro[0]=Integer.toString(item.getId());
            registro[1]=Integer.toString(item.getCategoriaId());
            registro[2]=item.getCategoriaNombre();
            registro[3]=item.getCodigo();
            registro[4]=item.getNombre();
            registro[5]=Double.toString(item.getPrecio());
            registro[6]=Integer.toString(item.getStock());
            registro[7]=item.getDescripcion();
            registro[8]=item.getImagen();
            registro[9]=estado;
           
            this.modeloTabla.addRow(registro);
            this.registrosMostrados=this.registrosMostrados+1;
        }
        return this.modeloTabla;
    }
    
    public String insertar(int categoriaId, String codigo, String nombre, double precio, int stock,String descripcion, String imagen){
       
        if (datos.existe(nombre)){
            return "El registro ya existe.";
        }else{
            obj.setCategoriaId(categoriaId);
            obj.setCodigo(codigo);
            obj.setNombre(nombre);
            obj.setPrecio(precio);
            obj.setStock(stock);
            obj.setDescripcion(descripcion);
            obj.setImagen(imagen);

            if (datos.insertar(obj)){
                return "OK";
            }else{
                return "Error en el registro.";
            }
        }
    }
    
    public String actualizar(int id,String nombre, String nombreAnt,int categoriaId, String codigo,  double precio, int stock,String descripcion, String imagen){
        if (nombre.equals(nombreAnt)){
            obj.setId(id);
            obj.setCategoriaId(categoriaId);
            obj.setCodigo(codigo);
            obj.setNombre(nombre);
            obj.setPrecio(precio);
            obj.setStock(stock);
            obj.setDescripcion(descripcion);
            obj.setImagen(imagen);
            if(datos.actualizar(obj)){
                return "OK";
            }else{
                return "Error en la actualización.";
            }
        }else{
            if (datos.existe(nombre)){
                return "El registro ya existe.";
            }else{
                 obj.setId(id);
                obj.setCategoriaId(categoriaId);
                obj.setCodigo(codigo);
                obj.setNombre(nombre);
                obj.setPrecio(precio);
                obj.setStock(stock);
                obj.setDescripcion(descripcion);
                obj.setImagen(imagen);

                if (datos.actualizar(obj)){
                    return "OK";
                }else{
                    return "Error en la actualización.";
                }
            }
        }
    }
    
    public String desactivar(int id){
        if (datos.desactivar(id)){
            return "OK";
        }else{
            return "No se puede desactivar el registro";
        }
    }
    
    public String activar(int id){
        if (datos.activar(id)){
            return "OK";
        }else{
            return "No se puede activar el registro";
        }
    }
    
    public int total(){
        return datos.total();
    }
    
    public int totalMostrados(){
        return this.registrosMostrados;
    }

 

    
}
