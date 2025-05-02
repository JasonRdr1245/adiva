package com.example.demo.domain.schemas;

import com.example.demo.domain.enums.UserType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Enumerated(EnumType.STRING)
    private UserType type;
    private String lastName;
    private String password;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String phone;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "advisor_entity_id", nullable = true) // la FK puede ser null
    private AdvisorEntity advisorEntity;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserImageEntity userImage;

    // Getters y setters
    public UserImageEntity getUserImage() {
        return userImage;
    }

    public void setUserImage(UserImageEntity userImage) {
        this.userImage = userImage;
    }

    public UserEntity() {
    }

    public UserEntity(UserType type) {
        this.type = type;
    }

    public UserEntity(String name, UserType type, String lastName, String password, String email, String phone,
            AdvisorEntity advisorEntity) {
        this.name = name;
        this.type = type;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.advisorEntity = advisorEntity;
    }

    public int getId() {
        return id;
    }

    public void setId(long userId) {
        this.id = (int) userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public AdvisorEntity getAdvisorEntity() {
        return advisorEntity;
    }

    public void setAdvisorEntity(AdvisorEntity advisorEntity) {
        this.advisorEntity = advisorEntity;
    }
}
