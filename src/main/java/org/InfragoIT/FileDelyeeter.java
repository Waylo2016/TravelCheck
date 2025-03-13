package org.InfragoIT;

import java.io.File;

public class FileDelyeeter{
    public static void FileDeleter(){
        File DoneWithXML = new File("C:/Users/stijn/TravelCheck/src/XmlUnformedData.xml");
        DoneWithXML.delete();
    }
}
