package com.example.demo.domain.schemas;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;

@Entity
public class UserImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Lob
    private byte[] image;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    @JsonBackReference // Este lado de la relación es el "opuesto" y no se serializa
    private UserEntity user;

    // Getters y setters
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    // tostring
    @Override
    public String toString() {
        return "UserImageEntity [id=" + id + ", image=" + image + ", user=" + user.getId() + "]";
    }
}