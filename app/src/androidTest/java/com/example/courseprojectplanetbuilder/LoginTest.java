package com.example.courseprojectplanetbuilder;

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4ClassRunner.class)
public class LoginTest {
    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule =
            new ActivityTestRule<MainActivity>(MainActivity.class);

    @BeforeClass
    public static void setUp() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            FirebaseAuth.getInstance().signOut();
        }
    }

    @Test
    public void testEmailFieldIsDisplayed() {
        onView(withId(R.id.login_email_field)).check(matches(isDisplayed()));
    }

    @Test
    public void testPasswordFieldIsDisplayed() {
        onView(withId(R.id.login_password_field)).check(matches(isDisplayed()));

    }

    @Test
    public void testLoginButtonIsDisplayed() {
        onView(withId(R.id.login_login_button)).check(matches(isDisplayed()));

    }

    @Test
    public void testNewAccountButtonIsDisplayed() {
        onView(withId(R.id.login_new_account_button)).check(matches(isDisplayed()));

    }
}
