package io.github.gianpamx.superheros.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import io.github.gianpamx.superheros.Character;
import io.github.gianpamx.superheros.data.CharacterRepository;

public class CharacterViewModel extends ViewModel {
    private MutableLiveData<Character> liveCharacter;

    public void init(String characterId, CharacterRepository characterRepository, LiveDataFactory<Character> liveDataFactory) {
        if (liveCharacter == null) {
            liveCharacter = liveDataFactory.newInstance();
            characterRepository.findById(characterId, new CharacterRepository.Callback() {
                @Override
                public void onSuccess(Character character) {
                    liveCharacter.setValue(character);
                }
            });
        }
    }

    public LiveData<Character> getLiveCharacter() {
        return liveCharacter;
    }
}
