package com.kurdev.marvel.model.list;

import com.kurdev.marvel.model.StorySummary;
import com.kurdev.marvel.model.list.ListBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;

@EqualsAndHashCode(callSuper = true)

@Data
public class StoryList extends ListBase {
    private ArrayList<StorySummary> items;
}
