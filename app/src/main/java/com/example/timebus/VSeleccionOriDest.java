package com.example.timebus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static android.R.layout.simple_spinner_item;

public class VSeleccionOriDest extends AppCompatActivity {

    private ArrayList<SpinnerModel> goodModelArrayList;
    private ArrayList<String> playerNames = new ArrayList<String>();
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vseleccion_ori_dest);

        spinner = findViewById(R.id.spCompany);
        fetchJSON();
    }

    private void fetchJSON(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SpinnerInterface.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        SpinnerInterface api = retrofit.create(SpinnerInterface.class);

        Call<String> call = api.getJSONString();

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        String jsonresponse = response.body().toString();
                        spinJSON(jsonresponse);

                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    private void spinJSON(String response){

        try {

            JSONObject obj = new JSONObject(response);
            //if(obj.optString("status").equals("true")){

            goodModelArrayList = new ArrayList<>();
            JSONArray dataArray  = obj.getJSONArray("data");

            for (int i = 0; i < dataArray.length(); i++) {

                SpinnerModel spinnerModel = new SpinnerModel();
                JSONObject dataobj = dataArray.getJSONObject(i);

                //spinnerModel.setName(dataobj.getString("name"));
                //spinnerModel.setCountry(dataobj.getString("country"));
                //spinnerModel.setCity(dataobj.getString("city"));
                //spinnerModel.setImgURL(dataobj.getString("imgURL"));

                spinnerModel.setIdCiudad(dataobj.getString("idCiudad"));
                spinnerModel.setNomCiudad(dataobj.getString("nomCiudad"));


                goodModelArrayList.add(spinnerModel);

            }

            for (int i = 0; i < goodModelArrayList.size(); i++){
                //playerNames.add(goodModelArrayList.get(i).getName().toString());
                playerNames.add(goodModelArrayList.get(i).getNomCiudad().toString());
            }

            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(VSeleccionOriDest.this, simple_spinner_item, playerNames);
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
            spinner.setAdapter(spinnerArrayAdapter);

            //}

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
