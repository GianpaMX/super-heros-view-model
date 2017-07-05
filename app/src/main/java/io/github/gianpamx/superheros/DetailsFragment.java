package io.github.gianpamx.superheros;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailsFragment extends Fragment {
    public static final String MARVEL_API = "https://gateway.marvel.com/v1/";
    public static final String API_KEY = "f50abdda9be0a3da1346c57de0becc4d";
    public static final String API_SECRET = "a25c48cd8c6010b5ee324b06f78d76e26879cb1f";

    private ImageView thumbnail;
    private TextView nameTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.details_fragment, container, false);

        thumbnail = view.findViewById(R.id.thumbnail);
        nameTextView = view.findViewById(R.id.name);

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

        Call<CharacterDataWrapper> call = service.getCharacter("1011334");
        call.enqueue(new Callback<CharacterDataWrapper>() {
            @Override
            public void onResponse(Call<CharacterDataWrapper> call, Response<CharacterDataWrapper> response) {
                if (response.isSuccessful()) {
                    Character character = response.body().data.results.get(0);
                    nameTextView.setText(character.name);
                }
            }

            @Override
            public void onFailure(Call<CharacterDataWrapper> call, Throwable t) {

            }
        });

        return view;
    }

}
