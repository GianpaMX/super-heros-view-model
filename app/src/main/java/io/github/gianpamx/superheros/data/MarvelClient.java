package io.github.gianpamx.superheros.data;

import io.github.gianpamx.superheros.MarvelInterceptor;
import io.github.gianpamx.superheros.MarvelService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MarvelClient {
    public static final String MARVEL_API = "https://gateway.marvel.com/v1/";
    public static final String API_KEY = "f50abdda9be0a3da1346c57de0becc4d";
    public static final String API_SECRET = "a25c48cd8c6010b5ee324b06f78d76e26879cb1f";

    public final MarvelService service;

    public MarvelClient() {
        MarvelInterceptor marvelInterceptor = new MarvelInterceptor(API_KEY, API_SECRET);

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(marvelInterceptor)
                .addInterceptor(loggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MARVEL_API)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(MarvelService.class);
    }
}
