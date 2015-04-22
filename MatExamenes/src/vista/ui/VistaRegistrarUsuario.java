/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.ui;

import javax.swing.JOptionPane;
import modelo.dto.UsuarioDTO;
import vista.controlador.CVMantenerUsuarios;
import vista.interfaz.InterfazVista;

/**
 *
 * @author Alf
 */
public class VistaRegistrarUsuario extends javax.swing.JPanel implements InterfazVista{

    private CVMantenerUsuarios cvMantenerUsuarios;
    private InterfazVista padre;
    /**
     * Creates new form VistaRegistrarUsuario2
     */
    public VistaRegistrarUsuario() {
        initComponents();
    }
    
    public void setPadre(InterfazVista padre){
        this.padre = padre;
    }
    
    public void setControlador(CVMantenerUsuarios cvMantenerUsuarios){
        this.cvMantenerUsuarios = cvMantenerUsuarios;
    }
    
    public UsuarioDTO encapsularUsuario(){
        UsuarioDTO usuario = new UsuarioDTO();
        //Falta validar los camposs!!! Fijate en Validador en vista.controlador
        
        usuario.setApellidoMaterno(txtfApellidoMaterno.getText());
        usuario.setApellidoPaterno(txtfApellidoPaterno.getText());
        usuario.setNombre(txtfNombre.getText());
        char pass[] = txtpPassword.getPassword();
        usuario.setPassword(String.valueOf(pass));
        usuario.setUsuario(txtfUsuario.getText());
        
        if(rbtnAlumno.isSelected()){
            usuario.setTipo(UsuarioDTO.Tipo.Alumno);
        } else if(rbtnMaestro.isSelected()){
            usuario.setTipo(UsuarioDTO.Tipo.Maestro);
        }
        
        return usuario;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        lblPassword = new javax.swing.JLabel();
        lblTipo = new javax.swing.JLabel();
        txtfNombre = new javax.swing.JTextField();
        lblTitulo = new javax.swing.JLabel();
        txtfApellidoPaterno = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        txtfApellidoMaterno = new javax.swing.JTextField();
        rbtnMaestro = new javax.swing.JRadioButton();
        txtfUsuario = new javax.swing.JTextField();
        rbtnAlumno = new javax.swing.JRadioButton();
        lblNombre = new javax.swing.JLabel();
        lblApellidoPaterno = new javax.swing.JLabel();
        lblApellidoMaterno = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        txtpPassword = new javax.swing.JPasswordField();

        lblPassword.setText("Password:");

        lblTipo.setText("Tipo de usuario");

        txtfNombre.setPreferredSize(new java.awt.Dimension(100, 25));

        lblTitulo.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblTitulo.setText("Registrar Usuario");

        txtfApellidoPaterno.setPreferredSize(new java.awt.Dimension(100, 25));

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        txtfApellidoMaterno.setPreferredSize(new java.awt.Dimension(100, 25));
        txtfApellidoMaterno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtfApellidoMaternoActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbtnMaestro);
        rbtnMaestro.setText("Maestro");

        txtfUsuario.setPreferredSize(new java.awt.Dimension(100, 25));

        buttonGroup1.add(rbtnAlumno);
        rbtnAlumno.setText("Alumno");

        lblNombre.setText("Nombre:");

        lblApellidoPaterno.setText("Apellido Paterno:");

        lblApellidoMaterno.setText("Apellido Materno:");

        lblUsuario.setText("Usuario:");

        txtpPassword.setPreferredSize(new java.awt.Dimension(100, 25));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(216, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblTitulo)
                        .addGap(163, 163, 163))
                    .addComponent(btnGuardar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblUsuario)
                                    .addComponent(lblPassword)
                                    .addComponent(lblTipo))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(rbtnMaestro)
                                        .addGap(18, 18, 18)
                                        .addComponent(rbtnAlumno))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(193, 193, 193)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtfUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtpPassword, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNombre)
                                    .addComponent(lblApellidoPaterno)
                                    .addComponent(lblApellidoMaterno))
                                .addGap(182, 182, 182)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtfApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtfApellidoPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(75, 75, 75)))
                .addGap(143, 143, 143))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(lblTitulo)
                .addGap(72, 72, 72)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombre))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtfApellidoPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblApellidoPaterno))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtfApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblApellidoMaterno))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtfUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUsuario))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPassword)
                    .addComponent(txtpPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTipo)
                    .addComponent(rbtnMaestro)
                    .addComponent(rbtnAlumno))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 51, Short.MAX_VALUE)
                .addComponent(btnGuardar)
                .addGap(52, 52, 52))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtfApellidoMaternoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtfApellidoMaternoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfApellidoMaternoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        UsuarioDTO usuario = encapsularUsuario();
        if(usuario == null) {
            JOptionPane.showMessageDialog(this, "Datos incorrectos, porfavor " +
                    "sólo ingresa números y letras");
            return;
        }
        Integer id = cvMantenerUsuarios.guardarUsuario(usuario);
        
        if (id == null) {
            //No se pudo guardar porque habia un tema duplicado
            JOptionPane.showMessageDialog(this, "Usuario existente");
        } else {
            JOptionPane.showMessageDialog(this, "Usuario Registrado");
            padre.mostrarVista(InterfazVista.Vista.HOME);
            limpiar();
        }
    }//GEN-LAST:event_btnGuardarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel lblApellidoMaterno;
    private javax.swing.JLabel lblApellidoPaterno;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JRadioButton rbtnAlumno;
    private javax.swing.JRadioButton rbtnMaestro;
    private javax.swing.JTextField txtfApellidoMaterno;
    private javax.swing.JTextField txtfApellidoPaterno;
    private javax.swing.JTextField txtfNombre;
    private javax.swing.JTextField txtfUsuario;
    private javax.swing.JPasswordField txtpPassword;
    // End of variables declaration//GEN-END:variables

    @Override
    public void limpiar(){
        txtfNombre.setText("");
        txtfApellidoPaterno.setText("");
        txtfApellidoMaterno.setText("");
        txtpPassword.setText("");
        txtfUsuario.setText("");
        if(rbtnAlumno.isSelected()){
            rbtnAlumno.setSelected(false);
        } else {
            rbtnMaestro.setSelected(false);
        }
        
    }
    
    @Override
    public void mostrarVistaConEntidad(Object entidad, Vista vista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mostrarVista(Vista vista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mostrarEntidad(Object entidad) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
