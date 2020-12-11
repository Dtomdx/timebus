package com.example.timebus.retrofit_data;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient3 {

    private static Retrofit retrofit = null;
    public static final String URL_BASE = "http://192.168.1.6/ejemploBDRemota/";

    public static RetrofitDetalleViaje getApiService3() {
        if (retrofit == null) {
            retrofit = new  Retrofit.Builder()
                    .baseUrl(URL_BASE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(RetrofitDetalleViaje.class);
    }
}
