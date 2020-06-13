package com.example.courseprojectplanetbuilder.Fragments.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.example.courseprojectplanetbuilder.R;
import com.example.courseprojectplanetbuilder.Repository.PlanetRepository;
import com.example.courseprojectplanetbuilder.Utility.EmailValidation;
import com.example.courseprojectplanetbuilder.Utility.InvalidInputException;


public class NewAccountViewModel extends AndroidViewModel {
    private PlanetRepository planetRepository;
    private Application application;

    public NewAccountViewModel(Application application) {
        super(application);
        this.planetRepository = PlanetRepository.getInstance(application);
        this.application = application;
    }

    public boolean isValidUsername(String username) throws InvalidInputException {
        if (isEmpty(username)) {
            throw new InvalidInputException(application.getString(R.string.empty_username));
        }
        return true;
    }

    public boolean isValidEmail(String email) throws InvalidInputException {
        if (isEmpty(email)) {
            throw new InvalidInputException(application.getString(R.string.empty_email));
        }

        if (!EmailValidation.isValidEmail(email)) {
            throw new InvalidInputException(application.getString(R.string.invalid_email));
        }

        return true;
    }

    public boolean isValidPassword(String password, String confirmationPassword) throws InvalidInputException {
        if (isEmpty(password)) {
            throw new InvalidInputException(application.getString(R.string.empty_password));
        }

        if (isEmpty(confirmationPassword)) {
            throw new InvalidInputException(application.getString(R.string.empty_confirmation_password));
        }

        if (!password.equals(confirmationPassword)) {
            throw new InvalidInputException(application.getString(R.string.passwords_not_match));
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
