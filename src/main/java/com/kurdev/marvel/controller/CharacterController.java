package com.kurdev.marvel.controller;

import com.kurdev.marvel.dto.CharacterDto;
import com.kurdev.marvel.dto.ComicDto;
import com.kurdev.marvel.service.CharacterService;
import com.kurdev.marvel.service.ComicService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/v1/public/characters", produces = "application/json; charset=utf-8")
//Распростроненная проблема для Swagger3 слетает кодировка
public class CharacterController {

    private CharacterService characterService;
    private ComicService comicService;

    @Operation(summary = "Создать нового персонажа")
    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity<?> createCharacter(@RequestBody CharacterDto characterDto) {
        CharacterDto saveCharacter = characterService.createCharacter(characterDto);
        if (saveCharacter == null || saveCharacter.getId() == null) {
            return ResponseEntity.badRequest().body("Поля пустые");
        }
        return new ResponseEntity<>(saveCharacter, HttpStatus.CREATED);
    }

    @Operation(summary = "Получить всех персонажей")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAllCharacters() {
        List<CharacterDto> characterDtoList = characterService.getAllCharacters();
        if (characterDtoList == null || characterDtoList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Не найдено ни одного персонажа.");
        }
        return new ResponseEntity<>(characterDtoList, HttpStatus.FOUND);
    }

    @Operation(summary = "Получить персонажа по id")
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<?> getCharacter(@PathVariable Long id) {
        CharacterDto getCharacter = characterService.getCharacterById(id);
        if (getCharacter == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Персонажа с таким \"ID : " + id + "\" не существует ");
        }
        return new ResponseEntity<>(getCharacter, HttpStatus.OK);
    }

    @Operation(summary = "Получить комиксы с персонажем по id персонажа")
    @RequestMapping(method = RequestMethod.GET, value = "/{id}/comics")
    public ResponseEntity<?> getAllComicsByCharacterId(@PathVariable(value = "id") Long characterId) {
        if (!characterService.existsById(characterId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Персонажа с таким \"ID : " + characterId + "\" не существует.");
        }
        if (characterService.getAllComicsByCharacterId(characterId).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("У персонажа с таким \"ID : " + characterId + "\" нет комиксов.");
        }
        List<ComicDto> comicDto = characterService.getAllComicsByCharacterId(characterId);
        return new ResponseEntity<>(comicDto, HttpStatus.OK);
    }

    @Operation(summary = "Связать id персонажа с id комикса")
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}/addComic/{comicId}")
    public ResponseEntity<?> addComicByCharacterId(@PathVariable(value = "id") Long characterId,
                                                   @PathVariable(value = "comicId") Long comicId) {
        if (!comicService.existsById(comicId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Комикса с таким \"ID : " + comicId + "\" не существует.");
        }
        if (!characterService.existsById(characterId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Персонажа с таким \"ID : " + characterId + "\" не существует.");
        }
        characterService.addComic(comicId, characterId);
        return ResponseEntity.ok().build();
    }
}