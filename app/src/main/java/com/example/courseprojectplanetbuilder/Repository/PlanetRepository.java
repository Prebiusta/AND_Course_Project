package com.example.courseprojectplanetbuilder.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.courseprojectplanetbuilder.DAO.PlanetRemoteDAO;
import com.example.courseprojectplanetbuilder.DAO.UserDataDAO;
import com.example.courseprojectplanetbuilder.DAO.UserDataDatabase;
import com.example.courseprojectplanetbuilder.Model.Planet;
import com.example.courseprojectplanetbuilder.Model.UserData;

import java.util.ArrayList;

public class PlanetRepository {
    private final PlanetRemoteDAO planetRemoteDAO;
    private final UserDataDAO userDataDAO;

    private MutableLiveData<Planet> currentPlanet;

    private static PlanetRepository instance;

    private PlanetRepository(Application application){
        UserDataDatabase localDatabase = UserDataDatabase.getInstance(application);
        planetRemoteDAO = PlanetRemoteDAO.getInstance();
        userDataDAO = localDatabase.userDataDAO();
        currentPlanet = new MutableLiveData<>();
    }

    public static PlanetRepository getInstance(Application application) {
        if (instance == null)
            instance = new PlanetRepository(application);
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

        if (newSize <= updatingPlanet.getMaxSize()) {
            updatingPlanet.setCurrentSize(newSize);
        }

        if (newSize == updatingPlanet.getMaxSize()){
            updatingPlanet.setCompleted(true);
        }

        currentPlanet.setValue(updatingPlanet);
    }

    public void resetCurrentPlanet() {
        currentPlanet = new MutableLiveData<>();
    }

    public void saveUserData(UserData userData) {
        new UpdateUserDataAsync(userDataDAO).execute(userData);
    }

    private class InsertUserDataAsync extends AsyncTask<UserData, Void, Void> {
        private UserDataDAO userDataDAO;

        public InsertUserDataAsync(UserDataDAO userDataDAO) {
            this.userDataDAO = userDataDAO;
        }

        @Override
        protected Void doInBackground(UserData... userData) {
            userDataDAO.insert(userData[0]);
            return null;
        }
    }

    private class UpdateUserDataAsync extends AsyncTask<UserData, Void, Void> {
        private UserDataDAO userDataDAO;

        public UpdateUserDataAsync(UserDataDAO userDataDAO) {
            this.userDataDAO = userDataDAO;
        }

        @Override
        protected Void doInBackground(UserData... userData) {
            userDataDAO.update(userData[0]);
            return null;
        }
    }
}
