package com.kurdev.marvel.service;

import com.kurdev.marvel.dto.ComicDto;

public interface ComicService {

    ComicDto createComic(ComicDto comicDto);

    ComicDto getComicById(Long comicId);
}
