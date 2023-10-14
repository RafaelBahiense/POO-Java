package fitsystem;


import fitsystem.entities.Admin;
import fitsystem.pages.*;
import fitsystem.persistence.DatabaseService;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
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
    private AddClientPagePanel addClientPanel;
    private HomePagePanel homePanel;
    private CalcPagePanel calcPanel;
    private ResultPagePanel resultPanel;


    public MainFrame(DatabaseService databaseService) throws IOException, SQLException {
        this.databaseService = databaseService;
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

    private void InitComponents() {
        panelContainer = new JPanel(cardLayout);
        
        border = BorderFactory.createEmptyBorder(15, 15, 15, 15);   
        cardLayout = new CardLayout();
        
        panelContainer.setBorder(border);
        panelContainer.setLayout(cardLayout);
        
        Runnable goToHomePagePanel = () -> cardLayout.show(panelContainer, "Home Page");
        Runnable goToCalcPagePanel = () -> cardLayout.show(panelContainer, "Calc Page");
        Runnable goToResultPagePanel = () -> {
            resultPanel.ReRender();
            cardLayout.show(panelContainer, "Result Page");
        };
        Runnable loginPageButton = () -> {

            cardLayout.show(panelContainer, "AddClient Page");
        };
        Runnable addClientPageButton = () -> cardLayout.show(panelContainer, "Home Page");

        loginPanel = new LoginPagePanel(loginPageButton, this::login);
        addClientPanel = new AddClientPagePanel(addClientPageButton);
        homePanel = new HomePagePanel(goToCalcPagePanel);
        calcPanel = new CalcPagePanel(goToResultPagePanel);
        resultPanel = new ResultPagePanel(goToHomePagePanel, goToCalcPagePanel);
        
        //panelContainer.add(new NewJPanel(), "");
        panelContainer.add(loginPanel, "Login Page");
        panelContainer.add(addClientPanel, "AddClient Page");
        panelContainer.add(homePanel, "Home Page");
        panelContainer.add(calcPanel, "Calc Page");
        panelContainer.add(resultPanel, "Result Page");
    }

    public void goToCaclPage() {
        cardLayout.show(panelContainer, "Calc Page");
    }

    
    private Admin login(String username, String password) throws SQLException {
        return this.databaseService.login(username, password);
    }
    
}
