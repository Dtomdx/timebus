package com.example.timebus.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.timebus.R;

import java.util.HashMap;
import java.util.Map;

public class VLogin extends AppCompatActivity {
    String usuario,password;
    EditText edtUsuario,edtPasword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vlogin);

        edtUsuario=findViewById(R.id.edtUsuario);
        edtPasword=findViewById(R.id.edtPassword);
        btnLogin=findViewById(R.id.btnLogin);
        recuperarPreferencias();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario=edtUsuario.getText().toString();
                password=edtPasword.getText().toString();

                if(!usuario.isEmpty()&&!password.isEmpty()){
                validarUsuario("http://192.168.1.6/ejemploBDRemota/validar_usuario.php");
            }else{
                     Toast.makeText(VLogin.this,"No se permiten campos vacios",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private void validarUsuario(String URL)
    {
        String valor_usuario = edtUsuario.getText().toString();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(!response.isEmpty()) {
                    guardarPreferencia();
                    Intent intent = new Intent(getApplicationContext(),VUsuarioColaborador.class);
                    intent.putExtra("pasardatos",valor_usuario);
                    startActivity(intent);
                }else {
                    Toast.makeText(VLogin.this,"Usuario o contraseña incorrecta",Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(VLogin.this,error.toString(),Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String,String>();
                parametros.put("dni",usuario);
                parametros.put("password",password);
                return parametros;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void  guardarPreferencia(){
        SharedPreferences preferences = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("usuario", usuario);
        editor.putString("password",password);
        editor.putBoolean("sesion", true);
        editor.commit();
    }
    private void recuperarPreferencias()
    {
        SharedPreferences preferences=getSharedPreferences("preferencias",Context.MODE_PRIVATE);
        edtUsuario.setText(preferences.getString("usuario",""));
        edtPasword.setText(preferences.getString("password",""));
    }




}
