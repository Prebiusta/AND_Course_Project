package com.example.courseprojectplanetbuilder.Fragments.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.courseprojectplanetbuilder.Model.Planet;
import com.example.courseprojectplanetbuilder.Repository.PlanetRepository;

import java.util.ArrayList;

public class AllPlanetsViewModel extends AndroidViewModel {
    private final PlanetRepository planetRepository;

    public AllPlanetsViewModel(Application application) {
        super(application);
        this.planetRepository = PlanetRepository.getInstance(application);
    }

    public LiveData<ArrayList<Planet>> getAllPlanetsLiveData(){
        return planetRepository.getPlanetLiveData();
    }

    public void setCurrentPlanet(Planet planet){
        planetRepository.setCurrentPlanet(planet);
    }
}
