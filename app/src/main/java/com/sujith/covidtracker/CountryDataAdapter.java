package com.sujith.covidtracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
/**
 * To hold CountriesList.
 */
public class CountryDataAdapter extends ArrayAdapter<CountryData> {
    private String code;
    private Context context;
    private List<CountryData> countryModelsList;
    private List<CountryData> countryModelsListFiltered;

    public CountryDataAdapter(Context context, List<CountryData> countryModelsList) {
        super(context, R.layout.country_data_adapter, countryModelsList);
        this.context = context;
        this.countryModelsList = countryModelsList;
        this.countryModelsListFiltered = countryModelsList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_data_adapter, null, true);
        TextView tvCountryName = view.findViewById(R.id.tvCountryName);
        ImageView imageView = view.findViewById(R.id.imageFlag);
        tvCountryName.setText(countryModelsListFiltered.get(position).getCountry());
        code = countryModelsListFiltered.get(position).getAlphaCode();
        code = code.toLowerCase();
        String url = "https://flagcdn.com/h40/" + code + ".png";
        Glide.with(context).load(url).into(imageView);
        return view;
    }

    @Override
    public int getCount() {
        return countryModelsListFiltered.size();
    }

    @Nullable
    @Override
    public CountryData getItem(int position) {
        return countryModelsListFiltered.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (constraint == null || constraint.length() == 0) {
                    filterResults.count = countryModelsList.size();
                    filterResults.values = countryModelsList;
                } else {
                    List<CountryData> resultsModel = new ArrayList<>();
                    String searchStr = constraint.toString().toLowerCase();
                    for (CountryData itemsModel : countryModelsList) {
                        if (itemsModel.getCountry().toLowerCase().contains(searchStr)) {
                            resultsModel.add(itemsModel);
                        }
                        filterResults.count = resultsModel.size();
                        filterResults.values = resultsModel;
                    }
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                countryModelsListFiltered = (List<CountryData>) results.values;
                MainActivity.countryModelsList = (List<CountryData>) results.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }
}