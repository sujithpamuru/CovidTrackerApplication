package com.sujith.covidtracker;
/**
 * To initialize Country data.
 */
public class CountryData {
    private String alphaCode, country;

    public CountryData(String country, String alphaCode) {
        this.country = country;
        this.alphaCode = alphaCode;
    }

    public String getAlphaCode() {
        return alphaCode;
    }

    public String getCountry() {
        return country;
    }
}