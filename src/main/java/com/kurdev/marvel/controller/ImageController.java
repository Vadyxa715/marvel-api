package com.kurdev.marvel.controller;

import com.kurdev.marvel.dto.ComicDto;
import com.kurdev.marvel.dto.ImageDto;
import com.kurdev.marvel.entity.Image;
import com.kurdev.marvel.service.ImageService;
import jakarta.persistence.Convert;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/public/images")
public class ImageController {

    private ImageService imageService;

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
    public ResponseEntity<byte[]> get(@PathVariable Long id) {

        ImageDto imageDto = imageService.getImageById(id);
        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(MediaType.IMAGE_JPEG_VALUE))
                .body(imageDto.getImageData());
    }
}
