//Creado por: Yojhan Alonso Espinoza Sanchez
//Cedula: 402490298
package UI;
import Class.AlgoritmoDelBanquero;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class UI extends javax.swing.JFrame {

   
    public UI() {
        initComponents();
        this.Solicitar.setEnabled(false);
        this.Liberar.setEnabled(false);
        setResizable(false); 
        setLocationRelativeTo(null);
    }
/////////////////////////////////////////////////////////////////////
//Metodos
/////////////////////////////////////////////////////////////////////
AlgoritmoDelBanquero algoritmo;

public void capturar() {
    int procesos = Integer.parseInt(this.ProcesosBox.getText());
    int recursos = Integer.parseInt(this.RecursosBox.getText());
    algoritmo = new AlgoritmoDelBanquero(procesos, recursos);

    algoritmo.inicializarRecursos();
    
    actualizarModelosDeTabla();
}

public void solicitarRecursos() {
    int idProceso = Integer.parseInt(JOptionPane.showInputDialog(this, "ID del proceso:"));
    int[] solicitud = new int[algoritmo.getRecursosTotales().length];
    
    // Capturar la solicitud de recursos
    for (int i = 0; i < solicitud.length; i++) {
        solicitud[i] = Integer.parseInt(JOptionPane.showInputDialog(this, "Solicitud de recurso " + (i + 1) + ":"));
    }

    // Verificae la asignación y realizar la solicitud
    if (algoritmo.verificarAsignacion(idProceso, solicitud)) {
        algoritmo.solicitarRecursos(idProceso, solicitud);
        JOptionPane.showMessageDialog(this, "Solicitud concedida.");
    } else {
        JOptionPane.showMessageDialog(this, "Solicitud denegada.");
    }
    actualizarModelosDeTabla(); 
}

 public void liberar() {//Liberar proceso
        int idProceso = Integer.parseInt(JOptionPane.showInputDialog(this, "ID del proceso a liberar:"));
        
        // Llama al método liberarRecursos en AlgoritmoDelBanquero
        boolean exito = algoritmo.liberarRecursos(idProceso);
        
        if (exito) {
            JOptionPane.showMessageDialog(this, "Recursos liberados con éxito.");
        } else {
            JOptionPane.showMessageDialog(this, "No se pudieron liberar los recursos.");
        }
        actualizarModelosDeTabla();
    }
    
    
 //Actualizacion de las tablas   
public void actualizarModelosDeTabla() {
    
    DefaultTableModel modeloTotales = new DefaultTableModel();
    DefaultTableModel modeloDisponibles = new DefaultTableModel();
    DefaultTableModel modeloAsignados = new DefaultTableModel();
    DefaultTableModel modeloSolicitados = new DefaultTableModel();
    DefaultTableModel modeloNecesarios = new DefaultTableModel();


    String[] columnas = new String[algoritmo.getRecursosTotales().length];
    for (int i = 0; i < columnas.length; i++) {
        columnas[i] = "Recurso " + (i + 1);
    }
    modeloTotales.setColumnIdentifiers(columnas);
    modeloDisponibles.setColumnIdentifiers(columnas);
    modeloAsignados.setColumnIdentifiers(columnas);
    modeloSolicitados.setColumnIdentifiers(columnas);
    modeloNecesarios.setColumnIdentifiers(columnas);

    // Añadir los datos a las tablas
    // Recursos Totales
    Object[] filaTotales = new Object[algoritmo.getRecursosTotales().length];
    for (int i = 0; i < algoritmo.getRecursosTotales().length; i++) {
        filaTotales[i] = algoritmo.getRecursosTotales()[i];
    }
    modeloTotales.addRow(filaTotales);
    MatrizTotales.setModel(modeloTotales);

    // Recursos Disponibles
    Object[] filaDisponibles = new Object[algoritmo.getRecursosDisponibles().length];
    for (int i = 0; i < algoritmo.getRecursosDisponibles().length; i++) {
        filaDisponibles[i] = algoritmo.getRecursosDisponibles()[i];
    }
    modeloDisponibles.addRow(filaDisponibles);
    MatrizDisponibles.setModel(modeloDisponibles);

    // Recursos Asignados
    for (int i = 0; i < algoritmo.getRecursosAsignados().length; i++) {
        Object[] filaAsignados = new Object[algoritmo.getRecursosAsignados()[i].length];
        for (int j = 0; j < algoritmo.getRecursosAsignados()[i].length; j++) {
            filaAsignados[j] = algoritmo.getRecursosAsignados()[i][j];
        }
        modeloAsignados.addRow(filaAsignados);
    }
    MatrizAsignados.setModel(modeloAsignados);

    // Recursos Solicitados
    for (int i = 0; i < algoritmo.getRecursosSolicitados().length; i++) {
        Object[] filaSolicitados = new Object[algoritmo.getRecursosSolicitados()[i].length];
        for (int j = 0; j < algoritmo.getRecursosSolicitados()[i].length; j++) {
            filaSolicitados[j] = algoritmo.getRecursosSolicitados()[i][j];
        }
        modeloSolicitados.addRow(filaSolicitados);
    }
    MatrizSolicitados.setModel(modeloSolicitados);

    // Recursos Necesarios
    for (int i = 0; i < algoritmo.getRecursosNecesarios().length; i++) {
        Object[] filaNecesarios = new Object[algoritmo.getRecursosNecesarios()[i].length];
        for (int j = 0; j < algoritmo.getRecursosNecesarios()[i].length; j++) {
            filaNecesarios[j] = algoritmo.getRecursosNecesarios()[i][j];
        }
        modeloNecesarios.addRow(filaNecesarios);
    }
    MatrizNecesarios.setModel(modeloNecesarios);
}
    
    
////////////////////////////////////////////////////////////////////    
//Fin de Metodos
////////////////////////////////////////////////////////////////////    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BackGroundPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        MatrizTotales = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        MatrizDisponibles = new javax.swing.JTable();
        Disponibles = new javax.swing.JLabel();
        Totales = new javax.swing.JLabel();
        Asignados = new javax.swing.JLabel();
        Disponibles2 = new javax.swing.JLabel();
        Necesarios = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        MatrizAsignados = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        MatrizSolicitados = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        MatrizNecesarios = new javax.swing.JTable();
        ProcesosBox = new javax.swing.JTextField();
        RecursosBox = new javax.swing.JTextField();
        PocesosLabel = new javax.swing.JLabel();
        RecursosLabel = new javax.swing.JLabel();
        Aceptar = new javax.swing.JButton();
        Solicitar = new javax.swing.JButton();
        Liberar = new javax.swing.JButton();
        Totales1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        BackGroundPanel.setBackground(new java.awt.Color(0, 0, 102));

        MatrizTotales.setBackground(new java.awt.Color(51, 51, 51));
        MatrizTotales.setForeground(new java.awt.Color(0, 204, 204));
        MatrizTotales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(MatrizTotales);

        MatrizDisponibles.setBackground(new java.awt.Color(51, 51, 51));
        MatrizDisponibles.setForeground(new java.awt.Color(0, 204, 204));
        MatrizDisponibles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(MatrizDisponibles);

        Disponibles.setFont(new java.awt.Font("Showcard Gothic", 0, 12)); // NOI18N
        Disponibles.setForeground(new java.awt.Color(255, 255, 255));
        Disponibles.setText("Recursos Disponibles");

        Totales.setFont(new java.awt.Font("Showcard Gothic", 0, 12)); // NOI18N
        Totales.setForeground(new java.awt.Color(255, 255, 255));
        Totales.setText("Recursos Totales");

        Asignados.setFont(new java.awt.Font("Showcard Gothic", 0, 12)); // NOI18N
        Asignados.setForeground(new java.awt.Color(255, 255, 255));
        Asignados.setText("Recursos Asignados");

        Disponibles2.setFont(new java.awt.Font("Showcard Gothic", 0, 12)); // NOI18N
        Disponibles2.setForeground(new java.awt.Color(255, 255, 255));
        Disponibles2.setText("Recursos Solicitados");

        Necesarios.setFont(new java.awt.Font("Showcard Gothic", 0, 12)); // NOI18N
        Necesarios.setForeground(new java.awt.Color(255, 255, 255));
        Necesarios.setText("Recursos Necesarios");

        MatrizAsignados.setBackground(new java.awt.Color(51, 51, 51));
        MatrizAsignados.setForeground(new java.awt.Color(0, 204, 204));
        MatrizAsignados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(MatrizAsignados);

        MatrizSolicitados.setBackground(new java.awt.Color(51, 51, 51));
        MatrizSolicitados.setForeground(new java.awt.Color(0, 204, 204));
        MatrizSolicitados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane4.setViewportView(MatrizSolicitados);

        jScrollPane5.setBackground(new java.awt.Color(51, 51, 51));
        jScrollPane5.setBorder(null);

        MatrizNecesarios.setBackground(new java.awt.Color(51, 51, 51));
        MatrizNecesarios.setForeground(new java.awt.Color(0, 204, 204));
        MatrizNecesarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane5.setViewportView(MatrizNecesarios);

        ProcesosBox.setBackground(new java.awt.Color(51, 51, 51));
        ProcesosBox.setForeground(new java.awt.Color(255, 255, 255));
        ProcesosBox.setBorder(null);
        ProcesosBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ProcesosBoxKeyTyped(evt);
            }
        });

        RecursosBox.setBackground(new java.awt.Color(51, 51, 51));
        RecursosBox.setForeground(new java.awt.Color(255, 255, 255));
        RecursosBox.setBorder(null);
        RecursosBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RecursosBoxActionPerformed(evt);
            }
        });
        RecursosBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                RecursosBoxKeyTyped(evt);
            }
        });

        PocesosLabel.setFont(new java.awt.Font("Showcard Gothic", 0, 12)); // NOI18N
        PocesosLabel.setForeground(new java.awt.Color(255, 255, 255));
        PocesosLabel.setText("Cantidad de Procesos");

        RecursosLabel.setFont(new java.awt.Font("Showcard Gothic", 0, 12)); // NOI18N
        RecursosLabel.setForeground(new java.awt.Color(255, 255, 255));
        RecursosLabel.setText("Cantidad de Recursos");

        Aceptar.setBackground(new java.awt.Color(51, 51, 51));
        Aceptar.setFont(new java.awt.Font("Showcard Gothic", 0, 12)); // NOI18N
        Aceptar.setForeground(new java.awt.Color(255, 255, 255));
        Aceptar.setText("Aceptar");
        Aceptar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AceptarActionPerformed(evt);
            }
        });

        Solicitar.setBackground(new java.awt.Color(51, 51, 51));
        Solicitar.setFont(new java.awt.Font("Showcard Gothic", 0, 12)); // NOI18N
        Solicitar.setForeground(new java.awt.Color(255, 255, 255));
        Solicitar.setText("Solicitar Recursos");
        Solicitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SolicitarActionPerformed(evt);
            }
        });

        Liberar.setBackground(new java.awt.Color(51, 51, 51));
        Liberar.setFont(new java.awt.Font("Showcard Gothic", 0, 12)); // NOI18N
        Liberar.setForeground(new java.awt.Color(255, 255, 255));
        Liberar.setText("Liberar Proceso");
        Liberar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LiberarActionPerformed(evt);
            }
        });

        Totales1.setFont(new java.awt.Font("Showcard Gothic", 0, 24)); // NOI18N
        Totales1.setForeground(new java.awt.Color(255, 255, 255));
        Totales1.setText("Algoritmo del Banquero");

        javax.swing.GroupLayout BackGroundPanelLayout = new javax.swing.GroupLayout(BackGroundPanel);
        BackGroundPanel.setLayout(BackGroundPanelLayout);
        BackGroundPanelLayout.setHorizontalGroup(
            BackGroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BackGroundPanelLayout.createSequentialGroup()
                .addGroup(BackGroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BackGroundPanelLayout.createSequentialGroup()
                        .addGroup(BackGroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(BackGroundPanelLayout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addGroup(BackGroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(BackGroundPanelLayout.createSequentialGroup()
                                        .addComponent(PocesosLabel)
                                        .addGap(18, 18, 18)
                                        .addComponent(ProcesosBox, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(BackGroundPanelLayout.createSequentialGroup()
                                        .addComponent(RecursosLabel)
                                        .addGap(18, 18, 18)
                                        .addGroup(BackGroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(BackGroundPanelLayout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(Aceptar))
                                            .addComponent(RecursosBox, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(BackGroundPanelLayout.createSequentialGroup()
                                .addGap(367, 367, 367)
                                .addGroup(BackGroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(Solicitar)
                                    .addComponent(Liberar, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE))
                    .addGroup(BackGroundPanelLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(BackGroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(BackGroundPanelLayout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(Totales)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Disponibles)
                                .addGap(90, 90, 90))
                            .addGroup(BackGroundPanelLayout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(51, 51, 51)))))
                .addGroup(BackGroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BackGroundPanelLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(BackGroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BackGroundPanelLayout.createSequentialGroup()
                        .addComponent(Totales1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Asignados)
                        .addGap(113, 113, 113))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BackGroundPanelLayout.createSequentialGroup()
                        .addComponent(Disponibles2)
                        .addGap(110, 110, 110))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BackGroundPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(Necesarios)
                .addGap(109, 109, 109))
        );
        BackGroundPanelLayout.setVerticalGroup(
            BackGroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BackGroundPanelLayout.createSequentialGroup()
                .addGroup(BackGroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BackGroundPanelLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(BackGroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Asignados)
                            .addComponent(Totales1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BackGroundPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(BackGroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BackGroundPanelLayout.createSequentialGroup()
                                .addComponent(Disponibles)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BackGroundPanelLayout.createSequentialGroup()
                                .addComponent(Totales)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGroup(BackGroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BackGroundPanelLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(Disponibles2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(Necesarios)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(BackGroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(BackGroundPanelLayout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(Solicitar)
                                .addGap(18, 18, 18)
                                .addComponent(Liberar))
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(BackGroundPanelLayout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addGroup(BackGroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ProcesosBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PocesosLabel))
                        .addGap(32, 32, 32)
                        .addGroup(BackGroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(RecursosBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(RecursosLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Aceptar)))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        Totales1.getAccessibleContext().setAccessibleName("Header");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BackGroundPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BackGroundPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void RecursosBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RecursosBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RecursosBoxActionPerformed

    private void AceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AceptarActionPerformed
        try {
            if (!this.ProcesosBox.getText().isBlank() && !this.RecursosBox.getText().isBlank()) {
                capturar(); 
                actualizarModelosDeTabla();  
                this.ProcesosBox.setEnabled(false);  
                this.RecursosBox.setEnabled(false);
                this.Aceptar.setEnabled(false);
                this.Solicitar.setEnabled(true);
                this.Liberar.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, complete ambos campos antes de continuar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese valores numéricos válidos en ambos campos.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Ha ocurrido un error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_AceptarActionPerformed

    private void SolicitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SolicitarActionPerformed
        solicitarRecursos();
    }//GEN-LAST:event_SolicitarActionPerformed

    private void LiberarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LiberarActionPerformed
        liberar();
    }//GEN-LAST:event_LiberarActionPerformed

    private void ProcesosBoxKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ProcesosBoxKeyTyped
        char c = evt.getKeyChar();

        if (!Character.isDigit(c)) {
            evt.consume(); 
        }

        if (ProcesosBox.getText().isEmpty() && c == '0') {
            evt.consume(); 
        }
    }//GEN-LAST:event_ProcesosBoxKeyTyped

    private void RecursosBoxKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RecursosBoxKeyTyped
        char c = evt.getKeyChar();

        if (!Character.isDigit(c)) {
            evt.consume(); 
        }

        if (ProcesosBox.getText().isEmpty() && c == '0') {
            evt.consume(); 
        }
    }//GEN-LAST:event_RecursosBoxKeyTyped

  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Aceptar;
    private javax.swing.JLabel Asignados;
    private javax.swing.JPanel BackGroundPanel;
    private javax.swing.JLabel Disponibles;
    private javax.swing.JLabel Disponibles2;
    private javax.swing.JButton Liberar;
    private javax.swing.JTable MatrizAsignados;
    private javax.swing.JTable MatrizDisponibles;
    private javax.swing.JTable MatrizNecesarios;
    private javax.swing.JTable MatrizSolicitados;
    private javax.swing.JTable MatrizTotales;
    private javax.swing.JLabel Necesarios;
    private javax.swing.JLabel PocesosLabel;
    private javax.swing.JTextField ProcesosBox;
    private javax.swing.JTextField RecursosBox;
    private javax.swing.JLabel RecursosLabel;
    private javax.swing.JButton Solicitar;
    private javax.swing.JLabel Totales;
    private javax.swing.JLabel Totales1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    // End of variables declaration//GEN-END:variables
}
