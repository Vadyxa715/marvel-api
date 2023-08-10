package com.kurdev.marvel.service.impl;

import com.kurdev.marvel.dto.CharacterDto;
import com.kurdev.marvel.dto.ComicDto;
import com.kurdev.marvel.entity.Character;
import com.kurdev.marvel.mapper.CharacterMapper;
import com.kurdev.marvel.mapper.ComicMapper;
import com.kurdev.marvel.repo.CharacterRepo;
import com.kurdev.marvel.repo.ComicRepo;
import com.kurdev.marvel.service.CharacterService;
import jakarta.annotation.PostConstruct;
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

    @Override
    public List<CharacterDto> getAllCharacters() {
        List<Character> characterList = characterRepo.findAll();
        return characterList.stream().map(CharacterMapper::mapToCharacterDto)
                .collect(Collectors.toList());
    }

    @PostConstruct
    void initDb (){
        Character character = new Character(1L,"Капитан Америка","Воистину радостный звук");
        Character character1 = new Character(2L,"Карнаж","Высококачественный прототипние сомнения");
        Character character2 = new Character(3L,"Человек Паук","Органический трафик  сомнения");
        Character character3 = new Character(4L,"Капитан Марвел","Независимые СМИ потому ");
        Character character4 = new Character(5L,"Черная Вдова","Не следует забывать, что был совхоза");
        Character character5 = new Character(6L,"Доктор Стрендж","Частотность поисковых запросов ошибочной");
        Character character6 = new Character(7L,"Рассомаха","Оказывается, обучение задача");
        Character character7 = new Character(8L,"Халк","Инцидент не исчерпан: был сорван совхоза!");
        Character character8 = new Character(9L,"Тор","Франция намерена исследовать, меньше");
        Character character9 = new Character(10L,"Человек Муравей","Давайте не будем меньше");
        List<Character> characterList = List.of(character, character1, character2, character3,
                character4, character5, character6, character7, character8, character9);
        characterRepo.saveAll(characterList);
    }
}
