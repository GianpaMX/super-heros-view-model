package io.github.gianpamx.superheros.data;

import io.github.gianpamx.superheros.data.model.CharacterDataWrapper;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MarvelService {
    @GET("public/characters")
    Call<CharacterDataWrapper> getCharacters();

    @GET("public/characters/{characterId}")
    Call<CharacterDataWrapper> getCharacter(@Path("characterId") String characterId);
}
