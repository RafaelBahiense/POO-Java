package fitsystem.entities;

import java.math.BigDecimal;
import java.sql.Date;

public class ClientHealthMetrics {
    public int Id;
    public int ClientId;
    public BigDecimal Weight;
    public BigDecimal Height;
    public BigDecimal ImcValue;
    public Date DateRecorded;
}