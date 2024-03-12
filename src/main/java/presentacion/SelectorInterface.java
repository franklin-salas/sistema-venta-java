/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package presentacion;

/**
 *
 * @author frank
 */
public interface SelectorInterface {

  public static final int CLIENTE = 1;
  public static final int PROVEEDOR = 2;
  public static final int ARTICULO = 3;
  
    public void seleccionarObjetoDialog(Object aObjeto,int tipoObjeto);
}


