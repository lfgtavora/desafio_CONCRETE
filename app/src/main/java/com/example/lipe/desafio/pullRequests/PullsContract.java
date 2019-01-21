package com.example.lipe.desafio.pullRequests;


import com.example.lipe.desafio.models.PullRequest;

import java.util.List;

public interface PullsContract {

    interface View{
        void setupList();
        String[] getIntentData();
        void displayList(List<PullRequest> items);
    }

    interface Presenter{
        void getIntentData();
    }
}
