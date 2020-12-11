package com.example.timebus.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.timebus.R;
import com.example.timebus.adaptador.RecyclerAdapterEmpColaborador;
import com.example.timebus.model.ItemList5;
import com.example.timebus.retrofit_data.RetrofitClient5;
import com.example.timebus.retrofit_data.RetrofitEmpColaborador;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VBuscarEmpColaborador extends AppCompatActivity implements RecyclerAdapterEmpColaborador.RecyclerItemClick, SearchView.OnQueryTextListener{


    private RecyclerView rvLista;
    private SearchView svSearch;
    private RecyclerAdapterEmpColaborador adapter;
    private List<ItemList5> items;
    private RetrofitEmpColaborador retrofitEmpColaborador;

    Bundle datos;
    private TextView mostrarDatos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vbuscar_emp_colaborador);

        datos = getIntent().getExtras();
        String datosobtenidos = datos.getString("pasardatos3");
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
        retrofitEmpColaborador = RetrofitClient5.getApiService5();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rvLista.setLayoutManager(manager);

        getItemsSQL();
    }

    private void initListener() {
        svSearch.setOnQueryTextListener(this);
    }


    private void getItemsSQL() {
        String valor1_String = mostrarDatos.getText().toString();

        List<String> valores_dni_1 = new ArrayList<String>();
        valores_dni_1.add("73545825");
        valores_dni_1.add("75271531");
        valores_dni_1.add("70776127");


        if (valores_dni_1.contains(valor1_String)) {
            retrofitEmpColaborador.getItemsDB5(valor1_String).enqueue(new Callback<List<ItemList5>>() {

                @Override
                public void onResponse(Call<List<ItemList5>> call, Response<List<ItemList5>> response) {
                    items = response.body();
                    adapter = new RecyclerAdapterEmpColaborador(items, VBuscarEmpColaborador.this::itemClick);
                    rvLista.setAdapter(adapter);
                }

                @Override
                public void onFailure(Call<List<ItemList5>> call, Throwable t) {
                    Toast.makeText(VBuscarEmpColaborador.this, "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }



    }
    @Override
    public void itemClick(ItemList5 item, String valor_recibido) {

        //String valor_usuario = mostrarDatos.getText().toString();

        Intent siguiente =new Intent(this,VDetalleViajeColaborador.class) ;
        siguiente.putExtra("pasardatos4",valor_recibido);
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


}
