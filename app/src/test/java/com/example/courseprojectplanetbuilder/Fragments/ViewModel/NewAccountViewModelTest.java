package com.example.courseprojectplanetbuilder.Fragments.ViewModel;

import android.app.Application;

import com.example.courseprojectplanetbuilder.Repository.PlanetRepository;
import com.example.courseprojectplanetbuilder.Utility.InvalidInputException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

@RunWith(PowerMockRunner.class)
@PrepareForTest(PlanetRepository.class)
public class NewAccountViewModelTest {
    @Mock
    private Application applicationMock;

    private NewAccountViewModel viewModel;

    @Before
    public void setUp() throws Exception {
        PlanetRepository planetRepositoryMock = PowerMockito.mock(PlanetRepository.class);

        PowerMockito.mockStatic(PlanetRepository.class);
        PowerMockito.when(PlanetRepository.getInstance(applicationMock)).thenReturn(planetRepositoryMock);

        MockitoAnnotations.initMocks(this);
        viewModel = new NewAccountViewModel(applicationMock, application1);
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

