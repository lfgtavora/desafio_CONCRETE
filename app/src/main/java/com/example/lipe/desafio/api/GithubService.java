package com.example.lipe.desafio.api;

import com.example.lipe.desafio.models.PullRequest;
import com.example.lipe.desafio.models.Repository;
import com.example.lipe.desafio.models.Results;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GithubService {

    public static final String BASE_URL = "https://api.github.com/";

    @GET("search/repositories?q=language:Java")
    Call<Results> getRepositories(@Query("sort") String sort,
                                  @Query("page") String Page
    );

    @GET("repos/{login}/{name}/pulls")
    Call<List<PullRequest>> getPulls(@Path("login") String login,
                                     @Path("name") String name
    );

}
