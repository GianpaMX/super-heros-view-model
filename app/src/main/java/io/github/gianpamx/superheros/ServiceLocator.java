package io.github.gianpamx.superheros;

import io.github.gianpamx.superheros.data.MarvelService;
import io.github.gianpamx.superheros.data.CharacterRepository;
import io.github.gianpamx.superheros.data.MarvelClient;

public class ServiceLocator {
    private static ServiceLocator instance;

    private MarvelClient marvelClient;
    private MarvelService marvelService;
    private CharacterRepository characterRepository;

    private ServiceLocator() {
    }

    public static ServiceLocator getInstance() {
        if (instance == null) {
            instance = new ServiceLocator();
        }
        return instance;
    }
    
    public MarvelClient getMarvelClient() {
        if (marvelClient == null) {
            marvelClient = new MarvelClient();
        }

        return marvelClient;
    }

    public void setMarvelClient(MarvelClient marvelClient) {
        this.marvelClient = marvelClient;
    }

    public MarvelService getMarvelService() {
        if (marvelService == null) {
            marvelService = getMarvelClient().service;
        }
        return marvelService;
    }

    public void setMarvelService(MarvelService marvelService) {
        this.marvelService = marvelService;
    }

    public CharacterRepository getCharacterRepository() {
        if (characterRepository == null) {
            characterRepository = new CharacterRepository(getMarvelService());
        }
        return characterRepository;
    }

    public void setCharacterRepository(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }
}
