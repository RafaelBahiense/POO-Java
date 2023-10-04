package fitsystem.pages;

import fitsystem.GridBagConstraintsFactory;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class AddClientPagePanel extends JPanel {
    private GridBagLayout layoutManager;
    
    private JLabel title;
    
    private JLabel nameLabel;
    private JTextField nameInput;
    
    private JLabel ageLabel;
    private JTextField ageInput;
    
    private JLabel genderLabel;
    private JTextField genderInput;
    
    private JLabel phoneLabel;
    private JTextField phoneInput;
    
    private JLabel addressLabel;
    private JTextField addressInput;
    
    private JButton addClientButton;
    private Runnable addClientButtonActionPerformaded;
    
    public AddClientPagePanel(Runnable addClientButtonActionPerformaded) {
        this.addClientButtonActionPerformaded = addClientButtonActionPerformaded;
        InitComponents();
    }
    
    private void InitComponents() {
        layoutManager = new GridBagLayout();
        
        title = new JLabel("Cadastro do Cliente");
        title.setFont(new Font("Segoe UI", 0, 24));
        title.setHorizontalAlignment(JLabel.CENTER);
        
        nameLabel = new JLabel("Nome: ");
        nameInput = new JTextField();
        
        ageLabel = new JLabel("Idade: ");
        ageInput = new JTextField();
        
        genderLabel = new JLabel("Genero: ");
        genderInput = new JTextField();
                
        phoneLabel = new JLabel("Telefone: ");
        phoneInput = new JTextField();
        
        addressLabel = new JLabel("Endereço: ");
        addressInput = new JTextField();
        
        addClientButton = new JButton("Cadastrar");
        
        
        addClientButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addClientButtonActionPerformaded.run();
            }
        });
        
        
        setLayout(layoutManager);
        add(title, GridBagConstraintsFactory.createGbc(0, 0));
        add(nameLabel, GridBagConstraintsFactory.createGbc(0, 1));
        add(nameInput, GridBagConstraintsFactory.createGbc(1, 1));
        add(ageLabel, GridBagConstraintsFactory.createGbc(0, 2));
        add(ageInput, GridBagConstraintsFactory.createGbc(1, 2));
        add(genderLabel, GridBagConstraintsFactory.createGbc(0, 3));
        add(genderInput, GridBagConstraintsFactory.createGbc(1, 3));
        add(phoneLabel, GridBagConstraintsFactory.createGbc(0, 4));
        add(phoneInput, GridBagConstraintsFactory.createGbc(1, 4));
        add(addressLabel, GridBagConstraintsFactory.createGbc(0, 5));
        add(addressInput, GridBagConstraintsFactory.createGbc(1, 5));
        add(addClientButton, GridBagConstraintsFactory.createGbc(0, 6));
    }
}
