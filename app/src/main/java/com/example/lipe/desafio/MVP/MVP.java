package com.example.lipe.desafio.MVP;

import com.example.lipe.desafio.models.Results;

public interface MVP {

    interface Model {
        public void getRepositories(int page);
        public void setScrollListener();
        public void loadNextDataFromApi(int page);
        public Results getResults();
    }

    interface View {

    }

    interface Presenter {
        public void setScrollListener();
    }


}
