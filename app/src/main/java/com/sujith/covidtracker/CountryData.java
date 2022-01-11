package com.sujith.covidtracker;

public class CountryData {
    private String alphaCode, country;

    public CountryData(String country, String alphaCode) {
        this.country = country;
        this.alphaCode = alphaCode;
    }

    public CountryData(String country) {
        this.country = country;
    }

    public String getAlphaCode() {
        return alphaCode;
    }

    public String getCountry() {
        return country;
    }
}