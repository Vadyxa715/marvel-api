package com.kurdev.marvel.mapper;

import com.kurdev.marvel.dto.ImageDto;
import com.kurdev.marvel.entity.Image;

public class ImageMapper {
    public static ImageDto mapToImageDto(Image image){
        return new ImageDto(
                image.getId(),
                image.getPath(),
                image.getExtension()
        );
    }

    public static Image mapToImage(ImageDto imageDto){
        return new Image(
                imageDto.getId(),
                imageDto.getPath(),
                imageDto.getExtension()
        );
    }
}