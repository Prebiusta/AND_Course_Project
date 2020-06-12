package com.example.courseprojectplanetbuilder.Fragments.ViewModel;

import android.app.Application;

import com.example.courseprojectplanetbuilder.Model.Planet;
import com.example.courseprojectplanetbuilder.Repository.PlanetRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.powermock.api.support.SuppressCode.suppressConstructor;

@RunWith(PowerMockRunner.class)
@PrepareForTest(PlanetRepository.class)
public class AllPlanetsViewModelTest {
    @Mock
    private Application applicationMock;

    private AllPlanetsViewModel allPlanetsViewModel;

    @Before
    public void setUp() throws Exception {
        suppressConstructor(PlanetRepository.class);
        PowerMockito.mockStatic(PlanetRepository.class);
        PlanetRepository planetRepositoryMock = PowerMockito.mock(PlanetRepository.class);
//
        when(PlanetRepository.getInstance(applicationMock)).thenReturn(planetRepositoryMock);
        when(planetRepositoryMock.getCompletedPlanetIdsForCurrentUser()).thenReturn(getDummyPlanetIds(10));

        allPlanetsViewModel = new AllPlanetsViewModel(applicationMock);
    }

    private List<String> getDummyPlanetIds(int sizeOfDummyArray) {
        List<String> planetIds = new ArrayList<>();

        for (int i = 0; i < sizeOfDummyArray; i++) {
            planetIds.add(i + "");
        }

        return planetIds;
    }

    private ArrayList<Planet> getDummyPlanetList(int sizeOfList) {
        ArrayList<Planet> dummyPlanetList = new ArrayList<>();
        for (int i = 0; i < sizeOfList; i++) {
            Planet planet = new Planet();
            planet.setId(i + "");
            planet.setName("Test" + i);
            dummyPlanetList.add(planet);
        }

        return dummyPlanetList;
    }

    @Test
    public void getNotCompletedPlanetsSizeDifferenceTest() {
        ArrayList<Planet> notCompletedPlanets = allPlanetsViewModel.getNotCompletedPlanets(getDummyPlanetList(20));
        int noOfCompletedPlanets = 0;

        for (Planet planet : notCompletedPlanets)
            if (planet.isCompleted())
                noOfCompletedPlanets++;

        assertEquals(10, noOfCompletedPlanets);
    }

    @Test
    public void getNotCompletedPlanetsHalfSizeDifferenceTest() {
        ArrayList<Planet> notCompletedPlanets = allPlanetsViewModel.getNotCompletedPlanets(getDummyPlanetList(5));
        int noOfCompletedPlanets = 0;

        for (Planet planet : notCompletedPlanets)
            if (planet.isCompleted())
                noOfCompletedPlanets++;

        assertEquals(5, noOfCompletedPlanets);
    }

    @Test
    public void getNotCompletedPlanetsEmptyArrayTest() {
        ArrayList<Planet> notCompletedPlanets = allPlanetsViewModel.getNotCompletedPlanets(new ArrayList<Planet>());

        int noOfCompletedPlanets = 0;

        for (Planet planet : notCompletedPlanets)
            if (planet.isCompleted())
                noOfCompletedPlanets++;

        assertEquals(0, noOfCompletedPlanets);
    }

    @Test (expected = NullPointerException.class)
    public void getNotCompletedPlanetsNullArrayTest() {
        ArrayList<Planet> notCompletedPlanets = allPlanetsViewModel.getNotCompletedPlanets(null);

        int noOfCompletedPlanets = 0;

        for (Planet planet : notCompletedPlanets)
            if (planet.isCompleted())
                noOfCompletedPlanets++;

        assertEquals(0, noOfCompletedPlanets);
    }

    @Test
    public void getNotCompletedPlanetsFirstAndLastCompletedTest() {
        ArrayList<Planet> remotePlanets = new ArrayList<>();

        Planet firstPlanet = new Planet();
        firstPlanet.setId(0+"");

        Planet lastPlanet = new Planet();
        lastPlanet.setId(9+"");

        remotePlanets.add(firstPlanet);
        remotePlanets.add(lastPlanet);


        ArrayList<Planet> notCompletedPlanets = allPlanetsViewModel.getNotCompletedPlanets(remotePlanets);

        assertEquals(2, notCompletedPlanets.size());
        assertTrue(notCompletedPlanets.get(0).isCompleted());
        assertTrue(notCompletedPlanets.get(1).isCompleted());
    }

}