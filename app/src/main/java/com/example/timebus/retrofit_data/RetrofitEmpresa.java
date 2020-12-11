package com.example.timebus.retrofit_data;

import com.example.timebus.model.ItemList2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitEmpresa {


    @GET("getEmpresasDB.php")
    Call<List<ItemList2>> consultaEmpresas();

    @GET("getEmpCiuDest1.php")
    Call<List<ItemList2>> consultaEmpresas(@Query("ciuDest1") String ciuDest1);

    @GET("getEmpCiuDest2.php")
    Call<List<ItemList2>> consultaEmpresas1(@Query("ciuDest2") String ciuDest2);

    @GET("getEmpCiuDest3.php")
    Call<List<ItemList2>> consultaEmpresas2(@Query("ciuDest3") String ciuDest3);

//comparaciones


    @GET("getEmpCiuDest1_2_T1.php")
    Call<List<ItemList2>> consultaEmpresas1_2_T2(@Query("ciuDest1") String ciuDest1);

    @GET("getEmpCiuDest1_3_T1.php")
    Call<List<ItemList2>> consultaEmpresas1_3_T2(@Query("ciuDest1") String ciuDest1);

    @GET("getEmpCiuDest2_3_T1.php")
    Call<List<ItemList2>> consultaEmpresas2_3_T2(@Query("ciuDest2") String ciuDest2);

//para la otra terminal
    @GET("getEmpCiuDest1_2_T2.php")
    Call<List<ItemList2>> consultaEmpresas1_2_T1(@Query("ciuDest1") String ciuDest1);

    @GET("getEmpCiuDest1_3_T2.php")
    Call<List<ItemList2>> consultaEmpresas1_3_T1(@Query("ciuDest1") String ciuDest1);

    @GET("getEmpCiuDest2_3_T2.php")
    Call<List<ItemList2>> consultaEmpresas2_3_T1(@Query("ciuDest2") String ciuDest2);


}
