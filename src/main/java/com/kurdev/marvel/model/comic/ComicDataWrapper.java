package com.kurdev.marvel.model.comic;

import lombok.Data;

@Data
public class ComicDataWrapper {
    private int code;
    private String status;
    private String copycight;
    private String attributionText;
    private String attributionHTML;
    private ComicDataContainer data;
    private String etag;
}
