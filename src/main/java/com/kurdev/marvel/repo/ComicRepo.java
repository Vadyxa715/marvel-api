package com.kurdev.marvel.repo;

import com.kurdev.marvel.entity.Comic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComicRepo extends JpaRepository<Comic, Long> {
}
