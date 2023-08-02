package com.kurdev.marvel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComicDto {
    private Long id;
    private String title;
    private double issueNumber;
    private String description;
    private int pageCount;
}
