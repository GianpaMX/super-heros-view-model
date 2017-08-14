package io.github.gianpamx.superheros.viewmodel;

import android.arch.lifecycle.MutableLiveData;

public interface LiveDataFactory<T> {
    MutableLiveData<T> newInstance();
}
