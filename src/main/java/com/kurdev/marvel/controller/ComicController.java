package com.kurdev.marvel.controller;

import com.kurdev.marvel.dto.CharacterDto;
import com.kurdev.marvel.dto.ComicDto;
import com.kurdev.marvel.service.ComicService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/public/comics")
public class ComicController {

    private ComicService comicService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createComic(@RequestBody ComicDto comicDto) {
        ComicDto saveComic = comicService.createComic(comicDto);
        if (saveComic == null || saveComic.getId() == null) {
            return ResponseEntity.badRequest().body("Поля пустые");
        }
        return new ResponseEntity<>(saveComic, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<?> getComic(@PathVariable Long id) {
        ComicDto getComic = comicService.getComicById(id);
        if (getComic == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Комикса с таким \"ID : " + id + "\" не существует ");
        }
        return new ResponseEntity<>(getComic, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/characters")
    public ResponseEntity<?> getCharactersFromComic(@PathVariable Long id) {
        ComicDto getComic = comicService.getComicById(id);
        if (getComic == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Комикса с таким \"ID : " + id + "\" не существует ");
        }
        return new ResponseEntity<>(getComic, HttpStatus.OK);
    }
}
