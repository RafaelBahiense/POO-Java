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
    
    private GroupLayout layoutManager;
    
    private JLabel title;

    private JLabel logo;
    
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
        layoutManager = new GroupLayout(this);
        
        title = new JLabel("Login de Administrador");
        title.setFont(new Font("Segoe UI", 0, 24));
        title.setHorizontalAlignment(JLabel.CENTER);

        logo = new JLabel();
        logo.setIcon(new ImageIcon(getClass().getResource("/fitsystem/assets/Logo.png")));
        
        usernameLabel = new JLabel("Usuário: ");
        usernameInput = new JTextField();
        
        passwordLabel = new JLabel("Senha: ");
        passwordInput = new JTextField();
        
        loginButton = new JButton("Login");
        
        
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
        layoutManager.setHorizontalGroup(
            layoutManager.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layoutManager.createSequentialGroup()
                .addGroup(layoutManager.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layoutManager.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layoutManager.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(usernameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(12, 12, 12)
                            .addComponent(usernameInput, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layoutManager.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(passwordLabel)
                            .addGap(25, 25, 25)
                            .addComponent(passwordInput)))
                    .addGroup(layoutManager.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(title))
                    .addGroup(layoutManager.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(73, Short.MAX_VALUE))
            .addGroup(layoutManager.createSequentialGroup()
                .addGap(204, 204, 204)
                .addComponent(loginButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layoutManager.setVerticalGroup(
            layoutManager.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layoutManager.createSequentialGroup()
                .addContainerGap()
                .addComponent(title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(logo)
                .addGap(18, 18, 18)
                .addGroup(layoutManager.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usernameInput, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usernameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layoutManager.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordInput, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(loginButton)
                .addContainerGap(42, Short.MAX_VALUE))
        );
    }
}
