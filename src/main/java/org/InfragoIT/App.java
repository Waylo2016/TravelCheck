package org.InfragoIT;

import java.sql.*;

class Werknemer {
    String Personeelsnummer = "";
    String Personeelsnaam = "";
    String Email = "";
    String RedenVoorReis = "";
    String MethodOfTravel = "";



    public Werknemer() { // maak klasse voor werknemer
        this.Personeelsnummer = "";
        this.Personeelsnaam = "";
        this.Email = "";
        this.RedenVoorReis = "";
        this.MethodOfTravel = "";
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
    static boolean hasRun = false;


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


        Country countryCount = new Country();
        for (xmlRecord current : reader.xmlRecords) {

            current.werknemer.Personeelsnaam = current.werknemer.Personeelsnaam.replaceAll("-", " ");
            current.werknemer.RedenVoorReis = current.werknemer.RedenVoorReis.replaceAll("-", " ");
            current.werknemer.MethodOfTravel = current.werknemer.MethodOfTravel.replaceAll("-", " ");
            current.bedrijf.Bedrijfsnaam = current.bedrijf.Bedrijfsnaam.replaceAll("-", " ");
            current.aankomstland.Airport = current.aankomstland.Airport.replaceAll("-", " ");
            current.oorsprongsland.Airport = current.oorsprongsland.Airport.replaceAll("-", " ");

            countryCount.popularCountry(current.aankomstland.Country, current.oorsprongsland.Country);
            countryCount.popularAirport(current.aankomstland.Airport, current.oorsprongsland.Airport);
            /*
            if ((counter == current.werknemer.Personeelsnummer.size() - 1)) {
                System.out.println("The most popular country is: " + (country.popularCountry(reader.ArrivalCountries.get(counter), reader.DepartureCountries.get(counter))));
                fileOut.PopularCountry = country.popularCountry(reader.ArrivalCountries.get(counter), reader.DepartureCountries.get(counter));
                System.out.println("The most popular airport is: " + (country.popularAirport(reader.ArrivalAirports.get(counter), reader.DepartureAirports.get(counter))));
                fileOut.PopularAirport = country.popularAirport(reader.ArrivalAirports.get(counter), reader.DepartureAirports.get(counter));
            }
            */
            while (!hasRun) {
                hasRun = true;
                try {
                    sql.SqlComparer(conn, current.werknemer.Personeelsnummer, current.werknemer.Personeelsnaam, current.werknemer.Email, current.werknemer.RedenVoorReis, current.werknemer.MethodOfTravel, current.aankomstland.Airport,
                            current.aankomstland.Country, current.aankomstland.ArrivalDate, current.oorsprongsland.Airport, current.oorsprongsland.Country, current.oorsprongsland.DepartureDate,
                            current.bedrijf.Bedrijfsnaam, current.bedrijf.Afdeling);

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
