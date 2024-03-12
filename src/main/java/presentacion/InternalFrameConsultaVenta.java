/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package presentacion;

import entidades.Articulo;
import java.sql.Date;
import java.util.Calendar;


import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import negocio.VentaControl;

/**
 *
 * @author lanister
 */
public class InternalFrameConsultaVenta extends javax.swing.JInternalFrame  {

    private final VentaControl control;
    //  private String accion;
    private String id;

    private int totalPorPagina = 10;
    private int numPagina = 1;
    private boolean primeraCarga = true;
    private int totalRegistros;
   
    public DefaultTableModel modeloDetalle;

    public InternalFrameConsultaVenta() {
        initComponents();

        this.control = new VentaControl();
      
        //   this.accion = "NUEVO";
      

       
        this.paginar();
      
        this.listar( false);
     
        this.primeraCarga = false;
        this.ocultarColumnas();
        this.crearDetalle();
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
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        labelTotalRegistros = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cboNumPagina = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        cboTotalPorPagina = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        dcFechaInicio = new com.toedter.calendar.JDateChooser();
        dcFechaFin = new com.toedter.calendar.JDateChooser();

        setBackground(new java.awt.Color(230, 235, 240));
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setTitle("Ventas");
        setToolTipText("");

        jPanel1.setPreferredSize(new java.awt.Dimension(580, 450));

        jLabel1.setText("Fecha Inicio");

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabla.setRowHeight(25);
        jScrollPane1.setViewportView(tabla);

        labelTotalRegistros.setText("Registros :");

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

        jLabel2.setText("Fecha Fin");

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cboNumPagina, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(99, 99, 99)
                .addComponent(jLabel10)
                .addGap(26, 26, 26)
                .addComponent(cboTotalPorPagina, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 377, Short.MAX_VALUE)
                .addComponent(labelTotalRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dcFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(87, 87, 87)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(dcFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2))
                    .addComponent(dcFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dcFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cboNumPagina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(cboTotalPorPagina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTotalRegistros))
                .addGap(72, 72, 72))
        );

        tabCrud.addTab("Listado", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabCrud, javax.swing.GroupLayout.DEFAULT_SIZE, 928, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(tabCrud, javax.swing.GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void cboTotalPorPaginaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTotalPorPaginaActionPerformed
        // TODO add your handling code here:
        this.paginar();
    }//GEN-LAST:event_cboTotalPorPaginaActionPerformed

    private void cboNumPaginaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboNumPaginaItemStateChanged
        // TODO add your handling code here:

        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED && this.primeraCarga == false) {

            this.listar( true);
        }
    }//GEN-LAST:event_cboNumPaginaItemStateChanged

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        //this.listar(textBuscar.getText(), false);
    }//GEN-LAST:event_btnBuscarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JComboBox<String> cboNumPagina;
    private javax.swing.JComboBox<String> cboTotalPorPagina;
    private com.toedter.calendar.JDateChooser dcFechaFin;
    private com.toedter.calendar.JDateChooser dcFechaInicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelTotalRegistros;
    private javax.swing.JTabbedPane tabCrud;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables

    private void crearDetalle() {
        String[] titulos = {"Id", "Código", "Articulo", "Stock", "Cantidad", "Precio", "Descuento", "Subtotal"};

        this.modeloDetalle = new DefaultTableModel(null, titulos) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Hacer que todas las celdas no sean editables

                return column == 4 || column == 5 || column == 6;
            }

            @Override
            public Object getValueAt(int row, int column) {

                if (column == 7) {
                    Double cantidad;
                    Double precio;
                    Double descuento;
                    try {
                        String textCant = String.valueOf(getValueAt(row, 4));
                        String textprecio = String.valueOf(getValueAt(row, 5));
                        String textDescuento = String.valueOf(getValueAt(row, 6));
                        cantidad = Double.valueOf(textCant);
                        precio = Double.valueOf(textprecio);
                        descuento = Double.valueOf(textDescuento);
                    } catch (NumberFormatException e) {
                        cantidad = 1.0;
                        precio = 0.0;
                        descuento = 0.0;
                    }
                    return String.format(Locale.US, "%.2f",( cantidad * precio )- descuento);
                }

                return super.getValueAt(row, column);
            }

            @Override
            public void setValueAt(Object aValue, int row, int column) {

                if (column == 4) {
                    int cantidad = 0;
                    int stock = 0;
                    try {
                        String textCant = String.valueOf(aValue);
                        String textStock = String.valueOf(getValueAt(row, 3));
                        cantidad = Integer.parseInt(textCant);
                        stock = Integer.parseInt(textStock);

                    } catch (NumberFormatException e) {
                        cantidad = 1;
                        stock = 1;
                    }

                    // Asegurar que la cantidad no sea mayor que el stock
                    cantidad = Math.min(cantidad, stock);

                    // Asegurar que la cantidad no sea menor que 1
                    cantidad = Math.max(cantidad, 1);

                    super.setValueAt(String.valueOf(cantidad), row, column);
                } else {
                    super.setValueAt(aValue, row, column);
                }

             

                fireTableDataChanged();

            }
        };

   
        /*  this.tablaDetalle.getColumnModel().getColumn(3).setMaxWidth(0);
        this.tablaDetalle.getColumnModel().getColumn(3).setMinWidth(0);
        this.tablaDetalle.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(0);
        this.tablaDetalle.getTableHeader().getColumnModel().getColumn(3).setMinWidth(0);*/
    }



    private void listenerEdit() {
        ListSelectionModel selectionModel = tabla.getSelectionModel();
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    boolean filaSeleccionada = !selectionModel.isSelectionEmpty() && tabla.getSelectedRowCount() == 1;

                  

                    if (filaSeleccionada) {
                        int filaSeleccionadaIndex = tabla.getSelectedRow();

                        id = String.valueOf(tabla.getValueAt(filaSeleccionadaIndex, 0));

                        //String tipoPersona =String.valueOf(tabla.getValueAt(filaSeleccionadaIndex,1));
                        String estado = String.valueOf(tabla.getValueAt(filaSeleccionadaIndex, 11));

                      

                    }
                }

            }
        });

    }

    private void paginar() {
        int totalPaginas;

        this.totalRegistros = this.control.total();
        this.totalPorPagina = Integer.parseInt((String) cboTotalPorPagina.getSelectedItem());
        totalPaginas = (int) (Math.ceil((double) this.totalRegistros / this.totalPorPagina));
        if (totalPaginas == 0) {
            totalPaginas = 1;
        }
        cboNumPagina.removeAllItems();

        for (int i = 1; i <= totalPaginas; i++) {
            cboNumPagina.addItem(Integer.toString(i));
        }
        cboNumPagina.setSelectedIndex(0);
    }

    private void listar( boolean paginar) {
        
        
        Calendar cal;
        Date fechaIni;
        Date fechaFin;
        int d,m,a;
        
        cal= dcFechaInicio.getCalendar();
        d = cal.get(Calendar.DAY_OF_MONTH);
        m=cal.get(Calendar.MONTH);
        a=cal.get(Calendar.YEAR) - 1900;
        fechaIni=new Date(a,m,d);
        
        cal= dcFechaFin.getCalendar();
        d=cal.get(Calendar.DAY_OF_MONTH);
        m=cal.get(Calendar.MONTH);
        a=cal.get(Calendar.YEAR) - 1900;
        fechaFin=new Date(a,m,d);
        
                
     //   tablaListado.setModel(this.CONTROL.consultaFechas(fechaIni,fechaFin));
        
       

        this.totalPorPagina = Integer.parseInt((String) cboTotalPorPagina.getSelectedItem());
        if ((String) cboNumPagina.getSelectedItem() != null) {
            this.numPagina = Integer.parseInt((String) cboNumPagina.getSelectedItem());
        }

        if (paginar == true) {
            tabla.setModel(this.control.consultaFechas(fechaIni,fechaFin, this.totalPorPagina, this.numPagina));
        } else {
            tabla.setModel(this.control.consultaFechas(fechaIni,fechaFin, this.totalPorPagina, 1));
        }

        //this.tabla.setModel(this.control.listar(text, totalPorPagina, numPagina));
        TableRowSorter orden = new TableRowSorter(this.tabla.getModel());
        this.tabla.setRowSorter(orden);

        this.labelTotalRegistros.setText("Mostrando " + this.control.totalMostrados() + " de " + this.control.total());

    }

    private void ocultarColumnas() {
        this.tabla.getColumnModel().getColumn(1).setMaxWidth(0);
        this.tabla.getColumnModel().getColumn(1).setMinWidth(0);
        this.tabla.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(0);
        this.tabla.getTableHeader().getColumnModel().getColumn(1).setMinWidth(0);

        this.tabla.getColumnModel().getColumn(3).setMaxWidth(0);
        this.tabla.getColumnModel().getColumn(3).setMinWidth(0);
        this.tabla.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(0);
        this.tabla.getTableHeader().getColumnModel().getColumn(3).setMinWidth(0);
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

      
        this.crearDetalle();
       
        this.id = "";
        //this.nombreAnt = "";
        

    }

    public void addDetalleArticulo(Articulo art) {

        int existe = -1;
        for (int i = 0; i < this.modeloDetalle.getRowCount(); i++) {
            String valor = String.valueOf(modeloDetalle.getValueAt(i, 0));
            // System.out.println("articulo :"+ valor);
            // System.out.println(modeloDetalle.getValueAt(i, 0));
            int idArt = Integer.parseInt(valor);
            if (idArt == art.getId()) {
                existe = i;
               
                break;
            }
        }

        //{"Id", "Código", "Articulo","Stock", "Cantidad", "Precio", "Descuento", "Subtotal"};
        if (existe == -1) {
            this.modeloDetalle.addRow(new Object[]{art.getId(), art.getCodigo(), art.getNombre(), art.getStock(), "1", art.getPrecio(), "0.00", art.getPrecio()});
            // this.tablaDetalle.setModel(this.modeloDetalle);
            // this.modeloDetalle.fireTableDataChanged();
           
            return;
        }

        String valor = String.valueOf(modeloDetalle.getValueAt(existe, 4));
        int stock = Integer.parseInt(valor);

        this.modeloDetalle.setValueAt(stock + 1, existe, 4);

       

    }




}