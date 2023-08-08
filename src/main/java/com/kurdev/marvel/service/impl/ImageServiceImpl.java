package com.kurdev.marvel.service.impl;

import com.kurdev.marvel.dto.ImageDto;
import com.kurdev.marvel.entity.Character;
import com.kurdev.marvel.entity.Image;
import com.kurdev.marvel.mapper.ImageMapper;
import com.kurdev.marvel.repo.CharacterRepo;
import com.kurdev.marvel.repo.ImageRepo;
import com.kurdev.marvel.service.ImageService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Transactional
@Service
@AllArgsConstructor
public class ImageServiceImpl implements ImageService {

    private ImageRepo imageRepo;
    private CharacterRepo characterRepo;

    @Override
    public ImageDto createImage(MultipartFile file, Long characterId) throws IOException {
        if (file.getOriginalFilename() == null || file.isEmpty() || file.getOriginalFilename().isEmpty()) {
            throw new IllegalArgumentException("Некорректный файл.");
        }
        if (characterRepo.existsById(characterId)) {
            throw new IllegalArgumentException("Персонажа с таким ID: " + characterId + " не существует.");
        }
        Image image = new Image();
        image.setName(file.getName());
        image.setExtension(file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1));
        image.setImageData(file.getBytes());
        Image saveImage = imageRepo.save(image);

        Character characterWithImage = characterRepo.findById(characterId).get();
        characterWithImage.setImage(image);
        characterRepo.save(characterWithImage);

        return ImageMapper.mapToImageDto(saveImage);
    }

    @Override
    public ImageDto getImageById(Long imageId) {
        Image image = imageRepo.findById(imageId)
                .orElse(null);
        return ImageMapper.mapToImageDto(image);
    }
}
