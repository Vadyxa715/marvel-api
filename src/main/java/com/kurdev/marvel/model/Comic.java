package com.kurdev.marvel.model;

        import com.kurdev.marvel.model.list.CharacterList;
        import com.kurdev.marvel.model.list.CreatorList;
        import com.kurdev.marvel.model.list.EventList;
        import com.kurdev.marvel.model.list.StoryList;
        import com.kurdev.marvel.model.summary.ComicSummary;
        import com.kurdev.marvel.model.summary.SeriesSummary;
        import lombok.Data;

        import java.util.ArrayList;
        import java.util.Date;

@Data
public class Comic {
    private int id;
    private int digitalId;
    private String title;
    private double issueNumber;
    private String variantDescription;
    private String description;
    private Date modified;
    private String isbn;
    private String upc;
    private String diamondCode;
    private String ean;
    private String issn;
    private String format;
    private int pageCount;
    private ArrayList<TextObject> textObjects;
    private String resourceURI;
    private ArrayList<Url> urls;
    private SeriesSummary series;
    private ArrayList<ComicSummary> variants;
    private ArrayList<ComicSummary> collections;
    private ArrayList<ComicSummary> collectedIssues;
    private ArrayList<ComicDate> dates;
    private ArrayList<ComicPrice> prices;
    private Image thumbnail;
    private ArrayList<Image> images;
    private CreatorList creators;
    private CharacterList characters;
    private StoryList stories;
    private EventList events;
}
