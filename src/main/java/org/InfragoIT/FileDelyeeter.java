package org.InfragoIT;

import java.io.File;
import java.nio.file.*;

public class FileDelyeeter{
    public static void FileDeleter(){
        Path path = Paths.get(System.getProperty("user.home"), "TravelCheck", "src", "Frontend", "TraveCheckFront", "XmlFolder", "XmlUnformedData");
        File file = path.toFile();
        File newPath = new File(file.getAbsolutePath());
        newPath.delete();
    }
}
