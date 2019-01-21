package com.example.lipe.desafio.pullRequests;

import com.example.lipe.desafio.models.PullRequest;

import java.util.List;

public class PullsPresenter implements PullsContract.Presenter {

    PullsContract.View view;
    PullsModel model;

    PullsPresenter(PullsContract.View view) {
        this.view = view;
        this.model = new PullsModel(this);
    }

    public void getIntentData(){
        model.getPulls(view.getIntentData());
    }

    public void setPulls(List<PullRequest> items){
        view.displayList(items);
    }


}
