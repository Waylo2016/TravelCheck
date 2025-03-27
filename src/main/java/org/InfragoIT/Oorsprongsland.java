package org.InfragoIT;

class Oorsprongsland extends Country { // maak klasse van oorsprongsland
    String DepartureDate;

    public Oorsprongsland(String DepartureDate, String Country, String Airport) {
        super(Airport, Country);
        this.DepartureDate = DepartureDate;
    }

    public Oorsprongsland() {
        super();
    }
}
