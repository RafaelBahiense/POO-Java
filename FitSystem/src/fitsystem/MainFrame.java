package fitsystem;


import fitsystem.entities.Admin;
import fitsystem.entities.Client;
import fitsystem.entities.ClientHealthMetrics;
import fitsystem.pages.*;
import fitsystem.persistence.DatabaseService;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.Border;


public class MainFrame extends JFrame {
    
    private DatabaseService databaseService;
    private Border border;
    private CardLayout cardLayout;
    private JPanel panelContainer;
    private LoginPagePanel loginPanel;
    private ClientsPagePanel clientsPanel;
    private AddClientPagePanel addClientPanel;

    private UpdateClientPagePanel updateClientPanel;
    private IMCHomePagePanel imcHomePanel;
    private IMCCalcPagePanel calcPanel;
    private ResultPagePanel resultPanel;
    private State state;

    public MainFrame(DatabaseService databaseService) throws IOException, SQLException {
        this.databaseService = databaseService;
        state = State.getInstance();
        InitComponents();
        SetupFrame();
    }
    
    private void SetupFrame() {
        add(panelContainer, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 550);
        setTitle("FitSystem");
        setMaximumSize(new Dimension(500, 550));
        setMinimumSize(new Dimension(500, 550));
        setPreferredSize(new Dimension(500, 550));
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try {
                    if (databaseService != null) {
                        databaseService.closeConnection();
                    }
                } catch (SQLException sqlException) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, sqlException);
                }
            }
        });
    }

    private void InitComponents() throws SQLException {
        panelContainer = new JPanel(cardLayout);
        
        border = BorderFactory.createEmptyBorder(15, 15, 15, 15);   
        cardLayout = new CardLayout();
        
        panelContainer.setBorder(border);
        panelContainer.setLayout(cardLayout);

        Runnable loginPageButton = () -> {
            try {
                clientsPanel.ReRender();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            cardLayout.show(panelContainer, "Clients Page");
        };
        Runnable goToAddClientPageButton = () ->  {
            try {
                addClientPanel.ReRender();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            cardLayout.show(panelContainer, "AddClient Page");
        };
        Runnable goToClientsPagePanel = () -> {
            try {
                clientsPanel.ReRender();
                cardLayout.show(panelContainer, "Clients Page");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        };
        Runnable goToCalcPagePanel = () -> {
            calcPanel.ReRender();
            cardLayout.show(panelContainer, "Calc Page");
        };
        Runnable goToResultPagePanel = () -> {
            resultPanel.ReRender();
            cardLayout.show(panelContainer, "Result Page");
        };
        Runnable addClientPageButton = () -> {
            try {
                clientsPanel.ReRender();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            cardLayout.show(panelContainer, "IMC Home Page");
        };
        Runnable goToUpdateClientPagePanel = () -> {
            try {
                updateClientPanel.ReRender();
                cardLayout.show(panelContainer, "UpdateClient Page");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        };

        loginPanel = new LoginPagePanel(loginPageButton, this::login);
        clientsPanel = new ClientsPagePanel(goToAddClientPageButton, goToUpdateClientPagePanel, this::getClients, this::deleteClient, state);
        addClientPanel = new AddClientPagePanel(addClientPageButton, this::insertClient, state);
        updateClientPanel = new UpdateClientPagePanel(goToClientsPagePanel, this::updateClient, this::getClientHealthMetrics, state);
        imcHomePanel = new IMCHomePagePanel(goToCalcPagePanel);
        calcPanel = new IMCCalcPagePanel(goToResultPagePanel, this::insertClientHealthMetrics, state);
        resultPanel = new ResultPagePanel(goToClientsPagePanel, state);

        panelContainer.add(loginPanel, "Login Page");
        panelContainer.add(clientsPanel, "Clients Page");
        panelContainer.add(addClientPanel, "AddClient Page");
        panelContainer.add(updateClientPanel, "UpdateClient Page");
        panelContainer.add(imcHomePanel, "IMC Home Page");
        panelContainer.add(calcPanel, "Calc Page");
        panelContainer.add(resultPanel, "Result Page");
    }
    
    private Admin login(String username, String password) throws SQLException {
        return this.databaseService.login(username, password);
    }

    private List<Client> getClients() throws SQLException {
        return this.databaseService.getClients();
    }

    private int insertClient(Client client) throws SQLException {
        state.CurrentClient = client;
        var id = this.databaseService.insertClient(client);
        state.CurrentClient.Id = id;
        return id;
    }

    private int insertClientHealthMetrics(ClientHealthMetrics clientHealthMetrics) throws SQLException {
        return this.databaseService.insertClientHealthMetrics(clientHealthMetrics);
    }

    private void deleteClient(Client client) {
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                databaseService.deleteClient(client);
                return null;
            }

            @Override
            protected void done() {
                try {
                    get();
                    clientsPanel.ReRender();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        worker.execute();
    }

    private void updateClient(Client client, ClientHealthMetrics clientHealthMetrics) throws SQLException {
        this.databaseService.updateClient(client, clientHealthMetrics);
    }

    private List<ClientHealthMetrics> getClientHealthMetrics(int clientId) throws SQLException {
        return this.databaseService.GetClientHealthMetrics(clientId);
    }

}
