package com.boretskyi.oleksii.mobile;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.boretskyi.oleksii.mobile.Adapter.CountryAdapter;
import com.boretskyi.oleksii.mobile.Entity.Country;
import com.boretskyi.oleksii.mobile.Entity.Result;
import com.boretskyi.oleksii.mobile.Retrofit.ApiService;
import com.boretskyi.oleksii.mobile.Retrofit.Utils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ApiService apiService;
    private CountryAdapter adapter;
    @BindView(R.id.tv_error)
    TextView tvError;
    @BindView(R.id.rv_countries)
    RecyclerView rvCounties;
    @BindView(R.id.pullToRefresh)
    SwipeRefreshLayout pullToRefresh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }


    private void init() {
        ButterKnife.bind(this);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getCountries();
                pullToRefresh.setRefreshing(false);
            }
        });
        getCountries();
        adapter = new CountryAdapter(this, new ArrayList<Country>(0));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvCounties.setLayoutManager(layoutManager);
        rvCounties.setAdapter(adapter);
        rvCounties.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
    }

    private void getCountries() {
        apiService = Utils.getSOService();
        apiService.getCounties().enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.isSuccessful()) {
                    adapter.updateAnswers(response.body().getCountries());
                    Log.d("MainActivity", response.body().getCountries().toString());
                }
            }
            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                tvError.setText("Не вдалось завантажити дані");
                Log.d("MainActivity", "error loading from API");
            }
        });
    }
}
