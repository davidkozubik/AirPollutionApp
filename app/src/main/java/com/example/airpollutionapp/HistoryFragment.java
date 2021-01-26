package com.example.airpollutionapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class HistoryFragment extends Fragment {

    final static String appFileName = "appFile.txt";
    final static String appDirName = "/appDir/";
    final static String appDirPath = Environment.getExternalStorageDirectory().getAbsolutePath() + appDirName;
    String oneLine = "";
    TextView historickyText;

    public HistoryFragment() {
        // Required empty public constructor
    }

    private boolean readFromExternalMemory()
    {
        try
        {
            File appFile = new File(appDirPath + appFileName);
            if(appFile.exists())
            {
                FileInputStream fis = new FileInputStream (new File(appDirPath + appFileName));
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader bufferedReader = new BufferedReader(isr);

                String readResult = "";

                while ( (oneLine = bufferedReader.readLine()) != null )
                {
                    readResult += oneLine + "\n";
                }

                bufferedReader.close();
                isr.close();
                fis.close();

                historickyText.setText(readResult);
            }

            return true;
        }
        catch (Exception e)
        {
            historickyText.setText("Chyba cteni");
            return false;
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_history, container, false);
        historickyText = v.findViewById(R.id.historickyText);
        readFromExternalMemory();
        return v;
    }
}