/*
 * Copyright (C) 2015 Fernando Enrique Avendaño Hernández
 *
 * This file is part of MatExamenes.
 *
 * MatExamenes is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * MatExamenes is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 51
 * Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
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
import vista.controlador.Validador;
import vista.interfaz.InterfaceGrupo;
import vista.interfaz.InterfaceVista;

/**
 * JPanel que mostrará la interfaz gráfica de Modificar Grupo
 *
 * @author Fernando Enrique Avendaño Hernández
 * @version 1 18 Mayo 2015
 */
public class VistaModificarGrupo extends javax.swing.JPanel implements
        InterfaceVista, InterfaceGrupo {

    /**
     * Controlador de la vista del caso de uso mantener grupos, funciona para
     * manejar la información obtenida en la vista para comunicarse con las
     * capas inferiores.
     */
    private CVMantenerGrupos controlVista;

    /**
     * Interface de comunicación con la interfaz padre, la cuál sirve para
     * comunicarse con ella y recibir mensajes para mostrar otras vistas, En ese
     * caso es utilizada para comunicarse con el JFrame Principal.
     */
    private InterfaceVista padre;

    /**
     * JPanel utilizado para realizar la busqueda de usuarios de tipo alumno y
     * agregarlos al grupo.
     */
    private FrmAgregarAlumnos vistaAgregarAlumnos;

    /**
     * JPanel utilizado para realizar la busqueda de usuarios de tipo maestro y
     * agregarlos al grupo.
     */
    private FrmAgregarMaestro vistaAgregarMaestro;

    /**
     * Objeto de tipo GrupoDTO utilizado para saber cual es el grupo que se esta
     * modificando y al guardarlo no existan conflictos con un grupo ya
     * existente.
     */
    private GrupoDTO grupoActual;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    /**
    * Boton para agregar alumnos.
    */
    private javax.swing.JButton btnAgrAlumnos;
    /**
    * Boton para agregar maestros.
    */
    private javax.swing.JButton btnAgrMaestros;
    /**
    * Boton para cancelar.
    */
    private javax.swing.JButton btnCancelar;
    /**
    * Boton para guardar.
    */
    private javax.swing.JButton btnGuardar;
    /**
    * Boton para remover alumnos.
    */
    private javax.swing.JButton btnRmvAlumnos;
    /**
    * Boton para remover maestros.
    */
    private javax.swing.JButton btnRmvMaestro;
    /**
    * Combobox para el grado.
    */
    private javax.swing.JComboBox cmbGrado;
    /**
    * Combobox para el nombre.
    */
    private javax.swing.JComboBox cmbNombre;
    /**
    * Combobox para el turno.
    */
    private javax.swing.JComboBox cmbTurno;
    /**
    * Scrollpane para la tabla de maestros.
    */
    private javax.swing.JScrollPane jScrollPane1;
    /**
    * Scrollpane para la tabla de alumnos.
    */
    private javax.swing.JScrollPane jScrollPane2;
    /**
    * Label para la tabla alumnos.
    */
    private javax.swing.JLabel lblAlumnos;
    /**
    * Label para el grado.
    */
    private javax.swing.JLabel lblGrado;
    /**
    * Label para la tabla maestros.
    */
    private javax.swing.JLabel lblMaestros;
    /**
    * Label para el nombre.
    */
    private javax.swing.JLabel lblNombre;
    /**
    * Label para el titulo de la vista.
    */
    private javax.swing.JLabel lblTitulo;
    /**
    * Label para el turno.
    */
    private javax.swing.JLabel lblTurno;
    /**
    * Tabla para mostrar alumnos.
    */
    private javax.swing.JTable tblAlumnos;
    /**
    * Tabla para mostrar los maestros.
    */
    private javax.swing.JTable tblMaestros;
    // End of variables declaration//GEN-END:variables

    /**
     * Crea un objeto VistaModificarGrupo e inicializa sus atributos, establece
     * los JComboBox de la vista en seleccion de -1 e inicializa el objeto
     * GrupoDTO.
     */
    public VistaModificarGrupo() {
        initComponents();
        grupoActual = new GrupoDTO();
        cmbGrado.setSelectedIndex(-1);
        cmbNombre.setSelectedIndex(-1);
        cmbTurno.setSelectedIndex(-1);
    }

    /**
     * Asigna el controlador de la vista que tendra la vista.
     *
     * @param controlVista Recibe un objeto de tipo CVMantenerGrupos.
     */
    public void setControlador(CVMantenerGrupos controlVista) {
        this.controlVista = controlVista;
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
     * Limpia los campos de la vista.
     */
    @Override
    public void limpiar() {
        cmbGrado.setSelectedIndex(-1);
        cmbNombre.setSelectedIndex(-1);
        cmbTurno.setSelectedIndex(-1);
        ((DefaultTableModel) tblAlumnos.getModel()).setRowCount(0);
        ((DefaultTableModel) tblMaestros.getModel()).setRowCount(0);
        grupoActual = new GrupoDTO();
    }

    /**
     * Muestra los datos del grupo en los componentes graficos de la vista.
     *
     * @param grupo Recibe un objeto de tipo GrupoDTO.
     */
    private void mostrarDatos(GrupoDTO grupo) {
        cmbNombre.setSelectedItem(grupo.getNombre());
        this.grupoActual.setNombre(grupo.getNombre());
        cmbGrado.setSelectedIndex(grupo.getGrado() - 1);
        this.grupoActual.setGrado(grupo.getGrado());
        if (grupo.getTurno() == Turno.M) {
            cmbTurno.setSelectedIndex(0);
            this.grupoActual.setTurno(Turno.M);
        } else {
            cmbTurno.setSelectedIndex(1);
            this.grupoActual.setTurno(Turno.V);
        }
        mostrarAlumnos(grupo.getAlumnos());
        HashMap<CursoDTO, UsuarioDTO> mapa = new HashMap<>();
        ((DefaultTableModel) tblMaestros.getModel()).setRowCount(0);
        for (CursoDTO curso : grupo.getMaestros().keySet()) {
            UsuarioDTO maestro = grupo.getMaestros().get(curso);
            mapa.put(curso, maestro);
        }
        mostrarMaestros(mapa);
    }

    /**
     * Muestra los alumnos en la tabla de alumnos.
     *
     * @param listaAlumnos Recibe la lista de alumnos que mostrara en la tabla
     * de alumnos.
     */
    @Override
    public void mostrarAlumnos(List<UsuarioDTO> listaAlumnos) {
        ((DefaultTableModel) tblAlumnos.getModel()).setRowCount(0);
        for (int i = 0; i < listaAlumnos.size(); i++) {
            UsuarioDTO alumno = listaAlumnos.get(i);
            Object[] fila = new Object[5];
            fila[0] = false;
            fila[1] = String.valueOf(alumno.getId());
            fila[2] = alumno.getNombre();
            fila[3] = alumno.getApellidoPaterno();
            fila[4] = alumno.getApellidoMaterno();
            ((DefaultTableModel) tblAlumnos.getModel()).addRow(fila);
        }
    }

    /**
     * Muestra los maestros en la tabla de maestros.
     *
     * @param mapaMaestros Recibe el mapa que contiene los maestros y los cursos
     * que mostrara en la tabla de maestros.
     */
    @Override
    public void mostrarMaestros(HashMap<CursoDTO, UsuarioDTO> mapaMaestros) {
        ((DefaultTableModel) tblMaestros.getModel()).setRowCount(0);
        for (CursoDTO curso : mapaMaestros.keySet()) {
            UsuarioDTO maestro = mapaMaestros.get(curso);
            Object[] fila = new Object[5];
            fila[0] = String.valueOf(maestro.getId());
            fila[1] = maestro.getNombre();
            fila[2] = maestro.getApellidoPaterno();
            fila[3] = maestro.getApellidoMaterno();
            fila[4] = curso.getNombre();
            ((DefaultTableModel) tblMaestros.getModel()).addRow(fila);
        }
    }

    /**
     * Remueve los alumnos de la tabla de alumnos.
     *
     * @param noFilasAlumnos Recibe los numeros de las filas de los alumnos que
     * seran eliminados de la tabla de alumnos.
     */
    public void removerAlumnos(List<Integer> noFilasAlumnos) {
        DefaultTableModel model = (DefaultTableModel) tblAlumnos.getModel();
        int cont = model.getRowCount();
        for (int i = 0; i < noFilasAlumnos.size(); i++) {
            model.removeRow(noFilasAlumnos.get(i) - i);
        }
        tblAlumnos.setModel(model);
    }

    /**
     * Remueve el maestro de la tabla de maestros.
     *
     * @param nombreCurso Recibe el nombre del curso del maestro que sera
     * eliminado de la tabla maestros.
     */
    public void removerMaestro(String nombreCurso) {
        DefaultTableModel modelo = (DefaultTableModel) tblMaestros.getModel();
        int cont = modelo.getRowCount();
        for (int i = 0; i < cont; i++) {
            if (String.valueOf(modelo.getValueAt(i, 4)).equals(nombreCurso)) {
                modelo.removeRow(i);
                break;
            }
        }
        tblMaestros.setModel(modelo);
    }

    /**
     * Valida y encapsula los datos ingresados en los componentes graficos y los
     * convierte en un objeto de tipo GrupoDTO.
     *
     * @return Retorna un objeto de tipo GrupoDTO.
     */
    public GrupoDTO encapsularGrupo() {
        GrupoDTO grupo = new GrupoDTO();
        String nombre = String.valueOf(cmbNombre.getSelectedItem());
        if (Validador.esGrupo(nombre)) {
            grupo.setNombre(nombre);
            if (cmbGrado.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(this, "Debes seleccionar un grado.",
                        "Advertencia", 2);
                return null;
            } else {
                grupo.setGrado(cmbGrado.getSelectedIndex() + 1);
            }
            if (cmbTurno.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(this, "Debes seleccionar un turno.",
                        "Advertencia", 2);
                return null;
            } else {
                if (cmbTurno.getSelectedIndex() == 0) {
                    grupo.setTurno(GrupoDTO.Turno.M);
                } else {
                    grupo.setTurno(GrupoDTO.Turno.V);
                }
            }
            if (controlVista.verificarExistencia(grupo)) {
                if (grupo.getGrado() != this.grupoActual.getGrado()
                        || grupo.getTurno() != this.grupoActual.getTurno()
                        || !grupo.getNombre().equalsIgnoreCase(this.grupoActual.getNombre())) {
                    JOptionPane.showMessageDialog(this, "El grupo seleccionado "
                            + "ya está registrado.", "Advertencia", 1);
                    return null;
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Debes seleccionar un nombre.",
                    "Advertencia", 2);
            return null;
        }
        return grupo;
    }

    /**
     * Inicializa los atributos gráficos y los coloca en su posición.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        lblGrado = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblTurno = new javax.swing.JLabel();
        lblMaestros = new javax.swing.JLabel();
        lblAlumnos = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMaestros = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblAlumnos = new javax.swing.JTable();
        btnAgrMaestros = new javax.swing.JButton();
        btnRmvMaestro = new javax.swing.JButton();
        btnAgrAlumnos = new javax.swing.JButton();
        btnRmvAlumnos = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        cmbGrado = new javax.swing.JComboBox();
        cmbNombre = new javax.swing.JComboBox();
        cmbTurno = new javax.swing.JComboBox();

        lblTitulo.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblTitulo.setText("Modificar Grupo");

        lblGrado.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblGrado.setText("Grado:");

        lblNombre.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblNombre.setText("Nombre:");

        lblTurno.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblTurno.setText("Turno:");

        lblMaestros.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblMaestros.setText("Maestros");

        lblAlumnos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblAlumnos.setText("Alumnos");

        tblMaestros.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblMaestros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nom", "A.P.", "A.M.", "Curso"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblMaestros);

        tblAlumnos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblAlumnos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "[x]", "Id", "Nom", "A.P.", "A.M."
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblAlumnos);

        btnAgrMaestros.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAgrMaestros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/agregar24.png"))); // NOI18N
        btnAgrMaestros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AgregarMaestro(evt);
            }
        });

        btnRmvMaestro.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnRmvMaestro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/remover24.png"))); // NOI18N
        btnRmvMaestro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoverMaestro(evt);
            }
        });

        btnAgrAlumnos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAgrAlumnos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/agregar24.png"))); // NOI18N
        btnAgrAlumnos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AgregarAlumnos(evt);
            }
        });

        btnRmvAlumnos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnRmvAlumnos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/remover24.png"))); // NOI18N
        btnRmvAlumnos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoverAlumnos(evt);
            }
        });

        btnGuardar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/guardar24.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarGrupo(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/cancelar24.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cancelar(evt);
            }
        });

        cmbGrado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmbGrado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1ro", "2do", "3ro" }));

        cmbNombre.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "A", "B", "C", "D", "E" }));

        cmbTurno.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmbTurno.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Matutino", "Vespertino" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAlumnos)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnRmvAlumnos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(btnAgrAlumnos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(btnRmvMaestro, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(btnAgrMaestros, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblMaestros)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblGrado)
                        .addGap(18, 18, 18)
                        .addComponent(cmbGrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(79, 79, 79)
                        .addComponent(lblNombre)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTitulo)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cmbNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(68, 68, 68)
                                .addComponent(lblTurno)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmbTurno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(99, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGuardar)
                .addGap(18, 18, 18)
                .addComponent(btnCancelar)
                .addGap(45, 45, 45))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(lblTitulo)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(lblGrado)
                    .addComponent(cmbGrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTurno)
                    .addComponent(cmbTurno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(lblMaestros)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAgrMaestros)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRmvMaestro))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblAlumnos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAgrAlumnos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRmvAlumnos))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnGuardar))
                .addContainerGap(53, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Llama al frame de agregar alumnos.
     *
     * @param evt Recibe el evento del boton que lo activo.
     */
    private void AgregarAlumnos(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarAlumnos
        vistaAgregarAlumnos = new FrmAgregarAlumnos();
        vistaAgregarAlumnos.inicializar(controlVista, this);
    }//GEN-LAST:event_AgregarAlumnos

    /**
     * Muestra un mensaje de confirmacion para cancelar y regresa a la vista
     * principal.
     *
     * @param evt Recibe el evento del boton que lo activo.
     */
    private void Cancelar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cancelar
        int ok = JOptionPane.showConfirmDialog(this, "¿Estás segur@ de que "
                + "quieres cancelar la operación?\nTodos los cambios no "
                + "guardados se perderán");
        if (ok == 0) {
            limpiar();
            controlVista.liberarMemoriaModificar();
            padre.mostrarVista(Vista.ConsultarGrupo);
        }
    }//GEN-LAST:event_Cancelar

    /**
     * Llama a encapsular grupo y lo manda al controlador de la vista para que
     * lo guarde.
     *
     * @param evt Recibe el evento del boton que lo activo.
     */
    private void GuardarGrupo(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarGrupo
        GrupoDTO grupo = encapsularGrupo();
        if (grupo != null) {
            boolean ok = controlVista.modificarGrupo(grupo);
            if (ok) {
                JOptionPane.showMessageDialog(this, "Grupo modificado "
                        + "correctamente.", "Exito", 1);
                padre.mostrarVista(Vista.ConsultarGrupo);
                limpiar();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo modificar!",
                        "Error", 3);
            }
        }
    }//GEN-LAST:event_GuardarGrupo

    /**
     * Verifica que alumnos se seleccionaron de la tabla y obtiene sus indices,
     * los envia al controlador de la vista y al metodo de remover alumnos para
     * su eliminacion.
     *
     * @param evt Recibe el evento del boton que lo activo.
     */
    private void RemoverAlumnos(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoverAlumnos
        List<Integer> indexes = new ArrayList<>();
        List<UsuarioDTO> eliminados = new ArrayList<>();
        int cont = tblAlumnos.getRowCount();
        for (int i = 0; i < cont; i++) {
            if (tblAlumnos.getValueAt(i, 0).equals(true)) {
                UsuarioDTO alumno = new UsuarioDTO();
                indexes.add(i);
                alumno.setId(Integer.parseInt((String) tblAlumnos.getValueAt(i, 1)));
                alumno.setNombre((String) tblAlumnos.getValueAt(i, 2));
                alumno.setApellidoPaterno((String) tblAlumnos.getValueAt(i, 3));
                alumno.setApellidoMaterno((String) tblAlumnos.getValueAt(i, 4));
                eliminados.add(alumno);
            }
        }
        if (cont == 0 || indexes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debes seleccionar al menos un "
                    + "alumno", "Advertencia", 2);
        } else {
            controlVista.removerAlumnos(indexes);
            controlVista.agregarEliminados(eliminados);
            removerAlumnos(indexes);
        }
    }//GEN-LAST:event_RemoverAlumnos

    /**
     * Llama al frame de agregar maestros.
     *
     * @param evt Recibe el evento del boton que lo activo.
     */
    private void AgregarMaestro(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarMaestro
        vistaAgregarMaestro = new FrmAgregarMaestro();
        vistaAgregarMaestro.inicializar(controlVista, this);
    }//GEN-LAST:event_AgregarMaestro

    /**
     * Verifica que maestros se seleccionaron de la tabla y obtiene sus indices,
     * los envia al controlador de la vista y al metodo de remover maestros para
     * su eliminacion.
     *
     * @param evt Recibe el evento del boton que lo activo.
     */
    private void RemoverMaestro(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoverMaestro
        String nombreCurso;
        int index = tblMaestros.getSelectedRow();
        if (index != -1) {
            DefaultTableModel model = (DefaultTableModel) tblMaestros.getModel();
            nombreCurso = String.valueOf(model.getValueAt(index, 4));
            controlVista.removerMaestro(nombreCurso);
            removerMaestro(nombreCurso);
        } else {
            JOptionPane.showMessageDialog(this, "Debes seleccionar un maestro.",
                    "Advertencia", 2);
        }
    }//GEN-LAST:event_RemoverMaestro

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
        //No implementado.
        return null;
    }

    @Override
    public InterfaceVista getPadre() {
        //No implementado.
        return null;
    }
}
