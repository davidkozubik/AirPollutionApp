package com.example.airpollutionapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.View;
import android.widget.Toast;
import android.widget.EditText;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import android.widget.Button;
import android.text.TextUtils;
import android.graphics.Color;

public class SecondActivity  extends AppCompatActivity implements OnMapReadyCallback
{
    //private GpsTracker gpsTracker;
    GoogleMap map;
    TextView textView;
    TextView textView2;
    TextView textView3;
    String delka;
    String sirka;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        textView = (TextView) findViewById(R.id.zobrazitStat);
        textView2 = (TextView) findViewById(R.id.zobrazitMesto);
        textView3 = (TextView) findViewById(R.id.zobrazitIndex);

        // create the get Intent object
        Intent intent = getIntent();

        // receive the value by getStringExtra() method
        // and key must be same which is send by first activity
        delka = intent.getStringExtra("message_key_delka");
        sirka = intent.getStringExtra("message_key_sirka");
        getTranslationOnClick();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void getTranslationOnClick()
    {
        // vytvořím instanci RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://api.airvisual.com/v2/nearest_city?lat="+sirka+"&lon="+delka+ "&key=b7f6e7a9-f780-4c1c-a60b-411feb253d92";

        // Vyžádání řetězcové odpovědi z poskytnuté adresy URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        // ZPRACOVANI JSONu:
                        try
                        {
                            //1. Z DAT, KTERA JSME OBDRZELI VYTVORIME JSONObject
                            JSONObject jsonObject = new JSONObject(response);

                            // 2. Z PROMENNE jsonObject ZISKAME "responseData" (viz struktura JSONu odpovedi)
                            JSONObject responseData = jsonObject.getJSONObject("data");
                            JSONObject responseData2 = responseData.getJSONObject("current");
                            JSONObject responseData3 = responseData2.getJSONObject("pollution");

                            // 3. Z PROMENNE responseData ZISKAME "zobrazit_" (viz struktura JSONu odpovedi)
                            // V PROMENNE zobrazit_ JE ULOZEN VYSLEDEK
                            String zobrazitStat = responseData.getString("country");
                            String zobrazitMesto = responseData.getString("city");
                            String zobrazitIndex = responseData3.getString("aqius");

                            // 4. V textView ZOBRAZIME VYSLEDEK
                            String[] pole = {zobrazitStat};
                            textView.setText(TextUtils.join(",",pole));

                            String[] pole2 = {zobrazitMesto};
                            textView2.setText(TextUtils.join(",",pole2));

                            String[] pole3 = {zobrazitIndex};
                            textView3.setText(TextUtils.join(",",pole3));

                            if(Integer.parseInt(pole3[0]) <= 50)
                            {
                                textView3.setBackgroundColor(Color.parseColor("#23FC01"));
                            }
                            else if((Integer.parseInt(pole3[0]) <= 100) && (Integer.parseInt(pole3[0]) >= 51))
                            {
                                textView3.setBackgroundColor(Color.parseColor("#FCE901"));
                            }
                            else if((Integer.parseInt(pole3[0]) <= 150) && (Integer.parseInt(pole3[0]) >= 101))
                            {
                                textView3.setBackgroundColor(Color.parseColor("#FC8201"));
                            }
                            else if((Integer.parseInt(pole3[0]) <= 200) && (Integer.parseInt(pole3[0]) >= 151))
                            {
                                textView3.setBackgroundColor(Color.parseColor("#FC0101"));
                            }
                            else if((Integer.parseInt(pole3[0]) <= 300) && (Integer.parseInt(pole3[0]) >= 201))
                            {
                                textView3.setBackgroundColor(Color.parseColor("#7D0A72"));
                            }
                            else if(Integer.parseInt(pole3[0]) >= 301)
                            {
                                textView3.setBackgroundColor(Color.parseColor("#8C0E4F"));
                            }
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        textView.setText("That didn't work!");
                    }
                });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        double s;
        double d;

        if (!sirka.equals("") && !delka.equals(""))
        {
            s = Double.parseDouble(sirka);
            d = Double.parseDouble(delka);
        }
        else
        {
        //    gpsTracker = new GpsTracker(this);
          //  if(gpsTracker.canGetLocation()){
            //    s = gpsTracker.getLatitude();
              //  d = gpsTracker.getLongitude();
            //}
          //  else
            //    {
                s = 49.22436;
                d = 17.66254;
              //  }
        }

        LatLng OznaceneMisto = new LatLng(s, d);
        map.addMarker(new MarkerOptions().position(OznaceneMisto).title("OznaceneMisto"));
        map.moveCamera(CameraUpdateFactory.newLatLng(OznaceneMisto));
    }
}