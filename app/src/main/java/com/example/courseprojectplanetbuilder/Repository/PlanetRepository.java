package com.example.courseprojectplanetbuilder.Repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.courseprojectplanetbuilder.DAO.PlanetRemoteDAO;
import com.example.courseprojectplanetbuilder.Model.Planet;

import java.util.ArrayList;

public class PlanetRepository {
    private final PlanetRemoteDAO planetRemoteDAO;

    private MutableLiveData<Planet> currentPlanet;

    private static PlanetRepository instance;

    private PlanetRepository(){
        planetRemoteDAO = PlanetRemoteDAO.getInstance();
        currentPlanet = new MutableLiveData<>();
    }

    public static PlanetRepository getInstance() {
        if (instance == null)
            instance = new PlanetRepository();
        return instance;
    }

    public void createPlanet(Planet newPlanet){
        planetRemoteDAO.createPlanet(newPlanet);

    }

    public LiveData<ArrayList<Planet>> getPlanetLiveData() {
        return planetRemoteDAO.getPlanetLiveData();
    }

    public void setCurrentPlanet(Planet planet){
        this.currentPlanet.setValue(planet);
    }

    public LiveData<Planet> getCurrentPlanet(){
        return this.currentPlanet;
    }

    public void updateCurrentPlanetProgress(int amount) {
        Planet updatingPlanet = currentPlanet.getValue();
        int newSize = updatingPlanet.getCurrentSize() + amount;
        updatingPlanet.setCurrentSize(newSize);
        currentPlanet.setValue(updatingPlanet);
    }
}
