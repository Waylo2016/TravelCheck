package org.InfragoIT;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class Werknemer {
    String Personeelsnaam;
    String Email;
    String RedenVoorReis;
    String MethodOfTravel;

    public Werknemer() { // maak klasse voor werknemer
        this.Personeelsnaam = Personeelsnaam;
        this.Email = Email;
        this.RedenVoorReis = RedenVoorReis;
        this.MethodOfTravel = MethodOfTravel;
    }
}

class Oorsprongsland { // maak klasse van oorsprongsland
    String DepartureAirportName;
    String CountryOfDeparture;
    String DepartureDate;

    public Oorsprongsland() {
        this.DepartureAirportName = DepartureAirportName;
        this.CountryOfDeparture = CountryOfDeparture;
        this.DepartureDate = DepartureDate;
    }
}

class Aankomstland { // maak klasse voor aankomstland
    String ArrivalAirportName;
    String CountryOfArrival;
    String ArrivalDate;

    public Aankomstland() {
        this.ArrivalAirportName = ArrivalAirportName;
        this.CountryOfArrival = CountryOfArrival;
        this.ArrivalDate = ArrivalDate;
    }
}

class Bedrijf { // maak klasse voor bedrijven
    String Bedrijfsnaam;
    String Afdeling;

    public Bedrijf() {
        this.Afdeling = Afdeling;
        this.Bedrijfsnaam = Bedrijfsnaam;
    }
}

public class App {

    static Connection conn;

    public static void main(String[] args) {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bamtravelcheck" + "user=root&password=welkom123");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Werknemer werknemer = new Werknemer();
        Oorsprongsland oorsprongsland = new Oorsprongsland();
        Aankomstland aankomstland = new Aankomstland();
        Bedrijf bedrijf = new Bedrijf();
        XMLReader reader = new XMLReader();


        int indexCounter = 0;
        for (String Personeelsnummer : reader.Personeelsnummers) {
            werknemer.Personeelsnaam = reader.Personeelsnamen.get(indexCounter);
            werknemer.Email = reader.EmailAdressen.get(indexCounter);
            bedrijf.Bedrijfsnaam = reader.Bedrijfsnamen.get(indexCounter);
            bedrijf.Afdeling = reader.Afdelingen.get(indexCounter);
            oorsprongsland.DepartureAirportName = reader.DepartureAirports.get(indexCounter);
            oorsprongsland.CountryOfDeparture = reader.DepartureCountries.get(indexCounter);
            oorsprongsland.DepartureDate = reader.DepartureDates.get(indexCounter);
            werknemer.RedenVoorReis = reader.TravelReasons.get(indexCounter);
            werknemer.MethodOfTravel = reader.TravelMethods.get(indexCounter);
            aankomstland.ArrivalAirportName = reader.ArrivalAirports.get(indexCounter);
            aankomstland.CountryOfArrival = reader.ArrivalCountries.get(indexCounter);
            aankomstland.ArrivalDate = reader.ArrivalDates.get(indexCounter);
            indexCounter++;

            System.out.println(werknemer.Personeelsnaam);
        }
    }
}

