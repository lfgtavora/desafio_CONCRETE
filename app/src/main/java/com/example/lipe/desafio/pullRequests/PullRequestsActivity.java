package com.example.lipe.desafio.pullRequests;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.example.lipe.desafio.R;

import com.example.lipe.desafio.models.PullRequest;

import java.util.ArrayList;
import java.util.List;

public class PullRequestsActivity extends AppCompatActivity implements PullsContract.View {

    private RecyclerView recyclerPulls;
    private List<PullRequest> items;
    private AdapterPulls adapterPulls;
    private PullsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_requests);

        items = new ArrayList<>();

        presenter = new PullsPresenter(this);

        findViews();
        setupList();

        presenter.getIntentData();
        setActionBarName();

    }

    public void findViews(){
        recyclerPulls = findViewById(R.id.recyclerPull);
    }

    public void displayList(List<PullRequest> items){
        adapterPulls.setPulls(items);
    }

    public void setupList(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerPulls.setLayoutManager(layoutManager);
        recyclerPulls.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        recyclerPulls.setHasFixedSize(true);
        adapterPulls = new AdapterPulls(getApplicationContext(), items, this);
        recyclerPulls.setAdapter(adapterPulls);
    }

    public String[] getIntentData(){
        String data[] =  new String[2];
        if (getIntent().hasExtra("name") && getIntent().hasExtra("login")) {
            data[0] = getIntent().getStringExtra("name");
            data[1] = getIntent().getStringExtra("login");
        }

        return data;
    }

    public void setActionBarName(){
        getSupportActionBar().setTitle(getIntent().getStringExtra("name"));
    }

    public void openOnBrowser(String url){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }




}
