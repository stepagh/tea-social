package com.dadaev.tea_social.model;


import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable()
public class ReviewTag {
    String teaType;
    String year;
    String origin;
}