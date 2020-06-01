package com.example.courseprojectplanetbuilder.Model.LocalStorage;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_planet")
public class UserFinishedPlanet {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String userId;
    private String planetId;

    public UserFinishedPlanet() {
    }

    public UserFinishedPlanet(Long id, String userId, String planetId) {
        this.id = id;
        this.userId = userId;
        this.planetId = planetId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPlanetId() {
        return planetId;
    }

    public void setPlanetId(String planetId) {
        this.planetId = planetId;
    }
}
