package org.InfragoIT;

import java.io.File;

public class FileDelyeeter{
    public static void FileDeleter(){
        String userHome = System.getProperty("user.home");
        File DoneWithXML = new File(userHome+"/TravelCheck/src/Frontend/TraveCheckFront/XmlFolder/XmlUnformedData.xml");
        DoneWithXML.delete();
    }
}
