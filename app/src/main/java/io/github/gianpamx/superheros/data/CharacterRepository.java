package io.github.gianpamx.superheros.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import io.github.gianpamx.superheros.Character;
import io.github.gianpamx.superheros.CharacterDataWrapper;
import io.github.gianpamx.superheros.MarvelService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharacterRepository {
    private final MarvelService service;

    public CharacterRepository(MarvelService service) {
        this.service = service;
    }

    public LiveData<Character> findById(String characterId) {
        final MutableLiveData<Character> character = new MutableLiveData<>();

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
