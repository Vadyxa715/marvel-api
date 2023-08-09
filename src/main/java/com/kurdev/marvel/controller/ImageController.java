package com.kurdev.marvel.controller;

import com.kurdev.marvel.dto.ImageDto;
import com.kurdev.marvel.service.CharacterService;
import com.kurdev.marvel.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/public/images")
public class ImageController {

    private ImageService imageService;
    private CharacterService characterService;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<?> getImage(@PathVariable Long id) {
        ImageDto imageDto = imageService.getImageById(id);
        if (imageDto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Картинки с таким id : " + id + " не существует.");
        }
        return new ResponseEntity<>(imageDto, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createImage(@RequestParam Long characterId,
                                         @RequestPart("file") MultipartFile file) throws IOException {
        ImageDto saveImage = imageService.createImage(file, characterId);
        if (saveImage == null || saveImage.getId() == null) {
            return ResponseEntity.badRequest().body("Пустые поля. Не удалось загрузить картинку.");
        }
        return new ResponseEntity<>(saveImage, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/show/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> get(@PathVariable("id") Long characterId) throws IllegalArgumentException{

        ImageDto imageDto = imageService.getImageByCharacterId(characterId);
        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(MediaType.IMAGE_JPEG_VALUE))
                .body(imageDto.getImageData());
    }
}
