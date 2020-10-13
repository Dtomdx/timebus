package com.example.timebus;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SpinnerInterface {

    String JSONURL = "http://192.168.1.3/ejemploBDRemota/";

    @GET("nuevo1.php")
    Call<String> getJSONString();


}
