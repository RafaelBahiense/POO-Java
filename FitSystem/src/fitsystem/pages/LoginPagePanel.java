package fitsystem.pages;

import fitsystem.GridBagConstraintsFactory;
import fitsystem.actions.LoginFunction;
import fitsystem.entities.Admin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
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
    private LoginFunction loginFunction;
    
    public LoginPagePanel(Runnable loginButtonActionPerformaded, LoginFunction loginFunction) {
        this.loginButtonActionPerformaded = loginButtonActionPerformaded;
        this.loginFunction = loginFunction;
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
                var username = usernameInput.getText();
                var password = passwordInput.getText();
                Admin admin = null;
                try {
                    admin = loginFunction.Login(username, password);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                if (admin == null) {
                    JOptionPane.showMessageDialog(null, "Usuário não encontrado");
                    return;
                }

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
