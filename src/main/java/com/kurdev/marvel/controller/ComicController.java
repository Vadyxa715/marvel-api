package com.kurdev.marvel.controller;

import com.kurdev.marvel.dto.CharacterDto;
import com.kurdev.marvel.dto.ComicDto;
import com.kurdev.marvel.service.CharacterService;
import com.kurdev.marvel.service.ComicService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/public/comics")
public class ComicController {

    private ComicService comicService;
    private CharacterService characterService;

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity<?> createComic(@RequestBody ComicDto comicDto) {
        ComicDto saveComic = comicService.createComic(comicDto);
        if (saveComic == null || saveComic.getId() == null) {
            return ResponseEntity.badRequest().body("Поля пустые");
        }
        return new ResponseEntity<>(saveComic, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAllComics() {
        List<ComicDto> comicListDto = comicService.getAllComics();
        if (comicListDto == null || comicListDto.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Не найдено ни одного комикса.");
        }
        return new ResponseEntity<>(comicListDto, HttpStatus.FOUND);
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
        if (!comicService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Комикса с таким \"ID : " + id + "\" не существует ");
        }

        List<CharacterDto> characterDto = comicService.getAllCharacterByComicId(id);
        return new ResponseEntity<>(characterDto, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}/addCharacter/{characterId}")
    public ResponseEntity<?> addCharacterByComicId(@PathVariable(value = "id") Long comicId,
                                                   @PathVariable(value = "characterId") Long characterId) {
        if (!comicService.existsById(comicId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Комикса с таким \"ID : " + comicId + "\" не существует.");
        }
        if (!characterService.existsById(characterId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Персонажа с таким \"ID : " + characterId + "\" не существует.");
        }
        comicService.addCharacter(characterId, comicId);
        return ResponseEntity.ok().build();
    }
}

