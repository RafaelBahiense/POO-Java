package fitsystem.actions;

import fitsystem.entities.Client;

import java.sql.SQLException;

public interface DeleteClientFunction {
    void deleteClient(Client client) throws SQLException;
}
