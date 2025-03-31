package org.InfragoIT;

import java.sql.*;

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


        try {
            String url = "jdbc:mysql://infragotraveldatabase.mysql.database.azure.com";
            String user = "InfraGoAdmin";
            String password = "InfraGo20";
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Werknemer werknemer = new Werknemer();
        Oorsprongsland oorsprongsland = new Oorsprongsland();
        Aankomstland aankomstland = new Aankomstland();
        Bedrijf bedrijf = new Bedrijf();
        XMLReader reader = new XMLReader();
        Country country = new Country();
        OutputFileCreator fileOut = new OutputFileCreator();
        SQL sql = new SQL();


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

            if ((indexCounter == reader.Personeelsnummers.size() - 1)) {
                System.out.println("The most popular country is: " + (country.popularCountry(reader.ArrivalCountries.get(indexCounter), reader.DepartureCountries.get(indexCounter))));
                fileOut.PopularCountry = country.popularCountry(reader.ArrivalCountries.get(indexCounter), reader.DepartureCountries.get(indexCounter));
                System.out.println("The most popular airport is: " + (country.popularAirport(reader.ArrivalAirports.get(indexCounter), reader.DepartureAirports.get(indexCounter))));
                fileOut.PopularAirport = country.popularAirport(reader.ArrivalAirports.get(indexCounter), reader.DepartureAirports.get(indexCounter));
            }

            try {
                sql.SqlComparer(conn, Personeelsnummer, werknemer.Personeelsnaam, werknemer.Email, werknemer.RedenVoorReis, werknemer.MethodOfTravel, aankomstland.Airport,
                        aankomstland.Country, aankomstland.ArrivalDate, oorsprongsland.Airport, oorsprongsland.Country, oorsprongsland.DepartureDate,
                        bedrijf.Afdeling, bedrijf.Bedrijfsnaam);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
