package com.sujith.covidtracker;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
/**
 * To show Covid details for Selected Country.
 */
public class CountryCovidData extends AppCompatActivity {
    ArrayList<CovidData> covidDataArrayList = new ArrayList<CovidData>();
    GridView covidDataGridView;
    String countryCode;
    String countryName;
    String recovered;
    String critical;
    String deaths;
    String confirmed;
    String currentDateTimeString;
    private int positionCountry;
    TextView countryNameHolder, time;
    ImageView flagImage;
    private static String xRapidapiKey = "x-rapidapi-key";
    private static String apiKey = "c76920adb1msh335f15c0d0f3ea0p13e2f4jsnce0810631275";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.country_covid_data);
        Intent intent = getIntent();
        positionCountry = intent.getIntExtra("position", 0);
        countryCode = MainActivity.countryModelsList.get(positionCountry).getAlphaCode();
        getSupportActionBar().setTitle("Details of " + MainActivity.countryModelsList.get(positionCountry).getCountry());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        currentDateTimeString = new SimpleDateFormat("MMM dd yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
        countryNameHolder = findViewById(R.id.countryName);
        getCountryData();
        countryCode = countryCode.toLowerCase();
        String url = "https://flagcdn.com/h40/" + countryCode + ".png";
        flagImage = findViewById(R.id.imageFlag);
        time = findViewById(R.id.countryTime);
        time.setText(currentDateTimeString + " GMT");
        Glide.with(CountryCovidData.this).load(url).into(flagImage);
        covidDataGridView = findViewById(R.id.countryDataGridViewLayout);
    }
    /**
     * To fetch the Country Covid details from given API.
     */
    private void getCountryData() {
        String url = "https://covid-19-data.p.rapidapi.com/country/code?code=" + countryCode;
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                countryName = jsonObject.getString("country");
                                countryNameHolder.setText(countryName);
                                confirmed = jsonObject.getString("confirmed");
                                covidDataArrayList.add(new CovidData(confirmed, "confirmed", Color.BLUE));
                                recovered = jsonObject.getString("recovered");
                                covidDataArrayList.add(new CovidData(recovered, "recovered", Color.GREEN));
                                critical = jsonObject.getString("critical");
                                covidDataArrayList.add(new CovidData(critical, "critical", Color.RED));
                                deaths = jsonObject.getString("deaths");
                                covidDataArrayList.add(new CovidData(deaths, "deaths", Color.DKGRAY));
                            }
                            CountryCovidDataAdapter adapter = new CountryCovidDataAdapter(CountryCovidData.this, covidDataArrayList);
                            covidDataGridView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CountryCovidData.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json; charset=UTF-8");
                params.put(xRapidapiKey, apiKey);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}