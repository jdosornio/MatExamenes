/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.ui;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;
import modelo.dto.GrupoDTO;
import modelo.dto.UsuarioDTO;
import vista.controlador.CVMantenerGrupos;
import vista.interfaz.InterfaceVista;

/**
 *
 * @author BoredmanDA
 */
public class VistaConsultarGrupo extends javax.swing.JPanel implements AncestorListener, InterfaceVista {
    
    private CVMantenerGrupos controladorVista;
    private InterfaceVista padre;
    private List<GrupoDTO> listaGrupos;

    /**
     * Creates new form CrearNuevoGrupo
     */
    public VistaConsultarGrupo() {
        initComponents();
        this.addAncestorListener(this);
    }

    /**
     * Asigna el controlador de la vista que tendra la vista.
     *
     * @param controlVista Recibe un objeto de tipo CVMantenerGrupos.
     */
    public void setControlador(CVMantenerGrupos controlVista) {
        this.controladorVista = controlVista;
    }

    /**
     * Asigna el padre que tendra la vista.
     *
     * @param padre Recibe un objeto de tipo InterfaceVista.
     */
    public void setPadre(InterfaceVista padre) {
        this.padre = padre;
    }

    /**
     * Limpia los campos de la vista
     */
    @Override
    public void limpiar() {
        DefaultTableModel modelo = (DefaultTableModel) tblGrupos.getModel();
        for (int i = modelo.getRowCount() - 1; i > -1; i--) {
            modelo.removeRow(i);
        }
        tblGrupos.setModel(modelo);
        controladorVista.liberarMemoriaConsultar();
    }

    /**
     * Llama al controlador de vista para que obtenga todos los grupos.
     */
    private void consultarGrupos() {
        this.listaGrupos = controladorVista.obtenerGrupos();
        if (listaGrupos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay grupos!", "Advertencia", 1);
            padre.mostrarVista(Vista.HOME);
            limpiar();
        } else {
            mostrarGrupos(this.listaGrupos);
        }
    }

    /**
     * Muestra los grupos en la tabla de grupos.
     *
     * @param listaGrupos Recibe la lista de los grupos.
     */
    private void mostrarGrupos(List<GrupoDTO> listaGrupos) {
        DefaultTableModel modelo = (DefaultTableModel) tblGrupos.getModel();
        for (int i = modelo.getRowCount() - 1; i > -1; i--) {
            modelo.removeRow(i);
        }
        for (GrupoDTO grupo : listaGrupos) {
            Object[] fila = new Object[4];
            fila[0] = false;
            fila[1] = grupo.getNombre();
            fila[2] = grupo.getGrado();
            fila[3] = grupo.getTurno();
            modelo.addRow(fila);
        }
        tblGrupos.setModel(modelo);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblGrupos = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblGrupos = new javax.swing.JTable();
        btnEliminar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        lblTitulo = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();

        lblGrupos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblGrupos.setText("Grupos");

        tblGrupos.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tblGrupos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "[x]", "Nombre", "Grado", "Turno"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblGrupos);

