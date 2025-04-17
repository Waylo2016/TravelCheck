package org.InfragoIT;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class XMLReaderTest {

    private static final String TEST_XML_PATH = "src/test/resources/TestXmlData.xml";
    private static final String CSV_OUTPUT_PATH = "CSVOutput/CompletedCheck.csv";

    @TempDir
    Path tempDir;

    private Connection mockConnection;
    private CallableStatement mockCallableStatement;
    private ResultSet mockResultSet;

    @BeforeEach
    void setUp() throws SQLException, IOException {
        // Create CSVOutput directory if it doesn't exist
        Path csvDir = Paths.get("CSVOutput");
        if (!Files.exists(csvDir)) {
            Files.createDirectories(csvDir);
        }

        // Set up mocks for database connection
        mockConnection = mock(Connection.class);
        mockCallableStatement = mock(CallableStatement.class);
        mockResultSet = mock(ResultSet.class);

        when(mockConnection.prepareCall(anyString())).thenReturn(mockCallableStatement);
        when(mockCallableStatement.executeQuery()).thenReturn(mockResultSet);

        // Mock result set to return data
        when(mockResultSet.next()).thenReturn(true, false); // Return true once, then false
        when(mockResultSet.getString(1)).thenReturn("1234567"); // Personeelsnummer
        when(mockResultSet.getString(2)).thenReturn("Test-User"); // personeelsnaam
        when(mockResultSet.getString(3)).thenReturn("test.user@INFRAGoTest.com"); // Email
        when(mockResultSet.getString(4)).thenReturn("Test"); // redenVoorReis
        when(mockResultSet.getString(5)).thenReturn("Flight"); // methodOfTravel
        when(mockResultSet.getString(6)).thenReturn("INFRAGoTest"); // bedrijfsnaam
        when(mockResultSet.getString(7)).thenReturn("DifferentDepartment"); // afdeling - different from XML
        when(mockResultSet.getString(8)).thenReturn("Test-Airport"); // Vertrek-Luchthaven
        when(mockResultSet.getString(9)).thenReturn("TestCountry"); // Land-van-oorsprong
        when(mockResultSet.getString(10)).thenReturn("2025-01-01"); // Vertrekdatum
        when(mockResultSet.getString(11)).thenReturn("Destination-Airport"); // Aankomst-Luchthaven
        when(mockResultSet.getString(12)).thenReturn("DestinationCountry"); // Land-van-aankomst
        when(mockResultSet.getString(13)).thenReturn("2025-01-02"); // Aankomst-datum
    }

    @AfterEach
    void tearDown() {
        // Clean up any resources if needed
    }

    @Test
    void testDepartmentDifference() throws SQLException, IOException {
        // Create a custom XMLReader that reads from the test XML file
        TestXMLReader xmlReader = new TestXMLReader(TEST_XML_PATH);

        // Verify that the XML was read correctly
        assertEquals(1, xmlReader.xmlRecords.size(), "Should have read 1 record from XML");
        assertEquals("TestDepartment", xmlReader.xmlRecords.get(0).bedrijf.afdeling, 
                    "Department in XML should be 'TestDepartment'");

        // Create SQL instance and test the comparison
        SQL sql = new SQL();
        xmlRecord record = xmlReader.xmlRecords.get(0);

        // Execute the SQL comparer with our mocked connection
        sql.SqlComparer(mockConnection, record.werknemer.Personeelsnummer, 
                       record.werknemer.redenVoorReis, record.aankomstland.airport, 
                       record.aankomstland.country, record.aankomstland.arrivalDate,
                       record.oorsprongsland.country, record.oorsprongsland.airport, 
                       record.oorsprongsland.departureDate, record.bedrijf.bedrijfsnaam, 
                       record.bedrijf.afdeling);

        // Verify that the SQL query was executed with the correct parameter
        verify(mockCallableStatement).setString(1, "1234567");

        // Verify CSV file exists and contains the expected error
        File csvFile = new File(CSV_OUTPUT_PATH);
        assertTrue(csvFile.exists(), "CSV file should be created");

        List<String> lines = Files.readAllLines(csvFile.toPath());
        assertTrue(lines.size() >= 2, "CSV should have at least header and one data row");

        // Check header
        assertTrue(lines.get(0).contains("Foutbericht:"), "CSV should have correct header");

        // Check data row - should contain department error
        String dataRow = lines.get(1);
        assertTrue(dataRow.contains("Departement voor:"), "CSV should contain department error message");
        assertTrue(dataRow.contains("TestDepartment"), "CSV should contain the wrong department");
        assertTrue(dataRow.contains("DifferentDepartment"), "CSV should contain the correct department");
        assertTrue(dataRow.contains("1234567"), "CSV should contain the personnel number");
    }

    // Custom XMLReader for testing that uses a specific XML file
    private static class TestXMLReader extends XMLReader {
        public TestXMLReader(String xmlPath) {
            // Since FILENAME is final, we need to create a new instance with our test file
            // We'll manually parse the XML file here
            try {
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document doc = db.parse(new File(xmlPath));
                doc.getDocumentElement().normalize();

                NodeList list = doc.getElementsByTagName("Werknemer");

                // Clear existing records and add our test records
                xmlRecords.clear();

                for (int temp = 0; temp < list.getLength(); temp++) {
                    Node node = list.item(temp);

                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;

                        xmlRecord current = new xmlRecord();

                        current.werknemer = new Werknemer();
                        current.oorsprongsland = new Oorsprongsland();
                        current.aankomstland = new Aankomstland();
                        current.bedrijf = new Bedrijf();

                        current.werknemer.Personeelsnummer = element.getElementsByTagName("Personeelsnummer").item(0).getTextContent();
                        current.werknemer.personeelsnaam = element.getElementsByTagName("Personeelsnaam").item(0).getTextContent();
                        current.werknemer.Email = element.getElementsByTagName("Email-adres").item(0).getTextContent();
                        current.bedrijf.bedrijfsnaam = element.getElementsByTagName("Bedrijfsnaam").item(0).getTextContent();
                        current.bedrijf.afdeling = element.getElementsByTagName("Afdeling").item(0).getTextContent();
                        current.oorsprongsland.airport = element.getElementsByTagName("Vertrek-Luchthaven").item(0).getTextContent();
                        current.oorsprongsland.country = element.getElementsByTagName("Land-van-oorsprong").item(0).getTextContent();
                        current.aankomstland.airport = element.getElementsByTagName("Aankomst-Luchthaven").item(0).getTextContent();
                        current.aankomstland.country = element.getElementsByTagName("Land-van-aankomst").item(0).getTextContent();
                        current.werknemer.methodOfTravel = element.getElementsByTagName("Methode-van-vervoer").item(0).getTextContent();
                        current.werknemer.redenVoorReis = element.getElementsByTagName("Reason-for-travel").item(0).getTextContent();
                        current.oorsprongsland.departureDate = element.getElementsByTagName("Vertrekdatum").item(0).getTextContent();
                        current.aankomstland.arrivalDate = element.getElementsByTagName("Aankomst-datum").item(0).getTextContent();

                        xmlRecords.add(current);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
