package com.example.courseprojectplanetbuilder.Fragments.ViewModel;

import androidx.lifecycle.ViewModel;

import com.example.courseprojectplanetbuilder.Utility.EmailValidation;
import com.example.courseprojectplanetbuilder.Utility.InvalidInputException;

public class LoginViewModel extends ViewModel {
    public boolean isValidPassword(String password) throws InvalidInputException {
        if (isEmpty(password)) {
            throw new InvalidInputException("Empty Password");
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

    private boolean isEmpty(String text) {
        return text.trim().length() == 0;
    }


}
