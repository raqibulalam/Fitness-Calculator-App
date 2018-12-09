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
import static org.lukasowy.fitnesscalculators.GeneralMethodsTest.withoutResultText;

/**
 * Created by Łukasz on 2018-03-25.
 */

public class BMITest {

    public void BMITest() throws InterruptedException {

        onSelectBMI();

        withoutResultText();

        BMIWithoutSexTest();

        BMIWithoutHeightTest();

        BMIWithoutWeightTest();

        BMIWithAllTypedTest();

        slidingUp();

        slidingDown();

        pressToBack();

    }

    private void onSelectBMI() {
        onView(withId(R.id.BMI)).perform(click());

    }

    public void BMIWithAllTypedTest() throws InterruptedException {
        onView(withId(R.id.radioBtnMale)).perform(click());
        onView(withId(R.id.inputHeight)).perform(clearText(), typeText("200"));
        onView(withId(R.id.inputWeight)).perform(clearText(), typeText("111"));
        onView(withId(R.id.btnCalculate)).perform(click());
        onView(withText("Overweight")).inRoot(new ToastMatcher()).check(matches(isDisplayed()));
        Thread.sleep(700);
        onView(withId(R.id.resultTextView))
                .check(matches(withText("27.75")));
    }

    public void BMIWithoutWeightTest() {
        onView(withId(R.id.radioBtnMale)).perform(click());
        onView(withId(R.id.inputHeight)).perform(clearText(), typeText("155"));
        onView(withId(R.id.inputWeight)).perform(clearText());
        onView(withId(R.id.btnCalculate)).perform(click());
        onView(withText("Please, type Weight.")).inRoot(new ToastMatcher()).check(matches(isDisplayed()));
    }

    public void BMIWithoutHeightTest() {
        onView(withId(R.id.radioBtnMale)).perform(click());
        onView(withId(R.id.inputHeight)).perform(clearText());
        onView(withId(R.id.inputWeight)).perform(clearText(), typeText("45"));
        onView(withId(R.id.btnCalculate)).perform(click());
        onView(withText("Please, type Height.")).inRoot(new ToastMatcher()).check(matches(isDisplayed()));
    }

    public void BMIWithoutSexTest() {
        onView(withId(R.id.inputHeight)).perform(clearText(), typeText("155"));
        onView(withId(R.id.inputWeight)).perform(clearText(), typeText("45"));
        onView(withId(R.id.btnCalculate)).perform(click());
        onView(withText("Please, select sex.")).inRoot(new ToastMatcher()).check(matches(isDisplayed()));

    }
}
