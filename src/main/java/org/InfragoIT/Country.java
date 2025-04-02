package org.InfragoIT;

import java.util.*;

class Country {
    String Country;
    String Airport;

    String popularCountry;


    public Country(String Country, String Airport) {
        this.Country = Country;
        this.Airport = Airport;
        this.popularCountry = "";
    }

    public Country() {
        this.popularCountry = "";
    }

    //count most popular arrival/departure country v Hashmaps
    public void popularCountry(String ArrivalCountry, String DepartureCountry) {
        Map<String, Integer> popularCountryCount = new HashMap<>();
        updateCountryFrequency(popularCountryCount, ArrivalCountry, DepartureCountry);
        int maxCount = 0;



        for (Map.Entry<String, Integer> entry : popularCountryCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                popularCountry = entry.getKey();
            }
        }
    }

    // up the count in the popular country count using both the arrival and departure countries
    private static void updateCountryFrequency(Map<String, Integer> popularCountryCount, String ArrivalCountry, String DepartureCountry) {
        popularCountryCount.put(ArrivalCountry, popularCountryCount.getOrDefault(ArrivalCountry, 0) + 1);
        popularCountryCount.put(DepartureCountry, popularCountryCount.getOrDefault(DepartureCountry, 0) + 1);
    }

    // counts the popular airport using hashmaps
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

    // up the count in the popular airport count using both the arrival and departure airports
    private static void updateAirportFrequency(Map<String, Integer> popularAirportCount, String ArrivalAirport, String DepartureAirport) {
        popularAirportCount.put(ArrivalAirport, popularAirportCount.getOrDefault(ArrivalAirport, 0) + 1);
        popularAirportCount.put(DepartureAirport, popularAirportCount.getOrDefault(DepartureAirport, 0) + 1);
    }
}
