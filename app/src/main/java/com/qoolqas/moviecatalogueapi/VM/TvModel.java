package com.qoolqas.moviecatalogueapi.VM;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.qoolqas.moviecatalogueapi.API.Client;
import com.qoolqas.moviecatalogueapi.API.Service;
import com.qoolqas.moviecatalogueapi.Data.TvShow;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


@SuppressWarnings("NullableProblems")
public class TvModel extends ViewModel {
    private final static String api = Client.getApiKey();
    private MutableLiveData<TvShow> liveData;

    public void loadTv(){
        Service service = Client.getClient().create(Service.class);
        Call<TvShow> tvShowCall = service.getShow(api);
        tvShowCall.enqueue(new Callback<TvShow>() {
            private Call<TvShow> call;
            private Response<TvShow> response;

            @Override
            public void onResponse(Call<TvShow> call, Response<TvShow> response) {
                this.call = call;
                this.response = response;
                liveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<TvShow> call, Throwable t) {
                Log.e("Failed To Load Film", Objects.requireNonNull(t.getMessage()));
            }
        });
    }

    public LiveData<TvShow> liveTv(){
        if (liveData == null){
            liveData = new MutableLiveData<>();
            loadTv();
        }
        return liveData;
    }
}
