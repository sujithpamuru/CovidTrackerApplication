package com.sujith.covidtracker;
/**
 * To initialize Country Covid details.
 */
public class CovidData {

    private String CountryName;
    private String confirmed;
    private int color;


    public CovidData(String CountryName, String confirmed, int color) {
        this.CountryName = CountryName;
        this.confirmed = confirmed;
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public String getCountryName() {
        return CountryName;
    }

    public String getConfirmed() {
        return confirmed;
    }
}