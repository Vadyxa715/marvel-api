package com.kurdev.marvel.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "comic")
public class Comic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "issue_number")
    private double issueNumber;

    @Column(name = "description")
    private String description;

    @Column(name = "page_count")
    private int pageCount;

    @ManyToMany
    @JoinTable(
            name = "characters_comics",
            joinColumns = @JoinColumn(name = "comic_id"),
            inverseJoinColumns = @JoinColumn(name = "character_id"))
      private List<Character> characters;

    public Comic(Long id, String title, double issueNumber, String description, int pageCount) {
        this.id = id;
        this.title = title;
        this.issueNumber = issueNumber;
        this.description = description;
        this.pageCount = pageCount;
    }
}
