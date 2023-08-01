package com.kurdev.marvel.model.list;

import com.kurdev.marvel.model.summary.ComicSummary;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;

@EqualsAndHashCode(callSuper = true)

@Data
public class ComicList extends ListBase {
    private ArrayList<ComicSummary> items;
}
