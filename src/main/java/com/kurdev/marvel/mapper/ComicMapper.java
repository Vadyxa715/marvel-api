package com.kurdev.marvel.mapper;

import com.kurdev.marvel.dto.ComicDto;
import com.kurdev.marvel.entity.Comic;

public class ComicMapper {
    public static ComicDto mapToComicDto(Comic comic) {
        if (comic == null) return null;
        return new ComicDto(
                comic.getId(),
                comic.getTitle(),
                comic.getIssueNumber(),
                comic.getDescription(),
                comic.getPageCount()
        );
    }

    public static Comic mapToComic(ComicDto comicDto) {
        if (comicDto == null) return null;
        return new Comic(
                comicDto.getId(),
                comicDto.getTitle(),
                comicDto.getIssueNumber(),
                comicDto.getDescription(),
                comicDto.getPageCount()
        );
    }
}
