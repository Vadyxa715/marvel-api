package com.kurdev.marvel.service;

import com.kurdev.marvel.dto.CharacterDto;

public interface CharacterService {
    CharacterDto createCharacter(CharacterDto characterDto);

    CharacterDto getCharacterById(Long characterId);
}
