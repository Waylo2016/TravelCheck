package org.InfragoIT;

import java.util.*;

class Country {
    String Country;
    String Airport;


    public Country(String Country, String Airport) {
        this.Country = Country;
        this.Airport = Airport;
    }

    public Country() {

    }

    public String popularCountry(String ArrivalCountry, String DepartureCountry) { //count most popular arrival/departure country
        String popularCountry = "";
        Map<String, Integer> popularCountryCount = new HashMap<>();
        updateCountryFrequency(popularCountryCount, ArrivalCountry, DepartureCountry);
        int maxCount = 0;


        for (Map.Entry<String, Integer> entry : popularCountryCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                popularCountry = entry.getKey();
            }
        }
        return popularCountry;
    }

    private static void updateCountryFrequency(Map<String, Integer> popularCountryCount, String ArrivalCountry, String DepartureCountry) {
        popularCountryCount.put(ArrivalCountry, popularCountryCount.getOrDefault(ArrivalCountry, 0) + 1);
        popularCountryCount.put(DepartureCountry, popularCountryCount.getOrDefault(DepartureCountry, 0) + 1);
    }

    public String popularAirport(String ArrivalAirport, String DepartureAirport){
        String popularAirport = "";
        Map<String, Integer> popularAirportCount = new HashMap<>();
        updateAirportFrequency(popularAirportCount, ArrivalAirport, DepartureAirport);

        int maxCount = 0;

        for (Map.Entry<String, Integer> entry : popularAirportCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                popularAirport = entry.getKey();
            }
        }


        return popularAirport;
    }

    private static void updateAirportFrequency(Map<String, Integer> popularAirportCount, String ArrivalAirport, String DepartureAirport) {
        popularAirportCount.put(ArrivalAirport, popularAirportCount.getOrDefault(ArrivalAirport, 0) + 1);
        popularAirportCount.put(DepartureAirport, popularAirportCount.getOrDefault(DepartureAirport, 0) + 1);
    }
}
