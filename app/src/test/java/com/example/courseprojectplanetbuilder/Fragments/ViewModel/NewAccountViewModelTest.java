package com.example.courseprojectplanetbuilder.Fragments.ViewModel;

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
    public void ValidUsername()  {
        assertTrue(viewModel.isValidUsername("David"));
    }

    @Test(expected = InvalidInputException.class)
    public void EmptyUsername()  {
        assertFalse(viewModel.isValidUsername(""));
    }

    @Test(expected = InvalidInputException.class)
    public void NoAtNoDomainEmail()  {
        assertFalse(viewModel.isValidEmail("email"));
    }

    @Test(expected = InvalidInputException.class)
    public void EmptyEmail()  {
        assertFalse(viewModel.isValidEmail(""));
    }

    @Test(expected = InvalidInputException.class)
    public void NotAtEmail()  {
        assertFalse(viewModel.isValidEmail("email.com"));
    }

    @Test(expected = InvalidInputException.class)
    public void NotDomainEmail()  {
        assertFalse(viewModel.isValidEmail("email@email"));
    }

    @Test
    public void ValidEmail()  {
        assertTrue(viewModel.isValidEmail("email@email.com"));
    }

    @Test(expected = InvalidInputException.class)
    public void EmptyPasswordEmptyConfirmationPassword()  {
        assertFalse(viewModel.isValidPassword("", ""));
    }

    @Test(expected = InvalidInputException.class)
    public void EmptyPasswordValidConfirmationPassword()  {
        assertFalse(viewModel.isValidPassword("", "123456"));

    }

    @Test(expected = InvalidInputException.class)
    public void ValidPasswordEmptyConfirmationPassword()  {
        assertFalse(viewModel.isValidPassword("123456", ""));

    }

    @Test(expected = InvalidInputException.class)
    public void ValidPasswordDifferentConfirmationPassword()  {
        assertFalse(viewModel.isValidPassword("123456", "12345"));

    }

    @Test(expected = InvalidInputException.class)
    public void DifferentPasswordValidConfirmationPassword()  {
        assertFalse(viewModel.isValidPassword("12345", "123456"));

    }

    @Test
    public void ValidPasswords()  {
        assertTrue(viewModel.isValidPassword("123456", "123456"));
    }

}

