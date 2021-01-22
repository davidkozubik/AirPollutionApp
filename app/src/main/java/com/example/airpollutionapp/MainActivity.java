package com.example.airpollutionapp;

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

public class MainActivity extends AppCompatActivity {

   // EditText jzemDelka;
   // EditText jzemSirka;
   // TextView textView;
   // EditText editText;

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       bottomNavigationView=findViewById(R.id.bottomNav);
       // jzemSirka = (EditText) findViewById(R.id.zemSirka);
       // jzemDelka = (EditText) findViewById(R.id.zemDelka);

        bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavMethod);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();

       // textView = (TextView) findViewById(R.id.translated_text);
        //editText = (EditText) findViewById(R.id.text_for_translation);

    }
/*
    public void getTranslationOnClick(View v)
    {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://api.airvisual.com/v2/nearest_city?lat="+jzemSirka.getText().toString()+"&lon="+jzemDelka.getText().toString() + "&key=b7f6e7a9-f780-4c1c-a60b-411feb253d92";

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

                            // 3. Z PROMENNE responseData ZISKAME "translatedText" (viz struktura JSONu odpovedi)
                            // V PROMENNE translatedText JE ULOZEN VYSLEDEK PREKLADU
                            String translatedText = responseData.getString("city");

                            // 4. V textView ZOBRAZIME VYSLEDEK PREKLADU
                            textView.setText(translatedText);
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


*/



    public void myButtonClick(View v)
    {
        EditText jzemDelka;
        EditText jzemSirka;

        jzemSirka = (EditText) findViewById(R.id.zemSirka);
        jzemDelka = (EditText) findViewById(R.id.zemDelka);

       // Toast.makeText(this, "", Toast.LENGTH_LONG).show();
        // get the value which input by user in EditText
        // and convert it to string
        String delka = jzemDelka.getText().toString();
        String sirka = jzemSirka.getText().toString();

        // Create the Intent object of this class Context() to Second_activity class
        Intent intent = new Intent(this, SecondActivity.class);

        // now by putExtra method put the value in key, value pair
        // key is message_key by this key we will receive the value, and put the string

        intent.putExtra("message_key_delka", delka);
        intent.putExtra("message_key_sirka", sirka);

        // start the Intent
        startActivity(intent);
    }



    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavMethod=new
            BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                    Fragment fragment = null;
                  switch (menuItem.getItemId())
                  {
                      case R.id.about:
                        fragment=new AboutFragment();
                        break;

                      case R.id.history:
                          fragment=new HistoryFragment();
                          break;

                      case R.id.home:
                          fragment=new HomeFragment();
                          break;
                  }
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
                    return true;
                }
            };
}