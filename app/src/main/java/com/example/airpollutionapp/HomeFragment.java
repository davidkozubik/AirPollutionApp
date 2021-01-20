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

public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_home, container, false);
        // Inflate the layout for this fragment


       /* Button tlacitko = (Button) v.findViewById(R.id.tlacitko);
        tlacitko.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getTranslationOnClick(v);
            }
        });
*/
        return v;
    }
/*
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tlacitko:
                c *= fComm.fragmentContactActivity(2);
                break;
    } */
}
