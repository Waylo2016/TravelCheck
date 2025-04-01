package org.InfragoIT;

import com.opencsv.CSVWriter;


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class OutputFileCreator {
    String PopularCountry = "";
    String PopularAirport = "";
    // flag to check if header is written
    boolean hasWritten = false;

    FileWriter OutputFile;

    {
        try {
            OutputFile = new FileWriter("CompletedCheck.csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    CSVWriter writer = new CSVWriter(OutputFile);


    public void OutputFileWriter(String ErrorWrong, String ErrorPnumber, String ErrorMsg, String ErrorRight) {


        if (hasWritten == false) {
            hasWritten = true;
            String[] header = {"Foutbericht: ", "Personeelsnummer van de fout", "Fout: ", "Had het moeten zijn: "};
            writer.writeNext(header);
            System.out.println("file should be created??");
        }


        ArrayList<String> entries = new ArrayList();
        entries.add(ErrorMsg);
        entries.add(ErrorPnumber);
        entries.add(ErrorWrong);
        entries.add(ErrorRight);

        writer.writeNext(entries.toArray(new String[entries.size()]));
        try {
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
