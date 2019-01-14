package com.example.lipe.desafio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.lipe.desafio.adapters.AdapterRepositories;
import com.example.lipe.desafio.api.GithubService;
import com.example.lipe.desafio.helpers.EndlessRecyclerViewScrollListener;
import com.example.lipe.desafio.helpers.RetrofitConfig;
import com.example.lipe.desafio.models.Repository;
import com.example.lipe.desafio.models.Results;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private List<Repository> items;
    private Results results;
    private RecyclerView recyclerRepositories;
    private AdapterRepositories adapterRepositories;
    private EndlessRecyclerViewScrollListener scrollListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items = new ArrayList<>();

        recyclerRepositories = findViewById(R.id.recyclerRepositories);

        //recycler view config
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerRepositories.setLayoutManager(layoutManager);
        recyclerRepositories.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        recyclerRepositories.setHasFixedSize(true);
        adapterRepositories = new AdapterRepositories(getApplicationContext(), items);
        recyclerRepositories.setAdapter(adapterRepositories);

        getRepositories(1);

        scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView recyclerView) {
                Log.i("page", String.valueOf(page));
                loadNextDataFromApi(page);
            }
        };

        recyclerRepositories.addOnScrollListener(scrollListener);

    }

    public void loadNextDataFromApi(int page) {

        getRepositories(page);
    }

    private void repositoriesView() {


    }
    private void getRepositories(int page) {

        //retofit config
        retrofit = RetrofitConfig.getRetrofitConfig();

        GithubService service = retrofit.create(GithubService.class);
        Call<Results> call = service.getRepositories("star", String.valueOf(page));

        call.enqueue(new Callback<Results>() {
            @Override
            public void onResponse(Call<Results> call, Response<Results> response) {

                if (response.isSuccessful()){
                    results = response.body();

                    for(int i = 0; i < results.items.size(); i++)
                        items.add(results.items.get(i));

                    adapterRepositories.setRepositories(items);
                    adapterRepositories.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Results> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
            }
        });
    }
}
