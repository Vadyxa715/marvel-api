package com.kurdev.marvel.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class ComicDataContainer {
    private int offset;
    private int limit;
    private int total;
    private int count;
    private ArrayList<Comic> results;
}
