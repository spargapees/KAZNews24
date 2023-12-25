package com.spargapees.newsportal.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "t_news")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "post_date")
    private String time;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @ManyToMany(mappedBy = "news")
    List<NewsCategories> categories;

    @OneToMany(mappedBy = "news", cascade = CascadeType.ALL)
    List<Comments> comments;

    public List<Comments> getReversedComments() {
        List<Comments> comments = this.getComments();
        Collections.reverse(comments);
        return comments;
    }
}
