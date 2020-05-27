package com.example.courseprojectplanetbuilder.ViewModel;

import androidx.lifecycle.ViewModel;

import com.example.courseprojectplanetbuilder.Utility.EmailValidation;
import com.example.courseprojectplanetbuilder.Utility.InvalidInputException;

public class NewAccountViewModel extends ViewModel {

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
}
