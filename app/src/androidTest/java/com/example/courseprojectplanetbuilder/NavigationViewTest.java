package com.example.courseprojectplanetbuilder;

import android.view.Gravity;

import androidx.test.espresso.contrib.DrawerActions;
import androidx.test.espresso.contrib.NavigationViewActions;
import androidx.test.rule.ActivityTestRule;

import com.example.courseprojectplanetbuilder.Fragments.View.AppLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.DrawerMatchers.isClosed;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class NavigationViewTest {
    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule =
            new ActivityTestRule<MainActivity>(MainActivity.class);

    @Before
    public void beforeEach(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            onView(withId(R.id.login_email_field_text)).perform(replaceText("test@test.dk"), closeSoftKeyboard());
            onView(withId(R.id.login_password_field_text)).perform(replaceText("123456"), closeSoftKeyboard());
            onView(withId(R.id.login_login_button)).perform(click());
        }

        mainActivityActivityTestRule
                .getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_layout, AppLayout.newInstance())
                .commit();

        onView(withId(R.id.app_layout_drawer_layout))
                .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
                .perform(DrawerActions.open());
    }

    @Test
    public void openCurrentPlanetTest() throws InterruptedException {
        onView(withId(R.id.app_layout_navigation))
                .perform(NavigationViewActions.navigateTo(R.id.nav_current_planet));

        Thread.sleep(1000);
        onView(withId(R.id.current_planet_fragment_layout)).check(matches(isDisplayed()));
    }

    @Test
    public void openAllPlanetsTest() throws InterruptedException {
        onView(withId(R.id.app_layout_navigation))
                .perform(NavigationViewActions.navigateTo(R.id.nav_all_planets));

        Thread.sleep(1000);
        onView(withId(R.id.all_planets_fragment_layout)).check(matches(isDisplayed()));
    }

    @Test
    public void openNewPlanetTest() throws InterruptedException {
        onView(withId(R.id.app_layout_navigation))
                .perform(NavigationViewActions.navigateTo(R.id.nav_new_planet));

        Thread.sleep(1000);
        onView(withId(R.id.new_planet_fragment_layout)).check(matches(isDisplayed()));
    }

    @Test
    public void openCoronaVirusTest() throws InterruptedException {
        onView(withId(R.id.app_layout_navigation))
                .perform(NavigationViewActions.navigateTo(R.id.nav_corona_virus));

        Thread.sleep(1000);
        onView(withId(R.id.corona_virus_fragment_layout)).check(matches(isDisplayed()));
    }

    @Test
    public void openProfileTest() throws InterruptedException {
        onView(withId(R.id.app_layout_navigation))
                .perform(NavigationViewActions.navigateTo(R.id.nav_profile));

        Thread.sleep(1000);
        onView(withId(R.id.profile_fragment_layout)).check(matches(isDisplayed()));
    }

    @Test
    public void openSettingsTest() throws InterruptedException {
        onView(withId(R.id.app_layout_navigation))
                .perform(NavigationViewActions.navigateTo(R.id.nav_settings));

        Thread.sleep(1000);
        onView(withId(R.id.settings_fragment_layout)).check(matches(isDisplayed()));
    }
}
