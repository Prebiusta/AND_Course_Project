package com.example.courseprojectplanetbuilder.Repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.courseprojectplanetbuilder.DAO.PlanetRemoteDAO;
import com.example.courseprojectplanetbuilder.DAO.UserDataDAO;
import com.example.courseprojectplanetbuilder.DAO.UserDataDatabase;
import com.example.courseprojectplanetbuilder.DAO.UserFinishedPlanetsDAO;
import com.example.courseprojectplanetbuilder.Fragments.View.AppLayout;
import com.example.courseprojectplanetbuilder.Model.LocalStorage.UserData;
import com.example.courseprojectplanetbuilder.Model.LocalStorage.UserFinishedPlanet;
import com.example.courseprojectplanetbuilder.Model.Planet;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class PlanetRepository {
    private static final String TAG = "PlanetRepository";
    private static PlanetRepository instance;
    private final PlanetRemoteDAO planetRemoteDAO;
    private final UserDataDAO userDataDAO;
    private final UserFinishedPlanetsDAO userFinishedPlanetsDAO;
    private MutableLiveData<Planet> currentPlanet;

    private PlanetRepository(Application application) {
        UserDataDatabase localDatabase = UserDataDatabase.getInstance(application);
        planetRemoteDAO = PlanetRemoteDAO.getInstance();

        userDataDAO = localDatabase.userDataDAO();
        userFinishedPlanetsDAO = localDatabase.userFinishedPlanetsDAO();

        currentPlanet = new MutableLiveData<>();
    }

    public static PlanetRepository getInstance(Application application) {
        if (instance == null)
            instance = new PlanetRepository(application);
        return instance;
    }

    public void createPlanet(Planet newPlanet) {
        planetRemoteDAO.createPlanet(newPlanet);

    }

    public LiveData<ArrayList<Planet>> getPlanetLiveData() {
        return planetRemoteDAO.getPlanetLiveData();
    }

    public LiveData<Planet> getCurrentPlanet() {
        return this.currentPlanet;
    }

    public void setCurrentPlanet(Planet planet) {
        this.currentPlanet.setValue(planet);
    }

    public void updateCurrentPlanetProgress(int amount) {
        Planet updatingPlanet = currentPlanet.getValue();
        int newSize = updatingPlanet.getCurrentSize() + amount;

        if (newSize <= updatingPlanet.getMaxSize()) {
            updatingPlanet.setCurrentSize(newSize);
        }

        if (newSize == updatingPlanet.getMaxSize()) {
            updateUserData(currentPlanet.getValue().getMaxSize());
            insertFinishedPlanetForUser();
        }

        currentPlanet.setValue(updatingPlanet);
    }

    private void insertFinishedPlanetForUser() {
        Log.i(TAG, "saveFinishedPlanetForUser: Saving finished planet ID " + currentPlanet.getValue().getId() + " for user ID " + AppLayout.user.getUid());
        UserFinishedPlanet userFinishedPlanet = new UserFinishedPlanet();
        userFinishedPlanet.setPlanetId(currentPlanet.getValue().getId());
        userFinishedPlanet.setUserId(AppLayout.user.getUid());

        new InsertUserFinishedPlanet(userFinishedPlanetsDAO).execute(userFinishedPlanet);
    }

    private void updateUserData(int numberOfClicks) {
        Log.i(TAG, "updateUserData: update async executed for user ID" + AppLayout.user.getUid());
        try {
            UserData currentUserData = new GetUserDataAsync(userDataDAO, FirebaseAuth.getInstance().getCurrentUser().getUid()).execute().get();
            currentUserData.setFinishedPlanets(currentUserData.getFinishedPlanets() + 1);
            currentUserData.setTotalClicks(currentUserData.getTotalClicks() + numberOfClicks);
            new UpdateUserDataAsync(userDataDAO).execute(currentUserData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void resetCurrentPlanet() {
        currentPlanet = new MutableLiveData<>();
    }

    public void saveUserData(UserData userData) {
        new UpdateUserDataAsync(userDataDAO).execute(userData);
    }

    public void createNewUserData(String uid) {
        UserData userData = new UserData(uid, 0, 0);

        new InsertUserDataAsync(userDataDAO).execute(userData);
    }

    public LiveData<UserData> getUserData() {
        return userDataDAO.getUserLiveData(AppLayout.user.getUid());
    }

    public List<String> getCompletedPlanetIdsForCurrentUser() {
        List<String> completedPlanetsIds = new ArrayList<>();
        List<UserFinishedPlanet> userFinishedPlanets = null;
        try {
            userFinishedPlanets = new GetUserFinishedPlanets(userFinishedPlanetsDAO, AppLayout.user.getUid()).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Log.i(TAG, "getCompletedPlanetIdsForCurrentUser: Number of completed planets for user: " + userFinishedPlanets.size());

        if (userFinishedPlanets != null) {
            for (UserFinishedPlanet planet : userFinishedPlanets) {
                completedPlanetsIds.add(planet.getPlanetId());
            }
        }

        return completedPlanetsIds;
    }

    private class InsertUserDataAsync extends AsyncTask<UserData, Void, Void> {
        private UserDataDAO userDataDAO;

        public InsertUserDataAsync(UserDataDAO userDataDAO) {
            this.userDataDAO = userDataDAO;
        }

        @Override
        protected Void doInBackground(UserData... userData) {
            userDataDAO.insert(userData[0]);
            Log.i(TAG, "doInBackground: user data created " + userData[0]);
            return null;
        }
    }

    private class InsertUserFinishedPlanet extends AsyncTask<UserFinishedPlanet, Void, Void> {
        private UserFinishedPlanetsDAO finishedPlanetsDAO;

        public InsertUserFinishedPlanet(UserFinishedPlanetsDAO finishedPlanetsDAO) {
            this.finishedPlanetsDAO = finishedPlanetsDAO;
        }

        @Override
        protected Void doInBackground(UserFinishedPlanet... userFinishedPlanets) {
            userFinishedPlanetsDAO.insert(userFinishedPlanets[0]);
            Log.i(TAG, "doInBackground: New planet saved for user");
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

    private class GetUserDataAsync extends AsyncTask<Void, Void, UserData> {
        private UserDataDAO userDataDAO;
        private String userId;

        public GetUserDataAsync(UserDataDAO userDataDAO, String userId) {
            this.userDataDAO = userDataDAO;
            this.userId = userId;
        }

        @Override
        protected UserData doInBackground(Void... Void) {
            return userDataDAO.getUserData(userId);
        }
    }

    private class GetUserFinishedPlanets extends AsyncTask<Void, Void, List<UserFinishedPlanet>> {
        private UserFinishedPlanetsDAO userFinishedPlanetsDAO;
        private String userId;

        public GetUserFinishedPlanets(UserFinishedPlanetsDAO userFinishedPlanetsDAO, String userId) {
            this.userFinishedPlanetsDAO = userFinishedPlanetsDAO;
            this.userId = userId;
        }

        @Override
        protected List<UserFinishedPlanet> doInBackground(Void... Void) {
            return userFinishedPlanetsDAO.getAllFinishedPlanetsForUser(userId);
        }
    }
}
