package com.testmeuapt;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.testmeuapt.ui.shots.ShotsActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;

/**
 * Created by jsantini on 23/09/17.
 */

@RunWith(AndroidJUnit4.class)
public class ShotsUITest {

    @Rule
    public ActivityTestRule<ShotsActivity> mRule = new ActivityTestRule<>(ShotsActivity.class, true, true);

    @Before
    public void registerIdlingResource() {
        Espresso.registerIdlingResources(
                mRule.getActivity().getCountingIdlingResource());
    }

    @Test
    public void titleShotsTest() throws Exception {
        String expectedActiveTitle = InstrumentationRegistry.getTargetContext()
                .getString(R.string.shots_title);
        onView(withText(containsString(expectedActiveTitle))).check(matches(isDisplayed()));
    }
}
