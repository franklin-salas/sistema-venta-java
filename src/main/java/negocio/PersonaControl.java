/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;


import datos.PersonaDAO;
import datos.RolDAO;

import entidades.Persona;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lanister
 */
public class PersonaControl {
  private final PersonaDAO datos;
    private  Persona obj;
    private DefaultTableModel modeloTabla;
    public int registrosMostrados;
    
    public PersonaControl(){
        this.datos= new PersonaDAO();
        
        this.obj=new Persona();
        this.registrosMostrados=0;
    }

    public DefaultTableModel listar(String texto,int total, int pagina, String tipoPersona ){
        List<Persona> lista=new ArrayList<>();
        lista.addAll(datos.listar(texto,total,pagina, tipoPersona));
        
          String[] titulos={"Id","Tipo Persona","Persona","Documento","# Documento","Dirección","Teléfono","Email","Estado"};
        this.modeloTabla=new DefaultTableModel(null,titulos){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // Hacer que todas las celdas no sean editables
                }
            };
        String estado;
        String[] registro = new String[11];
        
        this.registrosMostrados=0;
        for (Persona item:lista){
            if (item.isActivo()){
                estado="Activo";
            } else{
                estado="Inactivo";
            }
          
            
                  registro[0]=Integer.toString(item.getId());
            registro[1]=item.getTipoPersona();
            registro[2]=item.getNombre();
            registro[3]=item.getTipoDocumento();
            registro[4]=item.getNumDocumento();
            registro[5]=item.getDireccion();
            registro[6]=item.getTelefono();
            registro[7]=item.getEmail();
            registro[8]=estado;
           
            this.modeloTabla.addRow(registro);
            this.registrosMostrados=this.registrosMostrados+1;
        }
        return this.modeloTabla;
    }
  
    public String insertar(String tipoPersona, String nombre, String tipoDocumento, String numDocumento, String direccion, String telefono, String email){
       
        if (datos.existe(nombre)){
            return "El registro ya existe.";
        }else{
            obj.setTipoPersona(tipoPersona);
            obj.setNombre(nombre);
            obj.setTipoDocumento(tipoDocumento);
            obj.setNumDocumento(numDocumento);
            obj.setDireccion(direccion);
            obj.setTelefono(telefono);
            obj.setEmail(email);
            
            if (datos.insertar(obj)){
                return "OK";
            }else{
                return "Error en el registro.";
            }
        }
    }
    
    public String actualizar(int id,String tipoPersona, String nombre, String nombreAnt, String tipoDocumento, String numDocumento, String direccion, String telefono, String email){
        if (nombre.equals(nombreAnt)){
            
              obj.setId(id);
            obj.setTipoPersona(tipoPersona);
            obj.setNombre(nombre);
            obj.setTipoDocumento(tipoDocumento);
            obj.setNumDocumento(numDocumento);
            obj.setDireccion(direccion);
            obj.setTelefono(telefono);
            obj.setEmail(email);
          
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
                obj.setTipoPersona(tipoPersona);
                obj.setNombre(nombre);
                obj.setTipoDocumento(tipoDocumento);
                obj.setNumDocumento(numDocumento);
                obj.setDireccion(direccion);
                obj.setTelefono(telefono);
                obj.setEmail(email);

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
    
    public int totalClientes(){
        return datos.total("Cliente");
    }
    
    public int totalProveedor(){
        return datos.total("Proveedor");
    }
    public int totalMostrados(){
        return this.registrosMostrados;
    }

 

    
}
