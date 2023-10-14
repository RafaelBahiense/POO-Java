package fitsystem.pages;

import fitsystem.actions.GetClientsFunction;
import fitsystem.entities.Client;

import javax.swing.*;
import java.awt.*;
import javax.swing.BorderFactory;
import java.sql.SQLException;
import java.util.List;

public class ClientsPagePanel extends JPanel {

    private BoxLayout layoutManager;

    private JPanel addClientPanel;
    private JPanel clientsListPanel;
    private Runnable goToAddClientPageActionPerformaded;
    private GetClientsFunction getClientsFunction;
    private List<Client> clients;

    public ClientsPagePanel(Runnable goToAddClientPageActionPerformaded, GetClientsFunction getClientsFunction) throws SQLException {
        this.goToAddClientPageActionPerformaded = goToAddClientPageActionPerformaded;
        this.getClientsFunction = getClientsFunction;
        clients = getClientsFunction.GetClients();
        InitComponents();
    }

    private void InitComponents() {
        addClientPanel = new AddClientPanel(goToAddClientPageActionPerformaded, clients.size());
        clientsListPanel = new ClientsListPanel();
        layoutManager = new BoxLayout(this, BoxLayout.Y_AXIS);

        setLayout(layoutManager);
        add(addClientPanel);
        add(Box.createVerticalStrut(10));
        add(clientsListPanel);
    }

    public void ReRender() throws SQLException {
        clients = getClientsFunction.GetClients();
        removeAll();
        InitComponents();
    }
}

class AddClientPanel extends JPanel {
    
    private GroupLayout layoutManager;

    private int clientsCount;
    private JLabel title;
    private JLabel clientsCountLabel;
    private JButton addClientButton;

    private Runnable goToAddClientPageActionPerformaded;

    public AddClientPanel(Runnable goToAddClientPageActionPerformaded, int clientsCount) {
        this.clientsCount = clientsCount;
        this.goToAddClientPageActionPerformaded = goToAddClientPageActionPerformaded;
        InitComponents();
    }

    private void InitComponents() {
        layoutManager = new GroupLayout(this);
        
        title = new JLabel();
        title.setFont(new Font("Segoe UI", 0, 24)); // NOI18N
        title.setText("Clientes Cadastrados");

        clientsCountLabel = new JLabel();
        clientsCountLabel.setText("Clientes: " + clientsCount);

        addClientButton = new JButton();
        addClientButton.setText("Adicionar");
        addClientButton.addActionListener(evt -> goToAddClientPageActionPerformaded.run());

        setLayout(layoutManager);
        layoutManager.setHorizontalGroup(
            layoutManager.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layoutManager.createSequentialGroup()
                .addContainerGap(141, Short.MAX_VALUE)
                .addComponent(title)
                .addGap(138, 138, 138))
            .addGroup(layoutManager.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(clientsCountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addClientButton)
                .addGap(24, 24, 24))
        );
        layoutManager.setVerticalGroup(
            layoutManager.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layoutManager.createSequentialGroup()
                .addContainerGap()
                .addComponent(title)
                .addGap(55, 55, 55)
                .addGroup(layoutManager.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clientsCountLabel)
                    .addComponent(addClientButton))
                .addGap(34, 34, 34))
        );
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    }

    @Override
    public Dimension getMaximumSize() {
        return new Dimension(480, super.getMaximumSize().height);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(super.getPreferredSize().width, 140);
    }
}

class ClientsListPanel extends JPanel {

    public ClientsListPanel() {
        InitComponents();
    }

    private void InitComponents() {;
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    }

    @Override
    public Dimension getMaximumSize() {
        return new Dimension(480, super.getMaximumSize().height);
    }
}
