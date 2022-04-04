package com.example.runqr;

import android.app.Activity;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.robotium.solo.Solo;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class MainActivityTest {

    private Solo solo;
    @Rule
    public ActivityTestRule<MainActivity> rule =
            new ActivityTestRule<>(MainActivity.class, true, true);
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
    public void checkInvalidActivity() {
        solo.assertCurrentActivity("Wrong Activity", MainActivity.class);

    }

    @Test
    public void checkValidActivitySwitchToQRLibraryActivity() {
        solo.assertCurrentActivity("Wrong Activity", MainActivity.class);
        solo.clickOnMenuItem("QR Library");
        solo.sleep(1000); // give it time to change activity
        solo.assertCurrentActivity("Wrong activity", QRLibraryActivity.class);
    }

    @Test
    public void checkValidActivitySwitchToProfileActivity() {
        solo.assertCurrentActivity("Wrong Activity", MainActivity.class);
        solo.clickOnMenuItem("Profile");
        solo.sleep(1000); // give it time to change activity
        solo.assertCurrentActivity("Wrong activity", ProfileActivity.class);
    }
    @Test
    public void checkValidActivitySwitchToViewLeaderboardActivity() {
        solo.assertCurrentActivity("Wrong Activity", MainActivity.class);
        solo.clickOnMenuItem("View Leaderboard");
        solo.sleep(1000); // give it time to change activity
        solo.assertCurrentActivity("Wrong activity", LeaderboardActivity.class);
    }
    @Test
    public void checkValidActivitySwitchToAddAnotherDeviceActivity() {
        solo.assertCurrentActivity("Wrong Activity", MainActivity.class);
        solo.clickOnMenuItem("Add Another Device");
        solo.sleep(1000); // give it time to change activity
        solo.assertCurrentActivity("Wrong activity", AddDeviceActivity.class);
    }






}
