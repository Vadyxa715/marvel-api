package com.kurdev.marvel.model.character;

import lombok.Data;

@Data
public class CharacterDataWrapper {
    private int code;
    private String status ;
    private String copyright;
    private String attributionText;
    private String attributionHTML;
    private CharacterDataContainer data;
    private String etag;
}
