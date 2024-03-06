/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package presentacion;

import entidades.Categoria;
import entidades.Rol;
import java.awt.Image;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;
import negocio.UsuarioControl;

/**
 *
 * @author lanister
 */
public class InternalFrameUsuario extends javax.swing.JInternalFrame {

    private final UsuarioControl control;
    private String accion;
    private String id, emailAnt,claveAnt;
    
   
    private int totalPorPagina = 10;
    private int numPagina = 1;
    private boolean primeraCarga = true;
    private int totalRegistros;

    public InternalFrameUsuario() {
        initComponents();

        this.control = new UsuarioControl();
        this.accion = "NUEVO";
        this.tabCrud.setEnabledAt(1, false);
        this.btnEditar.setEnabled(false);
        this.btnActivar.setEnabled(false);
        this.btnDesactivar.setEnabled(false);
        this.paginar();
        textBuscar.requestFocus();
        this.listar("",false);
        this.primeraCarga =false;
        this.cargarRoles();
        this.listenerEdit();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabCrud = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        textBuscar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        labelTotalRegistros = new javax.swing.JLabel();
        btnAccion = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnActivar = new javax.swing.JButton();
        btnDesactivar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        cboNumPagina = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        cboTotalPorPagina = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        textNombre = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        cboRol = new javax.swing.JComboBox<Rol>();
        jLabel5 = new javax.swing.JLabel();
        textNumDocumento = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cboTipoDocumento = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        textDireccion = new javax.swing.JTextField();
        textTelefono = new javax.swing.JTextField();
        textClave = new javax.swing.JPasswordField();
        textEmail = new javax.swing.JTextField();

        setBackground(new java.awt.Color(230, 235, 240));
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setTitle("Usuarios");
        setToolTipText("");

        jPanel1.setPreferredSize(new java.awt.Dimension(580, 450));

        jLabel1.setText("Nombre");

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabla.setRowHeight(25);
        jScrollPane1.setViewportView(tabla);

        labelTotalRegistros.setText("Registros :");

        btnAccion.setText("Nuevo");
        btnAccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAccionActionPerformed(evt);
            }
        });

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnActivar.setText("Activar");
        btnActivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActivarActionPerformed(evt);
            }
        });

        btnDesactivar.setText("Desactivar");
        btnDesactivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDesactivarActionPerformed(evt);
            }
        });

        jLabel9.setText("# Página");

        cboNumPagina.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboNumPaginaItemStateChanged(evt);
            }
        });

        jLabel10.setText("Total");

        cboTotalPorPagina.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "10", "20", "30", "40", "50", "100" }));
        cboTotalPorPagina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTotalPorPaginaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(btnActivar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDesactivar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelTotalRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAccion, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cboNumPagina, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(104, 104, 104)
                .addComponent(jLabel10)
                .addGap(29, 29, 29)
                .addComponent(cboTotalPorPagina, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(btnBuscar)
                    .addComponent(btnAccion)
                    .addComponent(btnEditar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cboNumPagina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(cboTotalPorPagina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnActivar)
                    .addComponent(btnDesactivar)
                    .addComponent(labelTotalRegistros))
                .addContainerGap())
        );

        tabCrud.addTab("Listado", jPanel1);

        jPanel2.setPreferredSize(new java.awt.Dimension(504, 574));
        jPanel2.setRequestFocusEnabled(false);

        jLabel2.setText("Nombre (*)");

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jLabel4.setText("Rol(*)");

        jLabel5.setText("Documento");

        jLabel6.setText("Número Documeno");

        jLabel7.setText("Dirección");

        cboTipoDocumento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DNI", "CEDULA", "PASAPORTE", " " }));

        jLabel3.setText("Teléfono");

        jLabel8.setText("Email(*)");

        jLabel11.setText("Clave(*)");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel3)
                    .addComponent(jLabel8)
                    .addComponent(jLabel11))
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(textDireccion, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textNumDocumento, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboTipoDocumento, javax.swing.GroupLayout.Alignment.LEADING, 0, 234, Short.MAX_VALUE)
                    .addComponent(textNombre, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboRol, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textTelefono, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textClave, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textEmail, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap(189, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cboRol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addGap(2, 2, 2))
                    .addComponent(textNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(cboTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textNumDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(textDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textClave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(66, 66, 66)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnGuardar))
                .addContainerGap(104, Short.MAX_VALUE))
        );

        tabCrud.addTab("Registro", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabCrud)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tabCrud, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleName("Proveedor");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:  
        this.listar(textBuscar.getText(),false);
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnAccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAccionActionPerformed
        // TODO add your handling code here:
        this.tabCrud.setEnabledAt(0, false);
        this.tabCrud.setEnabledAt(1, true);
        this.tabCrud.setSelectedIndex(1);
        this.accion = "NUEVO";

        this.limpiarFormulario();

    }//GEN-LAST:event_btnAccionActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // TODO add your handling code here:

        if (id.length() > 0) {

            this.tabCrud.setEnabledAt(0, false);
            this.tabCrud.setEnabledAt(1, true);
            this.tabCrud.setSelectedIndex(1);
            this.accion = "EDITAR";
            this.btnGuardar.setText("Editar");

        }

    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnDesactivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesactivarActionPerformed
        // TODO add your handling code here:
        if (this.id.length() > 0) {
            if (JOptionPane.showConfirmDialog(this, "¿Desea desactivar el registro: " + emailAnt + " ?", "Desactivar", JOptionPane.YES_NO_CANCEL_OPTION) == 0) {
                String res = this.control.desactivar(Integer.parseInt(id));
                if (res.equals("OK")) {
                    this.listar("",false);
                } else {
                    this.mensajeError(res);
                }

            }
        }
    }//GEN-LAST:event_btnDesactivarActionPerformed

    private void btnActivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActivarActionPerformed
        // TODO add your handling code here:
        if (this.id.length() > 0) {
            if (JOptionPane.showConfirmDialog(this, "¿Desea activar el registro: " + emailAnt + " ?", "Activar", JOptionPane.YES_NO_CANCEL_OPTION) == 0) {
                String res = this.control.activar(Integer.parseInt(id));
                if (res.equals("OK")) {
                    this.listar("",false);
                } else {
                    this.mensajeError(res);
                }
            }
        }
    }//GEN-LAST:event_btnActivarActionPerformed
   

    private void cboTotalPorPaginaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTotalPorPaginaActionPerformed
        // TODO add your handling code here:
        this.paginar();
    }//GEN-LAST:event_cboTotalPorPaginaActionPerformed

    private void cboNumPaginaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboNumPaginaItemStateChanged
        // TODO add your handling code here:
       
        if ( evt.getStateChange() == java.awt.event.ItemEvent.SELECTED &&this.primeraCarga==false){
            
            this.listar("",true);
        }
    }//GEN-LAST:event_cboNumPaginaItemStateChanged

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:

        this.tabCrud.setEnabledAt(1, false);
        this.tabCrud.setEnabledAt(0, true);
        this.tabCrud.setSelectedIndex(0);
        this.limpiarFormulario();
        this.tabla.clearSelection();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:

        if (textNombre.getText().length()==0 || textNombre.getText().length()>70){
            JOptionPane.showMessageDialog(this, "Debes ingresar un nombre y no debe ser mayor a 70 caracteres, es obligatorio.","Sistema", JOptionPane.WARNING_MESSAGE);
            textNombre.requestFocus();
            return;
        }
        if (textEmail.getText().length()==0 || textEmail.getText().length()>50 ){
            JOptionPane.showMessageDialog(this, "Debes ingresar un email y no debe ser mayor a 50 caracteres, es obligatorio.","Sistema", JOptionPane.WARNING_MESSAGE);
            textEmail.requestFocus();
            return;
        }
        
        if (!this.accion.equals("EDITAR")&&(textClave.getPassword().length == 0 || textClave.getPassword().length>64) ){
            JOptionPane.showMessageDialog(this, "Debes ingresar una clave, es obligatorio.","Sistema", JOptionPane.WARNING_MESSAGE);
            textClave.requestFocus();
            return;
        }
        if (textNumDocumento.getText().length()>20){
            JOptionPane.showMessageDialog(this, "Debes ingresar un número de documento no mayor a 20 caracteres.","Sistema", JOptionPane.WARNING_MESSAGE);
            textNumDocumento.requestFocus();
            return;
        }
        if (textDireccion.getText().length()>70){
            JOptionPane.showMessageDialog(this, "Debes ingresar una dirección no mayor a 70 caracteres.","Sistema", JOptionPane.WARNING_MESSAGE);
            textDireccion.requestFocus();
            return;
        }
        if (textTelefono.getText().length()>15){
            JOptionPane.showMessageDialog(this, "Debes ingresar un teléfono no mayor a 15 caracteres.","Sistema", JOptionPane.WARNING_MESSAGE);
            textTelefono.requestFocus();
            return;
        }   
        

        String res = "";
        if (this.accion.equals("EDITAR")) {
           
           Rol seleccionado = (Rol)cboRol.getSelectedItem();
    
            String clave = claveAnt;
            if(textClave.getPassword().length>0){
                clave = new String(textClave.getPassword());
            }
            res =this.control.actualizar(Integer.parseInt(id),seleccionado.getId(),textNombre.getText(),(String)cboTipoDocumento.getSelectedItem(),textNumDocumento.getText(),textDireccion.getText(),textTelefono.getText(),textEmail.getText(),this.emailAnt,clave);
            this.tabCrud.setEnabledAt(1, false);
            this.tabCrud.setEnabledAt(0, true);
            this.tabCrud.setSelectedIndex(0);
        } else {

           Rol seleccionado = (Rol)cboRol.getSelectedItem();
            res=this.control.insertar(seleccionado.getId(),textNombre.getText(),(String)cboTipoDocumento.getSelectedItem(),textNumDocumento.getText(),textDireccion.getText(),textTelefono.getText(),textEmail.getText(),new String(textClave.getPassword()));
            
                 }

        if (res.equals("OK")) {

           

            this.mensajeSuccess("Registrado correctamente.");
            this.limpiarFormulario();
            this.tabla.clearSelection();
            this.listar("",false);
        } else {
            this.mensajeError(res);
        }

    }//GEN-LAST:event_btnGuardarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAccion;
    private javax.swing.JButton btnActivar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnDesactivar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cboNumPagina;
    private javax.swing.JComboBox<Rol> cboRol;
    private javax.swing.JComboBox<String> cboTipoDocumento;
    private javax.swing.JComboBox<String> cboTotalPorPagina;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelTotalRegistros;
    private javax.swing.JTabbedPane tabCrud;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField textBuscar;
    private javax.swing.JPasswordField textClave;
    private javax.swing.JTextField textDireccion;
    private javax.swing.JTextField textEmail;
    private javax.swing.JTextField textNombre;
    private javax.swing.JTextField textNumDocumento;
    private javax.swing.JTextField textTelefono;
    // End of variables declaration//GEN-END:variables

    private void listenerEdit() {
        ListSelectionModel selectionModel = tabla.getSelectionModel();
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    boolean filaSeleccionada = !selectionModel.isSelectionEmpty() && tabla.getSelectedRowCount() == 1;

                    btnEditar.setEnabled(filaSeleccionada);
                    btnDesactivar.setEnabled(false);
                    btnActivar.setEnabled(false);
                  
                    if (filaSeleccionada) {
                        int filaSeleccionadaIndex = tabla.getSelectedRow();

                        id = String.valueOf(tabla.getValueAt(filaSeleccionadaIndex, 0));
                     
                       
            int rolId=Integer.parseInt(String.valueOf(tabla.getValueAt(filaSeleccionadaIndex,1)));
            String rolNombre=String.valueOf(tabla.getValueAt(filaSeleccionadaIndex,2));
            String nombre=String.valueOf(tabla.getValueAt(filaSeleccionadaIndex,3));
            String tipoDocumento= String.valueOf(tabla.getValueAt(filaSeleccionadaIndex,4));
            String numDocumento= String.valueOf(tabla.getValueAt(filaSeleccionadaIndex,5));
            String direccion= String.valueOf(tabla.getValueAt(filaSeleccionadaIndex,6));
            String telefono= String.valueOf(tabla.getValueAt(filaSeleccionadaIndex,7));
            String email= String.valueOf(tabla.getValueAt(filaSeleccionadaIndex,8));
            emailAnt = String.valueOf(tabla.getValueAt(filaSeleccionadaIndex,8));
            String clave=String.valueOf(tabla.getValueAt(filaSeleccionadaIndex,9));
              String estado = String.valueOf(tabla.getValueAt(filaSeleccionadaIndex, 10));

                       /* Categoria seleccionado = new Categoria(categoriaId, categoriaNombre);

                        cboRol.setSelectedItem(seleccionado);
                        textNumDocumento.setText(codigo);
                        textNombre.setText(nombre);
                        textPrecio.setText(precio);
                        textStock.setText(stock);
                        textDescripcion.setText(descripcion);*/
                       
             claveAnt = clave;  
            Rol seleccionado=new Rol(rolId,rolNombre);
            cboRol.setSelectedItem(seleccionado);
            textNombre.setText(nombre);
            cboTipoDocumento.setSelectedItem(tipoDocumento);
            textNumDocumento.setText(numDocumento);
            textDireccion.setText(direccion);
            textTelefono.setText(telefono);
            textEmail.setText(email);
            textClave.setText(""); 
                     

                        if (estado.equals("Activo")) {
                            btnDesactivar.setEnabled(true);

                        }
                        if (estado.equals("Inactivo")) {
                            btnActivar.setEnabled(true);
                        }

                    }
                }

            }
        });

    }

    private void ocultarColumnas() {
         tabla.getColumnModel().getColumn(1).setMaxWidth(0);
        tabla.getColumnModel().getColumn(1).setMinWidth(0);
        tabla.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(0);
        tabla.getTableHeader().getColumnModel().getColumn(1).setMinWidth(0);
        
        tabla.getColumnModel().getColumn(9).setMaxWidth(0);
        tabla.getColumnModel().getColumn(9).setMinWidth(0);
        tabla.getTableHeader().getColumnModel().getColumn(9).setMaxWidth(0);
        tabla.getTableHeader().getColumnModel().getColumn(9).setMinWidth(0);
    }
    private void paginar(){
        int totalPaginas;
        
        this.totalRegistros=this.control.total();
        this.totalPorPagina=Integer.parseInt((String)cboTotalPorPagina.getSelectedItem());
        totalPaginas=(int)(Math.ceil((double)this.totalRegistros/this.totalPorPagina));
        if (totalPaginas==0){
            totalPaginas=1;
        }
        cboNumPagina.removeAllItems();
        
        for (int i = 1; i <= totalPaginas; i++) {
            cboNumPagina.addItem(Integer.toString(i));
        }
        cboNumPagina.setSelectedIndex(0);
    }
    private void listar(String text, boolean paginar) {
        
         this.totalPorPagina=Integer.parseInt((String)cboTotalPorPagina.getSelectedItem());
        if ((String)cboNumPagina.getSelectedItem()!=null){
            this.numPagina=Integer.parseInt((String)cboNumPagina.getSelectedItem());
        }
        
        if (paginar==true){
            tabla.setModel(this.control.listar(text,this.totalPorPagina,this.numPagina));
        }else{
            tabla.setModel(this.control.listar(text,this.totalPorPagina,1));
        }
        
        //this.tabla.setModel(this.control.listar(text, totalPorPagina, numPagina));
        TableRowSorter orden = new TableRowSorter(this.tabla.getModel());
        this.tabla.setRowSorter(orden);
        ocultarColumnas();
        this.labelTotalRegistros.setText("Mostrando " + this.control.totalMostrados() + " de " + this.control.total());

    }

    private void cargarRoles() {
        DefaultComboBoxModel<Rol> items = this.control.seleccionar();
        cboRol.setModel(items);
    }

    private void mensajeError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Sistema", JOptionPane.ERROR_MESSAGE);
    }

    private void mensajeWarning(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Sistema", JOptionPane.WARNING_MESSAGE);
    }

    private void mensajeSuccess(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Sistema", JOptionPane.INFORMATION_MESSAGE);
    }

    private void limpiarFormulario() {
        this.textBuscar.setText("");

        this.id = "";
        this.emailAnt = "";
        this.btnGuardar.setText("Guardar");
        this.textNumDocumento.setText("");
        this.textNombre.setText("");
        this.accion = "guardar";
   
        textDireccion.setText("");
        textTelefono.setText("");
        textEmail.setText("");
        textClave.setText("");
       this.accion="guardar";

    }

}
