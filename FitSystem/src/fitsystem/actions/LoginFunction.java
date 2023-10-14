package fitsystem.actions;

import fitsystem.entities.Admin;

import java.sql.SQLException;

public interface LoginFunction {
    Admin Login(String username, String password) throws SQLException;
}
