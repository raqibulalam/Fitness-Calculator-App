package org.lukasowy.fitnesscalculators;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.lukasowy.fitnesscalculators.GeneralMethodsTest.pressToBack;
import static org.lukasowy.fitnesscalculators.GeneralMethodsTest.slidingDown;
import static org.lukasowy.fitnesscalculators.GeneralMethodsTest.slidingUp;

/**
 * Created by Łukasz on 2018-03-27.
 */

public class MaxHeartRateTest {
    public void maxHeartRateTest() throws InterruptedException {
        onSelectMaxHeartRate();

        MaxHeartRateWithoutInputsTest();

        MaxHeartRateWithoutSexTest();

        MaxHeartRateWithoutAgeTest();

        MaxHeartRateWithAllTypedTest();

        slidingUp();

        slidingDown();

        pressToBack();
    }

    private void MaxHeartRateWithoutInputsTest() {
        onView(withId(R.id.btnCalculate)).perform(click());
        onView(withText("Please, select sex.")).inRoot(new ToastMatcher()).check(matches(isDisplayed()));
    }

    private void MaxHeartRateWithoutSexTest() throws InterruptedException {
        onView(withId(R.id.inputAge)).perform(clearText(), typeText("100"));
        onView(withId(R.id.btnCalculate)).perform(click());
        Thread.sleep(1000); // funny
        onView(withText("Please, select sex.")).inRoot(new ToastMatcher()).check(matches(isDisplayed()));
        onView(withId(R.id.inputAge)).perform(clearText());
    }

    private void MaxHeartRateWithoutAgeTest() throws InterruptedException {
        Thread.sleep(1000); // funny
        onView(withId(R.id.inputAge)).perform(clearText());
        onView(withId(R.id.radioBtnFemale)).perform(click());
        onView(withId(R.id.btnCalculate)).perform(click());
        Thread.sleep(1000); // funny
        onView(withText("Please, type correct Age.")).inRoot(new ToastMatcher()).check(matches(isDisplayed()));
    }


    private void MaxHeartRateWithAllTypedTest() {
        onView(withId(R.id.inputAge)).perform(clearText(), typeText("100"));
        onView(withId(R.id.radioBtnMale)).perform(click());
        onView(withId(R.id.btnCalculate)).perform(click());
        onView(withId(R.id.resultTextView)).check(matches(withText("120")));
    }

    private void onSelectMaxHeartRate() {
        onView(withId(R.id.MaxHeartRate)).perform(click());
    }
}
