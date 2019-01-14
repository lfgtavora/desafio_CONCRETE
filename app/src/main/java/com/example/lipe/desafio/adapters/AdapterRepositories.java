package com.example.lipe.desafio.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lipe.desafio.PullRequestsActivity;
import com.example.lipe.desafio.R;
import com.example.lipe.desafio.models.Repository;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterRepositories extends RecyclerView.Adapter<AdapterRepositories.MyViewHolder> {

    private List<Repository> items;
    private Context context;

    public AdapterRepositories(Context context, List<Repository> repositories) {
        this.items = repositories;
        this.context = context;
    }

    public void setRepositories(List<Repository> repositories) {
        this.items = repositories;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemList = LayoutInflater
                .from(context)
                .inflate(R.layout.item_repository_layout, viewGroup, false);
        final int itemViewType = getItemViewType(i);
        return new MyViewHolder(itemList);

    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int i) {

        Repository item = items.get(i);
        myViewHolder.name.setText(item.getName());
        myViewHolder.description.setText(item.getDescription());
        myViewHolder.login.setText(item.getOwner().login);
        myViewHolder.forks_count.setText(item.getForks_count());
        myViewHolder.stargazers_count.setText(item.getStargazers_count());
        //myViewHolder.avatar_url.setImageResource(R.drawable.ic_launcher_background);
        Picasso.get().load(item.getOwner().avatar_url).into(myViewHolder.avatar_url);

        //create intent

        final Intent intent = new Intent(context, PullRequestsActivity.class);
        intent.putExtra("name", item.getName());
        intent.putExtra("login", item.getOwner().login);

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView description;
        TextView login;
        TextView forks_count;
        TextView stargazers_count;
        ImageView avatar_url;
        ImageView fork_icon;
        ImageView star_icon;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            description = itemView.findViewById(R.id.body);
            login = itemView.findViewById(R.id.login);
            forks_count = itemView.findViewById(R.id.forks_count);
            stargazers_count = itemView.findViewById(R.id.stargazers_count);
            avatar_url = itemView.findViewById(R.id.avatar_url);
            fork_icon = itemView.findViewById(R.id.fork_icon);
            star_icon = itemView.findViewById(R.id.star_icon);

        }
    }

}
