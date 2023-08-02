package com.kurdev.marvel.controller;

import com.kurdev.marvel.dto.CharacterDto;
import com.kurdev.marvel.service.CharacterService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/public/characters")
public class CharacterController {

    private CharacterService characterService;
//    @RequestMapping(method = RequestMethod.GET)
//    public String printHello(ModelMap model) {
//        model.addAttribute("message", "Hello Spring MVC Framework!");
//        return "hello";
//    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createCharacter(@RequestBody CharacterDto characterDto){
        CharacterDto saveCharacter = characterService.createCharacter(characterDto);
        if(saveCharacter == null || saveCharacter.getId() == null){
            return ResponseEntity.badRequest().body("Поля пустые");
        }
        return new ResponseEntity<>(saveCharacter, HttpStatus.CREATED);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<?> getCharacter(@PathVariable Long id){
        CharacterDto getCharacter = characterService.getCharacterById(id);
        if(getCharacter == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Персонажа с таким \"ID : " + id + "\" не существует ");
        }
        return new ResponseEntity<>(getCharacter, HttpStatus.OK);

    }
}
