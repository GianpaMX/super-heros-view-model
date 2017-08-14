package io.github.gianpamx.superheros.viewmodel;

import android.arch.lifecycle.MutableLiveData;

import io.github.gianpamx.superheros.Character;

public class CharacterLiveDataFactory implements LiveDataFactory<Character> {
    @Override
    public MutableLiveData<Character> newInstance() {
        return new MutableLiveData<>();
    }
}
