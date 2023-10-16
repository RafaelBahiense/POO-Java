package fitsystem;

import fitsystem.entities.Client;
import fitsystem.entities.ClientHealthMetrics;

public class State {
    private static State instance;

    private State() {
        CurrentClient = new Client();
        CurrentClient.Name = "";
        CurrentClient.Age = 0;
    }

    public static State getInstance() {
        if (instance == null) {
            instance = new State();
        }
        return instance;
    }

    public Client CurrentClient;

    public ClientHealthMetrics CurrentClientHealthMetrics;
}
