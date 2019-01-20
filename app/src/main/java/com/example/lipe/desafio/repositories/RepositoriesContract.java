package com.example.lipe.desafio.repositories;

import com.example.lipe.desafio.models.RepositoriesListResponse;
import com.example.lipe.desafio.models.Repository;

import java.util.List;

public interface RepositoriesContract {

    interface View {
        void setupList();
        void displayList(List<Repository> items);
        void onRepositoryClikedListener(Repository repository);
        void onLoadMore();
    }

    interface Presenter {
        void getRepositoriesList(RepositoriesListResponse repositoriesListResponse);
    }

}
