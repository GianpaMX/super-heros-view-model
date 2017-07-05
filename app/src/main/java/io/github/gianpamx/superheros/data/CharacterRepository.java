package io.github.gianpamx.superheros.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import io.github.gianpamx.superheros.Character;
import io.github.gianpamx.superheros.CharacterDataWrapper;
import io.github.gianpamx.superheros.MarvelInterceptor;
import io.github.gianpamx.superheros.MarvelService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CharacterRepository {
    public static final String MARVEL_API = "https://gateway.marvel.com/v1/";
    public static final String API_KEY = "f50abdda9be0a3da1346c57de0becc4d";
    public static final String API_SECRET = "a25c48cd8c6010b5ee324b06f78d76e26879cb1f";

    public LiveData<Character> findById(String characterId) {
        final MutableLiveData<Character> character = new MutableLiveData<>();

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

        MarvelService service = retrofit.create(MarvelService.class);

        Call<CharacterDataWrapper> call = service.getCharacter(characterId);
        call.enqueue(new Callback<CharacterDataWrapper>() {
            @Override
            public void onResponse(Call<CharacterDataWrapper> call, Response<CharacterDataWrapper> response) {
                if (response.isSuccessful()) {
                    character.setValue(response.body().data.results.get(0));
                }
            }

            @Override
            public void onFailure(Call<CharacterDataWrapper> call, Throwable t) {

            }
        });

        return character;
    }
}
