package io.github.gianpamx.superheros.details;

import android.arch.lifecycle.MutableLiveData;

import io.github.gianpamx.superheros.data.model.Character;

public class CharacterLiveDataFactory implements LiveDataFactory<Character> {
    @Override
    public MutableLiveData<Character> newInstance() {
        return new MutableLiveData<>();
    }
}
