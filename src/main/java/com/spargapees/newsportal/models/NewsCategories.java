package com.spargapees.newsportal.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "t_news_categories")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NewsCategories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "t_news_newCategories",
            joinColumns = { @JoinColumn(name = "category_id")},
            inverseJoinColumns = {@JoinColumn(name = "news_id")}
    )
    List<News> news;
}
