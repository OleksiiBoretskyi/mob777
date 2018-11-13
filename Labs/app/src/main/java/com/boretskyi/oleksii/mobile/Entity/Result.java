package com.boretskyi.oleksii.mobile.Entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Oleksii on 23.10.2018.
 */
public class Result {
    @SerializedName("Response")
    private List<Country> countries = null;

    public List<Country> getCountries() {
        return countries;
    }
}
