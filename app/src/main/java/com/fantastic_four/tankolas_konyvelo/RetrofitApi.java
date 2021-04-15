package com.fantastic_four.tankolas_konyvelo;

import com.fantastic_four.tankolas_konyvelo.Data.ServerResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitApi {

    public String BASE_URL = "https://tankolas-konyvelo-db.herokuapp.com/";

    //Adott rendszámhoz tartozó adatok letöltése
    @FormUrlEncoded
    @POST("get_data")
    Call<ServerResponse> getData(@Field("plateNumber") String plateNumber);

    //Adott rendszámhoz tartozó adatok feltöltése
    @FormUrlEncoded
    @POST("upload_data")
    Call<Void> uploadData(@Field("data") String data);
}