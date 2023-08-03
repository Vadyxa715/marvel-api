package com.kurdev.marvel.service;

import com.kurdev.marvel.dto.CharacterDto;
import com.kurdev.marvel.dto.ComicDto;

import java.util.List;

public interface CharacterService {
    CharacterDto createCharacter(CharacterDto characterDto);

    CharacterDto getCharacterById(Long characterId);

    List<ComicDto> getAllComicsByCharacterId(Long characterId);
}