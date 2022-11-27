package com.example.demo.dto;

import java.io.Serializable;
import java.util.Date;

public class CommentDTO implements Serializable {

    private String text;
    private Date date;
    private AuthorDTO arthor;

    public CommentDTO() {
    }

    public CommentDTO(String text, Date date, AuthorDTO arthor) {
        this.text = text;
        this.date = date;
        this.arthor = arthor;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public AuthorDTO getArthor() {
        return arthor;
    }

    public void setArthor(AuthorDTO arthor) {
        this.arthor = arthor;
    }
}
