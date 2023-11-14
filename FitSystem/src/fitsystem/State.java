package fitsystem;

import fitsystem.entities.Client;
import fitsystem.entities.ClientHealthMetrics;
import fitsystem.entities.Gender;

import java.math.BigDecimal;
import java.util.List;

public class State {
    private static State instance;
    public Client CurrentClient;
    public ClientHealthMetrics CurrentClientHealthMetrics;
    public List<Client> Clients;

    private State() {
        CurrentClient = new Client();
        CurrentClient.Name = "";
        CurrentClient.Age = 0;
        CurrentClient.Gender = Gender.MALE;
        CurrentClient.Phone = "";
        CurrentClient.Address = "";
        CurrentClientHealthMetrics = new ClientHealthMetrics();
        CurrentClientHealthMetrics.Height = BigDecimal.ZERO;
        CurrentClientHealthMetrics.Weight = BigDecimal.ZERO;
        CurrentClientHealthMetrics.ImcValue = BigDecimal.ZERO;
    }

    public static State getInstance() {
        if (instance == null) {
            instance = new State();
        }
        return instance;
    }

    public void Reset() {
        CurrentClient = new Client();
        CurrentClientHealthMetrics = new ClientHealthMetrics();
    }
}
