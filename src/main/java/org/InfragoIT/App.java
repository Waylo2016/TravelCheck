package org.InfragoIT;

import java.sql.Connection;

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
/*
        try {
            String url = "jdbc:mysql://infragotraveldatabase.mysql.database.azure.com";
            String user = "InfraGoAdmin";
            String password = "InfraGo20";
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
 */
        Werknemer werknemer = new Werknemer();
        Oorsprongsland oorsprongsland = new Oorsprongsland();
        Aankomstland aankomstland = new Aankomstland();
        Bedrijf bedrijf = new Bedrijf();
        XMLReader reader = new XMLReader();


        int indexCounter = 0;
        for (String Personeelsnummer : reader.Personeelsnummers) {
            Personeelsnummer = reader.Personeelsnummers.get(indexCounter);
            werknemer.Personeelsnaam = reader.Personeelsnamen.get(indexCounter);
            werknemer.Email = reader.EmailAdressen.get(indexCounter);
            bedrijf.Bedrijfsnaam = reader.Bedrijfsnamen.get(indexCounter);
            bedrijf.Afdeling = reader.Afdelingen.get(indexCounter);
            oorsprongsland.Airport = reader.DepartureAirports.get(indexCounter);
            oorsprongsland.Country = reader.DepartureCountries.get(indexCounter);
            oorsprongsland.DepartureDate = reader.DepartureDates.get(indexCounter);
            werknemer.RedenVoorReis = reader.TravelReasons.get(indexCounter);
            werknemer.MethodOfTravel = reader.TravelMethods.get(indexCounter);
            aankomstland.Airport = reader.ArrivalAirports.get(indexCounter);
            aankomstland.Country = reader.ArrivalCountries.get(indexCounter);
            aankomstland.ArrivalDate = reader.ArrivalDates.get(indexCounter);
            indexCounter++;

            System.out.println(aankomstland.Airport + " " + oorsprongsland.Airport + " " +  Personeelsnummer);
/*
            try {
                String Schema = conn.getSchema();
                System.out.println(Schema);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

 */

        }
    }
}

