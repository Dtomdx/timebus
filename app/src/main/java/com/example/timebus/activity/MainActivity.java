package com.example.timebus.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.timebus.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view)
    {
        Intent miIntent = null;
        switch (view.getId())
        {
            case R.id.btnInicio:
                miIntent = new Intent(MainActivity.this, VSeleccionOriDest.class);
                break;
        }
        if (miIntent != null)
        {
            startActivity(miIntent);
        }
    }

    public void onClick2(View view) {
        Intent miIntent = null;
        switch (view.getId())
        {
            case R.id.btnColaborador:
                miIntent = new Intent(MainActivity.this, VLogin.class);
                break;
        }
        if (miIntent != null)
        {
            startActivity(miIntent);
        }
    }


}
