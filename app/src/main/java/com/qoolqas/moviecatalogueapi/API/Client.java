package com.qoolqas.moviecatalogueapi.API;

import com.qoolqas.moviecatalogueapi.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client {
    private final static String API_KEY = BuildConfig.API_KEY;
    public static final String BASE_URL = "https://api.themoviedb.org/3/discover/";
    public static Retrofit retrofit;

    public static String getApiKey() {
        return API_KEY;
    }

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
