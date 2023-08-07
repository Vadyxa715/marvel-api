package com.kurdev.marvel.repo;

import com.kurdev.marvel.entity.Character;
import com.kurdev.marvel.entity.Comic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComicRepo extends JpaRepository<Comic, Long> {

    @Query(value = "select c.* from characters c join characters_comics cc on cc.character_id = c.id " +
            " where cc.comic_id = ?1",nativeQuery = true)
    List<Character> findCharacterByComicId(Long comicId);

    @Query(value = "select c.* from comic c join characters_comics cc on cc.comic_id = c.id " +
            " where cc.character_id = ?1",nativeQuery = true)
    List<Comic> findByCharacterId(Long characterId);
}
