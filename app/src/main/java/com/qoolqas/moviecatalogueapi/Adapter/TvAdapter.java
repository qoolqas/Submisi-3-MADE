package com.qoolqas.moviecatalogueapi.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.qoolqas.moviecatalogueapi.Activity.DetailTv;
import com.qoolqas.moviecatalogueapi.Data.TvShow;
import com.qoolqas.moviecatalogueapi.R;

import java.util.List;

public class TvAdapter extends RecyclerView.Adapter<TvAdapter.ViewHolderTv> {
    private Context mContext;
    private List<TvShow> mlist;

    public TvAdapter(Context mContext, List<TvShow> mList) {
        this.mContext = mContext;
        this.mlist = mList;
    }

    public class ViewHolderTv extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title, release, rating;

        public ViewHolderTv(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.txt_name);
            release = itemView.findViewById(R.id.txt_release);
            rating = itemView.findViewById(R.id.txt_vote);
            imageView = itemView.findViewById(R.id.img_movies);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        TvShow clickedItem = mlist.get(position);
                        Intent intent = new Intent(mContext, DetailTv.class);
                        intent.putExtra("tv",clickedItem);
                        mContext.startActivity(intent);
                        Toast.makeText(view.getContext(), clickedItem.getTitle(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    @NonNull
    @Override
    public ViewHolderTv onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.card, parent, false);
        ViewHolderTv vHolder = new ViewHolderTv(view);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderTv holder, final int position) {
        holder.title.setText(mlist.get(position).getTitle());
        holder.release.setText(mlist.get(position).getReleaseDate());
        holder.rating.setText(String.valueOf(mlist.get(position).getVoteAverage()));

        Glide.with(mContext).load("https://image.tmdb.org/t/p/w185" + mlist.get(position).getPosterPath()).into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }
}
