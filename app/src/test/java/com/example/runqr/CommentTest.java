package com.example.runqr;

import static org.junit.Assert.assertTrue;

import android.app.Activity;
import android.widget.EditText;
import android.widget.ListView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

public class CommentTest {
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


        ListView codeList = (ListView) solo.getView(com.example.runqr.R.id.qrlibrary_list,1); //1 is ipmortant, dont know why
        //QRCode code  = (QRCode) codeList.getChildAt(0);
        solo2.clickInList(0);

        solo3.assertCurrentActivity("Wrong Activity", DisplayQRCodeActivity.class);
        assertTrue(solo.waitForText(score1.toString(), 1, 2000));

        RecyclerView commentList = (RecyclerView) solo3.getView(R.id.recyclerView);
        solo3.clickOnView(solo3.getView(R.id.add_comment_button));
        solo3.enterText((EditText)solo3.getView(R.id.comment_body_editText), "Edmonton");
        solo3.clickOnButton("OK"); //Select CONFIRM Button
        //solo3.clickInList()
        assertTrue(solo3.waitForText("Edmonton", 1, 2000));



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
