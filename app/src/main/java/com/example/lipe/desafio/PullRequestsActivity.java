package com.example.lipe.desafio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.lipe.desafio.adapters.AdapterPulls;
import com.example.lipe.desafio.api.GithubService;
import com.example.lipe.desafio.helpers.RetrofitConfig;
import com.example.lipe.desafio.models.PullRequest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PullRequestsActivity extends AppCompatActivity {

    private RecyclerView recyclerPulls;
    private List<PullRequest> items;
    private AdapterPulls adapterPulls;
    private Retrofit retrofit;

    private String name;
    private String login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_requests);

        items = new ArrayList<>();

        //get intent
        if (getIntent().hasExtra("name") && getIntent().hasExtra("login")) {
            name = getIntent().getStringExtra("name");
            login = getIntent().getStringExtra("login");

            getSupportActionBar().setTitle(name);
        }

        recyclerPulls = findViewById(R.id.recyclerPull);


        //recycler pulls config
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerPulls.setLayoutManager(layoutManager);
        recyclerPulls.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        recyclerPulls.setHasFixedSize(true);
        adapterPulls = new AdapterPulls(getApplicationContext(), items);
        recyclerPulls.setAdapter(adapterPulls);

        //retofit config
        retrofit = RetrofitConfig.getRetrofitConfig();

        GithubService service = retrofit.create(GithubService.class);
        Call<List<PullRequest>> call = service.getPulls(login, name);

        call.enqueue(new Callback<List<PullRequest>>() {
            @Override
            public void onResponse(Call<List<PullRequest>> call, Response<List<PullRequest>> response) {
                if (response.isSuccessful()) {
                    items = response.body();
                    adapterPulls.setPulls(items);
                }
            }

            @Override
            public void onFailure(Call<List<PullRequest>> call, Throwable t) {
                Log.d("TAG", "Response = " + t.toString());
            }
        });

    }
}
