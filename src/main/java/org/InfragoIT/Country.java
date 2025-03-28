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

    public String popularCountry(String AankomstLand, String VertrekLand) {
        String popularCountry = "";
        Map<String, Integer> popularCountryCount = new HashMap<>();
        updateFrequency(popularCountryCount, AankomstLand, VertrekLand);
        int maxCount = 0;


        for (Map.Entry<String, Integer> entry : popularCountryCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                popularCountry = entry.getKey();
            }
        }
        return popularCountry;
    }

    private static void updateFrequency(Map<String, Integer> popularCountryCount, String AankomstLand, String VertrekLand) {
        popularCountryCount.put(AankomstLand, popularCountryCount.getOrDefault(AankomstLand, 0) + 1);
        popularCountryCount.put(VertrekLand, popularCountryCount.getOrDefault(VertrekLand, 0) + 1);
    }
}
