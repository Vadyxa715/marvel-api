package com.kurdev.marvel.mapper;

import com.kurdev.marvel.dto.CharacterDto;
import com.kurdev.marvel.entity.Character;

public class CharacterMapper {
    public static CharacterDto mapToCharacterDto(Character character) {
        if (character == null) return null;
        return new CharacterDto(
                character.getId(),
                character.getName(),
                character.getDescription()
        );
    }

    public static Character mapToCharacter(CharacterDto characterDto) {
        if (characterDto == null) return null;
        return new Character(
                characterDto.getId(),
                characterDto.getName(),
                characterDto.getDescription()
        );
    }
}
