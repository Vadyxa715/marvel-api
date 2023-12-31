package com.kurdev.marvel.controller;

import com.kurdev.marvel.dto.CharacterDto;
import com.kurdev.marvel.dto.ComicDto;
import com.kurdev.marvel.service.CharacterService;
import com.kurdev.marvel.service.ComicService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/v1/public/comics", produces = "application/json; charset=utf-8")
//Распростроненная проблема для Swagger3 слетает кодировка
public class ComicController {

    private ComicService comicService;
    private CharacterService characterService;

    @Operation(summary = "Создать новый комикс")
    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity<?> createComic(@RequestBody ComicDto comicDto) {
        ComicDto saveComic = comicService.createComic(comicDto);
        if (saveComic == null || saveComic.getId() == null) {
            return ResponseEntity.badRequest().body("Поля пустые.");
        }
        return new ResponseEntity<>(saveComic, HttpStatus.CREATED);
    }

    @Operation(summary = "Получить все комиксы")
    @RequestMapping(method = RequestMethod.GET)
    public Page<ComicDto> getAllComics(
            @RequestParam(defaultValue = "title", required = false) String sort,
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "3", required = false) int size,
            @RequestParam(defaultValue = "ASC", required = false) String order
    ) {
        Pageable paging = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order),sort));
        Page<ComicDto> comicListDto = comicService.getAllComics(paging);
        if (comicListDto == null || comicListDto.isEmpty()) {
            throw new RuntimeException("Не найдено ни одного комикса.");
        }
        return comicListDto;
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleException(RuntimeException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

    @Operation(summary = "Получить комикс по id")
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<?> getComic(@PathVariable Long id) {
        ComicDto getComic = comicService.getComicById(id);
        if (getComic == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Комикса с таким \"ID : " + id + "\" не существует ");
        }
        return new ResponseEntity<>(getComic, HttpStatus.OK);
    }

    @Operation(summary = "Получить персонажей из комикса по id комикса")
    @RequestMapping(method = RequestMethod.GET, value = "/{id}/characters")
    public ResponseEntity<?> getCharactersFromComic(@PathVariable Long id) {
        if (!comicService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Комикса с таким \"ID : " + id + "\" не существует.");
        }
        if (comicService.getAllCharacterByComicId(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("В комиксе с таким \"ID : " + id + "\" нет персонажей.");
        }
        List<CharacterDto> characterDto = comicService.getAllCharacterByComicId(id);
        return new ResponseEntity<>(characterDto, HttpStatus.OK);
    }

    @Operation(summary = "Связать id комикса с id персонажа")
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

