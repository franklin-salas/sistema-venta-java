/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import datos.RolDAO;
import entidades.Rol;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author frank
 */
public class RolControl {
    private final RolDAO datos;
    private Rol obj;
    private DefaultTableModel modeloTabla;
    public int registrosMostrados;
    
    public RolControl(){
        this.datos=new RolDAO();
        this.obj=new Rol();
        this.registrosMostrados=0;
    }
    
    public DefaultTableModel listar(){
        List<Rol> lista=new ArrayList<>();
        lista.addAll(datos.listar());
        
        String[] titulos={"Id","Nombre","Descripción"};
        this.modeloTabla=new DefaultTableModel(null,titulos){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // Hacer que todas las celdas no sean editables
                }
            };
      
        String[] registro = new String[3];
        
        this.registrosMostrados=0;
        for (Rol item:lista){
           /* if (item.isActivo()){
                estado="Activo";
            } else{
                estado="Inactivo";
            }*/
            registro[0]=Integer.toString(item.getId());
            registro[1]=item.getNombre();
            registro[2]=item.getDescripcion();
          
            this.modeloTabla.addRow(registro);
            this.registrosMostrados=this.registrosMostrados+1;
        }
        return this.modeloTabla;
    }
    

    
 
  
    
    public int total(){
        return datos.total();
    }
    
    public int totalMostrados(){
        return this.registrosMostrados;
    }

}
