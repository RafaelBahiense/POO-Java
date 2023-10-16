package fitsystem.actions;

import fitsystem.entities.ClientHealthMetrics;
import java.sql.SQLException;

public interface InsertClientHealthMetrics {
    int insertClientHealthMetrics(ClientHealthMetrics metrics) throws SQLException;
}
