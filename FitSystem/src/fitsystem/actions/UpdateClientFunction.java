package fitsystem.actions;

import fitsystem.entities.Client;
import fitsystem.entities.ClientHealthMetrics;

import java.sql.SQLException;

public interface UpdateClientFunction {
    void UpdateClient(Client client, ClientHealthMetrics clientHealthMetrics) throws SQLException;
}
