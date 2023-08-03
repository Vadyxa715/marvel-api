package com.kurdev.marvel.service.impl;

import com.kurdev.marvel.dto.ImageDto;
import com.kurdev.marvel.entity.Image;
import com.kurdev.marvel.mapper.ImageMapper;
import com.kurdev.marvel.repo.ImageRepo;
import com.kurdev.marvel.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ImageServiceImpl implements ImageService {

    private ImageRepo imageRepo;
    @Override
    public ImageDto createImage(ImageDto imageDto) {
        Image image = ImageMapper.mapToImage(imageDto);
        Image saveImage = imageRepo.save(image);
        return ImageMapper.mapToImageDto(saveImage);
    }

    @Override
    public ImageDto getImageById(Long imageId) {
        Image image = imageRepo.findById(imageId)
                .orElse(null);
        return ImageMapper.mapToImageDto(image);
    }
}
