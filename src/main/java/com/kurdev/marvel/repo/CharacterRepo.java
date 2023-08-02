package com.kurdev.marvel.repo;

import com.kurdev.marvel.entity.Character;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepo extends JpaRepository<Character, Long> {
}
