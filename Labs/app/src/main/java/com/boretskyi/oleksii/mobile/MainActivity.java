package com.boretskyi.oleksii.mobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.boretskyi.oleksii.mobile.Entity.Result;
import com.boretskyi.oleksii.mobile.Retrofit.ApiService;
import com.boretskyi.oleksii.mobile.Retrofit.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ApiService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiService = Utils.getSOService();
        apiService.getCounties().enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.isSuccessful()) {

                    Log.d("MainActivity", response.body().getCountries().toString());
                }
            }
            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.d("MainActivity", "error loading from API");
            }
        });

    }
}
