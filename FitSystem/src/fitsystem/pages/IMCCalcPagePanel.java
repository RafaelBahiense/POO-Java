package fitsystem.pages;


import fitsystem.Calculator;
import fitsystem.State;
import fitsystem.actions.InsertClientHealthMetrics;
import fitsystem.entities.Client;
import fitsystem.entities.ClientHealthMetrics;

import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import javax.swing.*;


public class IMCCalcPagePanel extends JPanel {
    
    private GroupLayout layoutManager;
    private JLabel title;
    private JLabel nameLabel;
    private JLabel nameFieldLabel;
    private JLabel ageLabel;
    private JLabel ageFieldLabel;
    private JLabel genderLabel;
    private JLabel genderFieldLabel;
    private JLabel heightLabel;
    private JTextField heightInput;
    private JLabel weightLabel;
    private JTextField weightInput;
    private JButton goToResultPageButton;
    private Runnable goToResultPageButtonActionPerformaded;
    private InsertClientHealthMetrics insertClientHealthMetrics;
    private State state;
    
    
    public IMCCalcPagePanel(Runnable goToResultPageButtonActionPerformaded, InsertClientHealthMetrics insertClientHealthMetrics, State state) {
        this.goToResultPageButtonActionPerformaded = goToResultPageButtonActionPerformaded;
        this.insertClientHealthMetrics = insertClientHealthMetrics;
        this.state = state;
        InitComponents();
    }
    
    private void InitComponents() {
        layoutManager = new GroupLayout(this);
        
        title = new JLabel("Preencha os Dados de Saúde");
        title.setFont(new Font("Segoe UI", 0, 24));
        title.setHorizontalAlignment(JLabel.CENTER);
        
        nameLabel = new JLabel("Nome: ");
        nameFieldLabel = new JLabel(state.CurrentClient.Name);

        ageLabel = new JLabel("Idade: ");
        ageFieldLabel = new JLabel(String.valueOf(state.CurrentClient.Age));

        genderLabel = new JLabel("Genero: ");
        genderFieldLabel = new JLabel(String.valueOf(state.CurrentClient.Gender));
        
        heightLabel = new JLabel("Altura:");
        heightInput = new JTextField();
        
        weightLabel = new JLabel("Peso:  ");
        weightInput = new JTextField();
        
        goToResultPageButton = new JButton("Calcular");
        goToResultPageButton.addActionListener(e -> {

            var heightValue = heightInput.getText();
            var heightValueDouble = 0.0;
            try {
                heightValueDouble = Double.parseDouble(heightValue);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Altura inválida");
                return;
            }

            if(heightValueDouble < 1) {
                JOptionPane.showMessageDialog(null, "Altura inválida");
                return;
            }

            var weightValue = weightInput.getText();
            var weightValueDouble = 0.0;
            try {
                weightValueDouble = Double.parseDouble(weightValue);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Peso inválido");
                return;
            }

            if(weightValueDouble < 1) {
                JOptionPane.showMessageDialog(null, "Peso inválido");
                return;
            }

            var clientHealthMetrics = new ClientHealthMetrics();
            clientHealthMetrics.ClientId = state.CurrentClient.Id;
            clientHealthMetrics.DateRecorded = new Date(System.currentTimeMillis());
            clientHealthMetrics.Height = BigDecimal.valueOf(heightValueDouble);
            clientHealthMetrics.Weight = BigDecimal.valueOf(weightValueDouble);
            clientHealthMetrics.ImcValue = BigDecimal.valueOf(Calculator.getIMC(heightValueDouble, weightValueDouble));

            state.CurrentClientHealthMetrics = clientHealthMetrics;

            try {
                insertClientHealthMetrics.insertClientHealthMetrics(clientHealthMetrics);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            heightInput.setText("");
            weightInput.setText("");

            goToResultPageButtonActionPerformaded.run();
        });
        goToResultPageButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));


        setLayout(layoutManager);
        layoutManager.setHorizontalGroup(
                layoutManager.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layoutManager.createSequentialGroup()
                                .addGroup(layoutManager.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layoutManager.createSequentialGroup()
                                                .addGap(102, 102, 102)
                                                .addComponent(title))
                                        .addGroup(layoutManager.createSequentialGroup()
                                                .addGap(190, 190, 190)
                                                .addComponent(goToResultPageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layoutManager.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(layoutManager.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(genderLabel)
                                                        .addComponent(nameLabel)
                                                        .addComponent(ageLabel))
                                                .addGap(26, 26, 26)
                                                .addGroup(layoutManager.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(ageFieldLabel)
                                                        .addComponent(nameFieldLabel)
                                                        .addComponent(genderFieldLabel)))
                                        .addGroup(layoutManager.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(layoutManager.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(heightLabel)
                                                        .addComponent(weightLabel))
                                                .addGap(18, 18, 18)
                                                .addGroup(layoutManager.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(weightInput, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(heightInput, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(73, Short.MAX_VALUE))
        );
        layoutManager.setVerticalGroup(
                layoutManager.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layoutManager.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(title)
                                .addGap(105, 105, 105)
                                .addGroup(layoutManager.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(nameLabel)
                                        .addComponent(nameFieldLabel))
                                .addGap(41, 41, 41)
                                .addGroup(layoutManager.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(ageLabel)
                                        .addComponent(ageFieldLabel))
                                .addGap(41, 41, 41)
                                .addGroup(layoutManager.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(genderLabel)
                                        .addComponent(genderFieldLabel))
                                .addGap(33, 33, 33)
                                .addGroup(layoutManager.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(heightLabel)
                                        .addComponent(heightInput, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25)
                                .addGroup(layoutManager.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(weightLabel)
                                        .addComponent(weightInput, GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                                .addComponent(goToResultPageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30))
        );
    }

    public void ReRender() {
        removeAll();
        InitComponents();
    }
    
}
