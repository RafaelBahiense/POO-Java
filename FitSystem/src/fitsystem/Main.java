package fitsystem;

import fitsystem.persistence.DatabaseService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;


public class Main {

    private ConfigReader configReader;
    private DatabaseService databaseService;
    
    private MainFrame mainFrame;
    
    public Main() throws SQLException, IOException {
        InitConfigs();
        InitDBService();
        
        SwingUtilities.invokeLater(() -> {
            try {
                mainFrame = new MainFrame(databaseService);
            } catch (IOException | SQLException e) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
            }
        });
        
    }
    
    private void InitConfigs() throws IOException {
        configReader = new ConfigReader("src/fitsystem/config.properties");
    }
    
    private void InitDBService() throws SQLException {
        var dbUrl = configReader.getValue("db.url");
        var user = configReader.getValue("db.user");
        var pass = configReader.getValue("db.pass");
        
        this.databaseService = new DatabaseService(dbUrl, user, pass);
    }
    
    public static void main(String[] args) throws SQLException, IOException {
        new Main();
    }
}
