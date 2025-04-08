package org.InfragoIT;

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
import java.util.ArrayList;


public class XMLReader {
    String userHome = System.getProperty("user.home");
    public final String FILENAME = userHome + "/TravelCheck/src/main/XmlFolder/XmlUnformedData.xml"; //read CSV file. Only use the C# code to create new XML file from Project Stijn - Blad 1.csv in working directory

    ArrayList<xmlRecord> xmlRecords = new ArrayList<>();


    public XMLReader() {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();


        try {

            // optional, but recommended
            // process XML securely, avoid attacks like XML External Entities (XXE)
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            // parse XML file
            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(new File(FILENAME));

            // optional, but recommended
            // http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

            System.out.println("Root Element :" + doc.getDocumentElement().getNodeName());
            System.out.println("------");

            // get <staff>
            NodeList list = doc.getElementsByTagName("Werknemer");

            for (int temp = 0; temp < list.getLength(); temp++) {

                Node node = list.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;
                    xmlRecord current = new xmlRecord();
                    current.werknemer = new Werknemer();
                    current.oorsprongsland = new Oorsprongsland();
                    current.aankomstland = new Aankomstland();
                    current.bedrijf = new Bedrijf();

                    // get text
                    current.werknemer.Personeelsnummer = element.getElementsByTagName("Personeelsnummer").item(0).getTextContent(); // read XML file into separate strings for printing and further use
                    current.werknemer.Personeelsnaam = element.getElementsByTagName("Personeelsnaam").item(0).getTextContent();
                    current.werknemer.Email = element.getElementsByTagName("Email-adres").item(0).getTextContent();
                    current.bedrijf.Bedrijfsnaam = element.getElementsByTagName("Bedrijfsnaam").item(0).getTextContent();
                    current.bedrijf.Afdeling = element.getElementsByTagName("Afdeling").item(0).getTextContent();
                    current.oorsprongsland.Airport = element.getElementsByTagName("Vertrek-Luchthaven").item(0).getTextContent();
                    current.oorsprongsland.Country = element.getElementsByTagName("Land-van-oorsprong").item(0).getTextContent();
                    current.aankomstland.Airport = element.getElementsByTagName("Aankomst-Luchthaven").item(0).getTextContent();
                    current.aankomstland.Country = element.getElementsByTagName("Land-van-aankomst").item(0).getTextContent();
                    current.werknemer.MethodOfTravel = element.getElementsByTagName("Methode-van-vervoer").item(0).getTextContent();
                    current.werknemer.RedenVoorReis = element.getElementsByTagName("Reason-for-travel").item(0).getTextContent();
                    current.oorsprongsland.DepartureDate = element.getElementsByTagName("Vertrekdatum").item(0).getTextContent();
                    current.aankomstland.ArrivalDate = element.getElementsByTagName("Aankomst-datum").item(0).getTextContent();

                    xmlRecords.add(current); // adds the current data to the arraylist
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

    }

}


