package com.example.rplrus06.midsemester12rpl;

import retrofit2.Call;
import retrofit2.http.GET;

public interface json_api {

    @GET("upcoming?api_key=a5db9144717c709a534910444763b21f")
    Call<JsonRespons>getJsonNowPlaying();
}
