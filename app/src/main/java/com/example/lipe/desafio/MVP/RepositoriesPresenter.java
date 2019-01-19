package com.example.lipe.desafio.MVP;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.lipe.desafio.helpers.EndlessRecyclerViewScrollListener;
import com.example.lipe.desafio.models.Repository;
import com.example.lipe.desafio.models.Results;

import java.util.ArrayList;
import java.util.List;

public class RepositoriesPresenter implements MVP.Presenter{

    private MVP.View view;
    private MVP.Model model;
    private EndlessRecyclerViewScrollListener scrollListener;
    public List<Repository> items;
    public Results results;

    public RepositoriesPresenter(){
        model = new RepositoriesModel(this);
    }

    public Results getResults() {
        return this.results;
    }

    public void setView(MVP.View view){
        this.view = view;
    }

    public void setScrollListener() {
        scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView recyclerView) {
                Log.i("page", String.valueOf(page));
                loadNextDataFromApi(page);
            }
        };
    }

    public void loadNextDataFromApi(int page) {
        model.getRepositories(page);
    }



}
