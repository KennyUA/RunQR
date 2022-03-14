package com.example.runqr;

import android.app.Activity;
import android.widget.EditText;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import com.robotium.solo.Solo;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;


public class LoginActivityTest {
    private Solo solo;
    @Rule
    public ActivityTestRule<LoginActivity> rule =
            new ActivityTestRule<>(LoginActivity.class, true, true);
    /**
     * Runs before all tests and creates solo instance.
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception{
        solo = new Solo(InstrumentationRegistry.getInstrumentation(),rule.getActivity());
    }
    /**
     * Gets the Activity
     * @throws Exception
     */

    @Test
    public void start() throws Exception{
        Activity activity = rule.getActivity();
    }

    @Test
    public void checkInvalidUsername() {
        solo.assertCurrentActivity("Wrong Activity", LoginActivity.class);
        solo.enterText((EditText) solo.getView(R.id.username), "111");
        solo.enterText((EditText) solo.getView(R.id.email), "test@gmail.com");
        solo.clickOnButton("CONFIRM");
        solo.assertCurrentActivity("Wrong Activity", LoginActivity.class);
    }

    @Test
    public void checkInvalidEmail() {
        solo.assertCurrentActivity("Wrong Activity", LoginActivity.class);
        solo.enterText((EditText) solo.getView(R.id.username), "11111");
        solo.enterText((EditText) solo.getView(R.id.email), "test");
        solo.clickOnButton("CONFIRM");
        solo.assertCurrentActivity("Wrong Activity", LoginActivity.class);
    }

    @Test
    public void checkTakenUsername() {
        solo.assertCurrentActivity("Wrong Activity", LoginActivity.class);
        solo.enterText((EditText) solo.getView(R.id.username), "kasych");
        solo.enterText((EditText) solo.getView(R.id.email), "test@gmail.com");
        solo.clickOnButton("CONFIRM");
        solo.assertCurrentActivity("Wrong Activity", LoginActivity.class);
    }

    @Test
    public void checkValidLogin() {
        solo.assertCurrentActivity("Wrong Activity", LoginActivity.class);
        solo.enterText((EditText) solo.getView(R.id.username), "kasych_new");
        solo.enterText((EditText) solo.getView(R.id.email), "test@gmail.com");
        solo.clickOnButton("CONFIRM");
        solo.assertCurrentActivity("Wrong Activity", MainActivity.class);
    }

    @Test
    public void checkActivity() {
        solo.sleep(2000);
        solo.assertCurrentActivity("Wrong Activity", MainActivity.class);
    }

}
