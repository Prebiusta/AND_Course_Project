package com.example.courseprojectplanetbuilder.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.courseprojectplanetbuilder.Model.LocalStorage.UserFinishedPlanet;

import java.util.List;

@Dao
public interface UserFinishedPlanetsDAO {
    @Insert
    void insert(UserFinishedPlanet userFinishedPlanet);

    @Update
    void update(UserFinishedPlanet userFinishedPlanet);

    @Delete
    void delete(UserFinishedPlanet userFinishedPlanet);

    @Query("SELECT * FROM user_planet WHERE userId = :userId")
    List<UserFinishedPlanet> getAllFinishedPlanetsForUser(String userId);
}
