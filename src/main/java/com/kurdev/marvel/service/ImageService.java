package com.kurdev.marvel.service;

import com.kurdev.marvel.dto.ImageDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {

    ImageDto createImage(MultipartFile file, Long characterId) throws IOException;

    ImageDto getImageById(Long imageId);
}
