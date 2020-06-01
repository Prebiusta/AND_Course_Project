package com.example.courseprojectplanetbuilder.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.courseprojectplanetbuilder.Model.LocalStorage.UserData;

@Dao
public interface UserDataDAO {
    @Insert
    void insert(UserData userData);

    @Update
    void update(UserData userData);

    @Delete
    void delete(UserData userData);

    @Query("SELECT * FROM user_data WHERE uid = :userId")
    LiveData<UserData> getUserLiveData(String userId);

    @Query("SELECT * FROM user_data WHERE uid = :userId")
    UserData getUserData(String userId);
}
