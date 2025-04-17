package org.InfragoIT;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * A simplified version of the SQL class for testing purposes.
 * This class doesn't require a real database connection but still performs
 * the department comparison and writes to the CSV file.
 */
public class TestSQL {

    /**
     * Compares the department from the XML file with a predefined "database" department
     * and writes an error to the CSV file if they don't match.
     *
     * @param personeelsnummer  Personnel number
     * @param redenVoorReis     Reason for travel
     * @param aankomstAirport   Arrival airport
     * @param aankomstLand      Arrival country
     * @param aankomstDatum     Arrival date
     * @param oorsprongsLand    Origin country
     * @param oorsprongsAirport Origin airport
     * @param oorsprongsDatum   Origin date
     * @param companyName       Company name
     * @param department        Department from XML
     * @throws SQLException If an SQL error occurs
     */
    public void testDepartmentComparison(
            String personeelsnummer,
            String redenVoorReis,
            String aankomstAirport,
            String aankomstLand,
            String aankomstDatum,
            String oorsprongsLand,
            String oorsprongsAirport,
            String oorsprongsDatum,
            String companyName,
            String department) {

        // Predefined "database" values for testing
        String dbDepartment = "DifferentDepartment";
        String dbPersoneelsnaam = "Test-User";
        String dbPnumber = personeelsnummer;

        // Create output file creator
        OutputFileCreator fOut = new OutputFileCreator();

        // Check if department differs
        if (!dbDepartment.contains(department)) {
            String errorMsg = "Departement voor: " + dbPersoneelsnaam + " is fout";
            String errorWrong = department;
            String errorRight = dbDepartment;
            String errorPnumber = dbPnumber;
            fOut.OutputFileWriter(errorWrong, errorPnumber, errorMsg, errorRight);
        }
    }
}