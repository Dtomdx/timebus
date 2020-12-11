package com.example.timebus.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.timebus.R;

public class VUsuarioColaborador extends AppCompatActivity {
    Button btnCerrar;
    Bundle datos;
    private TextView tvDni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_v_usuario_colaborador);
        btnCerrar = findViewById(R.id.btnCerrar);
        datos = getIntent().getExtras();
        String datosobtenidos_dni=datos.getString("pasardatos");
        tvDni = (TextView) findViewById(R.id.tvDni);
        tvDni.setText(datosobtenidos_dni);
        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
                preferences.edit().clear().commit();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }
    public void onClick1(View view)
    {
        String valor=tvDni.getText().toString();
        Intent miIntent = null;
        switch (view.getId())
        {
            case R.id.btnLocalizarT:
                miIntent = new Intent(VUsuarioColaborador.this, VBuscarTermColaborador.class);
                miIntent.putExtra("pasardatos2",valor);
                break;
        }
        if (miIntent != null)
        {
            startActivity(miIntent);
        }
    }

}