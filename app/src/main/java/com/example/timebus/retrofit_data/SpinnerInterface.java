package com.example.timebus.retrofit_data;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SpinnerInterface {

    String JSONURL = "http://192.168.1.7/users/";

    @GET("nuevo1.php")
    Call<String> getJSONString();


}
