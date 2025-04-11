package org.InfragoIT;

import java.sql.*;

public class SQL {
    String db_Pnumber;
    String db_Pname;
    String db_Email;
    String db_ReasonForTravel;
    String db_MethodOfTravel;
    String db_ArrivalAirport;
    String db_DepartureAirport;
    String db_ArrivalCountry;
    String db_DepartureCountry;
    String db_ArrivalDate;
    String db_DepartureDate;
    String DBDepartment;
    String db_CompanyName;
    String errorPnumber;
    String errorMsg;
    String errorWrong;
    String errorRight;


    public void SqlComparer(Connection conn, String personeelsnummer, String RedenVoorReis, String AankomstAirpot, String AankomstLand, String AankomstDatum, String OorsprongsLand, String OorsprongsAirport, String OorsprongsDatum, String CompanyName, String Department) throws SQLException {

        OutputFileCreator fOut = new OutputFileCreator(); // create object for the output file creator for later use

        String SQL = "{call bamtravelcheck.selectForEquation(?)}"; //create sql statement that calls stored procedure in mysql database. Stored Procedure is also in the SQLRollback.sql file

        try (CallableStatement cstmt = conn.prepareCall(SQL)) { //assigns the stuff in the SQL string to the conn object
            cstmt.setString(1, personeelsnummer); // assigns value to ? in the callable statement

            ResultSet rs = cstmt.executeQuery(); // executes query and assigns it to result set
            while (rs.next()) { // while resultset (rs) has a result, do the following:
                        /*
                        column index numbers:
                        1 = Personeelsnummer
                        2 = personeelsnaam
                        3 = EmailAdres
                        4 = redenVoorReis
                        5 = MethodeVanVervoer
                        6 = bedrijfsnaam
                        7 = afdeling
                        8 = VliegveldID (OorsprongsAirport)
                        9 = Land van oorsprong
                        10 = Vertrekdatum
                        11 = VliegveldID (AankomstAirport)
                        12 = Land van aankomst
                        13 = aankomstdatum
                         */

                db_Pnumber = rs.getString(1);
                db_Pname = rs.getString(2);
                db_Email = rs.getString(3);
                db_ReasonForTravel = rs.getString(4);
                db_MethodOfTravel = rs.getString(5);
                db_CompanyName = rs.getString(6);
                DBDepartment = rs.getString(7);
                db_DepartureAirport = rs.getString(8);
                db_DepartureCountry = rs.getString(9);
                db_DepartureDate = rs.getString(10);
                db_ArrivalAirport = rs.getString(11);
                db_ArrivalCountry = rs.getString(12);
                db_ArrivalDate = rs.getString(13);

                db_Pname = db_Pname.replaceAll("-", " ");
                db_ReasonForTravel = db_ReasonForTravel.replaceAll("-", " ");
                db_MethodOfTravel = db_MethodOfTravel.replaceAll("-", " ");
                db_DepartureAirport = db_DepartureAirport.replaceAll("-", " ");
                db_ArrivalAirport = db_ArrivalAirport.replaceAll("-", " ");
                db_CompanyName = db_CompanyName.replaceAll("-", " ");


                if (!DBDepartment.contains(Department)) {
                    errorMsg = "Departement voor: " + db_Pname + " is fout";
                    errorWrong = Department;
                    errorRight = DBDepartment;
                    errorPnumber = db_Pnumber;
                    fOut.OutputFileWriter(errorWrong, errorPnumber, errorMsg, errorRight);
                }
                if (!db_ReasonForTravel.contains(RedenVoorReis)) {
                    errorMsg = "Reden voor reis is fout voor: " + db_Pname;
                    errorWrong = RedenVoorReis;
                    errorRight = db_ReasonForTravel;
                    errorPnumber = db_Pnumber;
                    fOut.OutputFileWriter(errorWrong, errorPnumber, errorMsg, errorRight);
                }
                if (!db_DepartureAirport.contains(OorsprongsAirport)) {
                    errorMsg = "Oorsprongsvluchthaven is fout voor: " + db_Pname;
                    errorWrong = OorsprongsAirport;
                    errorRight = db_DepartureAirport;
                    errorPnumber = db_Pnumber;
                    fOut.OutputFileWriter(errorWrong, errorPnumber, errorMsg, errorRight);
                }
                if (!db_ArrivalAirport.contains(AankomstAirpot)) {
                    errorMsg = "Aankomstluchthaven is fout voor: " + db_Pname;
                    errorWrong = AankomstAirpot;
                    errorRight = db_ArrivalAirport;
                    errorPnumber = db_Pnumber;
                    fOut.OutputFileWriter(errorWrong, errorPnumber, errorMsg, errorRight);
                }
                if (!db_CompanyName.contains(CompanyName)) {
                    errorMsg = "bedrijfsnaam is fout voor: " + db_Pname;
                    errorWrong = CompanyName;
                    errorRight = db_CompanyName;
                    errorPnumber = db_Pnumber;
                    fOut.OutputFileWriter(errorWrong, errorPnumber, errorMsg, errorRight);
                }
                if (!db_ArrivalCountry.contains(AankomstLand)) {
                    errorMsg = "Aankomstland is fout voor: " + db_Pname;
                    errorWrong = OorsprongsAirport;
                    errorRight = db_DepartureAirport;
                    errorPnumber = db_Pnumber;
                    fOut.OutputFileWriter(errorWrong, errorPnumber, errorMsg, errorRight);
                }
                if (!db_DepartureCountry.contains(OorsprongsLand)) {
                    errorMsg = "Oorsprongsland is fout voor: " + db_Pname;
                    errorWrong = OorsprongsLand;
                    errorRight = db_DepartureCountry;
                    errorPnumber = db_Pnumber;
                    fOut.OutputFileWriter(errorWrong, errorPnumber, errorMsg, errorRight);
                }
                if (!db_DepartureDate.contains(OorsprongsDatum)) {
                    errorMsg = "Vertrekdatum is fout voor: " + db_Pname;
                    errorWrong = OorsprongsDatum;
                    errorRight = db_DepartureDate;
                    errorPnumber = db_Pnumber;
                    fOut.OutputFileWriter(errorWrong, errorPnumber, errorMsg, errorRight);
                }
                if (!db_ArrivalDate.contains(AankomstDatum)) {
                    errorMsg = "Aankomstdatum is fout voor: " + db_Pname;
                    errorWrong = AankomstDatum;
                    errorRight = db_ArrivalDate;
                    errorPnumber = db_Pnumber;
                    fOut.OutputFileWriter(errorWrong, errorPnumber, errorMsg, errorRight);
                }



            }
        }
    }
}

