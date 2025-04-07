package org.InfragoIT;

class Aankomstland extends Country { // maak klasse voor aankomstland
    String ArrivalDate;

    public Aankomstland(String ArrivalDate, String Country, String Airport) {
        super(Country, Airport);
        this.ArrivalDate = ArrivalDate;
    }

    public Aankomstland() {
        super();
    }

    public String returnMostCommonAirport() {
        String mostCommonAirport = "";
        return mostCommonAirport;
    }
}
