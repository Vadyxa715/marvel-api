package com.kurdev.marvel.model.list;

import com.kurdev.marvel.model.summary.CreatorSummary;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;

@EqualsAndHashCode(callSuper = true)

@Data
public class CreatorList extends ListBase {
    private ArrayList<CreatorSummary> items;
}
