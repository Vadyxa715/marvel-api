package com.kurdev.marvel.model.summary;

import com.kurdev.marvel.model.summary.Summary;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CharacterSummary extends Summary {
    private String role;
}
