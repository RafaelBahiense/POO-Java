package fitsystem.pages;

import fitsystem.Calculator;
import fitsystem.State;
import fitsystem.actions.GetClientHealthMetrics;
import fitsystem.actions.UpdateClientFunction;
import fitsystem.entities.Client;
import fitsystem.entities.ClientHealthMetrics;
import fitsystem.entities.Gender;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.sql.SQLException;

public class UpdateClientPagePanel extends JPanel {

    private JTextField addressInput;
    private JLabel addressLabel;
    private JLabel ageLabel;
    private JSpinner ageSpinner;
    private JButton cancelButton;
    private JComboBox<String> genderCombo;
    private JLabel genderLabel;
    private JTextField heightInput;
    private JLabel heightLabel;
    private JTextField nameInput;
    private JLabel nameLabel;
    private JTextField phoneInput;
    private JLabel phoneLabel;
    private JButton saveButton;
    private JLabel title;
    private JTextField weightInput;
    private JLabel weightLabel;

    private Runnable returnToAddClientsPage;
    private UpdateClientFunction updateClientFunction;
    private GetClientHealthMetrics getClientHealthMetrics;
    private State state;


    public UpdateClientPagePanel(Runnable returnToAddClientsPage, UpdateClientFunction updateClientFunction, GetClientHealthMetrics getClientHealthMetrics, State state) throws SQLException {
        this.returnToAddClientsPage = returnToAddClientsPage;
        this.updateClientFunction = updateClientFunction;
        this.getClientHealthMetrics = getClientHealthMetrics;
        this.state = state;
        initComponents();
    }

    private void initComponents() throws SQLException {


        title = new JLabel();
        nameLabel = new JLabel();
        nameInput = new JTextField(state.CurrentClient.Name);
        ageLabel = new JLabel();
        ageSpinner = new JSpinner(new SpinnerNumberModel(state.CurrentClient.Age, 0, Integer.MAX_VALUE, 1));
        genderLabel = new JLabel();
        genderCombo = new JComboBox<>(new DefaultComboBoxModel<>(new String[] { Gender.MALE.toString(), Gender.FEMALE.toString(), Gender.OTHER.toString() }));
        genderCombo.getModel().setSelectedItem(state.CurrentClient.Gender.toString());
        phoneLabel = new JLabel();
        phoneInput = new JTextField(state.CurrentClient.Phone);
        addressLabel = new JLabel();
        addressInput = new JTextField(state.CurrentClient.Address);
        heightLabel = new JLabel();
        heightInput = new JTextField(state.CurrentClientHealthMetrics.Height.toString());
        weightLabel = new JLabel();
        weightInput = new JTextField(state.CurrentClientHealthMetrics.Weight.toString());
        cancelButton = new JButton();
        saveButton = new JButton();

        title.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
        title.setText("Editando Cliente");

        nameLabel.setText("Nome:");

        ageLabel.setText("Idade:");

        genderLabel.setText("Genero:");

        genderCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { Gender.MALE.toString(), Gender.FEMALE.toString(), Gender.OTHER.toString() }));

        phoneLabel.setText("Telefone:");

        addressLabel.setText("Endereço:");

        heightLabel.setText("Altura:");

        weightLabel.setText("Peso:");

        cancelButton.setText("Cancelar");
        cancelButton.addActionListener(evt -> cancelButtonActionPerformed(evt));

        saveButton.setText("Salvar");
        saveButton.addActionListener(evt -> {
            try {
                saveButtonActionPerformed(evt);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(181, 181, 181)
                                                .addComponent(title))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(16, 16, 16)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(nameLabel)
                                                                        .addComponent(ageLabel))
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addGap(170, 170, 170)
                                                                                .addComponent(genderLabel)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(genderCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addGap(30, 30, 30)
                                                                                .addComponent(nameInput, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(phoneLabel)
                                                                .addGap(18, 18, 18)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(ageSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(phoneInput, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(addressLabel)
                                                                        .addComponent(heightLabel))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(cancelButton)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addComponent(saveButton))
                                                                        .addComponent(addressInput, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(heightInput, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(weightLabel)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(weightInput)))))))
                                .addContainerGap(100, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(title)
                                .addGap(53, 53, 53)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(nameInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(nameLabel))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(ageLabel)
                                                .addComponent(ageSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(genderLabel)
                                                .addComponent(genderCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(phoneLabel)
                                        .addComponent(phoneInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(addressLabel)
                                        .addComponent(addressInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(heightLabel)
                                        .addComponent(heightInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(weightLabel)
                                        .addComponent(weightInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(77, 77, 77)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cancelButton)
                                        .addComponent(saveButton))
                                .addContainerGap(161, Short.MAX_VALUE))
        );
    }

    private void saveButtonActionPerformed(ActionEvent evt) throws SQLException {
        state.CurrentClient.Name = nameInput.getText();
        state.CurrentClient.Age = (int) ageSpinner.getValue();
        state.CurrentClient.Gender = Gender.valueOf(genderCombo.getSelectedItem().toString().toUpperCase());
        state.CurrentClient.Address = addressInput.getText();
        state.CurrentClient.Phone = phoneInput.getText();

        state.CurrentClientHealthMetrics.Height = new BigDecimal(heightInput.getText());
        state.CurrentClientHealthMetrics.Weight = new BigDecimal(weightInput.getText());
        state.CurrentClientHealthMetrics.ClientId = state.CurrentClient.Id;
        state.CurrentClientHealthMetrics.DateRecorded = new java.sql.Date(System.currentTimeMillis());
        state.CurrentClientHealthMetrics.ImcValue = Calculator.getIMC(state.CurrentClientHealthMetrics.Height.doubleValue(), state.CurrentClientHealthMetrics.Weight.doubleValue());

        updateClientFunction.UpdateClient(state.CurrentClient, state.CurrentClientHealthMetrics);

        state.Reset();
        returnToAddClientsPage.run();
    }

    private void cancelButtonActionPerformed(ActionEvent evt) {
        state.Reset();
        returnToAddClientsPage.run();
    }

    public void ReRender() throws SQLException {
        var metrics = getClientHealthMetrics.GetClientHelthMetrics(state.CurrentClient.Id);

        state.CurrentClientHealthMetrics = metrics.get(0);

        nameInput.setText(state.CurrentClient.Name);
        ageSpinner .setValue(state.CurrentClient.Age);
        genderCombo.getModel().setSelectedItem(state.CurrentClient.Gender.toString());
        phoneInput.setText(state.CurrentClient.Phone);
        addressInput.setText(state.CurrentClient.Address);
        heightInput.setText(state.CurrentClientHealthMetrics.Height.toString());
        weightInput.setText(state.CurrentClientHealthMetrics.Weight.toString());
    }
}
