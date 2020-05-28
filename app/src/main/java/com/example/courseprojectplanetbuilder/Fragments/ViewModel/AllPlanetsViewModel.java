package com.example.courseprojectplanetbuilder.Fragments.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.courseprojectplanetbuilder.Model.Planet;
import com.example.courseprojectplanetbuilder.Repository.PlanetRepository;

import java.util.ArrayList;

public class AllPlanetsViewModel extends ViewModel {
    private final PlanetRepository planetRepository;

    public AllPlanetsViewModel() {
        this.planetRepository = PlanetRepository.getInstance();
    }

    public LiveData<ArrayList<Planet>> getAllPlanetsLiveData(){
        return planetRepository.getPlanetLiveData();
    }

    public void setCurrentPlanet(Planet planet){
        planetRepository.setCurrentPlanet(planet);
    }
}
