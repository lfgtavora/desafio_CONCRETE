package com.example.lipe.desafio.repositories;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lipe.desafio.PullRequestsActivity;
import com.example.lipe.desafio.R;
import com.example.lipe.desafio.models.Repository;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RepositoriesAdapter extends RecyclerView.Adapter<RepositoriesAdapter.MyViewHolder> {

    private List<Repository> items;
    private Context context;
    private RepositoriesContract.View view;

    public RepositoriesAdapter(Context context, List<Repository> repositories, RepositoriesContract.View view ) {
        this.items = repositories;
        this.context = context;
        this.view = view;
    }


    void setRepositories(List<Repository> repositories) {
        this.items.addAll(repositories);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemList = LayoutInflater
                .from(context)
                .inflate(R.layout.item_repository_layout, viewGroup, false);
        return new MyViewHolder(itemList);

    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int position) {
        myViewHolder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView description;
        TextView login;
        TextView forks_count;
        TextView stargazers_count;
        ImageView avatar_url;
        ImageView fork_icon;
        ImageView star_icon;

        MyViewHolder(@NonNull View itemView) {
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

        void bind(final Repository repository){
            name.setText(repository.getName());
            description.setText(repository.getDescription());
            login.setText(repository.getOwner().login);
            forks_count.setText(repository.getForks_count());
            stargazers_count.setText(repository.getStargazers_count());
            Picasso.get().load(repository.getOwner().avatar_url).into(avatar_url);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    view.onRepositoryClikedListener(repository);
                }
            });


        }
    }

}
