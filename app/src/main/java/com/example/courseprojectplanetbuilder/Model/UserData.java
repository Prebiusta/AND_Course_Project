package com.example.courseprojectplanetbuilder.Model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_data")
public class UserData {
    @PrimaryKey
    @NonNull
    private String uid;
    private int finishedPlanets;
    private int totalClicks;

    public UserData(String uid, int finishedPlanets, int totalClicks) {
        this.uid = uid;
        this.finishedPlanets = finishedPlanets;
        this.totalClicks = totalClicks;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getFinishedPlanets() {
        return finishedPlanets;
    }

    public void setFinishedPlanets(int finishedPlanets) {
        this.finishedPlanets = finishedPlanets;
    }

    public int getTotalClicks() {
        return totalClicks;
    }

    public void setTotalClicks(int totalClicks) {
        this.totalClicks = totalClicks;
    }
}
