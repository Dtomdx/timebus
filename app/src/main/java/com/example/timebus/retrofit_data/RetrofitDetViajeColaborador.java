package com.example.timebus.retrofit_data;

import com.example.timebus.model.ItemList6;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitDetViajeColaborador {

    @GET("getDetViajeColaborador.php")
    Call<List<ItemList6>> getItemsDB6(@Query("nomEmpBus") String nomEmpBus);


}
