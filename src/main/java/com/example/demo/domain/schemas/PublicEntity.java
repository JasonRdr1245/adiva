package com.example.demo.domain.schemas;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class PublicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userOwned;
    // not null
    @NotNull
    private Long likes;
    // not null
    @NotNull
    private String comments;
    // not null
    @NotNull
    private String type;
    // fecha
    @NotNull
    private String date;

    // imagen en bytes
    // not null
    @NotNull
    @Lob
    private byte[] image;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUserOwned() {
        return userOwned;
    }

    public void setUserOwned(UserEntity userOwned) {
        this.userOwned = userOwned;
    }

    public Long getLikes() {
        return likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public byte[] getImage() {
        return image;
    }

    public String getImageUrl() {
        if (image != null) {
            return "data:image/png;base64," + java.util.Base64.getEncoder().encodeToString(image);
        }
        return "";
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
    // tostring

    @Override
    public String toString() {
        return "PublicEntity [id=" + id + ", userOwned=" + userOwned.getId() + ", likes=" + likes + ", comments="
                + comments + ", type=" + type + ", date=" + date + "]";
    }
}
