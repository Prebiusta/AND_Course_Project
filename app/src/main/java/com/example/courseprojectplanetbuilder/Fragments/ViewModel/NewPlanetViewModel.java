package com.example.courseprojectplanetbuilder.Fragments.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.example.courseprojectplanetbuilder.Model.Planet;
import com.example.courseprojectplanetbuilder.Repository.PlanetRepository;
import com.example.courseprojectplanetbuilder.Utility.InvalidInputException;

public class NewPlanetViewModel extends AndroidViewModel {
    private final PlanetRepository planetRepository;

    public NewPlanetViewModel(Application application) {
        super(application);
        planetRepository = PlanetRepository.getInstance(application);
    }

    public boolean isValidName(String name) throws InvalidInputException {
        if (isEmpty(name)) {
            throw new InvalidInputException("Name cannot be empty");
        }

        if (name.trim().length() < 4) {
            throw new InvalidInputException("Name shorter than 4 characters");
        }

        if (name.trim().length() > 20) {
            throw new InvalidInputException("Name too long");
        }

        return true;
    }

    public boolean isValidSize(String size) throws InvalidInputException {
        if (isEmpty(size)) {
            throw new InvalidInputException("Size cannot be empty");
        }

        if (Integer.parseInt(size) > 1000 || Integer.parseInt(size) <= 0) {
            throw new InvalidInputException("Size must be between 0 and 1000");
        }

        return true;
    }

    private boolean isEmpty(String text) {
        return text.trim().length() == 0;
    }

    public void createPlanet(String planetName, String planetSize, String author) {
        planetRepository.createPlanet(new Planet(planetName, Integer.parseInt(planetSize), author));
    }
}
