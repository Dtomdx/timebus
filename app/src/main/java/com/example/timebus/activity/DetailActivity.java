package com.example.timebus.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.timebus.R;
import com.example.timebus.model.ItemList;
import com.example.timebus.model.ItemList6;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DetailActivity extends AppCompatActivity {
    DatabaseReference mRootReferences;
    TextView hora;
    private EditText tvPrecio;
    private EditText tvAsiento;
    private TextView tvCiudad;
    private TextView tvEmpresa;

    private ItemList6 itemDetail;


    Bundle datos;

    private Button btnActualizar;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        datos = getIntent().getExtras();
        String datosobtenidos = datos.getString("pasardatosempresa");
        tvEmpresa = (TextView) findViewById(R.id.tvEmpresa);
        tvEmpresa.setText(datosobtenidos);

        mRootReferences= FirebaseDatabase.getInstance().getReference();

        setTitle(getClass().getSimpleName());
        initViews();
        initValues();
    }
    private void initViews() {
        tvPrecio= findViewById(R.id.tvPrecio);
        tvAsiento = findViewById(R.id.tvAsiento);
        tvCiudad = findViewById(R.id.tvCiudad);
//        fechaCom =(TextView)findViewById(R.id.fecha_com);
        hora=(TextView)findViewById(R.id.horacio);
        final Date date= new Date();
        btnActualizar = findViewById(R.id.btnActualizar);

        //btnActualizar.setOnClickListener(new View.OnClickListener() {
           // @Override
           // public void onClick(View v) {
             // ejecutarServicio("http://192.168.1.4/ejemploBDRemota/insert.php");

               /* SimpleDateFormat fechaC = new SimpleDateFormat("d, MMMM 'del',yyyy");
                String sFecha=fechaC.format(date);
                fechaCom.setText(sFecha);

                final Date date= new Date();
                SimpleDateFormat h=new SimpleDateFormat("h:mm a");
                String sHora= h.format(date);
                hora.setText(sHora);*/
            }
       // });
   // }
    public void Enviar(View view){
        ejecutarServicio("http://192.168.1.6/ejemploBDRemota/insert.php");
        final Date date= new Date();
        SimpleDateFormat h=new SimpleDateFormat("h:mm a");
        String sHora= h.format(date);
        hora.setText(sHora);

        String horas= hora.getText().toString();
        Map<String,Object> subirDatos=new HashMap<>();
        subirDatos.put("hora",horas);

        mRootReferences.child("usuario").updateChildren(subirDatos);

        /*Intent i =new Intent(DetailActivity.this,VSeleccionEmpresa.class);
        Bundle miBundle= new Bundle();
        miBundle.putString("datos_fecha",hora.getText().toString());
        i.putExtras(miBundle);*/
        //i.putExtra("datos_fecha",
        // hora.getText().toString());
       /* SharedPreferences preferencias=getSharedPreferences("hora", Context.MODE_PRIVATE);
        SharedPreferences.Editor Objeditor=preferencias.edit();
        Objeditor.putString("horact",hora.getText().toString());
        Objeditor.commit();*/
        }
      /*  public void Guardar(View view){
            SharedPreferences preferencias=getSharedPreferences("hora", Context.MODE_PRIVATE);
            SharedPreferences.Editor Objeditor=preferencias.edit();
            Objeditor.putString("horact",hora.getText().toString());

        }*/
    private void initValues() {
        itemDetail = (ItemList6) getIntent().getExtras().getSerializable("itemDetail");


        tvPrecio.setText(itemDetail.getPreCiu());
        tvAsiento.setText(itemDetail.getAsientoDispBus());
        tvCiudad.setText(itemDetail.getCiuDest());

    }

    private void ejecutarServicio(String URL) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Operacion exitosa", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<>();
                parametros.put("nomEmpBus",tvEmpresa.getText().toString());
                parametros.put("ciuDest",tvCiudad.getText().toString());
                parametros.put("preCiu",tvPrecio.getText().toString());
                parametros.put("asientoDispBus",tvAsiento.getText().toString());
                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void onClick2(View v) {
        if(tvPrecio.length() != 3) { // revisar si es 3 digitos
            Toast.makeText(this, "Solo se permite 3 dígitos", Toast.LENGTH_SHORT).show();
        }
    }
}
