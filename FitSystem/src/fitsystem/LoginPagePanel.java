package fitsystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class LoginPagePanel extends JPanel {
    
    private GridBagLayout layoutManager;
    
    private JLabel title;
    
    private JLabel usernameLabel;
    private JTextField usernameInput;
    
    private JLabel passwordLabel;
    private JTextField passwordInput;
    
    private JButton loginButton;
    private Runnable loginButtonActionPerformaded;
    
    public LoginPagePanel(Runnable loginButtonActionPerformaded) {
        this.loginButtonActionPerformaded = loginButtonActionPerformaded;
        InitComponents();
    }
    
    private void InitComponents() {
        layoutManager = new GridBagLayout();
        
        title = new JLabel("Calculadora do IMC");
        title.setFont(new Font("Segoe UI", 0, 24));
        title.setHorizontalAlignment(JLabel.CENTER);
        
        usernameLabel = new JLabel("Usuário: ");
        usernameInput = new JTextField();
        
        passwordLabel = new JLabel("Senha: ");
        passwordInput = new JTextField();
        
        loginButton = new JButton("Logar");
        
        
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loginButtonActionPerformaded.run();
            }
        });
        
        
        setLayout(layoutManager);
        add(title, GridBagConstraintsFactory.createGbc(0, 0));
        add(usernameLabel, GridBagConstraintsFactory.createGbc(0, 1));
        add(usernameInput, GridBagConstraintsFactory.createGbc(1, 1));
        add(passwordLabel, GridBagConstraintsFactory.createGbc(0, 2));
        add(passwordInput, GridBagConstraintsFactory.createGbc(1, 2));
        add(loginButton, GridBagConstraintsFactory.createGbc(0, 3));
    }
}
