package com.kurdev.marvel.mapper;

import com.kurdev.marvel.dto.ImageDto;
import com.kurdev.marvel.entity.Image;

public class ImageMapper {
    public static ImageDto mapToImageDto(Image image) {
        if (image == null) return null;
        return new ImageDto(
                image.getId(),
                image.getName(),
                image.getExtension(),
                image.getImageData()
        );
    }

    public static Image mapToImage(ImageDto imageDto) {
        if (imageDto == null) return null;
        return new Image(
                imageDto.getId(),
                imageDto.getName(),
                imageDto.getExtension(),
                imageDto.getImageData()
        );
    }
}