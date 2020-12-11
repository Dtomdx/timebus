package com.example.timebus.retrofit_data;


import com.example.timebus.model.ItemList5;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitEmpColaborador {

    //@GET("getEmpColaborador0.php")
    //Call<List<ItemList5>> getItemsDB5();

    @GET("getEmpColaborador.php")
    Call<List<ItemList5>> getItemsDB5(@Query("dni") String dni);
}
