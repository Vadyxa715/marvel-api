package com.kurdev.marvel.model;

import lombok.Data;

import java.util.List;

@Data
public class CharacterDataContainer {
    private int offset;
    private int limit ;
    private int total ;
    private int count;
    private List<Character> result;

}
