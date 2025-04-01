package org.InfragoIT;

import java.sql.*;

public class SQL {
    String DBPnumber;
    String DBPname;
    String DBEmail;
    String DBReasonForTravel;
    String DBMethodOfTravel;
    String DBArrivalAirport;
    String DBDepartureAirport;
    String DBArrivalCountry;
    String DBDepartureCountry;
    String DBArrivalDate;
    String DBDepartureDate;
    String DBDepartment;
    String DBCompanyName;
    String ErrorPnumber;
    String ErrorMsg;
    String ErrorWrong;
    String ErrorRight;


    public void SqlComparer(Connection conn, String personeelsnummer, String personeelsNaam, String Email, String RedenVoorReis, String MethodOfTravel, String AankomstAirpot, String AankomstLand, String AankomstDatum, String OorsprongsLand, String OorsprongsAirport, String OorsprongsDatum, String CompanyName, String Department) throws SQLException {
        OutputFileCreator fOut = new OutputFileCreator();
        String SQL = "{call bamtravelcheck.selectForEquation(?)}";
        try (CallableStatement cstmt = conn.prepareCall(SQL)) {
            cstmt.setString(1, personeelsnummer);
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                        /*
                        column index numbers:
                        1 = Personeelsnummer
                        2 = Personeelsnaam
                        3 = EmailAdres
                        4 = RedenVoorReis
                        5 = MethodeVanVervoer
                        6 = Bedrijfsnaam
                        7 = Afdeling
                        8 = VliegveldID (OorsprongsAirport)
                        9 = Land van oorsprong
                        10 = Vertrekdatum
                        11 = VliegveldID (AankomstAirport)
                        12 = Land van aankomst
                        13 = aankomstdatum
                         */

                    DBPnumber = rs.getString(1);
                    DBPname = rs.getString(2);
                    DBEmail = rs.getString(3);
                    DBReasonForTravel = rs.getString(4);
                    DBMethodOfTravel = rs.getString(5);
                    DBCompanyName = rs.getString(6);
                    DBDepartment = rs.getString(7);
                    DBDepartureAirport = rs.getString(8);
                    DBDepartureCountry = rs.getString(9);
                    DBDepartureDate = rs.getString(10);
                    DBArrivalAirport = rs.getString(11);
                    DBArrivalCountry = rs.getString(12);
                    DBArrivalDate = rs.getString(13);

                DBPname = DBPname.replaceAll("-", " ");
                DBReasonForTravel = DBReasonForTravel.replaceAll("-", " ");
                DBMethodOfTravel = DBMethodOfTravel.replaceAll("-", " ");
                DBDepartureAirport = DBDepartureAirport.replaceAll("-", " ");
                DBArrivalAirport = DBArrivalAirport.replaceAll("-", " ");
                DBCompanyName = DBCompanyName.replaceAll("-", " ");


                if (!DBDepartment.contains(Department)) {

                    System.out.println(DBPnumber);
                    System.out.println(personeelsnummer);
                    ErrorMsg = "Department for " + DBPname + " is incorrect";
                        ErrorWrong = Department;
                        ErrorRight = DBDepartment;
                    ErrorPnumber = DBPnumber;
                    fOut.OutputFileWriter(ErrorWrong, ErrorPnumber, ErrorMsg, ErrorRight);
                    }

            }
            }
    }
}
