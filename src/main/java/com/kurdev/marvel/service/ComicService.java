package com.kurdev.marvel.service;

import com.kurdev.marvel.dto.CharacterDto;
import com.kurdev.marvel.dto.ComicDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ComicService {

    ComicDto createComic(ComicDto comicDto);

    ComicDto getComicById(Long comicId);

    List<CharacterDto> getAllCharacterByComicId(Long comicId);

    boolean existsById(Long id);

    void addCharacter(Long characterId, Long comicId);

    Page<ComicDto> getAllComics(Pageable pageable);
}