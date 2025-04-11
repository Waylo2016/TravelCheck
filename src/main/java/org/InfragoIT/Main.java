package org.InfragoIT;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


class Werknemer {
    String Personeelsnummer = "";
    String personeelsnaam = "";
    String Email = "";
    String redenVoorReis = "";
    String methodOfTravel = "";


    public Werknemer() { // maak klasse voor werknemer
        this.Personeelsnummer = "";
        this.personeelsnaam = "";
        this.Email = "";
        this.redenVoorReis = "";
        this.methodOfTravel = "";
    }

}


class Bedrijf { // maak klasse voor bedrijven
    String bedrijfsnaam;
    String afdeling;

    public Bedrijf() {
        this.afdeling = afdeling;
        this.bedrijfsnaam = bedrijfsnaam;

    }

}

public class Main {

    static Connection conn;
    static boolean hasRun = false;

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://infragotraveldatabase.mysql.database.azure.com";
            String user = "InfraGoAdmin";
            String password = "InfraGo20";
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
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
        Country countryCount = new Country();


        for (xmlRecord current : reader.xmlRecords) {

            current.werknemer.personeelsnaam = current.werknemer.personeelsnaam.replaceAll("-", " ");
            current.werknemer.redenVoorReis = current.werknemer.redenVoorReis.replaceAll("-", " ");
            current.werknemer.methodOfTravel = current.werknemer.methodOfTravel.replaceAll("-", " ");
            current.bedrijf.bedrijfsnaam = current.bedrijf.bedrijfsnaam.replaceAll("-", " ");
            current.aankomstland.airport = current.aankomstland.airport.replaceAll("-", " ");
            current.oorsprongsland.airport = current.oorsprongsland.airport.replaceAll("-", " ");

            countryCount.popularCountry(current.aankomstland.country, current.oorsprongsland.country);
            countryCount.popularAirport(current.aankomstland.airport, current.oorsprongsland.airport);

            while (!hasRun) {
                hasRun = true;
                try {
                    sql.SqlComparer(conn, current.werknemer.Personeelsnummer, current.werknemer.redenVoorReis, current.aankomstland.airport, current.aankomstland.country, current.aankomstland.arrivalDate,
                            current.oorsprongsland.country, current.oorsprongsland.airport, current.oorsprongsland.departureDate, current.bedrijf.bedrijfsnaam, current.bedrijf.afdeling);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }


        }
        System.out.println(countryCount.popularCountry);
        System.out.println(countryCount.popularAirport);

        hasRun = false;
    }
}
