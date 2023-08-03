package com.kurdev.marvel.service.impl;

import com.kurdev.marvel.dto.CharacterDto;
import com.kurdev.marvel.dto.ComicDto;
import com.kurdev.marvel.entity.Character;
import com.kurdev.marvel.exeption.ResourceNotFoundExeption;
import com.kurdev.marvel.mapper.CharacterMapper;
import com.kurdev.marvel.mapper.ComicMapper;
import com.kurdev.marvel.repo.CharacterRepo;
import com.kurdev.marvel.repo.ComicRepo;
import com.kurdev.marvel.service.CharacterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@AllArgsConstructor
public class CharacterServiceImpl implements CharacterService {

    private CharacterRepo characterRepo;

    @Override
    public CharacterDto createCharacter(CharacterDto characterDto) {

        Character character = CharacterMapper.mapToCharacter(characterDto);
        Character saveCharacter = characterRepo.save(character);
        return CharacterMapper.mapToCharacterDto(saveCharacter);
    }

    @Override
    public CharacterDto getCharacterById(Long characterId) {
        Character character = characterRepo.findById(characterId)
                .orElse(null);
        return CharacterMapper.mapToCharacterDto(character);
    }

    @Override
    public List<ComicDto> getAllComicsByCharacterId(Long characterId) {
        if (!characterRepo.existsById(characterId)) {
            throw new IllegalArgumentException("Не существет персонажа с такий id = " + characterId);
        }
        return characterRepo.findComicByCharacterId(characterId)
                .stream().map(ComicMapper::mapToComicDto)
                .collect(Collectors.toList());
    }
}
