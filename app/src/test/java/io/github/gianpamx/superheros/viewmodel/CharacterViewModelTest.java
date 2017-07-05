package io.github.gianpamx.superheros.viewmodel;

import android.arch.lifecycle.LiveData;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.github.gianpamx.superheros.data.CharacterRepository;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CharacterViewModelTest {

    @Mock
    private CharacterRepository characterRepository;

    private CharacterViewModel characterViewModel;

    @Before
    public void setUp() {
        characterViewModel = new CharacterViewModel();
    }

    @Test
    public void init() {
        when(characterRepository.findById(anyString())).thenReturn(mock(LiveData.class));

        characterViewModel.init("ANY", characterRepository);

        assertNotNull(characterViewModel.getCharacter());
    }
}