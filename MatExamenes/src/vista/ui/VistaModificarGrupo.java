/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.dto.CursoDTO;
import modelo.dto.GrupoDTO;
import modelo.dto.GrupoDTO.Turno;
import modelo.dto.UsuarioDTO;
import vista.controlador.CVMantenerGrupos;
import vista.interfaz.InterfaceGrupo;
import vista.interfaz.InterfaceVista;

/**
 *
 * @author BoredmanDA
 */
public class VistaModificarGrupo extends javax.swing.JPanel implements
        InterfaceVista, InterfaceGrupo {

    private CVMantenerGrupos controlVista;
    private InterfaceVista padre;
    private FrmAgregarAlumnos vistaAgregarAlumnos;
    private FrmAgregarMaestro vistaAgregarMaestro;

    /**
     * Creates new form CrearNuevoGrupo
     */
    public VistaModificarGrupo() {
        initComponents();
    }

    public void setControlador(CVMantenerGrupos controlVista) {
        this.controlVista = controlVista;
    }

    public void setPadre(InterfaceVista padre) {
        this.padre = padre;
    }

    /**
     * Limpia los campos de la vista
     */
    @Override
    public void limpiar() {
        controlVista.liberarMemoriaModificar();
    }

    private void mostrarDatos(GrupoDTO grupo) {
        txtfNombre.setText(grupo.getNombre());
        cbGrado.setSelectedIndex(grupo.getGrado() - 1);
        if (grupo.getTurno() == Turno.M) {
            cbTurno.setSelectedIndex(0);
        } else {
            cbTurno.setSelectedIndex(1);
        }
        mostrarAlumnos(grupo.getAlumnos());
        HashMap<CursoDTO, UsuarioDTO> mapa = new HashMap<>();
        DefaultTableModel modelo2 = (DefaultTableModel) tblMaestros.getModel();
        for (int x = modelo2.getRowCount() - 1; x > -1; x--) {
            modelo2.removeRow(x);
        }
        for (CursoDTO curso : grupo.getMaestros().keySet()) {
            UsuarioDTO maestro = grupo.getMaestros().get(curso);
            mapa.put(curso, maestro);
            Object[] fila = new Object[6];
            fila[0] = false;
            fila[1] = String.valueOf(maestro.getId());
            fila[2] = maestro.getNombre();
            fila[3] = maestro.getApellidoPaterno();
            fila[4] = maestro.getApellidoMaterno();
            fila[5] = curso.getNombre();
        }
        mostrarMaestros(mapa);
    }

    @Override
    public void mostrarAlumnos(List<UsuarioDTO> listaAlumnos) {
        DefaultTableModel model = (DefaultTableModel) tblAlumnos.getModel();
        for (int x = model.getRowCount() - 1; x > -1; x--) {
            model.removeRow(x);
        }
        for (int x = 0; x < listaAlumnos.size(); x++) {
            UsuarioDTO alumno = listaAlumnos.get(x);
            Object[] fila = new Object[5];
            fila[0] = false;
            fila[1] = String.valueOf(alumno.getId());
            fila[2] = alumno.getNombre();
            fila[3] = alumno.getApellidoPaterno();
            fila[4] = alumno.getApellidoMaterno();
            model.addRow(fila);
        }
        tblAlumnos.setModel(model);
    }

    @Override
    public void mostrarMaestros(HashMap<CursoDTO, UsuarioDTO> mapa) {
        DefaultTableModel model = (DefaultTableModel) tblMaestros.getModel();
        for (int x = model.getRowCount() - 1; x > -1; x--) {
            model.removeRow(x);
        }
        for (CursoDTO curso : mapa.keySet()) {
            UsuarioDTO maestro = mapa.get(curso);
            Object[] fila = new Object[6];
            fila[0] = false;
            fila[1] = String.valueOf(maestro.getId());
            fila[2] = maestro.getNombre();
            fila[3] = maestro.getApellidoPaterno();
            fila[4] = maestro.getApellidoMaterno();
            fila[5] = curso.getNombre();
            model.addRow(fila);
        }
        tblMaestros.setModel(model);
    }

    public void removerAlumnos(List<Integer> nosFilaAlumno) {
        DefaultTableModel model = (DefaultTableModel) tblAlumnos.getModel();
        int cont = model.getRowCount();
        for (int i = 0; i < nosFilaAlumno.size(); i++) {
            model.removeRow(nosFilaAlumno.get(i) - i);
        }
        tblAlumnos.setModel(model);
    }

    public void removerMaestro(String curso) {
        DefaultTableModel model = (DefaultTableModel) tblMaestros.getModel();
        int cont = model.getRowCount();
        for (int i = 0; i < cont; i++) {
            if (String.valueOf(model.getValueAt(i, 5)).equals(curso)) {
                model.removeRow(i);
            }
        }
        tblMaestros.setModel(model);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        maestrosWin = new javax.swing.JFrame();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        alumnosWin = new javax.swing.JFrame();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblAlumnos = new javax.swing.JTable();
        txtfNombre = new javax.swing.JTextField();
        btnAgregarAlumnos = new javax.swing.JButton();
        btnRemoverAlumnos = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        cbGrado = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        cbTurno = new javax.swing.JComboBox();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblMaestros = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        btnRemoverMaestro = new javax.swing.JButton();
        btnAgregarMaestros = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        jLabel4.setText("Nombre Maestro");

        jTextField3.setText("jTextField3");

        jButton6.setText("Buscar");

        jButton7.setText("Aceptar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Cancelar");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "[x]", "Nom", "A.P.", "A.M."
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jTable4);

        javax.swing.GroupLayout maestrosWinLayout = new javax.swing.GroupLayout(maestrosWin.getContentPane());
        maestrosWin.getContentPane().setLayout(maestrosWinLayout);
        maestrosWinLayout.setHorizontalGroup(
            maestrosWinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, maestrosWinLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7)
                .addContainerGap())
            .addGroup(maestrosWinLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(maestrosWinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(maestrosWinLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton6)))
                .addContainerGap(73, Short.MAX_VALUE))
        );
        maestrosWinLayout.setVerticalGroup(
            maestrosWinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(maestrosWinLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(maestrosWinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(maestrosWinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7)
                    .addComponent(jButton8))
                .addContainerGap())
        );

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "#", "Nom", "A.P.", "A.M.", "Usuario"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane5.setViewportView(jTable5);

        jButton9.setText("Aceptar");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setText("Cancelar");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout alumnosWinLayout = new javax.swing.GroupLayout(alumnosWin.getContentPane());
        alumnosWin.getContentPane().setLayout(alumnosWinLayout);
        alumnosWinLayout.setHorizontalGroup(
            alumnosWinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(alumnosWinLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(alumnosWinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(alumnosWinLayout.createSequentialGroup()
                        .addComponent(jButton10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton9))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(69, Short.MAX_VALUE))
        );
        alumnosWinLayout.setVerticalGroup(
            alumnosWinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(alumnosWinLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(alumnosWinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton9)
                    .addComponent(jButton10))
                .addGap(27, 27, 27))
        );

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("Nombre del Grupo:");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("Grado:");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("Alumnos");

        tblAlumnos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblAlumnos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "[x]", "Id", "A.P.", "A.M.", "Nom"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblAlumnos);

        txtfNombre.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btnAgregarAlumnos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAgregarAlumnos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/botonAgregar.png"))); // NOI18N
        btnAgregarAlumnos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarAlumnosActionPerformed(evt);
            }
        });

        btnRemoverAlumnos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnRemoverAlumnos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/botonRemover.png"))); // NOI18N
        btnRemoverAlumnos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverAlumnosActionPerformed(evt);
            }
        });

        btnGuardar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/botonGuardar_1.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel7.setText("Modificar Grupo");

        cbGrado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbGrado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1ro", "2do", "3ro" }));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setText("Turno:");

        cbTurno.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbTurno.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Matutino", "Vespertino" }));

        tblMaestros.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblMaestros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "[x]", "Id", "A.P.", "A.M.", "Nom", "Curso"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane6.setViewportView(tblMaestros);

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setText("Maestros");

        btnRemoverMaestro.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnRemoverMaestro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/botonRemover.png"))); // NOI18N
        btnRemoverMaestro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverMaestroActionPerformed(evt);
            }
        });

        btnAgregarMaestros.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAgregarMaestros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/botonAgregar.png"))); // NOI18N
        btnAgregarMaestros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarMaestrosActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
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
                        .addContainerGap()
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnRemoverAlumnos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(btnAgregarAlumnos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(btnRemoverMaestro, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(btnAgregarMaestros, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbGrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbTurno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6)))
                .addContainerGap(120, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(314, 314, 314))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnGuardar)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar)
                        .addGap(58, 58, 58))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel7)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(cbGrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(cbTurno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAgregarMaestros)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRemoverMaestro))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(btnAgregarAlumnos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRemoverAlumnos))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnGuardar))
                .addGap(25, 25, 25))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
    }//GEN-LAST:event_jButton10ActionPerformed

    private void btnAgregarAlumnosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarAlumnosActionPerformed
        vistaAgregarAlumnos = new FrmAgregarAlumnos();
        vistaAgregarAlumnos.inicializar(controlVista, this);
    }//GEN-LAST:event_btnAgregarAlumnosActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        int ok = JOptionPane.showConfirmDialog(this, "¿Estás segur@ de que "
                + "quieres cancelar la operación?\nTodos los cambios no "
                + "guardados se perderán");
        if (ok == 0) {
            padre.mostrarVista(Vista.HOME);
            limpiar();
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnRemoverAlumnosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverAlumnosActionPerformed
        List<Integer> indexes = new ArrayList<>();
        int cont = tblAlumnos.getRowCount();
        for (int x = 0; x < cont; x++) {
            if (tblAlumnos.getValueAt(x, 0).equals(true)) {
                indexes.add(x);
            }
        }
        if (cont == 0 || indexes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecciona al menos un alumno", "Advertencia", 1);
        } else {
            controlVista.removerAlumnos(indexes);
            removerAlumnos(indexes);
        }
    }//GEN-LAST:event_btnRemoverAlumnosActionPerformed

    private void btnAgregarMaestrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarMaestrosActionPerformed
        vistaAgregarMaestro = new FrmAgregarMaestro();
        vistaAgregarMaestro.inicializar(controlVista, this);
    }//GEN-LAST:event_btnAgregarMaestrosActionPerformed

    private void btnRemoverMaestroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverMaestroActionPerformed
        String curso = null;
        int cont = tblMaestros.getRowCount();
        for (int x = 0; x < cont; x++) {
            if (tblMaestros.getValueAt(x, 0).equals(true)) {
                DefaultTableModel model = (DefaultTableModel) tblMaestros.getModel();
                curso = String.valueOf(model.getValueAt(x, 5));
            }
        }
        if (cont == 0 || curso == null) {
            JOptionPane.showMessageDialog(this, "Selecciona al menos un maestro", "Advertencia", 1);
        } else {
            controlVista.removerMaestro(curso);
            removerMaestro(curso);
        }
    }//GEN-LAST:event_btnRemoverMaestroActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFrame alumnosWin;
    private javax.swing.JButton btnAgregarAlumnos;
    private javax.swing.JButton btnAgregarMaestros;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnRemoverAlumnos;
    private javax.swing.JButton btnRemoverMaestro;
    private javax.swing.JComboBox cbGrado;
    private javax.swing.JComboBox cbTurno;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JFrame maestrosWin;
    private javax.swing.JTable tblAlumnos;
    private javax.swing.JTable tblMaestros;
    private javax.swing.JTextField txtfNombre;
    // End of variables declaration//GEN-END:variables

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
        mostrarDatos((GrupoDTO) entidad);
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

    @Override
    public InterfaceVista getPadre() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
