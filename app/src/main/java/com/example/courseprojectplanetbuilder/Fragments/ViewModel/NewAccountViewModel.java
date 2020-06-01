package com.example.courseprojectplanetbuilder.Fragments.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.example.courseprojectplanetbuilder.Repository.PlanetRepository;
import com.example.courseprojectplanetbuilder.Utility.EmailValidation;
import com.example.courseprojectplanetbuilder.Utility.InvalidInputException;

public class NewAccountViewModel extends AndroidViewModel {
    private final PlanetRepository planetRepository;

    public NewAccountViewModel(Application application) {
        super(application);
        this.planetRepository = PlanetRepository.getInstance(application);
    }

    public boolean isValidUsername(String username) throws InvalidInputException {
        if (isEmpty(username)) {
            throw new InvalidInputException("Username is empty");
        }
        return true;
    }

    public boolean isValidEmail(String email) throws InvalidInputException {
        if (isEmpty(email)) {
            throw new InvalidInputException("Email is empty");
        }

        if (!EmailValidation.isValidEmail(email)) {
            throw new InvalidInputException("Invalid email");
        }

        return true;
    }

    public boolean isValidPassword(String password, String confirmationPassword) throws InvalidInputException {
        if (isEmpty(password)) {
            throw new InvalidInputException("Empty Password");
        }

        if (isEmpty(confirmationPassword)) {
            throw new InvalidInputException("Empty Confirmation Password");
        }

        if (!password.equals(confirmationPassword)) {
            throw new InvalidInputException("Passwords do not match");
        }

        return true;
    }

    private boolean isEmpty(String text) {
        return text.trim().length() == 0;
    }

    public void createUserData(String uid) {
        planetRepository.createNewUserData(uid);
    }
}
