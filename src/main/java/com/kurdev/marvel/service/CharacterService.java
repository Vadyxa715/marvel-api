package com.kurdev.marvel.service;

import com.kurdev.marvel.dto.CharacterDto;
import com.kurdev.marvel.dto.ComicDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CharacterService {
    CharacterDto createCharacter(CharacterDto characterDto);

    CharacterDto getCharacterById(Long characterId);

    List<ComicDto> getAllComicsByCharacterId(Long characterId);

    boolean existsById(Long characterId);

    void addComic(Long comicId, Long characterId);

    Page<CharacterDto> getAllCharacters(Pageable pageable);

}
