package io.github.gianpamx.superheros;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MarvelService {
    @GET("public/characters")
    Call<CharacterDataWrapper> getCharacters();

    @GET("public/characters/{characterId}")
    Call<CharacterDataWrapper> getCharacter(@Path("characterId") String characterId);
}
