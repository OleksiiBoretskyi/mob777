package com.boretskyi.oleksii.mobile.Retrofit;

/**
 * Created by Oleksii on 23.10.2018.
 */
public class Utils {
    public static final String BASE_URL = "http://countryapi.gear.host/v1/";

    public static ApiService getSOService() {
        return RetrofitClient.getClient(BASE_URL).create(ApiService.class);
    }
}