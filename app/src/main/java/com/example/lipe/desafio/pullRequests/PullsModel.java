package com.example.lipe.desafio.pullRequests;

import android.content.Intent;
import android.util.Log;

import com.example.lipe.desafio.api.GithubService;
import com.example.lipe.desafio.helpers.RetrofitConfig;
import com.example.lipe.desafio.models.PullRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PullsModel {

    PullsPresenter presenter;
    GithubService service;


    public PullsModel(PullsPresenter presenter) {
        this.presenter = presenter;
        this.service = RetrofitConfig.getRetrofitConfig().create(GithubService.class);
    }

    public void getPulls(String[] data){

        //get name repository from intent
        String name = data[0];
        //get login repository from intent
        String login = data[1];

        Call<List<PullRequest>> call = service.getPulls(login, name);

        call.enqueue(new Callback<List<PullRequest>>() {
            @Override
            public void onResponse(Call<List<PullRequest>> call, Response<List<PullRequest>> response) {
                if (response.isSuccessful()) {
                    presenter.setPulls(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<PullRequest>> call, Throwable t) {
                Log.d("TAG", "Response = " + t.toString());
            }
        });
    }

}
