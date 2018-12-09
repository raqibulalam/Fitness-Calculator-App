package org.lukasowy.fitnesscalculators;

import android.support.test.espresso.action.ViewActions;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Łukasz on 2018-03-25.
 */

public class GeneralMethodsTest {

    public static void withoutResultText() {
        onView(withId(R.id.resultTextView))
                .check(matches(withText("...")));
    }

    public static void pressToBack() {
        onView(isRoot()).perform(ViewActions.pressBack());
    }

    public static void slidingUp() {
        onView(withId(R.id.slidingUp)).perform(click());
    }

    public static void slidingDown() {
        onView(withId(R.id.slidingUp)).perform(click());
    }
}
