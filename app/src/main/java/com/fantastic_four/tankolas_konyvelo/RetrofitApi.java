package com.fantastic_four.tankolas_konyvelo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitApi {

    public String BASE_URL = "https://tankolas-konyvelo-db.herokuapp.com/";

    @FormUrlEncoded
    @POST("get_data")
    Call<ServerResponse> getData(@Field("plateNumber") String plateNumber);

    @FormUrlEncoded
    @POST("upload_data")
    Call<Void> uploadData(@Field("data") String data);
}