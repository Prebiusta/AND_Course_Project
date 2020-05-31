package com.example.courseprojectplanetbuilder.Fragments.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.courseprojectplanetbuilder.Model.Planet;
import com.example.courseprojectplanetbuilder.Model.UserData;
import com.example.courseprojectplanetbuilder.Repository.PlanetRepository;

public class CurrentPlanetViewModel extends AndroidViewModel {
    private final PlanetRepository planetRepository;

    public CurrentPlanetViewModel(Application application) {
        super(application);
        planetRepository = PlanetRepository.getInstance(application);
    }

    public LiveData<Planet> getCurrentPlanet(){
        return planetRepository.getCurrentPlanet();
    }

    public void updateCurrentPlanetProgress(int amount) {
        planetRepository.updateCurrentPlanetProgress(amount);
    }

    public void saveUserData(UserData userData) {
        planetRepository.saveUserData(userData);
    }
}
