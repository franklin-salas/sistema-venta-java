/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/MDIApplication.java to edit this template
 */
package presentacion;

/**
 *
 * @author lanister
 */
public class FrmPrincipal extends javax.swing.JFrame {

       private  InternalFrameCategoria frmCategorias;
         private  InternalFrameArticulo frmArticulo;
         private InternalFrameRol frmRol;
           private InternalFrameUsuario frmUsuario;
           private InternalFrameProveedor  frmProveedor;
            private InternalFrameCliente  frmCliente;
            private InternalFrameIngreso  frmIngreso;
            private InternalFrameVenta  frmVenta;
            private InternalFrameConsultaVenta  frmConsultaVenta;
    /**
     * Creates new form FrmPrincipal
     */

    public FrmPrincipal() {
        initComponents();
       setLocationRelativeTo(null);
       setExtendedState(MAXIMIZED_BOTH);
       this.cargarOpcionesMenu();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPane = new Desktop();
        menuBar = new javax.swing.JMenuBar();
        menuAlmacen = new javax.swing.JMenu();
        menuItemCategorias = new javax.swing.JMenuItem();
        menuItemArticulos = new javax.swing.JMenuItem();
        menuCompras = new javax.swing.JMenu();
        menuItemIngresos = new javax.swing.JMenuItem();
        menuItemProveedores = new javax.swing.JMenuItem();
        menuVentas = new javax.swing.JMenu();
        menuItemClientes = new javax.swing.JMenuItem();
        menuItemVentas = new javax.swing.JMenuItem();
        menuAcceso = new javax.swing.JMenu();
        menuItemRoles = new javax.swing.JMenuItem();
        menuItemUsuarios = new javax.swing.JMenuItem();
        menuConsultas = new javax.swing.JMenu();
        menuItemConsultaCompras = new javax.swing.JMenuItem();
        menuItemConsultaVentas = new javax.swing.JMenuItem();
        menuSalir = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        menuAlmacen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/almacen.png"))); // NOI18N
        menuAlmacen.setText("Almacen");

        menuItemCategorias.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_DOWN_MASK));
        menuItemCategorias.setText("Categorias");
        menuItemCategorias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemCategoriasActionPerformed(evt);
            }
        });
        menuAlmacen.add(menuItemCategorias);

        menuItemArticulos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_DOWN_MASK));
        menuItemArticulos.setText("Articulos");
        menuItemArticulos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemArticulosActionPerformed(evt);
            }
        });
        menuAlmacen.add(menuItemArticulos);

        menuBar.add(menuAlmacen);

        menuCompras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/compras.png"))); // NOI18N
        menuCompras.setText("Compras");

        menuItemIngresos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.ALT_DOWN_MASK));
        menuItemIngresos.setText("Ingresos");
        menuItemIngresos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemIngresosActionPerformed(evt);
            }
        });
        menuCompras.add(menuItemIngresos);

        menuItemProveedores.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_DOWN_MASK));
        menuItemProveedores.setText("Proveedores");
        menuItemProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemProveedoresActionPerformed(evt);
            }
        });
        menuCompras.add(menuItemProveedores);

        menuBar.add(menuCompras);

        menuVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ventas.png"))); // NOI18N
        menuVentas.setText("Ventas");

        menuItemClientes.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuItemClientes.setText("Clientes");
        menuItemClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemClientesActionPerformed(evt);
            }
        });
        menuVentas.add(menuItemClientes);

        menuItemVentas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.ALT_DOWN_MASK));
        menuItemVentas.setText("Ventas");
        menuItemVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemVentasActionPerformed(evt);
            }
        });
        menuVentas.add(menuItemVentas);

        menuBar.add(menuVentas);

        menuAcceso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/acceso.png"))); // NOI18N
        menuAcceso.setText("Acceso");

        menuItemRoles.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuItemRoles.setText("Roles");
        menuItemRoles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemRolesActionPerformed(evt);
            }
        });
        menuAcceso.add(menuItemRoles);

        menuItemUsuarios.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuItemUsuarios.setText("Usuarios");
        menuItemUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemUsuariosActionPerformed(evt);
            }
        });
        menuAcceso.add(menuItemUsuarios);

        menuBar.add(menuAcceso);

        menuConsultas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/consultas.png"))); // NOI18N
        menuConsultas.setText("Consultas");

        menuItemConsultaCompras.setText("Consuta Compras");
        menuConsultas.add(menuItemConsultaCompras);

        menuItemConsultaVentas.setText("Consulta Ventas");
        menuItemConsultaVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemConsultaVentasActionPerformed(evt);
            }
        });
        menuConsultas.add(menuItemConsultaVentas);

        menuBar.add(menuConsultas);

        menuSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/salir.png"))); // NOI18N
        menuSalir.setText("Salir");
        menuSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuSalirMouseClicked(evt);
            }
        });
        menuBar.add(menuSalir);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1208, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuItemCategoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemCategoriasActionPerformed
        // TODO add your handling code here:
        frmCategorias  =  new InternalFrameCategoria();
        desktopPane.add(frmCategorias);
        frmCategorias.setVisible(true);
    }//GEN-LAST:event_menuItemCategoriasActionPerformed

    private void menuItemArticulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemArticulosActionPerformed
        // TODO add your handling code here:
           frmArticulo  =  new InternalFrameArticulo();
       desktopPane.add(frmArticulo);
        frmArticulo.setVisible(true);
    }//GEN-LAST:event_menuItemArticulosActionPerformed

    private void menuItemRolesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemRolesActionPerformed
        // TODO add your handling code here:
        
            frmRol  =  new InternalFrameRol();
       desktopPane.add(frmRol);
        frmRol.setVisible(true);
    }//GEN-LAST:event_menuItemRolesActionPerformed

    private void menuItemUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemUsuariosActionPerformed
        // TODO add your handling code here:
         frmUsuario  =  new InternalFrameUsuario();
       desktopPane.add(frmUsuario);
        frmUsuario.setVisible(true);
    }//GEN-LAST:event_menuItemUsuariosActionPerformed

    private void menuItemProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemProveedoresActionPerformed
        // TODO add your handling code here:
         frmProveedor  =  new InternalFrameProveedor();
       desktopPane.add(frmProveedor);
        frmProveedor.setVisible(true);
    }//GEN-LAST:event_menuItemProveedoresActionPerformed

    private void menuItemClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemClientesActionPerformed
        // TODO add your handling code here:
               frmCliente  =  new InternalFrameCliente();
       desktopPane.add(frmCliente);
        frmCliente.setVisible(true);
    }//GEN-LAST:event_menuItemClientesActionPerformed

    private void menuSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuSalirMouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_menuSalirMouseClicked

    private void menuItemIngresosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemIngresosActionPerformed
        // TODO add your handling code here:
        frmIngreso  =  new InternalFrameIngreso(this);
       desktopPane.add(frmIngreso);
        frmIngreso.setVisible(true);
    }//GEN-LAST:event_menuItemIngresosActionPerformed

    private void menuItemVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemVentasActionPerformed
        // TODO add your handling code here:
        frmVenta  =  new InternalFrameVenta(this);
       desktopPane.add(frmVenta);
        frmVenta.setVisible(true);
    }//GEN-LAST:event_menuItemVentasActionPerformed

    private void menuItemConsultaVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemConsultaVentasActionPerformed
        // TODO add your handling code here:

        frmConsultaVenta  =  new InternalFrameConsultaVenta();
       desktopPane.add(frmConsultaVenta);
        frmConsultaVenta.setVisible(true);
    }//GEN-LAST:event_menuItemConsultaVentasActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenu menuAcceso;
    private javax.swing.JMenu menuAlmacen;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuCompras;
    private javax.swing.JMenu menuConsultas;
    private javax.swing.JMenuItem menuItemArticulos;
    private javax.swing.JMenuItem menuItemCategorias;
    private javax.swing.JMenuItem menuItemClientes;
    private javax.swing.JMenuItem menuItemConsultaCompras;
    private javax.swing.JMenuItem menuItemConsultaVentas;
    private javax.swing.JMenuItem menuItemIngresos;
    private javax.swing.JMenuItem menuItemProveedores;
    private javax.swing.JMenuItem menuItemRoles;
    private javax.swing.JMenuItem menuItemUsuarios;
    private javax.swing.JMenuItem menuItemVentas;
    private javax.swing.JMenu menuSalir;
    private javax.swing.JMenu menuVentas;
    // End of variables declaration//GEN-END:variables

    
       private void cargarOpcionesMenu(){
           switch (negocio.Session.rolNombre) {
               case "Administrador":
                   menuAlmacen.setEnabled(true);
                   menuCompras.setEnabled(true);
                   menuVentas.setEnabled(true);
                   menuAcceso.setEnabled(true);
                   menuItemConsultaCompras.setEnabled(true);
                   menuItemConsultaVentas.setEnabled(true);
                   break;
               case "Almacenero":
                   menuAlmacen.setEnabled(true);
                   menuCompras.setEnabled(true);
                   menuVentas.setEnabled(false);
                   menuAcceso.setEnabled(false);
                   menuItemConsultaCompras.setEnabled(true);
                   menuItemConsultaVentas.setEnabled(false);
                   break;
               case "Vendedor":
                   menuAlmacen.setEnabled(false);
                   menuCompras.setEnabled(false);
                   menuVentas.setEnabled(true);
                   menuAcceso.setEnabled(false);
                   menuItemConsultaCompras.setEnabled(false);
                   menuItemConsultaVentas.setEnabled(true);
                   break;
               default:
                   menuAlmacen.setEnabled(false);
                   menuCompras.setEnabled(false);
                   menuVentas.setEnabled(false);
                   menuAcceso.setEnabled(false);
                   menuConsultas.setEnabled(false);
                   break;
           }
    }

}
