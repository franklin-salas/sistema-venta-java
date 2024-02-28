/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import datos.CategoriaDAO;
import entidades.Categoria;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lanister
 */
public class CategoriaControl {
  private final CategoriaDAO datos;
    private Categoria obj;
    private DefaultTableModel modeloTabla;
    public int registrosMostrados;
    
    public CategoriaControl(){
        this.datos=new CategoriaDAO();
        this.obj=new Categoria();
        this.registrosMostrados=0;
    }
    
    public DefaultTableModel listar(String texto){
        List<Categoria> lista=new ArrayList<>();
        lista.addAll(datos.listar(texto));
        
        String[] titulos={"Id","Nombre","Descripción","Estado"};
        this.modeloTabla=new DefaultTableModel(null,titulos){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // Hacer que todas las celdas no sean editables
                }
            };
        String estado;
        String[] registro = new String[4];
        
        this.registrosMostrados=0;
        for (Categoria item:lista){
            if (item.isActivo()){
                estado="Activo";
            } else{
                estado="Inactivo";
            }
            registro[0]=Integer.toString(item.getId());
            registro[1]=item.getNombre();
            registro[2]=item.getDescripcion();
            registro[3]=estado;
            this.modeloTabla.addRow(registro);
            this.registrosMostrados=this.registrosMostrados+1;
        }
        return this.modeloTabla;
    }
    
    public String insertar(String nombre,String descripcion){
        if (datos.existe(nombre)){
            return "El registro ya existe.";
        }else{
            obj.setNombre(nombre);
            obj.setDescripcion(descripcion);
            if (datos.insertar(obj)){
                return "OK";
            }else{
                return "Error en el registro.";
            }
        }
    }
    
    public String actualizar(int id, String nombre,String nombreAnt,String descripcion){
        if (nombre.equals(nombreAnt)){
            obj.setId(id);
            obj.setNombre(nombre);
            obj.setDescripcion(descripcion);
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
                obj.setNombre(nombre);
                obj.setDescripcion(descripcion);
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
