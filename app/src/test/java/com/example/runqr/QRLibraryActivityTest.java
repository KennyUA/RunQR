package com.example.runqr;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import android.app.Activity;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Tests for UI navigation from MainActivity to QRLibrary (from dropdown menu) and then to DisplayQRCodeActivity when QRCode is clicked.
 * Test QRCodes are added to QRLibrary and deleted. QRCode is clicked to display score currently (location and photo will be added in next part).
 * Robotium test framework is used.
 * NOTES: Don't know how to manually add QRCodes to the QRLibrary and test that QRCode is there and when clicked, opens a new activity.
 * POSSIBLE SOLUTION FOR NOW: instantiate player with some test QRCodes already present in QRLibrary 
 */
public class QRLibraryActivityTest {

    private Solo solo;
    private Solo solo2;
    private Solo solo3;

    @Rule
    public ActivityTestRule<MainActivity> rule =
            new ActivityTestRule<>(MainActivity.class, true, true);
    @Rule
    public ActivityTestRule<QRLibraryActivity> rule2 =
            new ActivityTestRule<>(QRLibraryActivity.class, true, true);

    @Rule
    public ActivityTestRule<DisplayQRCodeActivity> rule3 =
            new ActivityTestRule<>(DisplayQRCodeActivity.class, true, true);

    /**
     * Runs before all tests and creates solo instances for 3 activities being tested.
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        solo = new Solo(InstrumentationRegistry.getInstrumentation(), rule.getActivity());
        solo2 = new Solo(InstrumentationRegistry.getInstrumentation(), rule2.getActivity());
        solo3 = new Solo(InstrumentationRegistry.getInstrumentation(), rule3.getActivity());

    }

    /**
     * Gets the Activity
     * @throws Exception
     */
    @Test
    public void start() throws Exception{
        Activity activity = rule.getActivity();
    }

    /**
     * Checks whether activity switched correctly on click on list item
     */
    @Test
    public void checkActivitySwitch(){
        solo.assertCurrentActivity("Wrong Activity", MainActivity.class);
        solo.clickOnView(solo.getView(R.id.qr_library_item)); // Click on QRLibrary option in app bar
        solo2.assertCurrentActivity("Wrong Activity", QRLibrary.class);

    }

    /**
     * Add QRCodes to the listview and check the QRCode's hash and score using assertTrue
     * Clear all QRCodes from the listview and check again with assertFalse
     */

    @Test
    public void checkQRLibrary() {
        //Asserts that the current activity is the MainActivity.
        // Otherwise, show "Wrong Activity"

        solo.assertCurrentActivity("Wrong Activity", MainActivity.class);
        solo.clickOnView(solo.getView(R.id.qr_library_item)); // Click on QRLibrary option in app bar
        solo2.assertCurrentActivity("Wrong Activity", QRLibrary.class);

        // Add QRCodes to list
        Player currentPlayer = new Player(new Account(), new QRLibrary(new ArrayList<QRCode>(), 0));
        Hasher hasher = new Hasher();
        String hash1 = hasher.hashQRCode("hello");
        QRCode code1 = new QRCode(hash1);
        Integer score1 = code1.getScore();
        currentPlayer.getPlayerQRLibrary().addQRCode(code1);

        assertTrue(solo.waitForText(hash1, 1, 2000));
        assertTrue(solo.waitForText( score1.toString(), 1, 2000));

        currentPlayer.getPlayerQRLibrary().deleteQRCode(code1);
        assertFalse(solo.waitForText(hash1, 1, 2000));
        assertFalse(solo.waitForText( score1.toString(), 1, 2000));

        /*
        solo.clickOnButton("ADD CITY"); // Click ADD CITY Button
        //Get view for EditText and enter a city name
        solo.enterText((EditText)solo.getView(R.id.editText_name), "Edmonton");
        solo.clickOnButton("CONFIRM"); //Select CONFIRM Button
        solo.clearEditText((EditText) solo.getView(R.id.editText_name)); //Clear the EditText

        // True if there is a text: Edmonton on the screen, wait at least 2 seconds and find
        minimum one match.

        assertTrue(solo.waitForText("Edmonton", 1, 2000));

        solo.clickOnButton("CLEAR ALL"); //Select CLEAR ALL Button

        // True if there is no text: Edmonton, on the screen
        assertFalse(solo.searchText("Edmonton"));
        */

    }


    /**
     * Closes the activity after each test
     * @throws Exception
     */

    @After
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }

}
