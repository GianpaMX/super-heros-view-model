package io.github.gianpamx.superheros.details;

import android.arch.lifecycle.MutableLiveData;

public interface LiveDataFactory<T> {
    MutableLiveData<T> newInstance();
}
