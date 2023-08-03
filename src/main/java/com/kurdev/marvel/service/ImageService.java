package com.kurdev.marvel.service;

import com.kurdev.marvel.dto.ImageDto;

public interface ImageService {

    ImageDto createImage(ImageDto imageDto);

    ImageDto getImageById(Long imageId);
}
