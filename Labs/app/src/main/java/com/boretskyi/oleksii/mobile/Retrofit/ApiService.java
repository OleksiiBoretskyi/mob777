package com.boretskyi.oleksii.mobile.Retrofit;

import com.boretskyi.oleksii.mobile.Entity.Result;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Oleksii on 23.10.2018.
 */
public interface ApiService {
    @GET("Country/getCountries?pRegion=Europe")
    Call<Result> getCounties();
}
