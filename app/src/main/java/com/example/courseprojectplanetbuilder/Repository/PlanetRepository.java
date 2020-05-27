package com.example.courseprojectplanetbuilder.Repository;

import com.example.courseprojectplanetbuilder.DAO.PlanetRemoteDAO;
import com.example.courseprojectplanetbuilder.Model.Planet;

public class PlanetRepository {
    private final PlanetRemoteDAO planetRemoteDAO;

    private static PlanetRepository instance;

    private PlanetRepository(){
        planetRemoteDAO = PlanetRemoteDAO.getInstance();
    }

    public static PlanetRepository getInstance() {
        if (instance == null)
            instance = new PlanetRepository();
        return instance;
    }

    public void createPlanet(Planet newPlanet){
        planetRemoteDAO.createPlanet(newPlanet);

    }
}
