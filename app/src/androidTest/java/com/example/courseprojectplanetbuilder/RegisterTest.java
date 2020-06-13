package com.example.courseprojectplanetbuilder;

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;

import com.example.courseprojectplanetbuilder.Fragments.View.NewAccountFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4ClassRunner.class)
public class RegisterTest {
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

    @Before
    public void setUpFragment() {
        mainActivityActivityTestRule
                .getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_layout, NewAccountFragment.newInstance())
                .commit();
    }

    @Test
    public void usernameFieldPresentTest() {
        onView(withId(R.id.register_username)).check(matches(isDisplayed()));
    }

    @Test
    public void emailFieldPresentTest() {
        onView(withId(R.id.register_email)).check(matches(isDisplayed()));
    }

    @Test
    public void passwordFieldPresentTest() {
        onView(withId(R.id.register_password)).check(matches(isDisplayed()));
    }

    @Test
    public void confirmPasswordFieldPresentTest() {
        onView(withId(R.id.register_confirm_password)).check(matches(isDisplayed()));
    }

    @Test
    public void createAccountButtonTest() {
        onView(withId(R.id.register_create_account_button)).check(matches(isDisplayed()));
    }

    @Test
    public void emptyUsernameFieldTest() {
        onView(withId(R.id.register_create_account_button)).perform(click());
        onView(withText(R.string.empty_username)).check(matches(isDisplayed()));
    }

    @Test
    public void emptyEmailFieldTest() {
        onView(withId(R.id.register_username_text)).perform(replaceText("David"), closeSoftKeyboard());
        onView(withId(R.id.register_create_account_button)).perform(click());
        onView(withText(R.string.empty_email)).check(matches(isDisplayed()));
    }

    @Test
    public void invalidEmailFieldTest() {
        onView(withId(R.id.register_username_text)).perform(replaceText("David"), closeSoftKeyboard());
        onView(withId(R.id.register_email_text)).perform(replaceText("david"), closeSoftKeyboard());
        onView(withId(R.id.register_create_account_button)).perform(click());
        onView(withText(R.string.invalid_email)).check(matches(isDisplayed()));
    }

    @Test
    public void invalidEmailFieldTest2() {
        onView(withId(R.id.register_username_text)).perform(replaceText("David"), closeSoftKeyboard());
        onView(withId(R.id.register_email_text)).perform(replaceText("david@"), closeSoftKeyboard());
        onView(withId(R.id.register_create_account_button)).perform(click());
        onView(withText(R.string.invalid_email)).check(matches(isDisplayed()));
    }

    @Test
    public void invalidEmailFieldTest3() {
        onView(withId(R.id.register_username_text)).perform(replaceText("David"), closeSoftKeyboard());
        onView(withId(R.id.register_email_text)).perform(replaceText("david.com"), closeSoftKeyboard());
        onView(withId(R.id.register_create_account_button)).perform(click());
        onView(withText(R.string.invalid_email)).check(matches(isDisplayed()));
    }

    @Test
    public void emptyPasswordFieldTest() {
        onView(withId(R.id.register_username_text)).perform(replaceText("David"), closeSoftKeyboard());
        onView(withId(R.id.register_email_text)).perform(replaceText("david@david.dk"), closeSoftKeyboard());
        onView(withId(R.id.register_create_account_button)).perform(click());
        onView(withText(R.string.empty_password)).check(matches(isDisplayed()));
    }

    @Test
    public void emptyConfirmationPasswordFieldTest() {
        onView(withId(R.id.register_username_text)).perform(replaceText("David"), closeSoftKeyboard());
        onView(withId(R.id.register_email_text)).perform(replaceText("david@david.dk"), closeSoftKeyboard());
        onView(withId(R.id.register_password_text)).perform(replaceText("123456"), closeSoftKeyboard());
        onView(withId(R.id.register_create_account_button)).perform(click());
        onView(withText(R.string.empty_confirmation_password)).check(matches(isDisplayed()));
    }

    @Test
    public void notMatchingPasswordTest() {
        onView(withId(R.id.register_username_text)).perform(replaceText("David"), closeSoftKeyboard());
        onView(withId(R.id.register_email_text)).perform(replaceText("david@david.dk"), closeSoftKeyboard());
        onView(withId(R.id.register_password_text)).perform(replaceText("123456"), closeSoftKeyboard());
        onView(withId(R.id.register_confirm_password_text)).perform(replaceText("12345"), closeSoftKeyboard());
        onView(withId(R.id.register_create_account_button)).perform(click());
        onView(withText(R.string.passwords_not_match)).check(matches(isDisplayed()));
    }

}
