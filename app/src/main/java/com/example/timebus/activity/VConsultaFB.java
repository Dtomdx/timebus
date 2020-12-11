package com.example.timebus.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.timebus.R;

public class VConsultaFB extends AppCompatActivity {
    private TextView get_response_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_v_consulta_f_b);

        Button get_request_button=findViewById(R.id.get_data);
        get_response_text=findViewById(R.id.get_ruta);

        get_request_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendGetRequest();
            }
        });

    }

    private void sendGetRequest() {

        RequestQueue queue= Volley.newRequestQueue(VConsultaFB.this);
        String url= "http://192.168.1.6/ejemploBDRemota/prueba7.php";

        StringRequest stringRequest =new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                get_response_text.setText( ""+response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                get_response_text.setText("Error");

            }
        });
        queue.add(stringRequest);
    }
}