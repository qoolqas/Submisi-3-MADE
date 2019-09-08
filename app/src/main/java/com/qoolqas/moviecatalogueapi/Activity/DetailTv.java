package com.qoolqas.moviecatalogueapi.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.qoolqas.moviecatalogueapi.Data.TvShow;
import com.qoolqas.moviecatalogueapi.R;

public class DetailTv extends AppCompatActivity {
    TextView title, synopsis, rating, release;
    ImageView imageView;
    ProgressBar progressBar;
    TvShow tvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_tv);

        progressBar = new ProgressBar(this);
        progressBar.setIndeterminate(true);

        title = findViewById(R.id.txt_name2);
        synopsis = findViewById(R.id.txt_synopsis2);
        rating = findViewById(R.id.txt_vote2);
        release = findViewById(R.id.txt_release2);
        imageView = findViewById(R.id.img_detail2);
        progressBar = findViewById(R.id.pb2);

        tvShow = getIntent().getParcelableExtra("tv");
        {
            progressBar.setVisibility(View.VISIBLE);
            String imageView1 = tvShow.getPosterPath();
            String title1 = tvShow.getTitle();
            String synopsis1 = tvShow.getOverview();
            String rating1 = Double.toString(tvShow.getVoteAverage());
            String release1 = tvShow.getReleaseDate();

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