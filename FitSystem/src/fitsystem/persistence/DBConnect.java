package fitsystem.persistence;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

    private String DB_URL;
    private String USER;
    private String PASS;
    
    private Connection conn;
    
    public DBConnect(String db_url, String user, String pass) throws SQLException {

        DB_URL = db_url;
        USER = user;
        PASS = pass;

        connect();
    }

    private void connect() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public Connection getConnection() {
        return conn;
    }
    
    public void close() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }
}