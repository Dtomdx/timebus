package com.example.timebus.retrofit_data;


import com.example.timebus.model.ItemList3;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitDetalleViaje {

    @GET("consultar_detalle_viaje.php")
    Call<List<ItemList3>> consultaDetalleViaje(@Query("idTerm") String idTerm,
                                               @Query("nomCiu") String nomCiu,
                                               @Query("nomEmpBus") String nomEmpBus);
}
