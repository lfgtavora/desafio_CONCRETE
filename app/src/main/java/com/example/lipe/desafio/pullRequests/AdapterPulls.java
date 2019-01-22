package com.example.lipe.desafio.pullRequests;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lipe.desafio.R;
import com.example.lipe.desafio.models.PullRequest;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterPulls extends RecyclerView.Adapter<AdapterPulls.myViewHolder> {
    List<PullRequest> items;
    Context context;
    PullsContract.View view;

    public AdapterPulls(Context context, List<PullRequest> items, PullsContract.View view) {
        this.items = items;
        this.context = context;
        this.view = view;
    }

    public void setPulls(List<PullRequest> pulls) {
        this.items.addAll(pulls);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemList = LayoutInflater.from(context).inflate(R.layout.item_pull_layout, viewGroup, false);
        return new myViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull final myViewHolder myViewHolder, int i) {

        myViewHolder.bind(items.get(i));

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView body;
        TextView login;
        ImageView avatar_url;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_id);
            body = itemView.findViewById(R.id.body);
            login = itemView.findViewById(R.id.login);
            avatar_url = itemView.findViewById(R.id.avatar_url);
        }

        public void bind(final PullRequest pull) {
            title.setText(pull.getTitle());
            body.setText(pull.getBody());
            login.setText(pull.getUser().login);
            Picasso.get().load(pull.getUser().avatar_url).into(avatar_url);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    view.openOnBrowser(pull.getHtml_url());
                }
            });
        }
    }
}
