package com.example.timebus.retrofit_data;

import com.example.timebus.model.ItemList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitApiService {


    //@GET("getItemsDB.php")
    //Call<List<ItemList>> getItemsDB();

    @GET("getCiuDest1.php")
    Call<List<ItemList>> getItemsDB(@Query("ciuDest1") String ciuDest1);

    @GET("getCiuDest2.php")
    Call<List<ItemList>> getItemsDB1(@Query("ciuDest2") String ciuDest2);

    @GET("getCiuDest3.php")
    Call<List<ItemList>> getItemsDB2(@Query("ciuDest3") String ciuDest3);

    //comparaciones
    @GET("getCiuDest1_2.php")
    Call<List<ItemList>> getItemsDB1_2(@Query("ciuDest1") String ciuDest1);

    @GET("getCiuDest1_3php")
    Call<List<ItemList>> getItemsDB1_3(@Query("ciuDest1") String ciuDest1);

    @GET("getCiuDest2_3.php")
    Call<List<ItemList>> getItemsDB2_3(@Query("ciuDest2") String ciuDest1);



}
