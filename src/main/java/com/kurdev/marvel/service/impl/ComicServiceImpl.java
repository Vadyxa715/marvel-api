package com.kurdev.marvel.service.impl;

import com.kurdev.marvel.dto.ComicDto;
import com.kurdev.marvel.entity.Comic;
import com.kurdev.marvel.mapper.ComicMapper;
import com.kurdev.marvel.repo.ComicRepo;
import com.kurdev.marvel.service.ComicService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ComicServiceImpl implements ComicService {

    private ComicRepo comicRepo;
    @Override
    public ComicDto createComic(ComicDto comicDto) {
        Comic comic = ComicMapper.mapToComic(comicDto);
        Comic saveComic = comicRepo.save(comic);
        return ComicMapper.mapToComicDto(saveComic);
    }

    @Override
    public ComicDto getComicById(Long comicId) {
        Comic comic = comicRepo.findById(comicId)
                .orElse(null);
        return ComicMapper.mapToComicDto(comic);
    }
}