        btnEliminar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/eliminar24.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnModificar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/modificar24.png"))); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        lblTitulo.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblTitulo.setText("Consultar Grupos");

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/botonCancelar_1.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(311, 311, 311)
                        .addComponent(lblTitulo))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 554, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnModificar)
                                .addGap(18, 18, 18)
                                .addComponent(btnEliminar)
                                .addGap(18, 18, 18)
                                .addComponent(btnCancelar))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(356, 356, 356)
                        .addComponent(lblGrupos)))
                .addContainerGap(140, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lblTitulo)
                .addGap(36, 36, 36)
                .addComponent(lblGrupos)
                .addGap(48, 48, 48)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModificar)
                    .addComponent(btnEliminar))
                    .addComponent(btnEliminar)
                    .addComponent(btnCancelar))
                .addContainerGap(100, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Verifica que un grupo haya sido seleccionado, obtiene el grupo del
     * controlador de vista y lo envia a su padre para que lo muestre en la
     * vista de modificar.
     *
     * @param evt Recibe el evento del boton que lo activo.
     */
    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        int index = -1;
        int contador = tblGrupos.getRowCount();
        for (int i = 0; i < contador; i++) {
            if (tblGrupos.getValueAt(i, 0).equals(true)) {
                index = i;
            }
        }
        if (contador == 0 || index == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona al menos un grupo",
                    "Advertencia", 1);
        } else {
            GrupoDTO grupo = controladorVista.obtenerGrupo(index);
            if (grupo != null) {
                padre.mostrarVistaConEntidad(grupo, Vista.ModificarGrupo);
            } else {
                JOptionPane.showMessageDialog(this, "Ha ocurrido un error!",
                        "Advertencia", 1);
            }
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    /**
     * Verifica que un grupo haya sido seleccionado, muestra un mensaje de
     * confirmacion, obtiene el indice y lo envia al controlador de vista para
     * que lo elimine.
     *
     * @param evt Recibe el evento del boton que lo activo.
     */
    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int index = -1;
        int contador = tblGrupos.getRowCount();
        for (int i = 0; i < contador; i++) {
            if (tblGrupos.getValueAt(i, 0).equals(true)) {
                index = i;
            }
        }
        if (contador == 0 || index == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona al menos un grupo",
                    "Advertencia", 1);
        } else {
            int ok = JOptionPane.showConfirmDialog(this, "¿Estás segur@ de que "
                    + "quieres eliminar este grupo?\nEste proceso es irreversible");
            if (ok == 0) {
                boolean ban = controladorVista.eliminarGrupo(index);
                if (ban) {
                    JOptionPane.showMessageDialog(this, "Grupo Eliminado!",
                            "Exito", 1);
                    DefaultTableModel modelo = (DefaultTableModel) tblGrupos.getModel();
                    modelo.removeRow(index);
                    tblGrupos.setModel(modelo);
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo eliminar el grupo!",
                            "Advertencia", 1);
                }
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        padre.mostrarVista(Vista.HOME);
        limpiar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblGrupos;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTable tblGrupos;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mostrarVistaConEntidad(Object entidad, Vista vista) {
        //No implementado.
    }
    
    @Override
    public void mostrarVista(Vista vista) {
        //No implementado.
    }
    
    @Override
    public void mostrarEntidad(Object entidad) {
        //No implementado.
    }

    /**
     * Muestra un mensaje de confirmacion cuando se quiere cambiar de vista.
     *
     * @return Retorna una variable de tipo boleana que indicara si, se desea
     * cambiar de vista o no.
     */
    @Override
    public boolean confirmarCambio() {
        boolean cambiar = false;
        int ok = JOptionPane.showConfirmDialog(this, "¿Estás segur@ de que "
                + "quieres cambiar de pantalla?\nTodos los cambios no "
                + "guardados se perderán");
        if (ok == 0) {
            cambiar = true;
        }
        return cambiar;
    }
    
    @Override
    public UsuarioDTO obtenerUsuarioActual() {
        //No implementado.
        return null;
    }
    
    @Override
    public void ancestorAdded(AncestorEvent event) {
        if (isShowing()) {
            consultarGrupos();
        }
    }
    
    @Override
    public void ancestorRemoved(AncestorEvent event) {
        //No implementado.
    }
    
    @Override
    public void ancestorMoved(AncestorEvent event) {
        //No implementado.
    }
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.ui;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;
import modelo.dto.GrupoDTO;
import modelo.dto.UsuarioDTO;
import vista.controlador.CVMantenerGrupos;
import vista.interfaz.InterfaceVista;

/**
 *
 * @author BoredmanDA
 */
public class VistaConsultarGrupo extends javax.swing.JPanel implements AncestorListener, InterfaceVista {
    
    private CVMantenerGrupos controladorVista;
    private InterfaceVista padre;
    private List<GrupoDTO> listaGrupos;

    /**
     * Creates new form CrearNuevoGrupo
     */
    public VistaConsultarGrupo() {
        initComponents();
        this.addAncestorListener(this);
    }

    /**
     * Asigna el controlador de la vista que tendra la vista.
     *
     * @param controlVista Recibe un objeto de tipo CVMantenerGrupos.
     */
    public void setControlador(CVMantenerGrupos controlVista) {
        this.controladorVista = controlVista;
    }

    /**
     * Asigna el padre que tendra la vista.
     *
     * @param padre Recibe un objeto de tipo InterfaceVista.
     */
    public void setPadre(InterfaceVista padre) {
        this.padre = padre;
    }

    /**
     * Limpia los campos de la vista
     */
    @Override
    public void limpiar() {
        DefaultTableModel modelo = (DefaultTableModel) tblGrupos.getModel();
        for (int i = modelo.getRowCount() - 1; i > -1; i--) {
            modelo.removeRow(i);
        }
        tblGrupos.setModel(modelo);
        controladorVista.liberarMemoriaConsultar();
    }

    /**
     * Llama al controlador de vista para que obtenga todos los grupos.
     */
    private void consultarGrupos() {
        this.listaGrupos = controladorVista.obtenerGrupos();
        if (listaGrupos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay grupos!", "Advertencia", 1);
            padre.mostrarVista(Vista.HOME);
            limpiar();
        } else {
            mostrarGrupos(this.listaGrupos);
        }
    }

    /**
     * Muestra los grupos en la tabla de grupos.
     *
     * @param listaGrupos Recibe la lista de los grupos.
     */
    private void mostrarGrupos(List<GrupoDTO> listaGrupos) {
        DefaultTableModel modelo = (DefaultTableModel) tblGrupos.getModel();
        for (int i = modelo.getRowCount() - 1; i > -1; i--) {
            modelo.removeRow(i);
        }
        for (GrupoDTO grupo : listaGrupos) {
            Object[] fila = new Object[4];
            fila[0] = false;
            fila[1] = grupo.getNombre();
            fila[2] = grupo.getGrado();
            fila[3] = grupo.getTurno();
            modelo.addRow(fila);
        }
        tblGrupos.setModel(modelo);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblGrupos = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblGrupos = new javax.swing.JTable();
        btnEliminar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        lblTitulo = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();

        lblGrupos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblGrupos.setText("Grupos");

        tblGrupos.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tblGrupos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "[x]", "Nombre", "Grado", "Turno"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblGrupos);

        btnEliminar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/botonEliminar.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnModificar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/botonModificar.png"))); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        lblTitulo.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblTitulo.setText("Consultar Grupos");

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/botonCancelar_1.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(311, 311, 311)
                        .addComponent(lblTitulo))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 554, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnModificar)
                                .addGap(18, 18, 18)
                                .addComponent(btnEliminar)
                                .addGap(18, 18, 18)
                                .addComponent(btnCancelar))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(356, 356, 356)
                        .addComponent(lblGrupos)))
                .addContainerGap(140, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lblTitulo)
                .addGap(36, 36, 36)
                .addComponent(lblGrupos)
                .addGap(48, 48, 48)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModificar)
                    .addComponent(btnEliminar)
                    .addComponent(btnCancelar))
                .addContainerGap(100, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Verifica que un grupo haya sido seleccionado, obtiene el grupo del
     * controlador de vista y lo envia a su padre para que lo muestre en la
     * vista de modificar.
     *
     * @param evt Recibe el evento del boton que lo activo.
     */
    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        int index = -1;
        int contador = tblGrupos.getRowCount();
        for (int i = 0; i < contador; i++) {
            if (tblGrupos.getValueAt(i, 0).equals(true)) {
                index = i;
            }
        }
        if (contador == 0 || index == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona al menos un grupo",
                    "Advertencia", 1);
        } else {
            GrupoDTO grupo = controladorVista.obtenerGrupo(index);
            if (grupo != null) {
                padre.mostrarVistaConEntidad(grupo, Vista.ModificarGrupo);
            } else {
                JOptionPane.showMessageDialog(this, "Ha ocurrido un error!",
                        "Advertencia", 1);
            }
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    /**
     * Verifica que un grupo haya sido seleccionado, muestra un mensaje de
     * confirmacion, obtiene el indice y lo envia al controlador de vista para
     * que lo elimine.
     *
     * @param evt Recibe el evento del boton que lo activo.
     */
    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int index = -1;
        int contador = tblGrupos.getRowCount();
        for (int i = 0; i < contador; i++) {
            if (tblGrupos.getValueAt(i, 0).equals(true)) {
                index = i;
            }
        }
        if (contador == 0 || index == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona al menos un grupo",
                    "Advertencia", 1);
        } else {
            int ok = JOptionPane.showConfirmDialog(this, "¿Estás segur@ de que "
                    + "quieres eliminar este grupo?\nEste proceso es irreversible");
            if (ok == 0) {
                boolean ban = controladorVista.eliminarGrupo(index);
                if (ban) {
                    JOptionPane.showMessageDialog(this, "Grupo Eliminado!",
                            "Exito", 1);
                    DefaultTableModel modelo = (DefaultTableModel) tblGrupos.getModel();
                    modelo.removeRow(index);
                    tblGrupos.setModel(modelo);
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo eliminar el grupo!",
                            "Advertencia", 1);
                }
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        padre.mostrarVista(Vista.HOME);
        limpiar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblGrupos;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTable tblGrupos;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mostrarVistaConEntidad(Object entidad, Vista vista) {
        //No implementado.
    }
    
    @Override
    public void mostrarVista(Vista vista) {
        //No implementado.
    }
    
    @Override
    public void mostrarEntidad(Object entidad) {
        //No implementado.
    }

    /**
     * Muestra un mensaje de confirmacion cuando se quiere cambiar de vista.
     *
     * @return Retorna una variable de tipo boleana que indicara si, se desea
     * cambiar de vista o no.
     */
    @Override
    public boolean confirmarCambio() {
        boolean cambiar = false;
        int ok = JOptionPane.showConfirmDialog(this, "¿Estás segur@ de que "
                + "quieres cambiar de pantalla?\nTodos los cambios no "
                + "guardados se perderán");
        if (ok == 0) {
            cambiar = true;
        }
        return cambiar;
    }
    
    @Override
    public UsuarioDTO obtenerUsuarioActual() {
        //No implementado.
        return null;
    }
    
    @Override
    public void ancestorAdded(AncestorEvent event) {
        if (isShowing()) {
            consultarGrupos();
        }
    }
    
    @Override
    public void ancestorRemoved(AncestorEvent event) {
        //No implementado.
    }
    
    @Override
    public void ancestorMoved(AncestorEvent event) {
        //No implementado.
    }
}
