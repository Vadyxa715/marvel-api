package com.kurdev.marvel.repo;

import com.kurdev.marvel.entity.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepo extends JpaRepository<Character, Long> {
    @Query(value = "select c.* from characters c join characters_comics cc on cc.character_id = c.id " +
            " where cc.comic_id = ?1", nativeQuery = true)
    List<Character> findByComicId(Long comicId);
}
