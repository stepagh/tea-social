package com.dadaev.tea_social.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "reviews")
public class Review {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String reviewText;
    @Column(nullable = false)
    private Integer rating;
    @Column(nullable = false)
    private LocalDate createdAt;
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @JoinColumn(name = "tea_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Tea tea;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    @Embedded
    private ReviewTag tags;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDate.now();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Review review)) return false;
        return id != null && id.equals(review.getId());
    }

    @Override
    public int hashCode() {
        return this.getClass().hashCode();
    }
}
