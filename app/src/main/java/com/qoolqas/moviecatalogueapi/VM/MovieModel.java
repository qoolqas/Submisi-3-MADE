package com.qoolqas.moviecatalogueapi.VM;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.qoolqas.moviecatalogueapi.API.Client;
import com.qoolqas.moviecatalogueapi.API.Service;
import com.qoolqas.moviecatalogueapi.Data.Movie;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


@SuppressWarnings("NullableProblems")
public class MovieModel extends ViewModel {
    private final static String api = Client.getApiKey();
    private MutableLiveData<Movie> liveData;

    public void loadMovies() {
        Service service = Client.getClient().create(Service.class);
        Call<Movie> movieCall = service.getMovie(api);
        movieCall.enqueue(new Callback<Movie>() {
            private Call<Movie> call;
            private Response<Movie> response;

            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                this.call = call;
                this.response = response;
                liveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.e("Failed To Load Film", Objects.requireNonNull(t.getMessage()));
            }
        });
    }

    public LiveData<Movie> liveMovies() {
        if (liveData == null) {
            liveData = new MutableLiveData<>();
            loadMovies();
        }
        return liveData;
    }
}
