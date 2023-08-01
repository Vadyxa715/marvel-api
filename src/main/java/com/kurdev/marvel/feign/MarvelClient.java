package com.kurdev.marvel.feign;

import com.kurdev.marvel.model.character.CharacterDataWrapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("marvel")
public interface MarvelClient {
    @RequestMapping(method = RequestMethod.GET, value = "/v1/public/characters")
    CharacterDataWrapper getCharacters();

    @RequestMapping(method = RequestMethod.GET, value = "/v1/public/characters/{characterId}")
    CharacterDataWrapper getCharacterById(@PathVariable String id);

    @RequestMapping(method = RequestMethod.GET, value = "/v1/public/characters/{characterId}/comics")
    CharacterDataWrapper getComicsByCharacterId(@PathVariable String id);

    @RequestMapping(method = RequestMethod.GET, value = "/v1/public/comics")
    CharacterDataWrapper getComics();

    @RequestMapping(method = RequestMethod.GET, value = "/v1/public/comics/{comicId}")
    CharacterDataWrapper getComicsById(@PathVariable String id);

    @RequestMapping(method = RequestMethod.GET, value = "/v1/public/comics/{comicId}/characters")
    CharacterDataWrapper getCharactersByComicId(@PathVariable String id);

}
