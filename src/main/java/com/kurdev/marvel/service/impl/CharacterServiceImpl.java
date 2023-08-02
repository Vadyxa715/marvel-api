package com.kurdev.marvel.service.impl;

import com.kurdev.marvel.dto.CharacterDto;
import com.kurdev.marvel.entity.Character;
import com.kurdev.marvel.exeption.ResourceNotFoundExeption;
import com.kurdev.marvel.mapper.CharacterMapper;
import com.kurdev.marvel.repo.CharacterRepo;
import com.kurdev.marvel.service.CharacterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CharacterServiceImpl implements CharacterService {

    private CharacterRepo characterRepo;

    @Override
    public CharacterDto createCharacter(CharacterDto characterDto) {

        Character character = CharacterMapper.mapToCharacter(characterDto);
        Character saveCharacter =  characterRepo.save(character);
        return CharacterMapper.mapToCharacterDto(saveCharacter);
    }

    @Override
    public CharacterDto getCharacterById(Long characterId) {
        Character character = characterRepo.findById(characterId)
                .orElse(null);

//                .orElseThrow(()->
//                        new ResourceNotFoundExeption("Не существует персонажа с данным \"ID\" : " + characterId));
        return CharacterMapper.mapToCharacterDto(character);
    }
}
