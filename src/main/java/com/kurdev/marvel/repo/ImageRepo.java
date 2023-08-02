package com.kurdev.marvel.repo;

import com.kurdev.marvel.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepo extends JpaRepository<Image, Long> {
}
