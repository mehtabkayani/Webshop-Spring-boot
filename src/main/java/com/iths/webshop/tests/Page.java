package com.iths.webshop.tests;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "pages")
@Data
@NoArgsConstructor
public class Page {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 2, message = ("Title must be at least 2 character long"))
    private String title;

    private String url;

    @Size(min = 5, message = ("Content must be at least 5 character long"))
    private String content;
}
