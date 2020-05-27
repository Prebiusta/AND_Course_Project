package com.example.courseprojectplanetbuilder.ViewModel;

import com.example.courseprojectplanetbuilder.Utility.InvalidInputException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;


public class NewAccountViewModelTest {
    private NewAccountViewModel viewModel;

    @Before
    public void setUp() throws Exception {
        viewModel = new NewAccountViewModel();
    }

    @After
    public void tearDown() throws Exception {
        viewModel = null;
    }

    @Test
    public void ValidUsername() throws InvalidInputException {
        assertTrue(viewModel.isValidUsername("David"));
    }

    @Test(expected = InvalidInputException.class)
    public void EmptyUsername() throws InvalidInputException {
        assertFalse(viewModel.isValidUsername(""));
    }

    @Test(expected = InvalidInputException.class)
    public void NoAtNoDomainEmail() throws InvalidInputException {
        assertFalse(viewModel.isValidEmail("email"));
    }

    @Test(expected = InvalidInputException.class)
    public void EmptyEmail() throws InvalidInputException {
        assertFalse(viewModel.isValidEmail(""));
    }

    @Test(expected = InvalidInputException.class)
    public void NotAtEmail() throws InvalidInputException {
        assertFalse(viewModel.isValidEmail("email.com"));
    }

    @Test(expected = InvalidInputException.class)
    public void NotDomainEmail() throws InvalidInputException {
        assertFalse(viewModel.isValidEmail("email@email"));
    }

    @Test
    public void ValidEmail() throws InvalidInputException {
        assertTrue(viewModel.isValidEmail("email@email.com"));
    }

    @Test(expected = InvalidInputException.class)
    public void EmptyPasswordEmptyConfirmationPassword() throws InvalidInputException {
        assertFalse(viewModel.isValidPassword("", ""));
    }

    @Test(expected = InvalidInputException.class)
    public void EmptyPasswordValidConfirmationPassword() throws InvalidInputException {
        assertFalse(viewModel.isValidPassword("", "123456"));

    }

    @Test(expected = InvalidInputException.class)
    public void ValidPasswordEmptyConfirmationPassword() throws InvalidInputException {
        assertFalse(viewModel.isValidPassword("123456", ""));

    }

    @Test(expected = InvalidInputException.class)
    public void ValidPasswordDifferentConfirmationPassword() throws InvalidInputException {
        assertFalse(viewModel.isValidPassword("123456", "12345"));

    }

    @Test(expected = InvalidInputException.class)
    public void DifferentPasswordValidConfirmationPassword() throws InvalidInputException {
        assertFalse(viewModel.isValidPassword("12345", "123456"));

    }

    @Test
    public void ValidPasswords() throws InvalidInputException {
        assertTrue(viewModel.isValidPassword("123456", "123456"));
    }

}

