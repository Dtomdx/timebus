package com.example.timebus.retrofit_data;

import com.example.timebus.model.ItemList4;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitTermColaborador {

    //@GET("getTermColaborador2.php")
    //Call<List<ItemList4>> getItemsDB4();

    @GET("getTermColaborador.php")
    Call<List<ItemList4>> getItemsDB4(@Query("dni") String dni);


}
