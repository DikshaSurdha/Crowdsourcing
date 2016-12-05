package com.example.all.crowdsourcing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;

/**
 * Created by All on 12/4/2016.
 */

public class Report extends AppCompatActivity implements View.OnClickListener{

    private GoogleMap mMap;
    CheckBox dengue, chikungunya, malaria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report);
        // initiate views
        dengue = (CheckBox) findViewById(R.id.checkBox);
        dengue.setOnClickListener(this);
        chikungunya = (CheckBox) findViewById(R.id.checkBox2);
        chikungunya.setOnClickListener(this);
        malaria = (CheckBox) findViewById(R.id.checkBox3);
        malaria.setOnClickListener(this);
        // php = (CheckBox) findViewById(R.id.phpCheckBox);
        // php.setOnClickListener(this);
        // unity3D = (CheckBox) findViewById(R.id.unityCheckBox);
        //unity3D.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.checkBox:
                if (dengue.isChecked()) {
                    Intent a = new Intent(this, MapsActivity.class);
                    startActivity(a);
                }
                //
                // Toast.makeText(getApplicationContext(), "Dengue", Toast.LENGTH_LONG).show();
                break;
            case R.id.checkBox2:
                if (chikungunya.isChecked())
                    Toast.makeText(getApplicationContext(), "Chikungunya", Toast.LENGTH_LONG).show();
                break;
            case R.id.checkBox3:
                if (malaria.isChecked())
                    Toast.makeText(getApplicationContext(), "Malaria", Toast.LENGTH_LONG).show();
                break;
            /*case R.id.pythonCheckBox:
                if (python.isChecked())
                    Toast.makeText(getApplicationContext(), "Python", Toast.LENGTH_LONG).show();
                break;
            case R.id.unityCheckBox:
                if (unity3D.isChecked())
                    Toast.makeText(getApplicationContext(), "Unity 3D", Toast.LENGTH_LONG).show();
                break;
               */
        }
    }
}

