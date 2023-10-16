package fitsystem.actions;


import fitsystem.entities.Client;
import java.sql.SQLException;

public interface InsertClientFunction {
    int insertClient(Client client) throws SQLException;
}