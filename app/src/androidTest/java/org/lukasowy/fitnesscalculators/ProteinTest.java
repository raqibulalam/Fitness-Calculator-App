package org.lukasowy.fitnesscalculators;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.lukasowy.fitnesscalculators.GeneralMethodsTest.pressToBack;
import static org.lukasowy.fitnesscalculators.GeneralMethodsTest.slidingDown;
import static org.lukasowy.fitnesscalculators.GeneralMethodsTest.slidingUp;

/**
 * Created by Łukasz on 2018-03-27.
 */

public class ProteinTest {
    public void proteinTest() {
        onSelectProtein();

        ProteinWithoutAllTypedTest();

        ProteinWithoutSexTest();

        ProteinWithoutAgeTest();

        ProteinWithoutWeightTest();

        ProteinWithoutAgeAndWeight();

        ProteinWithAllTypedTest();

        slidingUp();

        slidingDown();

        pressToBack();
    }

    private void ProteinWithoutAgeAndWeight() {
        onView(withId(R.id.radioBtnFemale)).perform(click());
        onView(withId(R.id.inputAge)).perform(clearText());
        onView(withId(R.id.inputWeight)).perform(clearText());
        onView(withId(R.id.spinnerGoal)).perform(click());
        onData(allOf(is(instanceOf(String.class)))).atPosition(1).perform(click());
        onView(withId(R.id.btnCalculate)).perform(click());
        onView(withText("Please, type Weight and Age.")).inRoot(new ToastMatcher()).check(matches(isDisplayed()));
    }

    private void ProteinWithAllTypedTest() {
        onView(withId(R.id.radioBtnMale)).perform(click());
        onView(withId(R.id.inputAge)).perform(clearText(), typeText("25"));
        onView(withId(R.id.inputWeight)).perform(clearText(), typeText("100"));
        onView(withId(R.id.spinnerGoal)).perform(click());
        onData(allOf(is(instanceOf(String.class)))).atPosition(1).perform(click());
        onView(withId(R.id.btnCalculate)).perform(click());
        onView(withId(R.id.resultTextView)).check(matches(withText("1.0")));
    }

    private void ProteinWithoutAgeTest() {
        onView(withId(R.id.radioBtnMale)).perform(click());
        onView(withId(R.id.inputAge)).perform(clearText());
        onView(withId(R.id.inputWeight)).perform(clearText(), typeText("100"));
        onView(withId(R.id.spinnerGoal)).perform(click());
        onData(allOf(is(instanceOf(String.class)))).atPosition(1).perform(click());
        onView(withId(R.id.btnCalculate)).perform(click());
        onView(withText("Please, type Age.")).inRoot(new ToastMatcher()).check(matches(isDisplayed()));
    }

    private void ProteinWithoutWeightTest() {
        onView(withId(R.id.radioBtnMale)).perform(click());
        onView(withId(R.id.inputAge)).perform(clearText(), typeText("25"));
        onView(withId(R.id.inputWeight)).perform(clearText());
        onView(withId(R.id.spinnerGoal)).perform(click());
        onData(allOf(is(instanceOf(String.class)))).atPosition(1).perform(click());
        onView(withId(R.id.btnCalculate)).perform(click());
        onView(withText("Please, type Weight.")).inRoot(new ToastMatcher()).check(matches(isDisplayed()));
    }

    private void ProteinWithoutSexTest() {

        onView(withId(R.id.inputAge)).perform(clearText(), typeText("25"));
        onView(withId(R.id.inputWeight)).perform(clearText(), typeText("100"));
        onView(withId(R.id.spinnerGoal)).perform(click());
        onData(allOf(is(instanceOf(String.class)))).atPosition(1).perform(click());
        onView(withId(R.id.btnCalculate)).perform(click());
        onView(withText("Please, select sex.")).inRoot(new ToastMatcher()).check(matches(isDisplayed()));
    }

    private void ProteinWithoutAllTypedTest() {
        onView(withId(R.id.btnCalculate)).perform(click());
        onView(withText("Please, select sex.")).inRoot(new ToastMatcher()).check(matches(isDisplayed()));
    }

    private void onSelectProtein() {
        onView(withId(R.id.Protein)).perform(click());
    }
}
