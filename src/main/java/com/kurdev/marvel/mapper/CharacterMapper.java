package com.kurdev.marvel.mapper;

import com.kurdev.marvel.dto.CharacterDto;
import com.kurdev.marvel.entity.Character;

public class CharacterMapper {
    //обычно называется convert(конверт)
    public static CharacterDto mapToCharacterDto(Character character){
        if(character == null) return null;
        return new CharacterDto(
                character.getId(),
                character.getName(),
                character.getDescription()
        );
    }
    //обычно называется revert(реверт)
    public static Character mapToCharacter (CharacterDto characterDto){
        return new Character(
                characterDto.getId(),
                characterDto.getName(),
                characterDto.getDescription()
        );
    }
}
