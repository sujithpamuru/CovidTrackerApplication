package com.sujith.covidtracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
/**
 * To hold Covid details for Selected Country.
 */
class CountryCovidDataAdapter extends ArrayAdapter<CovidData> {
    public CountryCovidDataAdapter(@NonNull Context context, ArrayList<CovidData> covidDataArrayList) {
        super(context, 0, covidDataArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listitemView = convertView;
        if (listitemView == null) {
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.country_covid_data_adapter, parent, false);
        }
        CovidData covidData = getItem(position);
        TextView countryDataInfo = listitemView.findViewById(R.id.countryName);
        TextView countryName = listitemView.findViewById(R.id.covidData);
        countryDataInfo.setText(covidData.getCountryName());
        countryName.setText(covidData.getConfirmed());
        countryDataInfo.setTextColor(covidData.getColor());
        return listitemView;
    }
}