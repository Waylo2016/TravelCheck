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
            OutputFile = new FileWriter("C:\\Users\\stijn\\TravelCheck\\CSVOutput\\CompletedCheck.csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    CSVWriter writer = new CSVWriter(OutputFile);


    public void OutputFileWriter(String errorWrong, String errorPnumber, String errorMsg, String errorRight) {

        if (!hasWritten) {
            hasWritten = true;
            String[] header = {"Foutbericht: ", "Personeelsnummer van de fout", "Fout: ", "Had het moeten zijn: "};
            writer.writeNext(header);
            System.out.println("file should be created??");
        }


        ArrayList<String> entries = new ArrayList();
        entries.add(errorMsg);
        entries.add(errorPnumber);
        entries.add(errorWrong);
        entries.add(errorRight);

        writer.writeNext(entries.toArray(new String[entries.size()]));
        try {
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
