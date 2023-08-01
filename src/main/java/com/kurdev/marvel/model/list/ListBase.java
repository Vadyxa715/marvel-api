package com.kurdev.marvel.model.list;

import lombok.Data;

@Data
public class ListBase {
    private int available;
    private int returned;
    private String collectionURI;
}
