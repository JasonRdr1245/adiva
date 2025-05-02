package com.example.demo.domain.models;

import com.example.demo.domain.schemas.AdvisorEntity;
import com.example.demo.domain.schemas.UserEntity;

public class UserAdvisorForm {
    private UserEntity userEntity;
    private AdvisorEntity advisorEntity;
    
    // Getters and Setters
    public UserEntity getUserEntity() {
        return userEntity;
    }
    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
    public AdvisorEntity getAdvisorEntity() {
        return advisorEntity;
    }
    public void setAdvisorEntity(AdvisorEntity advisorEntity) {
        this.advisorEntity = advisorEntity;
    }
    @Override
    public String toString() {
        return "UserAdvisorForm{" +
                "userEntity=" + userEntity +
                ", advisorEntity=" + advisorEntity +
                '}';
    }
}