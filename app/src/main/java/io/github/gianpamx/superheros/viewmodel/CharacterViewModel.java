package io.github.gianpamx.superheros.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import io.github.gianpamx.superheros.Character;
import io.github.gianpamx.superheros.data.CharacterRepository;

public class CharacterViewModel extends ViewModel {
    private LiveData<Character> character;

    public void init(String characterId, CharacterRepository characterRepository) {
        if (character == null) {
            character = characterRepository.findById(characterId);
        }
    }

    public LiveData<Character> getCharacter() {
        return character;
    }
}
