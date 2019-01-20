package com.example.lipe.desafio.repositories;

import android.util.Log;

import com.example.lipe.desafio.api.GithubService;
import com.example.lipe.desafio.helpers.RetrofitConfig;
import com.example.lipe.desafio.models.RepositoriesListResponse;
import com.example.lipe.desafio.models.Repository;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositoriesModel {

    private RepositoriesPresenter presenter;
    private GithubService service;

    RepositoriesModel(RepositoriesPresenter presenter) {
        this.presenter = presenter;
        this.service = RetrofitConfig.getRetrofitConfig().create(GithubService.class);
    }

    void getRepositories(int page) {
        Call<RepositoriesListResponse> call = service.getRepositories("star", String.valueOf(page));

        call.enqueue(new Callback<RepositoriesListResponse>() {
            @Override
            public void onResponse(Call<RepositoriesListResponse> call, Response<RepositoriesListResponse> response) {

                if (response.isSuccessful()) {
                    presenter.setRepositoriesList(response.body().items);
                    presenter.onLoadMore();
                }
            }

            @Override
            public void onFailure (Call < RepositoriesListResponse > call, Throwable t){
                Log.d("TAG", "Response = " + t.toString());
            }
        });
    }

}
