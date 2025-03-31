package org.InfragoIT;

import java.io.FileWriter;
import java.io.IOException;

public class OutputFileCreator {
    String PopularCountry = "";
    String PopularAirport = "";


    public void OutputFileCreator(String PopularCountry, String PopularAirport) {
        try {
            FileWriter OutputFile = new FileWriter("CompletedCheck.csv");
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

}
