package com.example.lipe.desafio.repositories;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.lipe.desafio.PullRequestsActivity;
import com.example.lipe.desafio.R;
import com.example.lipe.desafio.helpers.EndlessRecyclerViewScrollListener;
import com.example.lipe.desafio.models.RepositoriesListResponse;
import com.example.lipe.desafio.models.Repository;

import java.util.ArrayList;
import java.util.List;


public class RepositoriesActivity extends AppCompatActivity implements RepositoriesContract.View {

    private LinearLayoutManager layoutManager;
    private List<Repository> items = new ArrayList<>();
    private RecyclerView recyclerRepositories;
    private RepositoriesAdapter repositoriesAdapter;
    private RepositoriesPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new RepositoriesPresenter(this);
        findViews();
        setupList();
        presenter.getRepositories(1);

        EndlessRecyclerViewScrollListener scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView recyclerView) {
                Log.i("page", String.valueOf(page));
                presenter.getRepositories(page);
            }
        };

        recyclerRepositories.addOnScrollListener(scrollListener);

    }

    public void findViews(){
        recyclerRepositories = findViewById(R.id.recyclerRepositories);
    }


    public void displayList(List<Repository> items){
        repositoriesAdapter.setRepositories(items);
    }

    public void setupList() {
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerRepositories.setLayoutManager(layoutManager);
        recyclerRepositories.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        recyclerRepositories.setHasFixedSize(true);
        repositoriesAdapter = new RepositoriesAdapter(getApplicationContext(), items, this);
        recyclerRepositories.setAdapter(repositoriesAdapter);
    }

    public void onRepositoryClikedListener(Repository repository){
        final Intent intent = new Intent(getBaseContext(), PullRequestsActivity.class);
        intent.putExtra("name", repository.getName());
        intent.putExtra("login", repository.getOwner().login);
        startActivity(intent);
    }

    public void onLoadMore(){
        repositoriesAdapter.notifyDataSetChanged();
    }

}
