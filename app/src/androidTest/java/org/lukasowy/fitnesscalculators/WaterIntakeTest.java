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
 * Created by Łukasz on 2018-03-26.
 */

public class WaterIntakeTest {
    public void waterIntakeTest(){
        onSelectWaterIntake();

        WaterIntakeWithoutWeightTest();

        WaterIntakeWithAllTypedTest();

        slidingUp();

        slidingDown();

        pressToBack();
    }

    private void WaterIntakeWithAllTypedTest() {
        onView(withId(R.id.inputWeight)).perform(clearText(), typeText("100"));

        //Select item from SpinnerActivity
        onView(withId(R.id.spinnerActivity)).perform(click());
        onData(allOf(is(instanceOf(String.class)))).atPosition(1).perform(click());

        //Select item from SpinnerClimate
        onView(withId(R.id.spinnerClimate)).perform(click());
        onData(allOf(is(instanceOf(String.class)))).atPosition(2).perform(click());

        onView(withId(R.id.waterCalculate)).perform(click());

        onView(withId(R.id.resultTextView))
                .check(matches(withText("3.41")));
    }

    private void WaterIntakeWithoutWeightTest() {
        onView(withId(R.id.inputWeight)).perform(clearText());
        onView(withId(R.id.spinnerActivity)).perform(click());
        onData(allOf(is(instanceOf(String.class)))).atPosition(1).perform(click());
        onView(withId(R.id.waterCalculate)).perform(click());
        onView(withText("Please, type Weight.")).inRoot(new ToastMatcher()).check(matches(isDisplayed()));
    }

    private void onSelectWaterIntake() {
        onView(withId(R.id.WaterIntake)).perform(click());
    }
}
