package com.example.timebus.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.timebus.R;
import com.example.timebus.adaptador.RecyclerAdapter;
import com.example.timebus.model.ItemList;
import com.example.timebus.retrofit_data.RetrofitApiService;
import com.example.timebus.retrofit_data.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VSeleccionTerminal extends AppCompatActivity implements RecyclerAdapter.RecyclerItemClick, SearchView.OnQueryTextListener {

    private RecyclerView rvLista;
    private SearchView svSearch;
    private RecyclerAdapter adapter;
    private List<ItemList> items;

    Bundle datos;
    //private Bundle datos = getIntent().getExtras();
    //private String datosobtenidos = datos.getString("pasardatos");
    private TextView mostrarDatos;

    private RetrofitApiService retrofitApiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vseleccion_terminal);

        datos = getIntent().getExtras();
        String datosobtenidos = datos.getString("pasardatos");
        mostrarDatos = (TextView) findViewById(R.id.tvEmpresa);
        mostrarDatos.setText(datosobtenidos);

        initViews();
        initValues();
        initListener();
    }

    private void initViews(){
        rvLista = findViewById(R.id.rvLista);
        svSearch = findViewById(R.id.svSearch);
    }

    private void initValues() {
        retrofitApiService = RetrofitClient.getApiService();

        LinearLayoutManager manager = new LinearLayoutManager(this);
        rvLista.setLayoutManager(manager);

        getItemsSQL();
    }

    private void initListener() {
        svSearch.setOnQueryTextListener(this);
    }


    private void getItemsSQL() {
        String valor1_String = mostrarDatos.getText().toString();

        /*valores_ciudad_1 = a,b,b*/
        List<String> valores_ciuDest1 = new ArrayList<String>();
        valores_ciuDest1.add("Lima");
        valores_ciuDest1.add("Oxapampa");
        valores_ciuDest1.add("La Merced");
        valores_ciuDest1.add("Satipo");

        List<String> valores_ciuDest2 = new ArrayList<String>();
        valores_ciuDest2.add("La Oroya");
        valores_ciuDest2.add("Cerro de Pasco");
        valores_ciuDest2.add("Ayacucho");
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
            retrofitApiService.getItemsDB1_2(valor1_String).enqueue(new Callback<List<ItemList>>() {
                @Override
                public void onResponse(Call<List<ItemList>> call, Response<List<ItemList>> response) {
                    items = response.body();
                    adapter = new RecyclerAdapter(items, VSeleccionTerminal.this::itemClick);
                    rvLista.setAdapter(adapter);
                }

                @Override
                public void onFailure(Call<List<ItemList>> call, Throwable t) {
                    Toast.makeText(VSeleccionTerminal.this, "Error: "+t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });

        }
        else{
            if(valores_ciuDest1.contains(valor1_String) && valores_ciuDest3.contains(valor1_String)){
                retrofitApiService.getItemsDB1_3(valor1_String).enqueue(new Callback<List<ItemList>>() {
                    @Override
                    public void onResponse(Call<List<ItemList>> call, Response<List<ItemList>> response) {
                        items = response.body();
                        adapter = new RecyclerAdapter(items, VSeleccionTerminal.this::itemClick);
                        rvLista.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(Call<List<ItemList>> call, Throwable t) {
                        Toast.makeText(VSeleccionTerminal.this, "Error: "+t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
            else{
                if(valores_ciuDest2.contains(valor1_String) && valores_ciuDest3.contains(valor1_String)){
                    retrofitApiService.getItemsDB2_3(valor1_String).enqueue(new Callback<List<ItemList>>() {
                        @Override
                        public void onResponse(Call<List<ItemList>> call, Response<List<ItemList>> response) {
                            items = response.body();
                            adapter = new RecyclerAdapter(items, VSeleccionTerminal.this::itemClick);
                            rvLista.setAdapter(adapter);
                        }

                        @Override
                        public void onFailure(Call<List<ItemList>> call, Throwable t) {
                            Toast.makeText(VSeleccionTerminal.this, "Error: "+t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
                else{
                    if(valores_ciuDest1.contains(valor1_String)){
                        retrofitApiService.getItemsDB(valor1_String).enqueue(new Callback<List<ItemList>>() {
                            @Override
                            public void onResponse(Call<List<ItemList>> call, Response<List<ItemList>> response) {
                                items = response.body();
                                adapter = new RecyclerAdapter(items, VSeleccionTerminal.this::itemClick);
                                rvLista.setAdapter(adapter);
                            }

                            @Override
                            public void onFailure(Call<List<ItemList>> call, Throwable t) {
                                Toast.makeText(VSeleccionTerminal.this, "Error: "+t.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                    else{
                        if(valores_ciuDest2.contains(valor1_String)){
                            retrofitApiService.getItemsDB1(valor1_String).enqueue(new Callback<List<ItemList>>() {
                                @Override
                                public void onResponse(Call<List<ItemList>> call, Response<List<ItemList>> response) {
                                    items = response.body();
                                    adapter = new RecyclerAdapter(items, VSeleccionTerminal.this::itemClick);
                                    rvLista.setAdapter(adapter);
                                }

                                @Override
                                public void onFailure(Call<List<ItemList>> call, Throwable t) {
                                    Toast.makeText(VSeleccionTerminal.this, "Error: "+t.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                        else{
                            if(valores_ciuDest3.contains(valor1_String)){
                                retrofitApiService.getItemsDB2(valor1_String).enqueue(new Callback<List<ItemList>>() {
                                    @Override
                                    public void onResponse(Call<List<ItemList>> call, Response<List<ItemList>> response) {
                                        items = response.body();
                                        adapter = new RecyclerAdapter(items, VSeleccionTerminal.this::itemClick);
                                        rvLista.setAdapter(adapter);
                                    }

                                    @Override
                                    public void onFailure(Call<List<ItemList>> call, Throwable t) {
                                        Toast.makeText(VSeleccionTerminal.this, "Error: "+t.getMessage(), Toast.LENGTH_LONG).show();
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
    public void itemClick(ItemList item, String valor_terminal) {
        String valor_ciudad = mostrarDatos.getText().toString();
        Intent siguiente =new Intent(this,VSeleccionEmpresa.class) ;
        siguiente.putExtra("pasardatosterminal",valor_terminal);
        siguiente.putExtra("pasardatosciudad",valor_ciudad);
        startActivity(siguiente);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.filter(newText);
        return false;
    }

    public void onClick(View view) {
        Intent miIntent = null;
        switch (view.getId())
        {
            case R.id.btnIR:
                miIntent = new Intent(VSeleccionTerminal.this, VUbicacionTerminal.class);
                break;
        }
        if (miIntent != null)
        {
            startActivity(miIntent);
        }
    }


}
