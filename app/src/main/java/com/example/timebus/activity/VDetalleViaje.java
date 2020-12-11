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
import com.example.timebus.adaptador.RecyclerAdapter;
import com.example.timebus.adaptador.RecyclerAdapterDetViaje;
import com.example.timebus.model.ItemList3;
import com.example.timebus.retrofit_data.RetrofitClient3;
import com.example.timebus.retrofit_data.RetrofitDetalleViaje;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VDetalleViaje extends AppCompatActivity implements RecyclerAdapterDetViaje.RecyclerItemClick, SearchView.OnQueryTextListener{

    private RecyclerView rvLista;
    private SearchView svSearch;
    private RecyclerAdapterDetViaje adapter;
    private List<ItemList3> items;

    private RetrofitDetalleViaje retrofitDetalleViaje;

    Bundle datos;
    private TextView tvTerminal;
    private TextView tvCiudad;
    private TextView tvEmpresa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_v_detalle_viaje);

        datos = getIntent().getExtras();
        String datosrecibidos_terminal = datos.getString("valor_terminaldetviaje");
        tvTerminal = (TextView) findViewById(R.id.tvTerminal);
        tvTerminal.setText(datosrecibidos_terminal);

        String datosrecibidos_ciudad = datos.getString("valor_ciudaddetviaje");
        tvCiudad = (TextView) findViewById(R.id.tvCiudad);
        tvCiudad.setText(datosrecibidos_ciudad);

        String datosrecibidos_empresa = datos.getString("valor_nomEmpresaBus");
        tvEmpresa = (TextView) findViewById(R.id.tvEmpresa);
        tvEmpresa.setText(datosrecibidos_empresa);

        initViews();
        initValues(datosrecibidos_terminal,datosrecibidos_ciudad, datosrecibidos_empresa);
        initListener();
    }
    private void initViews(){
        rvLista = findViewById(R.id.rvLista);
        svSearch = findViewById(R.id.svSearch);
    }

    private void initValues(String v_datosrecibidos_terminal,String v_datosrecibidos_ciudad, String v_datosrecibidos_empresa) {
        retrofitDetalleViaje = RetrofitClient3.getApiService3();

        LinearLayoutManager manager = new LinearLayoutManager(this);
        rvLista.setLayoutManager(manager);

        if (v_datosrecibidos_terminal.equals("Terminal de Huancayo")){
            String id_terminal = "T1";
            getItemsSQL_T1(id_terminal,v_datosrecibidos_ciudad, v_datosrecibidos_empresa);
        }

        if (v_datosrecibidos_terminal.equals("Terminal los Andes")){
            String id_terminal = "T2";
            getItemsSQL_T2(id_terminal,v_datosrecibidos_ciudad, v_datosrecibidos_empresa);
        }



        //getItemsSQL();
    }

    private void initListener() {
        svSearch.setOnQueryTextListener(this);
    }



    private void getItemsSQL_T1(String id_terminal,String ciuDest, String empresa) {
        retrofitDetalleViaje.consultaDetalleViaje(id_terminal,ciuDest, empresa).enqueue(new Callback<List<ItemList3>>() {
            @Override
            public void onResponse(Call<List<ItemList3>> call, Response<List<ItemList3>> response) {
                items = response.body();
                adapter = new RecyclerAdapterDetViaje(items, VDetalleViaje.this);
                rvLista.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<ItemList3>> call, Throwable t) {
                Toast.makeText(VDetalleViaje.this, "Error: "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getItemsSQL_T2(String id_terminal,String ciuDest, String empresa) {
        retrofitDetalleViaje.consultaDetalleViaje(id_terminal,ciuDest, empresa).enqueue(new Callback<List<ItemList3>>() {
            @Override
            public void onResponse(Call<List<ItemList3>> call, Response<List<ItemList3>> response) {
                items = response.body();
                adapter = new RecyclerAdapterDetViaje(items, VDetalleViaje.this);
                rvLista.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<ItemList3>> call, Throwable t) {
                Toast.makeText(VDetalleViaje.this, "Error: "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }



    @Override
    public void itemClick(ItemList3 item) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("itemDetail", item);
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