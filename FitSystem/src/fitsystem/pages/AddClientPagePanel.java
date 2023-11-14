package fitsystem.pages;

import fitsystem.State;
import fitsystem.actions.InsertClientFunction;
import fitsystem.entities.Admin;
import fitsystem.entities.Client;
import fitsystem.entities.Gender;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.*;


public class AddClientPagePanel extends JPanel {

    private GroupLayout layoutManager;
    private JLabel titlelabel;
    private JLabel nameInputLabel;
    private JTextField nameInput;
    private JLabel ageSpinnerLabel;
    private JSpinner ageSpinner;
    private JLabel genderComboLabel;
    private JComboBox<String> genderCombo;
    private JLabel phoneInputLabel;
    private JTextField phoneInput;
    private JLabel addresslabelInput;
    private JTextField addressInput;
    private JButton continueButton;
    private Runnable addClientButtonActionPerformaded;
    private InsertClientFunction insertClientFunction;
    private State state;
    
    public AddClientPagePanel(Runnable addClientButtonActionPerformaded, InsertClientFunction insertClientFunction, State state) {
        this.addClientButtonActionPerformaded = addClientButtonActionPerformaded;
        this.insertClientFunction = insertClientFunction;
        this.state = state;
        InitComponents();
    }
    
    private void InitComponents() {
        titlelabel = new JLabel();
        titlelabel.setFont(new Font("Segoe UI", 0, 24)); // NOI18N
        titlelabel.setText("Cadastrar Novo Cliente");

        nameInputLabel = new JLabel();
        nameInputLabel.setText("Nome:");
        nameInput = new JTextField();

        ageSpinnerLabel = new JLabel();
        ageSpinnerLabel.setText("Idade:");
        ageSpinner = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));

        genderComboLabel = new JLabel();
        genderComboLabel.setText("Genero:");
        genderCombo = new JComboBox<>();
        genderCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { Gender.MALE.toString(), Gender.FEMALE.toString(), Gender.OTHER.toString() }));

        phoneInputLabel = new JLabel();
        phoneInputLabel.setText("Telefone:");
        phoneInput = new JTextField();

        addresslabelInput = new JLabel();
        addresslabelInput.setText("Endereço:");
        addressInput = new JTextField();

        continueButton = new JButton();
        continueButton.setText("Continuar");
        continueButton.addActionListener(e -> {

            var name = nameInput.getText();
            var age = (Integer) ageSpinner.getValue();
            var gender = genderCombo.getSelectedItem().toString();
            var phone = phoneInput.getText();
            var address = addressInput.getText();


            var client = new Client();
            client.Name = name;
            client.Age = age;
            client.Gender = Gender.valueOf(gender.toUpperCase());
            client.Phone = phone;
            client.Address = address;

            var clientId = 0;

            try {
                clientId = insertClientFunction.insertClient(client);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            if (clientId == 0) {
                JOptionPane.showMessageDialog(null, "Usuário não encontrado");
                return;
            }

            state.CurrentClient = client;
            state.CurrentClient.Id = clientId;

            addClientButtonActionPerformaded.run();
        });
        layoutManager = new javax.swing.GroupLayout(this);
        setLayout(layoutManager);
        layoutManager.setHorizontalGroup(
                layoutManager.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layoutManager.createSequentialGroup()
                                .addGroup(layoutManager.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layoutManager.createSequentialGroup()
                                                .addGap(102, 102, 102)
                                                .addComponent(titlelabel))
                                        .addGroup(layoutManager.createSequentialGroup()
                                                .addGap(179, 179, 179)
                                                .addComponent(continueButton))
                                        .addGroup(layoutManager.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(addresslabelInput)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(addressInput, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layoutManager.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(phoneInputLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(phoneInput, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layoutManager.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(ageSpinnerLabel))
                                        .addGroup(layoutManager.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(layoutManager.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(genderComboLabel)
                                                        .addComponent(nameInputLabel))
                                                .addGap(26, 26, 26)
                                                .addGroup(layoutManager.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(nameInput, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(ageSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(genderCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(61, Short.MAX_VALUE))
        );
        layoutManager.setVerticalGroup(
                layoutManager.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layoutManager.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(titlelabel)
                                .addGap(97, 97, 97)
                                .addGroup(layoutManager.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(nameInputLabel)
                                        .addComponent(nameInput, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25)
                                .addGroup(layoutManager.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(ageSpinnerLabel)
                                        .addComponent(ageSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25)
                                .addGroup(layoutManager.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(genderComboLabel)
                                        .addComponent(genderCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25)
                                .addGroup(layoutManager.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(phoneInputLabel)
                                        .addComponent(phoneInput, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25)
                                .addGroup(layoutManager.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(addresslabelInput)
                                        .addComponent(addressInput, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                                .addComponent(continueButton)
                                .addGap(49, 49, 49))
        );
    }

    public void ReRender() throws SQLException {
        nameInput.setText("");
        ageSpinner.setValue(0);
        genderCombo.setSelectedIndex(0);
        phoneInput.setText("");
        addressInput.setText("");
    }
}
