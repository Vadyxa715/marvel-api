package com.kurdev.marvel.service.impl;

import com.kurdev.marvel.dto.CharacterDto;
import com.kurdev.marvel.dto.ComicDto;
import com.kurdev.marvel.entity.Character;
import com.kurdev.marvel.mapper.CharacterMapper;
import com.kurdev.marvel.mapper.ComicMapper;
import com.kurdev.marvel.repo.CharacterRepo;
import com.kurdev.marvel.repo.ComicRepo;
import com.kurdev.marvel.service.CharacterService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@AllArgsConstructor
public class CharacterServiceImpl implements CharacterService {

    private CharacterRepo characterRepo;
    private ComicRepo comicRepo;

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
            throw new IllegalArgumentException("Не существет персонажа с таким id = " + characterId);
        }
        //return characterRepo.findComicByCharacterId(characterId)
        return comicRepo.findByCharacterId(characterId)
                .stream().map(ComicMapper::mapToComicDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsById(Long characterId) {
        return characterRepo.existsById(characterId);
    }

    @Override
    public void addComic(Long comicId, Long characterId) {
        var characterOptional =  characterRepo.findById(characterId);
        var comicOptional = comicRepo.findById(comicId);
        if(characterOptional.isPresent() && comicOptional.isPresent()){
            Character character = characterOptional.get();
            character.getComics().add(comicOptional.get());
            characterRepo.save(character);
        }
    }
}
