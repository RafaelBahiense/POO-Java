package fitsystem.persistence;

import fitsystem.entities.Admin;
import fitsystem.entities.Client;
import fitsystem.entities.Gender;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseService {
    
    private Connection dbConnection;

    public DatabaseService(String dbUrl, String user, String pass) throws SQLException {
        this.dbConnection = DriverManager.getConnection(dbUrl, user, pass);
    }

    public Admin login(String username, String password) throws SQLException {
        var result = new ArrayList<Admin>();
        
        var sql = "SELECT * FROM admin WHERE username = ? AND password = ?";
        var stmt = dbConnection.prepareStatement(sql);
        stmt.setString(1, username);
        stmt.setString(2, password);

        var rs = stmt.executeQuery();

        while (rs.next()) {
            var id = rs.getInt("id");
            var currentUsername = rs.getString("username");

            var admin = new Admin();
            admin.Id = id;
            admin.Username = currentUsername;

            result.add(admin);
        }

        rs.close();
        stmt.close();

        return result.isEmpty() ? null : result.get(0);
    }

    public List<Client> getClients() throws SQLException {
        List<Client> result = new ArrayList<>();

        String sql = "SELECT * FROM client";
        var stmt = dbConnection.prepareStatement(sql);

        var rs = stmt.executeQuery();

        while (rs.next()) {
            var id = rs.getInt("id");
            var name = rs.getString("name");
            var age = rs.getInt("age");
            var gender = Gender.valueOf(rs.getString("gender").toUpperCase());
            var phone = rs.getString("phone");
            var address = rs.getString("address");

            Client client = new Client();
            client.Id = id;
            client.Name = name;
            client.Age = age;
            client.Gender = gender;
            client.Phone = phone;
            client.Address = address;

            result.add(client);
        }

        rs.close();
        stmt.close();

        return result;
    }

    public void closeConnection() throws SQLException {
        if (dbConnection != null) {
            dbConnection.close();
        }
    }
}
