package com.sujith.covidtracker;

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