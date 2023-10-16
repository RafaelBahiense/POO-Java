package fitsystem.pages;

import fitsystem.State;
import fitsystem.actions.DeleteClientFunction;
import fitsystem.actions.GetClientsFunction;
import fitsystem.entities.Client;

import javax.swing.*;
import java.awt.*;
import javax.swing.BorderFactory;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientsPagePanel extends JPanel {

    private BoxLayout layoutManager;

    private JPanel addClientPanel;
    private JPanel clientsListPanel;
    private JScrollPane clientsListScrollPane;
    private Runnable goToAddClientPageActionPerformaded;
    private GetClientsFunction getClientsFunction;
    private DeleteClientFunction deleteClientFunction;
    private State state;

    public ClientsPagePanel(Runnable goToAddClientPageActionPerformaded, GetClientsFunction getClientsFunction, DeleteClientFunction deleteClientFunction, State state) throws SQLException {
        this.goToAddClientPageActionPerformaded = goToAddClientPageActionPerformaded;
        this.getClientsFunction = getClientsFunction;
        this.deleteClientFunction = deleteClientFunction;
        this.state = state;
        this.state.Clients = getClientsFunction.GetClients();
        InitComponents();
    }

    private void InitComponents() {
        addClientPanel = new AddClientPanel(goToAddClientPageActionPerformaded, state);
        clientsListPanel = new ClientsListPanel(deleteClientFunction, state);

        clientsListScrollPane = new JScrollPane(clientsListPanel);
        clientsListScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        clientsListScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        layoutManager = new BoxLayout(this, BoxLayout.Y_AXIS);

        setLayout(layoutManager);
        add(addClientPanel);
        add(Box.createVerticalStrut(10));
        add(clientsListScrollPane);
    }

    public void ReRender() throws SQLException {
        SwingUtilities.invokeLater(() -> {
            try {
                state.Clients = getClientsFunction.GetClients();
                removeAll();
                InitComponents();
                revalidate();
                repaint();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
}

class AddClientPanel extends JPanel {
    
    private GroupLayout layoutManager;
    private JLabel title;
    private JLabel clientsCountLabel;
    private JButton addClientButton;
    private Runnable goToAddClientPageActionPerformaded;
    private State state;


    public AddClientPanel(Runnable goToAddClientPageActionPerformaded, State state) {
        this.goToAddClientPageActionPerformaded = goToAddClientPageActionPerformaded;
        this.state = state;
        InitComponents();
    }

    private void InitComponents() {
        layoutManager = new GroupLayout(this);
        
        title = new JLabel();
        title.setFont(new Font("Segoe UI", 0, 24)); // NOI18N
        title.setText("Clientes Cadastrados");

        clientsCountLabel = new JLabel();
        clientsCountLabel.setText("Clientes: " + state.Clients.size());

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

    private BoxLayout layoutManager;
    private State state;
    private DeleteClientFunction deleteClientFunction;
    private List<JPanel> clientEntriesPanels;
    public ClientsListPanel(DeleteClientFunction deleteClientFunction, State state) {
        this.deleteClientFunction = deleteClientFunction;
        this.state = state;
        InitComponents();
    }

    private void InitComponents() {;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        clientEntriesPanels = new ArrayList<>();
        for (Client client : state.Clients) {
            var clientEntry = new ClientEntry(client, deleteClientFunction);
            clientEntriesPanels.add(clientEntry);
            add(clientEntry);
            add(Box.createVerticalStrut(10));
        }
    }

    @Override
    public Dimension getMaximumSize() {
        return new Dimension(480, super.getMaximumSize().height);
    }
}

class ClientEntry extends JPanel {

    private GroupLayout layoutManager;
    private JLabel nameLabel;
    private JLabel ageLabel;
    private JLabel genderLabel;
    private JButton infosButton;
    private JButton deleteClientButton;
    private Client client;
    private DeleteClientFunction deleteClientFunction;

    public ClientEntry(Client client, DeleteClientFunction deleteClientFunction) {
        this.client = client;
        this.deleteClientFunction = deleteClientFunction;
        InitComponents();
    }

    private void InitComponents() {;
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        nameLabel = new JLabel();
        nameLabel.setText("Nome: " + client.Name);

        ageLabel = new JLabel();
        ageLabel.setText("Idade: " + client.Age);

        genderLabel = new JLabel();
        genderLabel.setText("Genero: " + client.Gender.toString());

        infosButton = new JButton();
        infosButton.setText("Infos");

        deleteClientButton = new JButton();
        deleteClientButton.setText("Deletar");
        deleteClientButton.addActionListener(evt -> {
            try {
                deleteClientFunction.deleteClient(client);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        setMaximumSize(new Dimension(451, 130));
        setPreferredSize(new Dimension(451, 130));

        layoutManager = new javax.swing.GroupLayout(this);
        setLayout(layoutManager);
        layoutManager.setHorizontalGroup(
                layoutManager.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layoutManager.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(layoutManager.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(nameLabel)
                                        .addComponent(genderLabel)
                                        .addComponent(ageLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 314, Short.MAX_VALUE)
                                .addGroup(layoutManager.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(infosButton)
                                        .addComponent(deleteClientButton))
                                .addGap(14, 14, 14))
        );
        layoutManager.setVerticalGroup(
                layoutManager.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layoutManager.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(layoutManager.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(nameLabel)
                                        .addComponent(infosButton))
                                .addGap(18, 18, 18)
                                .addComponent(ageLabel)
                                .addGap(18, 18, 18)
                                .addGroup(layoutManager.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(genderLabel)
                                        .addComponent(deleteClientButton))
                                .addContainerGap(10, Short.MAX_VALUE))
        );
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(super.getPreferredSize().width, 130);
    }

    @Override
    public Dimension getMaximumSize() {
        return new Dimension(451, super.getMaximumSize().height);
    }
}
