package com.kurdev.marvel.controller;

import com.kurdev.marvel.dto.ImageDto;
import com.kurdev.marvel.service.CharacterService;
import com.kurdev.marvel.service.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/v1/public/images", produces = "application/json; charset=utf-8")
//Распростроненная проблема для Swagger3 слетает кодировка
public class ImageController {

    private ImageService imageService;
    private CharacterService characterService;

    @Operation(summary = "Получить информацию о картинке по id картинки")
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<?> getImage(@PathVariable Long id) {
        ImageDto imageDto = imageService.getImageById(id);
        if (imageDto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Картинки с таким id : " + id + " не существует.");
        }
        return new ResponseEntity<>(imageDto, HttpStatus.OK);
    }

    @Operation(summary = "Загрузить картинку для персонажа по id персонажа")
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

    @Operation(summary = "Посмотреть картинку персонажа по id персонажа")
    @RequestMapping(method = RequestMethod.GET, value = "/show/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Long characterId) {

        if (!characterService.existsById(characterId)) {
            return ResponseEntity.badRequest().body("Персонаж с таким \"ID : " + characterId + "\" не найден.");
        }
        ImageDto imageDto = imageService.getImageByCharacterId(characterId);

        if (imageDto == null || imageDto.getImageData() == null) {
            return ResponseEntity.badRequest().body("У персонажа с таким \"ID : " + characterId + "\" нет картинки.");
        }
        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(MediaType.IMAGE_JPEG_VALUE))
                .body(imageDto.getImageData());
    }
}
