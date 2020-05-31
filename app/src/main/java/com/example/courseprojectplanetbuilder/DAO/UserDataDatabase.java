package com.example.courseprojectplanetbuilder.DAO;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.courseprojectplanetbuilder.Model.UserData;

@Database(entities = {UserData.class}, version = 1)
public abstract class UserDataDatabase extends RoomDatabase {

    private static UserDataDatabase instance;
    public abstract UserDataDAO userDataDAO();

    public static synchronized UserDataDatabase getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    UserDataDatabase.class, "user_data_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
