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
@RequestMapping("/v1/public/characters")
public class CharacterController {

    private CharacterService characterService;
    private ComicService comicService;

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity<?> createCharacter(@RequestBody CharacterDto characterDto) {
        CharacterDto saveCharacter = characterService.createCharacter(characterDto);
        if (saveCharacter == null || saveCharacter.getId() == null) {
            return ResponseEntity.badRequest().body("Поля пустые");
        }
        return new ResponseEntity<>(saveCharacter, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAllCharacters() {
        List<CharacterDto> characterDtoList = characterService.getAllCharacters();
        if (characterDtoList == null || characterDtoList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Не найдено ни одного персонажа.");
        }
        return new ResponseEntity<>(characterDtoList, HttpStatus.FOUND);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<?> getCharacter(@PathVariable Long id) {
        CharacterDto getCharacter = characterService.getCharacterById(id);
        if (getCharacter == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Персонажа с таким \"ID : " + id + "\" не существует ");
        }
        return new ResponseEntity<>(getCharacter, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/comics")
    public ResponseEntity<?> getAllComicsByCharacterId(@PathVariable(value = "id") Long characterId) {
        try {
            List<ComicDto> comicDto = characterService.getAllComicsByCharacterId(characterId);
            return new ResponseEntity<>(comicDto, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Персонаж с таким \"ID : " + characterId + "\" не найден. ");
        }
    }

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