package com.example.lipe.desafio.repositories;

import com.example.lipe.desafio.models.RepositoriesListResponse;
import com.example.lipe.desafio.models.Repository;

import java.util.List;

public class RepositoriesPresenter {

    private RepositoriesContract.View view;
    private RepositoriesModel model;

    RepositoriesPresenter(RepositoriesContract.View view) {
        this.view = view;
        this.model = new RepositoriesModel(this);
    }

    public void getRepositories(int page){
        model.getRepositories(page);
    }

    public void setRepositoriesList(List<Repository> items){
        view.displayList(items);
    }


    public void onLoadMore(){
        view.onLoadMore();
    }
}
