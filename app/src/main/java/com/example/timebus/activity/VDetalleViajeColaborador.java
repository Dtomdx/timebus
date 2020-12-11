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

import com.example.timebus.adaptador.RecyclerAdapterDetViajeColaborador;
import com.example.timebus.model.ItemList6;
import com.example.timebus.retrofit_data.RetrofitClient6;
import com.example.timebus.retrofit_data.RetrofitDetViajeColaborador;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class VDetalleViajeColaborador extends AppCompatActivity  implements RecyclerAdapterDetViajeColaborador.RecyclerItemClick, SearchView.OnQueryTextListener {

    private RecyclerView rvLista;
    private SearchView svSearch;
    private RecyclerAdapterDetViajeColaborador adapter;
    private List<ItemList6> items;

    private RetrofitDetViajeColaborador retrofitDetalleViajeColaborador;
    Bundle datos;
    private TextView tvEmpresa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_v_detalle_viaje_colaborador);

        datos = getIntent().getExtras();
        String datosobtenidos1 = datos.getString("pasardatos4");

        tvEmpresa = (TextView) findViewById(R.id.tvEmpresa);
        tvEmpresa.setText(datosobtenidos1);


        initViews();
        initValues(datosobtenidos1);
        initListener();
    }
    private void initViews(){
        rvLista = findViewById(R.id.rvLista);
        svSearch = findViewById(R.id.svSearch);
    }

    private void initValues(String valor_recibido_datosobtenidos1) {
        retrofitDetalleViajeColaborador = RetrofitClient6.getApiService6();

        LinearLayoutManager manager = new LinearLayoutManager(this);
        rvLista.setLayoutManager(manager);

        String valor_copia=valor_recibido_datosobtenidos1;
        getItemsSQL_T1(valor_copia);
    }

    private void initListener() {
        svSearch.setOnQueryTextListener(this);
    }



    private void getItemsSQL_T1(String valor_recibir) {
        retrofitDetalleViajeColaborador.getItemsDB6(valor_recibir).enqueue(new Callback<List<ItemList6>>() {
            @Override
            public void onResponse(Call<List<ItemList6>> call, Response<List<ItemList6>> response) {
                items = response.body();
                adapter = new RecyclerAdapterDetViajeColaborador(items, VDetalleViajeColaborador.this);
                rvLista.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<ItemList6>> call, Throwable t) {
                Toast.makeText(VDetalleViajeColaborador.this, "Error: "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    public void itemClick(ItemList6 item) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("itemDetail", item);
        intent.putExtra("pasardatosempresa",tvEmpresa.getText().toString());
        startActivity(intent);
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




