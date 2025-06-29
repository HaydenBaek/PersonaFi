package com.personafi.backend.model;

public class PersonalityType {

    private String type;
    private String description;
    private String tips;


    public PersonalityType(String type, String description, String tips) {
        this.type = type;
        this.description = description;
        this.tips = tips;
    }


    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getTips() {
        return tips;
    }


    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }
}
