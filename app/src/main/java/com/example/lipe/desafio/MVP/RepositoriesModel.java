package com.example.lipe.desafio.MVP;

import android.util.Log;

import com.example.lipe.desafio.api.GithubService;
import com.example.lipe.desafio.helpers.RetrofitConfig;
import com.example.lipe.desafio.models.Results;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RepositoriesModel implements MVP.Model {
    private MVP.Presenter presenter;
    private Retrofit retrofit;

    public RepositoriesModel(MVP.Presenter presenter){
        this.presenter = presenter;
    }

    @Override
    public void getRepositories(int page) {

        //retofit config
        retrofit = RetrofitConfig.getRetrofitConfig();

        GithubService service = retrofit.create(GithubService.class);
        Call<Results> call = service.getRepositories("star", String.valueOf(page));

        call.enqueue(new Callback<Results>() {
            @Override
            public void onResponse(Call<Results> call, Response<Results> response) {

                if (response.isSuccessful()) {
                    presenter = response.body();

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
