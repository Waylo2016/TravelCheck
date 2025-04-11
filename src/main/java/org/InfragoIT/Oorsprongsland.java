package org.InfragoIT;

class Oorsprongsland extends Country { // maak klasse van oorsprongsland
    String departureDate;

    public Oorsprongsland(String DepartureDate, String Country, String Airport) {
        super(Airport, Country);
        this.departureDate = DepartureDate;
    }

    public Oorsprongsland() {
        this(null, null, null);
    }
}
