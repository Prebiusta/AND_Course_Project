package com.example.courseprojectplanetbuilder.DAO;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.example.courseprojectplanetbuilder.Model.FirebaseReferenceLiveData;
import com.example.courseprojectplanetbuilder.Model.Planet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class PlanetRemoteDAO {
    private static final String TAG = "PlanetRemoteDAO";
    private static final DatabaseReference DATABASE_REF = FirebaseDatabase.getInstance().getReference("planets");
    private static PlanetRemoteDAO instance;
    private final LiveData<ArrayList<Planet>> planetLiveData;

    private PlanetRemoteDAO() {
        FirebaseReferenceLiveData liveData = new FirebaseReferenceLiveData(DATABASE_REF);
        planetLiveData = Transformations.map(liveData, new Deserializer());
    }

    public static PlanetRemoteDAO getInstance() {
        if (instance == null)
            instance = new PlanetRemoteDAO();
        return instance;
    }

    public void createPlanet(Planet newPlanet) {
        System.out.println(newPlanet);
        DATABASE_REF.push().setValue(newPlanet);
    }

    @NonNull
    public LiveData<ArrayList<Planet>> getPlanetLiveData() {
        return planetLiveData;
    }

    private class Deserializer implements Function<DataSnapshot, ArrayList<Planet>> {
        @Override
        public ArrayList<Planet> apply(DataSnapshot input) {
            ArrayList<Planet> allPlanets = new ArrayList<>();

            for (DataSnapshot planet : input.getChildren()) {
                Planet newPlanet = planet.getValue(Planet.class);
                newPlanet.setId(planet.getKey());
                Log.i(TAG, "Planet from firebase: " + newPlanet.toString());
                allPlanets.add(newPlanet);
            }

            return allPlanets;
        }
    }
}
