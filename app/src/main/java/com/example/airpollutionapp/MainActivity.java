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
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Environment;
import java.io.File;
import java.io.FileOutputStream;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    final static String appFileName = "appFile.txt";
    final static String appDirName = "/appDir/";
    final static String appDirPath = Environment.getExternalStorageDirectory().getAbsolutePath() + appDirName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       bottomNavigationView=findViewById(R.id.bottomNav);

        bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavMethod);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();
    }


    private boolean writeToExternalMemory(String dataToWrite)
    {
        try
        {
            File appDir = new File(appDirPath);
                appDir.mkdir();
                FileOutputStream fos = new FileOutputStream(appDirPath + appFileName,true);
                fos.write(dataToWrite.getBytes());
                fos.flush();
                fos.close();

            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }



    public void myButtonClick(View v)
    {
        EditText jzemDelka;
        EditText jzemSirka;

        jzemSirka = (EditText) findViewById(R.id.zemSirka);
        jzemDelka = (EditText) findViewById(R.id.zemDelka);

        String delka = jzemDelka.getText().toString();
        String sirka = jzemSirka.getText().toString();

        // Create the Intent object of this class Context() to Second_activity class
        Intent intent = new Intent(this, SecondActivity.class);

        // now by putExtra method put the value in key, value pair
        // key is message_key by this key we will receive the value, and put the string

        intent.putExtra("message_key_delka", delka);
        intent.putExtra("message_key_sirka", sirka);

        writeToExternalMemory("delka: "+delka+" sirka: "+sirka+"\n");

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