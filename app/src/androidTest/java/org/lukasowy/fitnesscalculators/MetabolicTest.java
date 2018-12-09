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

public class MetabolicTest {
    public void metabolicTest() {

        onSelectMetabolic();

        metabolicWithoutInputsTest();

        metabolicWithoutSexTest();

        //metabolicWithoutWeightTest();
        
        slidingUp();

        slidingDown();

        pressToBack();
    }

    private void metabolicWithoutWeightTest() {
        onView(withId(R.id.inputHeight)).perform(clearText(), typeText("200"));
        onView(withId(R.id.inputWeight)).perform(clearText(), typeText("100"));
        onView(withId(R.id.inputAge)).perform(clearText(), typeText("25"));
        onView(withId(R.id.btnCalculate)).perform(click());
        onView(withText("Please, select Weight.")).inRoot(new ToastMatcher()).check(matches(isDisplayed()));

    }

    private void metabolicWithoutSexTest() {
        onView(withId(R.id.inputHeight)).perform(clearText(), typeText("200"));
        onView(withId(R.id.inputWeight)).perform(clearText(), typeText("100"));
        onView(withId(R.id.inputAge)).perform(clearText(), typeText("25"));
        onView(withId(R.id.btnCalculate)).perform(click());
        onView(withText("Please, select sex.")).inRoot(new ToastMatcher()).check(matches(isDisplayed()));
    }

    private void metabolicWithoutInputsTest() {
        onView(withId(R.id.btnCalculate)).perform(click());
        onView(withText("Please, select sex.")).inRoot(new ToastMatcher()).check(matches(isDisplayed()));

    }

    private void onSelectMetabolic() {
        onView(withId(R.id.Metabolic)).perform(click());
    }
}
