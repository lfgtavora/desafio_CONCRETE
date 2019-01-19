package com.example.lipe.desafio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.lipe.desafio.MVP.MVP;
import com.example.lipe.desafio.MVP.RepositoriesPresenter;
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


public class MainActivity extends AppCompatActivity implements MVP.View {

    private List<Repository> items;
    private Results results;
    private RecyclerView recyclerRepositories;
    private LinearLayoutManager layoutManager;
    private AdapterRepositories adapterRepositories;
    private RepositoriesPresenter repositoriesPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        repositoriesPresenter = new RepositoriesPresenter();
        repositoriesPresenter.setView(this);

        items = new ArrayList<>();
        recyclerRepositories = findViewById(R.id.recyclerRepositories);

        setRepositoriesView();
        setScrollListener();
        getRepositories(1);

        recyclerRepositories.addOnScrollListener(scrollListener);

    }


    public void setScrollListener() { }

    public void loadNextDataFromApi(int page) {

        getRepositories(page);
    }

    private void setRepositoriesView() {
        //recycler view config
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerRepositories.setLayoutManager(layoutManager);
        recyclerRepositories.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        recyclerRepositories.setHasFixedSize(true);
        adapterRepositories = new AdapterRepositories(getApplicationContext(), items);
        recyclerRepositories.setAdapter(adapterRepositories);
    }

    private void getRepositories(int page) {

        //retofit config
        retrofit = RetrofitConfig.getRetrofitConfig();

        GithubService service = retrofit.create(GithubService.class);
        Call<Results> call = service.getRepositories("star", String.valueOf(page));

        call.enqueue(new Callback<Results>() {
            @Override
            public void onResponse(Call<Results> call, Response<Results> response) {

                if (response.isSuccessful()) {
                    results = response.body();

                    for (int i = 0; i < results.items.size(); i++)
                        items.add(results.items.get(i));

                    adapterRepositories.setRepositories(items);
                    adapterRepositories.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Results> call, Throwable t) {
                Log.d("TAG", "Response = " + t.toString());
            }
        });
    }
}
