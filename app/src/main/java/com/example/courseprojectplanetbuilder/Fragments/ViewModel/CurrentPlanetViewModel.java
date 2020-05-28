package com.example.courseprojectplanetbuilder.Fragments.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.courseprojectplanetbuilder.Model.Planet;
import com.example.courseprojectplanetbuilder.Repository.PlanetRepository;

public class CurrentPlanetViewModel extends ViewModel {
    private final PlanetRepository planetRepository;

    public CurrentPlanetViewModel() {
        planetRepository = PlanetRepository.getInstance();
    }

    public LiveData<Planet> getCurrentPlanet(){
        return planetRepository.getCurrentPlanet();
    }

    public void updateCurrentPlanetProgress(int amount) {
        planetRepository.updateCurrentPlanetProgress(amount);
    }
}
