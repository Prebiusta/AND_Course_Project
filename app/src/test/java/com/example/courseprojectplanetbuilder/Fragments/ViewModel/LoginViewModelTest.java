package com.example.courseprojectplanetbuilder.Fragments.ViewModel;

import com.example.courseprojectplanetbuilder.Utility.InvalidInputException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginViewModelTest {
    LoginViewModel loginViewModel;

    @Before
    public void setUp() throws Exception {
        loginViewModel = new LoginViewModel();
    }

    @After
    public void tearDown() throws Exception {
        loginViewModel = null;
    }

    @Test
    public void TestValidPassword() {
        assertTrue(loginViewModel.isValidPassword("asdaasd"));
        assertTrue(loginViewModel.isValidPassword("342gzdfg"));
        assertTrue(loginViewModel.isValidPassword("0-d89k;l,vdsv"));
        assertTrue(loginViewModel.isValidPassword("adf./,>A<@as"));
        assertTrue(loginViewModel.isValidPassword("l;k,,/.das"));
        assertTrue(loginViewModel.isValidPassword("][;d2.asd"));
    }

    @Test (expected = InvalidInputException.class)
    public void TestEmptyPassword() {
        loginViewModel.isValidPassword("");
    }

    @Test (expected = NullPointerException.class)
    public void TestNullPassword(){
        loginViewModel.isValidPassword(null);
    }
}