package org.InfragoIT;

class Aankomstland extends Country { // maak klasse voor aankomstland
    String arrivalDate;

    public Aankomstland(String arrivalDate, String Country, String Airport) {
        super(Country, Airport);
        this.arrivalDate = arrivalDate;
    }

    public Aankomstland() {
        this(null, null, null);
    }

}
