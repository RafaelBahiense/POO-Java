package fitsystem.actions;

import fitsystem.entities.Admin;
import fitsystem.entities.Client;

import java.sql.SQLException;
import java.util.List;

public interface GetClientsFunction {
    List<Client> GetClients() throws SQLException;
}
