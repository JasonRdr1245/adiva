package com.example.demo.domain.schemas;

import com.example.demo.domain.enums.AdvisorType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class AdvisorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
     @Enumerated(EnumType.STRING)
    private AdvisorType type;

    private String certificate;
    private String documentType;
    private String identifyCode;
    @OneToOne(mappedBy = "advisorEntity")
    private UserEntity userEntity;
    public AdvisorEntity() {
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCertificate() {
        return certificate;
    }
    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }
    public String getDocumentType() {
        return documentType;
    }
    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }
    public String getIdentifyCode() {
        return identifyCode;
    }
    public void setIdentifyCode(String identifyCode) {
        this.identifyCode = identifyCode;
    }
    public UserEntity getUserEntity() {
        return userEntity;
    }
    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
    public AdvisorType getType() {
        return type;
    }
    public void setType(AdvisorType type) {
        this.type = type;
    }
    @Override
    public String toString() {
        return "AdvisorEntity [id=" + id + ", type=" + type + ", certificate=" + certificate + ", documentType="
                + documentType + ", identifyCode=" + identifyCode + "]";
    }
}
