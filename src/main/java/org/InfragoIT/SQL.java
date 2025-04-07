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


    public void SqlComparer(Connection conn, String personeelsnummer, String RedenVoorReis, String AankomstAirpot, String AankomstLand, String AankomstDatum, String OorsprongsLand, String OorsprongsAirport, String OorsprongsDatum, String CompanyName, String Department) throws SQLException {
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
                    ErrorMsg = "Departement voor: " + DBPname + " is fout";
                    ErrorWrong = Department;
                    ErrorRight = DBDepartment;
                    ErrorPnumber = DBPnumber;
                    fOut.OutputFileWriter(ErrorWrong, ErrorPnumber, ErrorMsg, ErrorRight);
                }
                if (!DBReasonForTravel.contains(RedenVoorReis)) {
                    ErrorMsg = "Reden voor reis is fout voor: " + DBPname;
                    ErrorWrong = RedenVoorReis;
                    ErrorRight = DBReasonForTravel;
                    ErrorPnumber = DBPnumber;
                    fOut.OutputFileWriter(ErrorWrong, ErrorPnumber, ErrorMsg, ErrorRight);
                }
                if (!DBDepartureAirport.contains(OorsprongsAirport)) {
                    ErrorMsg = "Oorsprongsvluchthaven is fout voor: " + DBPname;
                    ErrorWrong = OorsprongsAirport;
                    ErrorRight = DBDepartureAirport;
                    ErrorPnumber = DBPnumber;
                    fOut.OutputFileWriter(ErrorWrong, ErrorPnumber, ErrorMsg, ErrorRight);
                }
                if (!DBArrivalAirport.contains(AankomstAirpot)) {
                    ErrorMsg = "Aankomstluchthaven is fout voor: " + DBPname;
                    ErrorWrong = AankomstAirpot;
                    ErrorRight = DBArrivalAirport;
                    ErrorPnumber = DBPnumber;
                    fOut.OutputFileWriter(ErrorWrong, ErrorPnumber, ErrorMsg, ErrorRight);
                }
                if (!DBCompanyName.contains(CompanyName)) {
                    ErrorMsg = "Bedrijfsnaam is fout voor: " + DBPname;
                    ErrorWrong = CompanyName;
                    ErrorRight = DBCompanyName;
                    ErrorPnumber = DBPnumber;
                    fOut.OutputFileWriter(ErrorWrong, ErrorPnumber, ErrorMsg, ErrorRight);
                }
                if (!DBArrivalCountry.contains(AankomstLand)) {
                    ErrorMsg = "Aankomstland is fout voor: " + DBPname;
                    ErrorWrong = OorsprongsAirport;
                    ErrorRight = DBDepartureAirport;
                    ErrorPnumber = DBPnumber;
                    fOut.OutputFileWriter(ErrorWrong, ErrorPnumber, ErrorMsg, ErrorRight);
                }
                if (!DBDepartureCountry.contains(OorsprongsLand)) {
                    ErrorMsg = "Oorsprongsland is fout voor: " + DBPname;
                    ErrorWrong = OorsprongsLand;
                    ErrorRight = DBDepartureCountry;
                    ErrorPnumber = DBPnumber;
                    fOut.OutputFileWriter(ErrorWrong, ErrorPnumber, ErrorMsg, ErrorRight);
                }
                if (!DBDepartureDate.contains(OorsprongsDatum)) {
                    ErrorMsg = "Vertrekdatum is fout voor: " + DBPname;
                    ErrorWrong = OorsprongsDatum;
                    ErrorRight = DBDepartureDate;
                    ErrorPnumber = DBPnumber;
                    fOut.OutputFileWriter(ErrorWrong, ErrorPnumber, ErrorMsg, ErrorRight);
                }
                if (!DBArrivalDate.contains(AankomstDatum)) {
                    ErrorMsg = "Aankomstdatum is fout voor: " + DBPname;
                    ErrorWrong = AankomstDatum;
                    ErrorRight = DBArrivalDate;
                    ErrorPnumber = DBPnumber;
                    fOut.OutputFileWriter(ErrorWrong, ErrorPnumber, ErrorMsg, ErrorRight);
                }

            }
        }
    }
}

