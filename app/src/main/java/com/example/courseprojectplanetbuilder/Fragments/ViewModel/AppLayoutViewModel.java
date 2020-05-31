package com.example.courseprojectplanetbuilder.Fragments.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.example.courseprojectplanetbuilder.Repository.PlanetRepository;

public class AppLayoutViewModel extends AndroidViewModel {
    private final PlanetRepository planetRepository;

    public AppLayoutViewModel(Application application) {
        super(application);
        planetRepository = PlanetRepository.getInstance(application);
    }

    public void resetLiveData() {
        planetRepository.resetCurrentPlanet();
    }
}
