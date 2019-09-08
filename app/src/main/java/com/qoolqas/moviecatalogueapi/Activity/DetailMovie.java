package com.qoolqas.moviecatalogueapi.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.qoolqas.moviecatalogueapi.Data.Movie;
import com.qoolqas.moviecatalogueapi.R;

public class DetailMovie extends AppCompatActivity {
    TextView title, synopsis, rating, release;
    ImageView imageView;
    ProgressBar progressBar;
    Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_movie);

        progressBar = new ProgressBar(this);
        progressBar.setIndeterminate(true);

        title = findViewById(R.id.txt_name1);
        synopsis = findViewById(R.id.txt_synopsis1);
        rating = findViewById(R.id.txt_vote1);
        release = findViewById(R.id.txt_release1);
        imageView = findViewById(R.id.img_detail1);
        progressBar = findViewById(R.id.pb1);

        movie = getIntent().getParcelableExtra("movies");
        {
            progressBar.setVisibility(View.VISIBLE);
            String imageView1 = movie.getPosterPath();
            String title1 = movie.getTitle();
            String synopsis1 = movie.getOverview();
            String rating1 = Double.toString(movie.getVoteAverage());
            String release1 = movie.getReleaseDate();

            String image_url = "https://image.tmdb.org/t/p/w185" + imageView1;

            Glide.with(this).load(image_url).placeholder(R.drawable.load).into(imageView);
            title.setText(title1);
            synopsis.setText(synopsis1);
            rating.setText(rating1);
            release.setText(release1);

        }
        progressBar.setVisibility(View.GONE);
    }

}
