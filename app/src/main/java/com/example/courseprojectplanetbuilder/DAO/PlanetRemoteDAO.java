package com.example.courseprojectplanetbuilder.DAO;

import com.example.courseprojectplanetbuilder.Model.Planet;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PlanetRemoteDAO {
    private static final DatabaseReference DATABASE_REF = FirebaseDatabase.getInstance().getReference("planets");
    private static PlanetRemoteDAO instance;

    private PlanetRemoteDAO() {
    }

    public static PlanetRemoteDAO getInstance() {
        if (instance == null)
            instance = new PlanetRemoteDAO();
        return instance;
    }

    public void createPlanet(Planet newPlanet){
        DATABASE_REF.push().setValue(newPlanet);
    }

}
