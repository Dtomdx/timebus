package com.example.timebus.retrofit_data;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SpinnerInterface {

    String JSONURL = "http://192.168.1.6/ejemploBDRemota/";

    @GET("getCiuViajero.php")
    Call<String> getJSONString();


}
