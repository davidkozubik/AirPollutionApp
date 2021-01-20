package com.example.airpollutionapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.view.View;
import android.widget.Toast;
import android.widget.EditText;
import android.os.Bundle;
import android.view.MenuItem;
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

public class SecondActivity  extends AppCompatActivity
{
    EditText editText;
    TextView textView;
    String delka;
    String sirka;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);

        editText = findViewById(R.id.second_layout_edittext);
        editText.setText("Obsah z APIčka zde ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        textView = (TextView) findViewById(R.id.translated_text);

        // create the get Intent object
        Intent intent = getIntent();

        // receive the value by getStringExtra() method
        // and key must be same which is send by first activity
        delka = intent.getStringExtra("message_key_delka");
        sirka = intent.getStringExtra("message_key_sirka");
        getTranslationOnClick();
    }

    public void getTranslationOnClick()
    {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://api.airvisual.com/v2/nearest_city?lat="+sirka+"&lon="+delka+ "&key=b7f6e7a9-f780-4c1c-a60b-411feb253d92";

        // Request a string response from the provided URL.
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

                            // 3. Z PROMENNE responseData ZISKAME "translatedText" (viz struktura JSONu odpovedi)
                            // V PROMENNE translatedText JE ULOZEN VYSLEDEK PREKLADU
                            String translatedText = responseData.getString("country");
                            String translatedText2 = responseData.getString("city");
                            String translatedText3 = responseData3.getString("maincn");

                            // 4. V textView ZOBRAZIME VYSLEDEK PREKLADU
                            String[] pole = {translatedText,translatedText2,translatedText3};
                            textView.setText(TextUtils.join(",",pole));

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

}