package com.kurdev.marvel.model.list;

import com.kurdev.marvel.model.summary.EventSummary;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;

@EqualsAndHashCode(callSuper = true)

@Data
public class EventList extends ListBase {
    private ArrayList<EventSummary> items;
}
