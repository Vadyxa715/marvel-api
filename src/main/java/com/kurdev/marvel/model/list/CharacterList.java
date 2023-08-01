package com.kurdev.marvel.model.list;

import com.kurdev.marvel.model.summary.CharacterSummary;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;

@EqualsAndHashCode(callSuper = true)
@Data
public class CharacterList extends ListBase {
    private ArrayList<CharacterSummary> items;
}
