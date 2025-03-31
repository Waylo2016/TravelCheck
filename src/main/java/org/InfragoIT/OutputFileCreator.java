package org.InfragoIT;

import com.opencsv.CSVWriter;
import com.opencsv.CSVWriter.*;


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class OutputFileCreator {
    String PopularCountry = "";
    String PopularAirport = "";


    public void OutputFileCreator(String ErrorWrong, String ErrorPnumber, String ErrorMsg, String ErrorRight) {
        try {
            String[] header = {"Foutbericht: ", "Personeelsnummer van de fout", "Fout: ", "Had het moeten zijn: "};
            FileWriter OutputFile = new FileWriter("CompletedCheck.csv");
            CSVWriter writer =  new CSVWriter(OutputFile);
            try{
                writer.writeNext(header);
            } catch (IOException e){
                e.printStackTrace();
            }


            ArrayList<> entries = new ArrayList();
            entries.add(ErrorMsg);
            entries.add(ErrorPnumber);
            entries.add(ErrorWrong);
            entries.add(ErrorRight);




        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
