package com.qoolqas.moviecatalogueapi.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.qoolqas.moviecatalogueapi.Adapter.MovieAdapter;
import com.qoolqas.moviecatalogueapi.Data.Movie;
import com.qoolqas.moviecatalogueapi.R;
import com.qoolqas.moviecatalogueapi.VM.MovieModel;

public class FragmentMovie extends Fragment {
    private MovieAdapter movieAdapter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private MovieModel movieModel;

    public FragmentMovie() {
    }

    View v;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.movie_fragment, container, false);
        recyclerView = v.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        progressBar = v.findViewById(R.id.pb);
        movieModel = ViewModelProviders.of(this).get(MovieModel.class);
        movieModel.liveMovies().observe(this, new Observer<Movie>() {
            @Override
            public void onChanged(Movie movie) {
                Toast.makeText(getContext(), "Change Orientation", Toast.LENGTH_SHORT).show();
                movieAdapter = new MovieAdapter(getContext(), movie.getResults());
                recyclerView.setAdapter(movieAdapter);
                progressBar.setVisibility(View.GONE);
            }
        });

        progressBar.setVisibility(View.VISIBLE);
        return v;
    }

}
