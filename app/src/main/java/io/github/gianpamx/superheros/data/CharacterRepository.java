package io.github.gianpamx.superheros.data;

import io.github.gianpamx.superheros.data.model.Character;
import io.github.gianpamx.superheros.data.model.CharacterDataWrapper;
import retrofit2.Call;
import retrofit2.Response;

public class CharacterRepository {
    private final MarvelService service;

    public CharacterRepository(MarvelService service) {
        this.service = service;
    }

    public void findById(String characterId, final Callback callback) {
        Call<CharacterDataWrapper> call = service.getCharacter(characterId);
        call.enqueue(new retrofit2.Callback<CharacterDataWrapper>() {
            @Override
            public void onResponse(Call<CharacterDataWrapper> call, Response<CharacterDataWrapper> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body().data.results.get(0));
                }
            }

            @Override
            public void onFailure(Call<CharacterDataWrapper> call, Throwable t) {

            }
        });
    }

    public interface Callback {
        void onSuccess(Character character);
    }
}
