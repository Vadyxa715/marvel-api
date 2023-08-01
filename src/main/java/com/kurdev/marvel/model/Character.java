package com.kurdev.marvel.model;

import com.kurdev.marvel.model.list.ComicList;
import com.kurdev.marvel.model.list.EventList;
import com.kurdev.marvel.model.list.SeriesList;
import com.kurdev.marvel.model.list.StoryList;
import lombok.Data;


import java.util.Date;
import java.util.List;

@Data
public class Character {
    private int id;
    private String name;
    private String description;
    private Date modified;
    private String resourceURI;
    private List<Url> urls;
    private Image thumbnail;
    private List<ComicList> comics;
    private List<StoryList> stories;
    private List<EventList> events;
    private List<SeriesList> series;
}
