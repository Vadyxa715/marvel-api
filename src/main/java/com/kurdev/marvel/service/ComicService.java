package com.kurdev.marvel.service;

import com.kurdev.marvel.dto.CharacterDto;
import com.kurdev.marvel.dto.ComicDto;

import java.util.List;

public interface ComicService {

    ComicDto createComic(ComicDto comicDto);

    ComicDto getComicById(Long comicId);

    List<CharacterDto> getAllCharacterByComicId(Long comicId);

}