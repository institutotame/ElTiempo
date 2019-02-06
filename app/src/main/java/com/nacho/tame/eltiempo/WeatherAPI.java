package com.nacho.tame.eltiempo;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WeatherAPI {
    @GET("location/766273/")
    Call<Weather> loadWeather();
}
