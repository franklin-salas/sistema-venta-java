/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author lanister
 */
public class Conector {

    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String URL = "jdbc:mysql://localhost:3306/";
    private final String DB = "dbsistema";
    private final String USER = "root";
    private  final String PASSWORD = "";

    private Connection cadena ;
    public static  Conector instancia; 
    
    
    private Conector(){
        this.cadena = null;
    }
    
    public Connection conectar (){
        try {
            
            if ( this.cadena != null && ! this.cadena.isClosed()) {
                 this.cadena.close();
            }
            Class.forName(DRIVER);
            this.cadena = DriverManager.getConnection(URL+ DB, USER, PASSWORD);
            
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
             throw new RuntimeException("Error al establecer la conexión a la base de datos.", e);
          
        }
        
        return this.cadena;
    }
    
    public  void desconectar(){
        
        try {
            this.cadena.close();
            
            if (this.cadena != null && !this.cadena.isClosed()) {
                this.cadena.close();
                System.out.println("Conexión cerrada correctamente.");
            }
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, e.getMessage());
            throw new RuntimeException("Error al cerrar la conexión a la base de datos.", e);
        }
    }
    
    public synchronized static Conector getInstance (){
        if(instancia == null){
          instancia = new Conector();
        }
        return instancia;
    }
}
