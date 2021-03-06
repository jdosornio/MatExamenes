/*
 * Copyright (C) 2015 Jesús Donaldo Osornio Hernández
 *
 * This file is part of MatExámenes.
 *
 * MatExámenes is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * MatExámenes is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 51
 * Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package vista.ui;

import java.awt.Component;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;
import modelo.dto.CursoDTO;
import modelo.dto.ExamenDTO;
import modelo.dto.ExamenDTO.Permiso;
import modelo.dto.ReactivoDTO;
import modelo.dto.UsuarioDTO;
import vista.controlador.CVMantenerExamenes;
import vista.controlador.Validador;
import vista.interfaz.InterfaceExamen;
import vista.interfaz.InterfaceVista;

/**
 * JPanel que mostrará la interfaz gráfica de Registrar Examen
 *
 * @author Jesus Donaldo Osornio Hernández
 * @version 1 18 Mayo 2015
 */
public class VistaGenerarExamen extends javax.swing.JPanel implements
        InterfaceVista, InterfaceExamen, FocusListener, AncestorListener,
        KeyListener {

    /**
     * Controlador de la vista del caso de uso mantener exámenes, funciona para
     * manejar la información obtenida en la vista para comunicarse con las
     * capas inferiores
     */
    private CVMantenerExamenes controlVista;
    /**
     * Interface de comunicación con la interfaz padre, la cuál sirve para
     * comunicarse con ella y recibir mensajes para mostrar otras vistas. En ese
     * caso es utilizada para comunicarse con el JFrame Principal
     */
    private InterfaceVista padre;
    /**
     * JFrame utilizado para agregar reactivos de forma manual o aleatoria
     */
    private final FrmAgregarReactivos frmAgregarReactivos;
    /**
     * JFrame utilizado para ver los detalles de un reactivo a selección
     */
    private final FrmVerReactivo frmVerReactivo;

    /**
     * Objeto que agrupa lógicamente los radio buttons para seleccionar el tipo
     * de permiso del examen
     */
    private final ButtonGroup permiso;

    /**
     * Almacena el icono del estado correcto.
     */
    private final ImageIcon ICONO_BIEN;
    /**
     * Almacena el icono del estado incorrecto.
     */
    private final ImageIcon ICONO_MAL;

    /**
     * Cadena para almacenar el mensaje que se cree en caso de que los campos de
     * la interfaz no cumplan con el formato esperado
     */
    private String mensajeDatosIncorrectos;

    /**
     * Bandera que sirve para que el evento de cambio no se dispare en los
     * combobox al ingresar nuevos datos, para evitar que el sistema tenga
     * resultados inesperados
     */
    private boolean noSelect;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnAgregarClave;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnDesbloquear;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnRemover;
    private javax.swing.JButton btnRemoverClave;
    private javax.swing.JButton btnVer;
    private javax.swing.JComboBox cmbCurso;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblClaves;
    private javax.swing.JLabel lblCurso;
    private javax.swing.JLabel lblEstadoInstrucciones;
    private javax.swing.JLabel lblEstadoNombre;
    private javax.swing.JLabel lblInstrucciones;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlPermiso;
    private javax.swing.JRadioButton rbtnPrivado;
    private javax.swing.JRadioButton rbtnPublico;
    private javax.swing.JTabbedPane tbpClaves;
    private javax.swing.JTextArea txtaInstrucciones;
    private javax.swing.JTextField txtfNombre;
    // End of variables declaration//GEN-END:variables

    /**
     * Crea un objeto VistaRegistrarExamen e inicializa sus atributos, oculta
     * las etiquetas para mostrar el estado de los campos de la vista,
     * inicializa los frames para agregar y ver reactivos y agrega los listeners
     * necesarios
     */
    public VistaGenerarExamen() {
        initComponents();

        ICONO_BIEN = new ImageIcon(getClass().getResource("/recursos/bien.png"));
        ICONO_MAL = new ImageIcon(getClass().getResource("/recursos/mal.png"));

        lblEstadoNombre.setVisible(false);
        lblEstadoInstrucciones.setVisible(false);

        //Inicializa los frames
        frmAgregarReactivos = new FrmAgregarReactivos();
        frmAgregarReactivos.setPadre(this);
        frmAgregarReactivos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frmVerReactivo = new FrmVerReactivo();
        frmVerReactivo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        permiso = new ButtonGroup();
        permiso.add(rbtnPublico);
        permiso.add(rbtnPrivado);

        //Agrega los listeners
        txtfNombre.addKeyListener(this);
        txtaInstrucciones.addKeyListener(this);

        addAncestorListener(this);

        cmbCurso.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!noSelect) {
                    //Una vez seleccionado un curso del cmbCurso se inhabilitan
                    //los cursos y se habilita el botón desbloquear
                    cmbCurso.setEnabled(false);
                    btnDesbloquear.setEnabled(true);
                }
            }

        });

        //Para hacer wrap de palabras en las instrucciones
        txtaInstrucciones.setWrapStyleWord(true);
    }

    /**
     * Almacena la interface del JFrame principal.
     *
     * @param padre Interface para interactuar con el JFrame principal.
     */
    public void setPadre(InterfaceVista padre) {
        this.padre = padre;
        frmVerReactivo.setPadre((JFrame) padre);
    }

    /**
     * Almacena el control de la vista
     *
     * @param controlVista El objeto encargado de realizar comunicar la vista
     * con las capas inferiores para acceder a los datos
     */
    public void setControlador(CVMantenerExamenes controlVista) {
        this.controlVista = controlVista;
        frmAgregarReactivos.setControlador(controlVista);
    }

    /**
     * Este método es utilizado para consultar y mostrar los cursos disponibles
     * en la base de datos, mediante la utilización del controlVista. En caso de
     * que no exista ningún curso se mostrará un mensaje y se regresará a la
     * vista principal.
     */
    private void consultarCursos() {
        //la lista de cursos obtenida desde la base de datos por el controlVista
        List<CursoDTO> cursos = controlVista.obtenerCursos();

        //Si hay cursos...
        if (cursos != null && !cursos.isEmpty()) {
            mostrarCursos(cursos);
        } else {
            //Si no hay mostrar un mensaje, regresar a la vista principal y
            //limpiar la vista actual
            JOptionPane.showMessageDialog(this, "No hay cursos");
            padre.mostrarVista(Vista.HOME);
            limpiar();
        }
    }

    /**
     * Este método es utilizado para mostrar una lista de cursos en el
     * componente comboBox de la vista para mostrar los cursos disponibles.
     *
     * @param cursos una lista de cursos CursoDTO a ser mostrada en el comboBox
     * de la vista
     */
    private void mostrarCursos(List<CursoDTO> cursos) {
        //Para limpiar el cmbCurso de información previa
        cmbCurso.removeAllItems();

        //Recorrer todos los elementos de la lista para mostrarlos en el comboBox
        for (CursoDTO curso : cursos) {
            cmbCurso.addItem(curso.getNombre());
        }

        //Deselecciona el comboBox y permite la funcionalidad correcta del
        //listener del cmbCurso al igualar la bandera a falso
        cmbCurso.setSelectedIndex(-1);
        noSelect = false;
    }

    /**
     * Crea un objeto de tipo ExamenDTO obteniendo los datos de los campos de
     * texto de la vista, también valida si la información capturada es
     * correcta, en caso de que no el objeto no se construye y se agrega la
     * información de la falla en una cadena mensajeDatosIncorrectos, de igual
     * forma se le muestra al usuario los campos donde falló por medio de las
     * etiquetas ICONO_BIEN, ICONO_MAL
     *
     * @return Un objeto ExamenDTO si los datos de los campos fueron
     * validos.<br>
     * Retorna null de otra forma.
     */
    private ExamenDTO encapsularExamen() {
        //El objeto examen donde se encapsularán todos los datos
        ExamenDTO examen = new ExamenDTO();
        mensajeDatosIncorrectos = "";

        //Datos del examen
        String nombre = txtfNombre.getText();
        String instrucciones = txtaInstrucciones.getText();
        Permiso opPermiso = null;

        //bandera que indica que el encapsulado fue correcto
        boolean ok = true;

        //Si el campo está vacío agregar el mensaje correspondiente y mostrar el
        //label correspondiente a un lado del campo
        if (Validador.estaVacio(nombre)) {
            ok = false;
            mensajeDatosIncorrectos = "* Nombre del Examen\n";
            mostrarLabelEstado(txtfNombre, false);
        } else {
            mostrarLabelEstado(txtfNombre, true);
        }

        if (Validador.estaVacio(instrucciones)) {
            ok = false;
            mensajeDatosIncorrectos += "* Instrucciones del examen\n";
            mostrarLabelEstado(txtaInstrucciones, false);
        } else {
            mostrarLabelEstado(txtaInstrucciones, true);
        }

        //Validación para acomodar el orden de los mensajes
        if (!ok) {
            mensajeDatosIncorrectos = "No se puede completar la operación, los "
                    + "siguientes campos necesitan ser corregidos:\n"
                    + mensajeDatosIncorrectos;
        }

        //Si no se seleccionó un tipo de permiso no se puede almacenar la
        //información del examen
        if (permiso.getSelection() == rbtnPublico.getModel()) {
            opPermiso = Permiso.Publico;
        } else if (permiso.getSelection() == rbtnPrivado.getModel()) {
            opPermiso = Permiso.Privado;
        } else {
            //selection == null
            ok = false;
            mensajeDatosIncorrectos += "Debes seleccionar un permiso para el "
                    + "examen\n";
        }

        //Si no se seleccionó un curso no se puede almacenar la información en
        //el examen
        if (cmbCurso.getSelectedIndex() == -1) {
            ok = false;
            mensajeDatosIncorrectos += "Debes seleccionar un curso para el "
                    + "examen\n";
        }

        //Si no hay por lo menos una clave de examen no se puede almacenar la
        //información del examen
        if (tbpClaves.getTabCount() == 0) {
            ok = false;
            mensajeDatosIncorrectos += "El examen debe contener por lo menos "
                    + "una clave";
        } else {
            //Buscar en todas las pestañas de las claves
            for (Component comp : tbpClaves.getComponents()) {
                PnlReactivosTab reactivosTab = (PnlReactivosTab) comp;

                //Llamar al PnlReactivosTab de cada pestaña para verificar si hay
                //reactivos o no
                if (reactivosTab.sinReactivos()) {
                    ok = false;
                    mensajeDatosIncorrectos += "No puede haber claves de examen sin "
                            + "reactivos\n";
                    break;
                }
            }
        }
        //Almacenar todos los datos obtenidos en el examen
        examen.setInstrucciones(instrucciones);
        examen.setPermiso(opPermiso);
        examen.setNombre(nombre);
        examen.setAutor(padre.obtenerUsuarioActual());
        examen.setFechaCreacion(new Date());
        examen.setFechaModificacion(new Date());

        //Si falló la validación regresar null
        if (!ok) {
            examen = null;
        } else {
            //Si no almacenar el curso seleccionado en controlVista
            controlVista.setCurso(cmbCurso.getSelectedIndex());
        }

        return examen;
    }

    /**
     * Este método es utilizado para remover de la tabla seleccionada las filas
     * de reactivos especificadas en indexesReactivo.
     *
     * @param indexesReactivo la lista de números de fila de la tabla, en el
     * orden en el que aparecen
     * @param tabla la tabla de la cuál se quieren eliminar las filas ingresadas
     */
    private void removerReactivos(List<Integer> indexesReactivo, JTable tabla) {

        //Ordena la lista al inverso para poder eliminar secuencialmente
        Collections.sort(indexesReactivo, Collections.reverseOrder());

        //Eliminar las filas
        for (int index : indexesReactivo) {
            ((DefaultTableModel) tabla.getModel())
                    .removeRow(index);
        }
    }

    /**
     * Este método sirve para mostrar el objeto ReactivoDTO ingresado en el
     * frame Ver Reactivo. Este método deshabilita la vista y muestra en un
     * frame nuevo los datos del reactivo.
     *
     * @param reactivo el objeto ReactivoDTO que se desea mostrar.
     */
    private void mostrarReactivo(ReactivoDTO reactivo) {
        frmVerReactivo.inicializar(reactivo);
    }

    /**
     * Este método se encarga de mostrar la lista de objetos reactivo ingresada
     * en la tabla ingresada.
     *
     * @param reactivos la lista de ReactivoDTO que se quiere mostrar en la
     * tabla
     * @param tabla la tabla JTable en la cuál se quiere mostrar los datos de
     * los reactivos
     */
    private void mostrarReactivos(List<ReactivoDTO> reactivos, JTable tabla) {
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();

        //Mostrar cada reactivo, no remover, si no buscar por medio del for
        for (ReactivoDTO reactivo : reactivos) {
            Object[] datos = new Object[6];

            datos[0] = false;
            datos[1] = reactivo.getId();
            datos[2] = reactivo.getNombre();
            datos[3] = reactivo.getFechaCreacion();
            datos[4] = reactivo.getFechaModificacion();
            if (reactivo.getAutor() != null) {
                datos[5] = reactivo.getAutor().getUsuario();
            } else {
                datos[5] = "Sin autor";
            }

            model.addRow(datos);
        }
    }

    /**
     * Muestra el estado del campo de texto dependiendo si la validacion fue
     * verdadera o falsa.
     *
     * @param o El objeto campo de texto al cual se quiere cambiar el estado.
     * @param estado Si es verdadero el estado sera correcto, si es falso el
     * estado sera incorrecto.
     */
    private void mostrarLabelEstado(Object o, boolean estado) {
        JTextComponent ob = (JTextComponent) o;

        try {
            Field field = getClass().getDeclaredField(ob.getName());
            JLabel label = (JLabel) field.get(this);
            if (!label.isVisible()) {
                label.setVisible(true);
            }
            if (estado) {
                label.setIcon(ICONO_BIEN);
            } else {
                label.setIcon(ICONO_MAL);
            }
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(VistaRegistrarUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Inicializa los atributos gráficos y los coloca en su posición.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        tbpClaves = new javax.swing.JTabbedPane();
        lblTitulo = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        txtfNombre = new javax.swing.JTextField();
        txtfNombre.addFocusListener(this);
        txtfNombre.setName("lblEstadoNombre");
        lblInstrucciones = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtaInstrucciones = new javax.swing.JTextArea();
        txtaInstrucciones.addFocusListener(this);
        txtaInstrucciones.setName("lblEstadoInstrucciones");
        pnlPermiso = new javax.swing.JPanel();
        rbtnPrivado = new javax.swing.JRadioButton();
        rbtnPublico = new javax.swing.JRadioButton();
        lblCurso = new javax.swing.JLabel();
        cmbCurso = new javax.swing.JComboBox();
        btnRemoverClave = new javax.swing.JButton();
        lblClaves = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnVer = new javax.swing.JButton();
        btnDesbloquear = new javax.swing.JButton();
        btnAgregarClave = new javax.swing.JButton();
        lblEstadoNombre = new javax.swing.JLabel();
        lblEstadoInstrucciones = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(790, 579));

        jPanel4.setAutoscrolls(true);
        jPanel4.setPreferredSize(new java.awt.Dimension(790, 579));

        lblTitulo.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Generar Examen");

        lblNombre.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblNombre.setText("Nombre del Examen:");

        txtfNombre.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtfNombre.setToolTipText("");
        txtfNombre.setPreferredSize(new java.awt.Dimension(6, 30));

        lblInstrucciones.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblInstrucciones.setText("Instrucciones del Examen:");

        txtaInstrucciones.setColumns(20);
        txtaInstrucciones.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtaInstrucciones.setLineWrap(true);
        txtaInstrucciones.setRows(5);
        jScrollPane2.setViewportView(txtaInstrucciones);

        pnlPermiso.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Permiso:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N
        pnlPermiso.setToolTipText("");
        pnlPermiso.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        rbtnPrivado.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        rbtnPrivado.setText("Privado");
        rbtnPrivado.setToolTipText("El examen solo puede ser utilizado por el autor y ningún maestro lo puede ver");

        rbtnPublico.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        rbtnPublico.setText("Público");
        rbtnPublico.setToolTipText("El examen puede ser visto y utilizado por todos los maestros");

        javax.swing.GroupLayout pnlPermisoLayout = new javax.swing.GroupLayout(pnlPermiso);
        pnlPermiso.setLayout(pnlPermisoLayout);
        pnlPermisoLayout.setHorizontalGroup(
            pnlPermisoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPermisoLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(rbtnPublico)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rbtnPrivado)
                .addGap(27, 27, 27))
        );
        pnlPermisoLayout.setVerticalGroup(
            pnlPermisoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPermisoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPermisoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtnPublico, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbtnPrivado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblCurso.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblCurso.setText("Curso:");

        cmbCurso.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cmbCurso.setToolTipText("el curso al que pertence el examen");
        cmbCurso.setPreferredSize(new java.awt.Dimension(78, 25));

        btnRemoverClave.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnRemoverClave.setText("Remover Clave");
        btnRemoverClave.setToolTipText("remueve la clave seleccionada");
        btnRemoverClave.setPreferredSize(new java.awt.Dimension(77, 30));
        btnRemoverClave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removerClave(evt);
            }
        });

        lblClaves.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblClaves.setText("Claves:");

        btnGuardar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/guardar24.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setToolTipText("");
        btnGuardar.setPreferredSize(new java.awt.Dimension(77, 30));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarExamen(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/cancelar24.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setPreferredSize(new java.awt.Dimension(77, 30));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnRemover.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/remover24.png"))); // NOI18N
        btnRemover.setToolTipText("remover reactivos");
        btnRemover.setPreferredSize(new java.awt.Dimension(77, 30));
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removerReactivos(evt);
            }
        });

        btnAgregar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/agregar24.png"))); // NOI18N
        btnAgregar.setToolTipText("agregar reactivos");
        btnAgregar.setPreferredSize(new java.awt.Dimension(77, 30));
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarReactivos(evt);
            }
        });

        btnVer.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnVer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/ver24.png"))); // NOI18N
        btnVer.setToolTipText("ver reactivo");
        btnVer.setPreferredSize(new java.awt.Dimension(77, 30));
        btnVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verReactivo(evt);
            }
        });

        btnDesbloquear.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnDesbloquear.setText("Desbloquear");
        btnDesbloquear.setToolTipText("habilita la selección de cursos. Esto también remueve todas las claves de examen");
        btnDesbloquear.setActionCommand("Desbloquear");
        btnDesbloquear.setEnabled(false);
        btnDesbloquear.setPreferredSize(new java.awt.Dimension(77, 30));
        btnDesbloquear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                desbloquearCurso(evt);
            }
        });

        btnAgregarClave.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnAgregarClave.setText("Agregar Clave");
        btnAgregarClave.setToolTipText("agrega una nueva clave sin reactivos al examen");
        btnAgregarClave.setPreferredSize(new java.awt.Dimension(118, 30));
        btnAgregarClave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarClave(evt);
            }
        });

        lblEstadoNombre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/bien.png"))); // NOI18N
        lblEstadoNombre.setToolTipText("No ingresar datos vacios");

        lblEstadoInstrucciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/bien.png"))); // NOI18N
        lblEstadoInstrucciones.setToolTipText("No ingresar datos vacios");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(btnDesbloquear, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 335, Short.MAX_VALUE)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(166, 166, 166)
                        .addComponent(lblEstadoNombre))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(265, 265, 265)
                        .addComponent(lblEstadoInstrucciones)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(5, 5, 5)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 779, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                                        .addComponent(txtfNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE))
                                    .addGap(31, 31, 31))
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(cmbCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addGap(27, 27, 27)
                                                .addComponent(lblCurso))
                                            .addComponent(lblNombre)
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addGap(57, 57, 57)
                                                .addComponent(lblInstrucciones))
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addGap(49, 49, 49)
                                                .addComponent(pnlPermiso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)))
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(tbpClaves, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnVer, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(lblClaves)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addGap(84, 84, 84)
                                    .addComponent(btnAgregarClave, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(31, 31, 31)
                                    .addComponent(btnRemoverClave, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addContainerGap()))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(lblEstadoNombre))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(154, 154, 154)
                        .addComponent(lblEstadoInstrucciones)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 323, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(btnDesbloquear, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56))))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(13, 13, 13)
                    .addComponent(lblTitulo)
                    .addGap(42, 42, 42)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(lblClaves)
                                    .addGap(38, 38, 38)
                                    .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnVer, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addGap(37, 37, 37)
                                    .addComponent(tbpClaves, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnRemoverClave, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnAgregarClave, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                            .addComponent(lblNombre)
                            .addGap(13, 13, 13)
                            .addComponent(txtfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(lblInstrucciones)
                            .addGap(18, 18, 18)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(pnlPermiso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(lblCurso)
                            .addGap(18, 18, 18)
                            .addComponent(cmbCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(16, 16, 16)))
                    .addContainerGap(85, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Este método es utilizado para remover una clave del examen a guardar, la
     * clave es removida junto con todos los reactivos que tuviera. Este método
     * es invocado al seleccionar la opción Remover Clave
     *
     * @param evt objeto ActionEvent generado en el evento
     */
    private void removerClave(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removerClave

        //Si hay una clave seleccionada remover la clave del controlVista y de
        //la vista
        if (tbpClaves.getSelectedIndex() != -1) {
            controlVista.removerClave(tbpClaves.getSelectedIndex());
            tbpClaves.remove(tbpClaves.getSelectedIndex());
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una clave");
        }
    }//GEN-LAST:event_removerClave

    /**
     * Este método es llamado cuando el botón para guardar el examen es
     * seleccionado. Encapsula la información de la vista en un objeto examen y
     * lo almacena en la base de datos mediante el controlVista. En caso de que
     * la validación falle se muestra un mensaje al usuario. Si todo fue
     * correcto se limpian los campos para volver a ingresar otro registro
     *
     * @param evt el objeto ActionEvent generado por el evento, no es utilizado.
     */
    private void guardarExamen(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarExamen

        //El objeto ExamenDTO obtenido al encapsular los datos de la vista
        ExamenDTO examen = encapsularExamen();

        //Si la validación falló se muestra un mensaje de advertencia
        if (examen == null) {
            JOptionPane.showMessageDialog(this, mensajeDatosIncorrectos, "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            //Si la valdiación fue correcta se guarda el objeto mediante el
            //control vista
            Integer id = controlVista.guardarExamen(examen);

            if (id != null) {
                //Si la operación se realiza con éxito se muestra un mensaje
                //de confirmación y se limpian los datos de la vista
                //JOptionPane.showMessageDialog(this, "Registro Completo");
                limpiar();
                //Realizar todo como si fuera un nuevo registro
                noSelect = true;
                tbpClaves.add("Clave 1", new PnlReactivosTab());
                controlVista.agregarClave(1);
                consultarCursos();
            } else {
                //Si no se pudo guardar el examen mostrar un mensaje de error
                JOptionPane.showMessageDialog(this, "No se pudo guardar "
                        + "el examen", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_guardarExamen

    /**
     * Este método es llamado cuando se selecciona el botón de cancelar el
     * registro, lo que hace es pedir una confirmación de la operación mediante
     * un mensaje, en caso de que se acepte se vuelve a la vista principal.
     *
     * @param evt el objeto ActionEvent generado por el evento, no es utilizado
     */
    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        padre.mostrarVista(Vista.HOME);
        limpiar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    /**
     * Este método es utilizado para remover los reactivos seleccionados de una
     * clave seleccionada, todos los reactivos seleccionados son removidos de
     * esa clave. Este método es invocado al seleccionar la opción Remover
     * Reactivos
     *
     * @param evt objeto ActionEvent generado en el evento
     */
    private void removerReactivos(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removerReactivos

        //Validar que haya una clave seleccionada
        if (tbpClaves.getSelectedIndex() != -1) {
            //Obtener el PnlReactivoTab de esa pestaña
            PnlReactivosTab tab = (PnlReactivosTab) tbpClaves.getComponentAt(
                    tbpClaves.getSelectedIndex());

            //Obtener las filas seleccionadas
            List<Integer> indexesReactivo = tab.getSelectedRows();

            if (indexesReactivo.size() > 0) {

                //Remover los reactivos del controlVista y de la vista
                controlVista.removerReactivos(indexesReactivo, tbpClaves
                        .getSelectedIndex());
                removerReactivos(indexesReactivo, tab.getTabla());
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un reactivo");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una clave");
        }

    }//GEN-LAST:event_removerReactivos

    /**
     * Este método es utilizado para agregar mostrar el frame Agregar Reactivos
     * para agregar reactivos a la clave seleccionada. Este método es llamado
     * cuando se selecciona la opción Agregar Reactivos
     *
     * @param evt objeto ActionEvent generado en el evento
     */
    private void agregarReactivos(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarReactivos

        //Si hay un curso seleccionado y una clave seleccionada mostrar el
        //frame para agregar reactivos
        if (cmbCurso.getSelectedIndex() != -1) {
            if (tbpClaves.getSelectedIndex() != -1) {
                frmAgregarReactivos.inicializar(cmbCurso.getSelectedIndex(),
                        tbpClaves.getSelectedIndex());
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione una clave "
                        + "primero");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un curso primero");
        }
    }//GEN-LAST:event_agregarReactivos

    /**
     * Este método es llamado cuando se selecciona la opción Ver Reactivo, y es
     * utilizado para mostrar la información del reactivo seleccionado
     *
     * @param evt el objeto ActionEvent generado por el evento
     */
    private void verReactivo(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verReactivo
        //Si hay una clave seleccionada
        if (tbpClaves.getSelectedIndex() != -1) {
            //Obtener el pnlReactivosTab de la pestaña
            PnlReactivosTab tab = (PnlReactivosTab) tbpClaves.getComponentAt(
                    tbpClaves.getSelectedIndex());

            //Si hay un reactivo selecccionado obtener sus datos con el
            //controlVista y mostrarlos en el frame Ver Reactivo
            if (tab.getSelectedIndex() != -1) {
                ReactivoDTO reactivo = controlVista
                        .obtenerReactivo(tab.getSelectedIndex(),
                                tbpClaves.getSelectedIndex());

                mostrarReactivo(reactivo);
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un reactivo");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una clave");
        }
    }//GEN-LAST:event_verReactivo

    /**
     * Este método sirve para permitir al usuario seleccionar un curso
     * nuevamente, sin embargo, al momento de permitir seleccionar otro curso
     * todas las claves y sus reactivos previos se eliminarán, ya que en un
     * examen no puede haber reactivos de cursos diferentes. Este método es
     * llamado cuando se selecciona la opción Desbloquear Curso
     *
     * @param evt el objeto ActionEvent generado con el evento
     */
    private void desbloquearCurso(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_desbloquearCurso

        int ok = JOptionPane.showConfirmDialog(this, "¿Está seguro de que "
                + "desea desbloquear los cursos?\nTodas las claves del examen "
                + "se eliminarán", "Confirmación", JOptionPane.YES_NO_OPTION);

        if (ok == JOptionPane.YES_OPTION) {
            noSelect = true;

            cmbCurso.setEnabled(true);
            btnDesbloquear.setEnabled(false);
            tbpClaves.removeAll();
            controlVista.removerTodasLasClaves();
            cmbCurso.setSelectedIndex(-1);

            noSelect = false;
        }
    }//GEN-LAST:event_desbloquearCurso

    /**
     * Este método es utilizado para agregar una clave al examen a guardar, una
     * nueva clave vacía es agregada al examen. Este método es invocado al
     * seleccionar la opción Agregar Clave
     *
     * @param evt objeto ActionEvent generado en el evento
     */
    private void agregarClave(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarClave

        int nuevo = tbpClaves.getTabCount();

        //Obtiene el numero de la ultima tab
        int ultimaTab;
        //Crea la nueva clave con el nombre siguiente
        if (nuevo > 0) {
            ultimaTab = Integer.parseInt(tbpClaves.getTitleAt(nuevo - 1)
                    .substring(6));
        } else {
            ultimaTab = 0;
        }
        tbpClaves.add("Clave " + (ultimaTab + 1), new PnlReactivosTab());
        controlVista.agregarClave(ultimaTab + 1);
    }//GEN-LAST:event_agregarClave

    /**
     * Este método es utilizado por los frames de ver y agregar reactivos para
     * poder deshabilitar el padre de esta vista y así enfocarse sólo a la
     * función que se está realizando en el frame emergente
     *
     * @return una interface InterfaceVista que conecta con el padre de esta
     * vista
     */
    @Override
    public InterfaceVista getPadre() {
        return padre;
    }

    @Override
    public void limpiar() {
        txtfNombre.setText("");
        txtaInstrucciones.setText("");
        permiso.clearSelection();
        cmbCurso.removeAllItems();
        cmbCurso.setEnabled(true);
        btnDesbloquear.setEnabled(false);
        tbpClaves.removeAll();

        lblEstadoNombre.setVisible(false);
        lblEstadoInstrucciones.setVisible(false);

        controlVista.liberarMemoriaRegistrar();
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
        int ok = JOptionPane.showConfirmDialog(this, "¿Está seguro de que "
                + "desea cambiar de pantalla?\nTodos los cambios no "
                + "guardados se perderán", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (ok == JOptionPane.YES_OPTION) {
            cambiar = true;
        }
        return cambiar;
    }

    @Override
    public UsuarioDTO obtenerUsuarioActual() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void focusGained(FocusEvent e) {
        //
    }

    /**
     * Este método es invocado cuando un campo pierde el foco, sirve para
     * validar los campos y para mostrar su label correspondiente en caso de que
     * los campos estén correctos o incorrectos
     *
     * @param e el objeto FocusEvent que se obtiene del evento
     */
    @Override
    public void focusLost(FocusEvent e) {
        JTextComponent ob = (JTextComponent) e.getSource();

        mostrarLabelEstado(ob, !Validador.estaVacio(ob.getText()));

    }

    /**
     * Este método es llamado cada vez que se muestra esta vista en el frame
     * principal, sirve para realizar el método inicial al mostrar una vista,
     * como una consulta
     *
     * @param event el objeto AncestorEvent que se obtiene del evento
     */
    @Override
    public void ancestorAdded(AncestorEvent event) {
        //Consultar cursos y agregar una nueva clave
        if (isShowing()) {
            noSelect = true;
            tbpClaves.add("Clave 1", new PnlReactivosTab());
            controlVista.agregarClave(1);
            consultarCursos();
        }
    }

    @Override
    public void ancestorRemoved(AncestorEvent event) {
    }

    @Override
    public void ancestorMoved(AncestorEvent event) {
    }

    /**
     * Este método sirve para mostrar los reactivos en la clave seleccionada,
     * los cuales son enviados por el frame Agregar Reactivos al haber hecho la
     * selección.
     *
     * @param reactivos la lista de ReactivoDTO a mostrar
     * @param clave el índice de la clave donde se mostrarán los reactivos
     */
    @Override
    public void mostrarReactivos(List<ReactivoDTO> reactivos, int clave) {
        //Mostrar reactivos agregados
        JTable tabla = ((PnlReactivosTab) tbpClaves.getComponentAt(clave))
                .getTabla();

        mostrarReactivos(reactivos, tabla);
    }

    /**
     * Valida que los campos no acepten mas de los caracteres estipulados en la
     * base de datos.
     *
     * @param e Objeto que contiene información del evento.
     */
    @Override
    public void keyTyped(KeyEvent e) {
        JTextComponent campo = (JTextComponent) e.getSource();
        int longitud = 0;

        //Asignar la longitud máxima según el campo de la vista
        if (campo.getName().contains("Nombre")) {
            longitud = Validador.LONGITUD_NOMBRE_EXAMEN;
        } else if (campo.getName().contains("Instrucciones")) {
            longitud = Validador.LONGITUD_INSTRUCCIONES_EXAMEN;
        }

        //Verificar si se está pegando contenido mediante Ctrl + V
        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_V) {
            String portapapeles = "";

            try {
                //Obtener la cadena del portapapeles del sistema
                portapapeles = (String) Toolkit.getDefaultToolkit()
                        .getSystemClipboard().getData(DataFlavor.stringFlavor);
            } catch (UnsupportedFlavorException | IOException | ClassCastException ex) {
                System.out.println(ex);
            }

            //Si la longitud del campo de texto mas la cadena del portapapeles
            //Es mayor del máximo permitido evitar la acción...
            if (!Validador.validarLongitud(longitud, campo.getText() + portapapeles)) {
                e.consume();
                Toolkit.getDefaultToolkit().beep();
            }
        } //Si no hay acción de pegado pero aún así se llegó al límite de caracteres
        //evitar la acción
        else if (!Validador.validarLongitud(longitud, campo.getText())) {
            e.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }

    /**
     * Valida que los campos no acepten mas de los caracteres estipulados en la
     * base de datos.
     *
     * @param e Objeto que contiene información del evento.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        JTextComponent campo = (JTextComponent) e.getSource();
        int longitud = 0;

        //Asignar la longitud máxima según el campo de la vista
        if (campo.getName().contains("Nombre")) {
            longitud = Validador.LONGITUD_NOMBRE_EXAMEN;
        } else if (campo.getName().contains("Instrucciones")) {
            longitud = Validador.LONGITUD_INSTRUCCIONES_EXAMEN;
        }

        //Verificar si se está pegando contenido mediante Ctrl + V
        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_V) {
            String portapapeles = "";

            try {
                portapapeles = (String) Toolkit.getDefaultToolkit()
                        .getSystemClipboard().getData(DataFlavor.stringFlavor);
            } catch (UnsupportedFlavorException | IOException | ClassCastException ex) {
                System.out.println(ex);
            }

            //Si la longitud del campo de texto mas la cadena del portapapeles
            //Es mayor del máximo permitido evitar la acción...
            if (!Validador.validarLongitud(longitud, campo.getText() + portapapeles)) {
                e.consume();
                Toolkit.getDefaultToolkit().beep();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
