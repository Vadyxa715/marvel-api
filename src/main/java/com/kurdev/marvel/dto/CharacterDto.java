package com.kurdev.marvel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CharacterDto {
    private Long id;
    @NonNull
    private String name;
    private String description;
}
