package com.example.courseprojectplanetbuilder.Fragments.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.courseprojectplanetbuilder.Model.Planet;
import com.example.courseprojectplanetbuilder.Repository.PlanetRepository;

import java.util.ArrayList;
import java.util.List;

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

    public ArrayList<Planet> getNotCompletedPlanets(ArrayList<Planet> remotePlanets) {
        List<String> completedPlanetIds = planetRepository.getCompletedPlanetIdsForCurrentUser();
        ArrayList<Planet> planetsCopy = new ArrayList<>();

        for (Planet planetToCopy : remotePlanets)
            planetsCopy.add(new Planet(planetToCopy.getId(), planetToCopy.getName(), planetToCopy.getAuthor(), planetToCopy.getMaxSize()));

        for (String completedPlanetId : completedPlanetIds){
            for (Planet remotePlanet : planetsCopy){
                if (remotePlanet.getId().equals(completedPlanetId))
                    remotePlanet.setCompleted(true);
            }
        }

        return planetsCopy;
    }
}
