package com.kurdev.marvel.model.list;

import com.kurdev.marvel.model.summary.SeriesSummary;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;

@EqualsAndHashCode(callSuper = true)

@Data
public class SeriesList extends ListBase {
    private ArrayList<SeriesSummary> items;
}
