/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;


import datos.RolDAO;
import datos.UsuarioDAO;
import entidades.Articulo;
import entidades.Categoria;
import entidades.Rol;
import entidades.Usuario;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lanister
 */
public class UsuarioControl {
  private final UsuarioDAO datos;
  private final RolDAO datosRol;
    private  Usuario obj;
    private DefaultTableModel modeloTabla;
    public int registrosMostrados;
    
    public UsuarioControl(){
        this.datos=new UsuarioDAO();
        this.datosRol=new RolDAO();
        this.obj=new Usuario();
        this.registrosMostrados=0;
    }
       public DefaultComboBoxModel seleccionar(){
        DefaultComboBoxModel<Rol> items = new DefaultComboBoxModel();
        List<Rol> lista=new ArrayList();
        lista=datosRol.seleccionar();
        for (Rol item: lista){
            items.addElement(new Rol(item.getId(),item.getNombre()));
        }
        return items;
    }
    public DefaultTableModel listar(String texto,int total, int pagina ){
        List<Usuario> lista=new ArrayList<>();
        lista.addAll(datos.listar(texto,total,pagina));
        
         String[] titulos={"Id","Rol ID","Rol","Usuario","Documento","# Documento","Dirección","Teléfono","Email","Clave","Estado"};
     
        this.modeloTabla=new DefaultTableModel(null,titulos){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // Hacer que todas las celdas no sean editables
                }
            };
        String estado;
        String[] registro = new String[11];
        
        this.registrosMostrados=0;
        for (Usuario item:lista){
            if (item.isActivo()){
                estado="Activo";
            } else{
                estado="Inactivo";
            }
          
            
            registro[0]=Integer.toString(item.getId());
            registro[1]=Integer.toString(item.getRolId());
            registro[2]=item.getRolNombre();
            registro[3]=item.getNombre();
            registro[4]=item.getTipoDocumento();
            registro[5]=item.getNumDocumento();
            registro[6]=item.getDireccion();
            registro[7]=item.getTelefono();
            registro[8]=item.getEmail();
            registro[9]=item.getClave();
            registro[10]=estado;
           
            this.modeloTabla.addRow(registro);
            this.registrosMostrados=this.registrosMostrados+1;
        }
        return this.modeloTabla;
    }
     private String encriptar(String valor){
         MessageDigest md;
	try {
		md = MessageDigest.getInstance("SHA-256");
	} 
	catch (NoSuchAlgorithmException e) {		
		return null;
	}
	    
	byte[] hash = md.digest(valor.getBytes());
	StringBuilder sb = new StringBuilder();
	    
	for(byte b : hash) {        
		sb.append(String.format("%02x", b));
	}
	    
	return sb.toString();
    }
    public String insertar(int RolId, String nombre, String tipoDocumento, String numDocumento, String direccion, String telefono, String email, String clave){
       
        if (datos.existe(email)){
            return "El registro ya existe.";
        }else{
            obj.setRolId(RolId);
            obj.setNombre(nombre);
            obj.setTipoDocumento(tipoDocumento);
            obj.setNumDocumento(numDocumento);
            obj.setDireccion(direccion);
            obj.setTelefono(telefono);
            obj.setEmail(email);
            obj.setClave(this.encriptar(clave));
            if (datos.insertar(obj)){
                return "OK";
            }else{
                return "Error en el registro.";
            }
        }
    }
    
    public String actualizar(int id,int RolId, String nombre, String tipoDocumento, String numDocumento, String direccion, String telefono, String email, String emailAnt, String clave){
        if (email.equals(emailAnt)){
            
            obj.setId(id);
            obj.setRolId(RolId);
            obj.setNombre(nombre);
            obj.setTipoDocumento(tipoDocumento);
            obj.setNumDocumento(numDocumento);
            obj.setDireccion(direccion);
            obj.setTelefono(telefono);
            obj.setEmail(email);
            
            String encriptado;
            if (clave.length()==64){
                encriptado=clave;
            }else{
                encriptado=this.encriptar(clave);
            }
            obj.setClave(encriptado);
            
            if(datos.actualizar(obj)){
                return "OK";
            }else{
                return "Error en la actualización.";
            }
        }else{
            if (datos.existe(email)){
                return "El registro ya existe.";
            }else{
                obj.setId(id);
                obj.setRolId(RolId);
                obj.setNombre(nombre);
                obj.setTipoDocumento(tipoDocumento);
                obj.setNumDocumento(numDocumento);
                obj.setDireccion(direccion);
                obj.setTelefono(telefono);
                obj.setEmail(email);
                String encriptado;
                if (clave.length() == 64) {
                    encriptado = clave;
                } else {
                    encriptado = this.encriptar(clave);
                }
                obj.setClave(encriptado);
            

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
