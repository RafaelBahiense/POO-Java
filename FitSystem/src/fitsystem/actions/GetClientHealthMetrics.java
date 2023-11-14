package fitsystem.actions;

import fitsystem.entities.ClientHealthMetrics;

import java.sql.SQLException;
import java.util.List;

public interface GetClientHealthMetrics {
    List<ClientHealthMetrics> GetClientHelthMetrics(int clientId) throws SQLException;
}
