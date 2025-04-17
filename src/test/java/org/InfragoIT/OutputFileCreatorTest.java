package org.InfragoIT;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OutputFileCreatorTest {

    private static final String CSV_OUTPUT_PATH = "CSVOutput/CompletedCheck.csv";
    
    @TempDir
    Path tempDir;
    
    @BeforeEach
    void setUp() throws IOException {
        // Create CSVOutput directory if it doesn't exist
        Path csvDir = Paths.get("CSVOutput");
        if (!Files.exists(csvDir)) {
            Files.createDirectories(csvDir);
        }
        
        // Delete existing CSV file if it exists
        File csvFile = new File(CSV_OUTPUT_PATH);
        if (csvFile.exists()) {
            csvFile.delete();
        }
    }
    
    @AfterEach
    void tearDown() {
        // Clean up resources if needed
    }
    
    @Test
    void testOutputFileCreation() throws IOException {
        // Create an instance of OutputFileCreator
        OutputFileCreator outputFileCreator = new OutputFileCreator();
        
        // Write a test error to the CSV file
        String errorWrong = "TestDepartment";
        String errorPnumber = "1234567";
        String errorMsg = "Departement voor: Test-User is fout";
        String errorRight = "DifferentDepartment";
        
        outputFileCreator.OutputFileWriter(errorWrong, errorPnumber, errorMsg, errorRight);
        
        // Verify CSV file exists
        File csvFile = new File(CSV_OUTPUT_PATH);
        assertTrue(csvFile.exists(), "CSV file should be created");
        
        // Read the CSV file and verify its contents
        List<String> lines = Files.readAllLines(csvFile.toPath());
        assertEquals(2, lines.size(), "CSV should have header and one data row");
        
        // Check header
        String header = lines.get(0);
        assertTrue(header.contains("Foutbericht:"), "CSV should have correct header");
        assertTrue(header.contains("Personeelsnummer van de fout"), "CSV should have correct header");
        assertTrue(header.contains("Fout:"), "CSV should have correct header");
        assertTrue(header.contains("Had het moeten zijn:"), "CSV should have correct header");
        
        // Check data row
        String dataRow = lines.get(1);
        assertTrue(dataRow.contains(errorMsg), "CSV should contain the error message");
        assertTrue(dataRow.contains(errorPnumber), "CSV should contain the personnel number");
        assertTrue(dataRow.contains(errorWrong), "CSV should contain the wrong value");
        assertTrue(dataRow.contains(errorRight), "CSV should contain the correct value");
    }
    
    @Test
    void testMultipleOutputs() throws IOException {
        // Create an instance of OutputFileCreator
        OutputFileCreator outputFileCreator = new OutputFileCreator();
        
        // Write multiple test errors to the CSV file
        outputFileCreator.OutputFileWriter("TestDepartment", "1234567", "Departement voor: Test-User is fout", "DifferentDepartment");
        outputFileCreator.OutputFileWriter("TestAirport", "1234567", "Oorsprongsvluchthaven is fout voor: Test-User", "RealAirport");
        outputFileCreator.OutputFileWriter("TestCountry", "1234567", "Oorsprongsland is fout voor: Test-User", "RealCountry");
        
        // Verify CSV file exists
        File csvFile = new File(CSV_OUTPUT_PATH);
        assertTrue(csvFile.exists(), "CSV file should be created");
        
        // Read the CSV file and verify its contents
        List<String> lines = Files.readAllLines(csvFile.toPath());
        assertEquals(4, lines.size(), "CSV should have header and three data rows");
        
        // Check header
        String header = lines.get(0);
        assertTrue(header.contains("Foutbericht:"), "CSV should have correct header");
        
        // Check data rows
        assertTrue(lines.get(1).contains("Departement voor:"), "First row should contain department error");
        assertTrue(lines.get(2).contains("Oorsprongsvluchthaven"), "Second row should contain airport error");
        assertTrue(lines.get(3).contains("Oorsprongsland"), "Third row should contain country error");
    }
}