package fitsystem.persistence;

import fitsystem.entities.Admin;
import fitsystem.entities.Client;
import fitsystem.entities.ClientHealthMetrics;
import fitsystem.entities.Gender;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;
import java.sql.Statement;
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

    public int insertClient(Client client) throws SQLException {
        String sql = "INSERT INTO client (name, age, gender, phone, address) VALUES (?, ?, ?, ?, ?)";

        var stmt = dbConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        stmt.setString(1, client.Name);
        stmt.setInt(2, client.Age);
        stmt.setString(3, client.Gender.toString());
        stmt.setString(4, client.Phone);
        stmt.setString(5, client.Address);

        int affectedRows = stmt.executeUpdate();
        if (affectedRows == 0) {
            throw new SQLException("Creating client failed, no rows affected.");
        }

        try (var generatedKeys = stmt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Creating client failed, no ID obtained.");
            }
        } finally {
            stmt.close();
        }
    }

    public int insertClientHealthMetrics(ClientHealthMetrics metrics) throws SQLException {
        String sql = "INSERT INTO client_health_metrics (client_id, weight, height, imc_value, date_recorded) VALUES (?, ?, ?, ?, ?)";

        var stmt = dbConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        stmt.setInt(1, metrics.ClientId);
        stmt.setBigDecimal(2, metrics.Weight);
        stmt.setBigDecimal(3, metrics.Height);
        stmt.setBigDecimal(4, metrics.ImcValue);
        stmt.setDate(5, metrics.DateRecorded);

        int affectedRows = stmt.executeUpdate();
        if (affectedRows == 0) {
            throw new SQLException("Creating client health metrics failed, no rows affected.");
        }

        try (var generatedKeys = stmt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Creating client health metrics failed, no ID obtained.");
            }
        } finally {
            stmt.close();
        }
    }

    public void deleteClient(Client client) throws SQLException {
        String deleteHealthMetricsSql = "DELETE FROM client_health_metrics WHERE client_id = ?";

        var healthMetricsStmt = dbConnection.prepareStatement(deleteHealthMetricsSql);

        healthMetricsStmt.setInt(1, client.Id);

        healthMetricsStmt.executeUpdate();

        healthMetricsStmt.close();

        String deleteClientSql = "DELETE FROM client WHERE id = ?";

        var clientStmt = dbConnection.prepareStatement(deleteClientSql);

        clientStmt.setInt(1, client.Id);

        int affectedRows = clientStmt.executeUpdate();
        if (affectedRows == 0) {
            throw new SQLException("Deleting client failed, no rows affected.");
        }

        clientStmt.close();
    }

    public void updateClient(Client client, ClientHealthMetrics clientHealthMetrics) throws SQLException {
        var updateClientSql = "UPDATE client SET name = ?, age = ?, gender = ?, phone = ?, address = ? WHERE id = ?";

        var clientStmt = dbConnection.prepareStatement(updateClientSql);

        clientStmt.setString(1, client.Name);
        clientStmt.setInt(2, client.Age);
        clientStmt.setString(3, client.Gender.toString());
        clientStmt.setString(4, client.Phone);
        clientStmt.setString(5, client.Address);
        clientStmt.setInt(6, client.Id);

        int affectedRows = clientStmt.executeUpdate();
        if (affectedRows == 0) {
            throw new SQLException("Deleting client failed, no rows affected.");
        }

        clientStmt.close();


        var updateHealthMetricsSql = "UPDATE client_health_metrics SET weight = ?, height = ?, imc_value = ?, date_recorded = ? WHERE id = ?";

        var healthMetricsStmt = dbConnection.prepareStatement(updateHealthMetricsSql);

        healthMetricsStmt.setBigDecimal(1, clientHealthMetrics.Weight);
        healthMetricsStmt.setBigDecimal(2, clientHealthMetrics.Height);
        healthMetricsStmt.setBigDecimal(3, clientHealthMetrics.ImcValue);
        healthMetricsStmt.setDate(4, clientHealthMetrics.DateRecorded);
        healthMetricsStmt.setInt(5, clientHealthMetrics.Id);

        affectedRows = healthMetricsStmt.executeUpdate();
        if (affectedRows == 0) {
            throw new SQLException("Deleting client failed, no rows affected.");
        }

        healthMetricsStmt.close();
    }

    public List<ClientHealthMetrics> GetClientHealthMetrics(int clientId) throws SQLException {
        List<ClientHealthMetrics> result = new ArrayList<>();

        String sql = "SELECT * FROM client_health_metrics WHERE client_id = ?";
        var stmt = dbConnection.prepareStatement(sql);

        stmt.setInt(1, clientId);

        var rs = stmt.executeQuery();

        while (rs.next()) {
            var id = rs.getInt("id");
            var weight = rs.getBigDecimal("weight");
            var height = rs.getBigDecimal("height");
            var imcValue = rs.getBigDecimal("imc_value");
            var dateRecorded = rs.getDate("date_recorded");

            ClientHealthMetrics clientHealthMetrics = new ClientHealthMetrics();
            clientHealthMetrics.Id = id;
            clientHealthMetrics.ClientId = clientId;
            clientHealthMetrics.Weight = weight;
            clientHealthMetrics.Height = height;
            clientHealthMetrics.ImcValue = imcValue;
            clientHealthMetrics.DateRecorded = dateRecorded;

            result.add(clientHealthMetrics);
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
