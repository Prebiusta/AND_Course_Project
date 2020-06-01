package com.example.courseprojectplanetbuilder.Fragments.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.courseprojectplanetbuilder.Model.UserData;
import com.example.courseprojectplanetbuilder.Repository.PlanetRepository;

public class ProfileViewModel extends AndroidViewModel {
    private final PlanetRepository planetRepository;

    public ProfileViewModel(Application application) {
        super(application);
        this.planetRepository = PlanetRepository.getInstance(application);
    }

    public LiveData<UserData> getCurrentUserData(){
        return planetRepository.getUserData();
    }
}
