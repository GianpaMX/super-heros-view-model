package io.github.gianpamx.superheros.viewmodel;

import android.arch.lifecycle.MutableLiveData;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.github.gianpamx.superheros.Character;
import io.github.gianpamx.superheros.data.CharacterRepository;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CharacterViewModelTest {

    public static final Character EXPECTED_CHARACTER = new Character();

    @Mock
    private CharacterRepository characterRepository;

    @Mock
    private MutableLiveData<Character> liveDataCharacter;

    @Captor
    private ArgumentCaptor<CharacterRepository.Callback> captor;

    private CharacterViewModel characterViewModel;

    @Test
    public void init() {
        characterViewModel = new CharacterViewModel();

        characterViewModel.init("ANY", characterRepository, mockLiveDataFactory);

        verifyAnyStringAndCapture().onSuccess(EXPECTED_CHARACTER);
        verify(liveDataCharacter).setValue(EXPECTED_CHARACTER);
    }

    private CharacterRepository.Callback verifyAnyStringAndCapture() {
        verify(characterRepository).findById(anyString(), captor.capture());
        return captor.getValue();
    }

    private LiveDataFactory<Character> mockLiveDataFactory = new LiveDataFactory<Character>() {
        @Override
        public MutableLiveData<Character> newInstance() {
            return liveDataCharacter;
        }
    };
}
