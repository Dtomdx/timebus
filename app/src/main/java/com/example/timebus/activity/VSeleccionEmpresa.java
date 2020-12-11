package com.example.timebus.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.timebus.R;
import com.example.timebus.adaptador.RecyclerAdapterEmpresas;
import com.example.timebus.model.ItemList2;
import com.example.timebus.retrofit_data.RetrofitClient2;
import com.example.timebus.retrofit_data.RetrofitEmpresa;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class VSeleccionEmpresa extends AppCompatActivity implements RecyclerAdapterEmpresas.RecyclerItemClick, SearchView.OnQueryTextListener {
    TextView msjhora;
    private TextView txthoraM2;
    private RecyclerView rvLista;
    private SearchView svSearch;
    private RecyclerAdapterEmpresas adapter;
    private List<ItemList2> items;
    private RetrofitEmpresa retrofitEmpresa;
    private DatabaseReference mDatabase;
    Bundle datos;
    private TextView mostrarDatos;
    private TextView tvTerminal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_v_seleccion_empresa);

        msjhora=(TextView)findViewById(R.id.txthoraM2);
        mDatabase= FirebaseDatabase.getInstance().getReference();

        mDatabase.child("usuario").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if(datasnapshot.exists()){

                    String hora =datasnapshot.child("hora").getValue().toString();
                    msjhora.setText("Viajes actualizados ultima ves a las: " +hora);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
  /*      Bundle miBundle=this.getIntent().getExtras();
        if(miBundle != null){
            String hora=miBundle.getString("datos_fecha");
            msjhora.setText("Ultima hora de Actualizacion: " + hora);

         }*/
       /* SharedPreferences guardar=getSharedPreferences("hora", Context.MODE_PRIVATE);
        msjhora.setText(guardar.getString("horact",""));*/
      /* txthoraM2=(TextView)findViewById(R.id.txthoraM2);

        String dato = getIntent().getStringExtra("datos_fecha");
        txthoraM2.setText("Ultima hora de Actualizacion"+ dato);*/

        datos = getIntent().getExtras();
        String datosobtenidos = datos.getString("pasardatosciudad");
        mostrarDatos = (TextView) findViewById(R.id.tvEmpresa);
        mostrarDatos.setText(datosobtenidos);
        String datosobtenidos_terminal = datos.getString("pasardatosterminal");
        tvTerminal = (TextView)findViewById(R.id.tvTerminal);
        tvTerminal.setText(datosobtenidos_terminal);
       /* String horaObtenida=datos.getString("datos_fecha");
        txthoraM2=(TextView)findViewById(R.id.txthoraM2);
        txthoraM2.setText(horaObtenida);*/

        initViews();
        initValues(datosobtenidos_terminal);
        initListener();
    }

    private void initViews() {
        rvLista = findViewById(R.id.rvLista);
        svSearch = findViewById(R.id.svSearch);
    }

    private void initValues(String valor_recibido_datosobtenidos1) {
        retrofitEmpresa = RetrofitClient2.getApiService2();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rvLista.setLayoutManager(manager);

        //getItemsSQL();
        if (valor_recibido_datosobtenidos1.equals("Terminal los Andes")){
            getItemsSQL_T2();
        }
        else{
            if(valor_recibido_datosobtenidos1.equals("Terminal de Huancayo")){
                getItemsSQL_T1();
            }
        }
    }

    private void initListener() {
        svSearch.setOnQueryTextListener(this);
    }


    private void getItemsSQL_T2() {
        String valor1_String = mostrarDatos.getText().toString();

        List<String> valores_ciuDest1 = new ArrayList<String>();
        valores_ciuDest1.add("Lima");
        valores_ciuDest1.add("Jauja");
        valores_ciuDest1.add("La Oroya");
        valores_ciuDest1.add("Oxapampa");
        valores_ciuDest1.add("La Merced");
        valores_ciuDest1.add("Satipo");


        List<String> valores_ciuDest2 = new ArrayList<String>();
        valores_ciuDest2.add("La Oroya");
        valores_ciuDest2.add("Cerro de Pasco");
        valores_ciuDest2.add("Huancayo");
        valores_ciuDest2.add("Satipo");
        valores_ciuDest2.add("Chanchamayo");
        valores_ciuDest2.add("Huanuco");

        List<String> valores_ciuDest3 = new ArrayList<String>();
        valores_ciuDest3.add("Jauja");
        valores_ciuDest3.add("Huancayo");
        valores_ciuDest3.add("Huancavelica");
        valores_ciuDest3.add("Pangoa");
        valores_ciuDest3.add("Cerro de Pasco");
        valores_ciuDest3.add("Pucallpa");




        if(valores_ciuDest1.contains(valor1_String) && valores_ciuDest2.contains(valor1_String)){
            retrofitEmpresa.consultaEmpresas1_2_T1(valor1_String).enqueue(new Callback<List<ItemList2>>(){

                @Override
                public void onResponse(Call<List<ItemList2>> call, Response<List<ItemList2>> response) {
                    items = response.body();
                    adapter = new RecyclerAdapterEmpresas(items, VSeleccionEmpresa.this::itemClick);
                    rvLista.setAdapter(adapter);
                }

                @Override
                public void onFailure(Call<List<ItemList2>> call, Throwable t) {
                    Toast.makeText(VSeleccionEmpresa.this, "Error: "+t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }

        else{
            if(valores_ciuDest1.contains(valor1_String) && valores_ciuDest3.contains(valor1_String)){
                retrofitEmpresa.consultaEmpresas1_3_T1(valor1_String).enqueue(new Callback<List<ItemList2>>(){

                    @Override
                    public void onResponse(Call<List<ItemList2>> call, Response<List<ItemList2>> response) {
                        items = response.body();
                        adapter = new RecyclerAdapterEmpresas(items, VSeleccionEmpresa.this::itemClick);
                        rvLista.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(Call<List<ItemList2>> call, Throwable t) {
                        Toast.makeText(VSeleccionEmpresa.this, "Error: "+t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
            else{
                if(valores_ciuDest2.contains(valor1_String) && valores_ciuDest3.contains(valor1_String)){
                    retrofitEmpresa.consultaEmpresas2_3_T1(valor1_String).enqueue(new Callback<List<ItemList2>>(){

                        @Override
                        public void onResponse(Call<List<ItemList2>> call, Response<List<ItemList2>> response) {
                            items = response.body();
                            adapter = new RecyclerAdapterEmpresas(items, VSeleccionEmpresa.this::itemClick);
                            rvLista.setAdapter(adapter);
                        }

                        @Override
                        public void onFailure(Call<List<ItemList2>> call, Throwable t) {
                            Toast.makeText(VSeleccionEmpresa.this, "Error: "+t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
                else{

                    if(valores_ciuDest1.contains(valor1_String)){
                        retrofitEmpresa.consultaEmpresas(valor1_String).enqueue(new Callback<List<ItemList2>>(){

                            @Override
                            public void onResponse(Call<List<ItemList2>> call, Response<List<ItemList2>> response) {
                                items = response.body();
                                adapter = new RecyclerAdapterEmpresas(items, VSeleccionEmpresa.this::itemClick);
                                rvLista.setAdapter(adapter);
                            }

                            @Override
                            public void onFailure(Call<List<ItemList2>> call, Throwable t) {
                                Toast.makeText(VSeleccionEmpresa.this, "Error: "+t.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                    else{
                        if(valores_ciuDest2.contains(valor1_String)){
                            retrofitEmpresa.consultaEmpresas1(valor1_String).enqueue(new Callback<List<ItemList2>>(){

                                @Override
                                public void onResponse(Call<List<ItemList2>> call, Response<List<ItemList2>> response) {
                                    items = response.body();
                                    adapter = new RecyclerAdapterEmpresas(items, VSeleccionEmpresa.this::itemClick);
                                    rvLista.setAdapter(adapter);
                                }

                                @Override
                                public void onFailure(Call<List<ItemList2>> call, Throwable t) {
                                    Toast.makeText(VSeleccionEmpresa.this, "Error: "+t.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            });
                        }

                        else{
                            if(valores_ciuDest3.contains(valor1_String)){
                                retrofitEmpresa.consultaEmpresas2(valor1_String).enqueue(new Callback<List<ItemList2>>(){

                                    @Override
                                    public void onResponse(Call<List<ItemList2>> call, Response<List<ItemList2>> response) {
                                        items = response.body();
                                        adapter = new RecyclerAdapterEmpresas(items, VSeleccionEmpresa.this::itemClick);
                                        rvLista.setAdapter(adapter);
                                    }

                                    @Override
                                    public void onFailure(Call<List<ItemList2>> call, Throwable t) {
                                        Toast.makeText(VSeleccionEmpresa.this, "Error: "+t.getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                });
                            }
                        }
                    }
                }


            }

        }
    }


    private void getItemsSQL_T1() {
        String valor1_String = mostrarDatos.getText().toString();

        List<String> valores_ciuDest1 = new ArrayList<String>();
        valores_ciuDest1.add("Lima");
        valores_ciuDest1.add("Jauja");
        valores_ciuDest1.add("La Oroya");
        valores_ciuDest1.add("Oxapampa");
        valores_ciuDest1.add("La Merced");
        valores_ciuDest1.add("Satipo");


        List<String> valores_ciuDest2 = new ArrayList<String>();
        valores_ciuDest2.add("La Oroya");
        valores_ciuDest2.add("Cerro de Pasco");
        valores_ciuDest2.add("Huancayo");
        valores_ciuDest2.add("Satipo");
        valores_ciuDest2.add("Chanchamayo");
        valores_ciuDest2.add("Huanuco");

        List<String> valores_ciuDest3 = new ArrayList<String>();
        valores_ciuDest3.add("Jauja");
        valores_ciuDest3.add("Huancayo");
        valores_ciuDest3.add("Huancavelica");
        valores_ciuDest3.add("Pangoa");
        valores_ciuDest3.add("Cerro de Pasco");
        valores_ciuDest3.add("Pucallpa");




        if(valores_ciuDest1.contains(valor1_String) && valores_ciuDest2.contains(valor1_String)){
            retrofitEmpresa.consultaEmpresas1_2_T2(valor1_String).enqueue(new Callback<List<ItemList2>>(){

                @Override
                public void onResponse(Call<List<ItemList2>> call, Response<List<ItemList2>> response) {
                    items = response.body();
                    adapter = new RecyclerAdapterEmpresas(items, VSeleccionEmpresa.this::itemClick);
                    rvLista.setAdapter(adapter);
                }

                @Override
                public void onFailure(Call<List<ItemList2>> call, Throwable t) {
                    Toast.makeText(VSeleccionEmpresa.this, "Error: "+t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }

        else{
            if(valores_ciuDest1.contains(valor1_String) && valores_ciuDest3.contains(valor1_String)){
                retrofitEmpresa.consultaEmpresas1_3_T2(valor1_String).enqueue(new Callback<List<ItemList2>>(){

                    @Override
                    public void onResponse(Call<List<ItemList2>> call, Response<List<ItemList2>> response) {
                        items = response.body();
                        adapter = new RecyclerAdapterEmpresas(items, VSeleccionEmpresa.this::itemClick);
                        rvLista.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(Call<List<ItemList2>> call, Throwable t) {
                        Toast.makeText(VSeleccionEmpresa.this, "Error: "+t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
            else{
                if(valores_ciuDest2.contains(valor1_String) && valores_ciuDest3.contains(valor1_String)){
                    retrofitEmpresa.consultaEmpresas2_3_T2(valor1_String).enqueue(new Callback<List<ItemList2>>(){

                        @Override
                        public void onResponse(Call<List<ItemList2>> call, Response<List<ItemList2>> response) {
                            items = response.body();
                            adapter = new RecyclerAdapterEmpresas(items, VSeleccionEmpresa.this::itemClick);
                            rvLista.setAdapter(adapter);
                        }

                        @Override
                        public void onFailure(Call<List<ItemList2>> call, Throwable t) {
                            Toast.makeText(VSeleccionEmpresa.this, "Error: "+t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
                else{

                    if(valores_ciuDest1.contains(valor1_String)){
                        retrofitEmpresa.consultaEmpresas(valor1_String).enqueue(new Callback<List<ItemList2>>(){

                            @Override
                            public void onResponse(Call<List<ItemList2>> call, Response<List<ItemList2>> response) {
                                items = response.body();
                                adapter = new RecyclerAdapterEmpresas(items, VSeleccionEmpresa.this::itemClick);
                                rvLista.setAdapter(adapter);
                            }

                            @Override
                            public void onFailure(Call<List<ItemList2>> call, Throwable t) {
                                Toast.makeText(VSeleccionEmpresa.this, "Error: "+t.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                    else{
                        if(valores_ciuDest2.contains(valor1_String)){
                            retrofitEmpresa.consultaEmpresas1(valor1_String).enqueue(new Callback<List<ItemList2>>(){

                                @Override
                                public void onResponse(Call<List<ItemList2>> call, Response<List<ItemList2>> response) {
                                    items = response.body();
                                    adapter = new RecyclerAdapterEmpresas(items, VSeleccionEmpresa.this::itemClick);
                                    rvLista.setAdapter(adapter);
                                }

                                @Override
                                public void onFailure(Call<List<ItemList2>> call, Throwable t) {
                                    Toast.makeText(VSeleccionEmpresa.this, "Error: "+t.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            });
                        }

                        else{
                            if(valores_ciuDest3.contains(valor1_String)){
                                retrofitEmpresa.consultaEmpresas2(valor1_String).enqueue(new Callback<List<ItemList2>>(){

                                    @Override
                                    public void onResponse(Call<List<ItemList2>> call, Response<List<ItemList2>> response) {
                                        items = response.body();
                                        adapter = new RecyclerAdapterEmpresas(items, VSeleccionEmpresa.this::itemClick);
                                        rvLista.setAdapter(adapter);
                                    }

                                    @Override
                                    public void onFailure(Call<List<ItemList2>> call, Throwable t) {
                                        Toast.makeText(VSeleccionEmpresa.this, "Error: "+t.getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                });
                            }
                        }
                    }
                }


            }

        }
    }


        @Override
        public void itemClick (ItemList2 item, String v_nomEmpresaBus ){
            String valor_terminaldetviaje = tvTerminal.getText().toString();
            String valor_ciudaddetviaje = mostrarDatos.getText().toString();
            Intent siguiente = new Intent(this, VDetalleViaje.class);
            siguiente.putExtra("valor_terminaldetviaje",valor_terminaldetviaje);
            siguiente.putExtra("valor_ciudaddetviaje", valor_ciudaddetviaje);
            siguiente.putExtra("valor_nomEmpresaBus", v_nomEmpresaBus);
            startActivity(siguiente);
        }

        @Override
        public boolean onQueryTextSubmit (String query){
            return false;
        }

        @Override
        public boolean onQueryTextChange (String newText){
            adapter.filter(newText);
            return false;
        }


    public void onClick(View view) {
        finish();
        startActivity(getIntent());
    }
    }



