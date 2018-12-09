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

public class CreatineTest {
    public void creatineTest(){
        onSelectCreatine();

        creatineWithoutWeight();

        creatineWithWeight();
        
        slidingUp();

        slidingDown();

        pressToBack();
    }

    private void creatineWithoutWeight() {
        onView(withId(R.id.btnCalculate)).perform(click());
        onView(withText("Please, type correct Weight.")).inRoot(new ToastMatcher()).check(matches(isDisplayed()));

    }

    private void creatineWithWeight() {
        onView(withId(R.id.inputWeight)).perform(clearText(), typeText("100"));
        onView(withId(R.id.btnCalculate)).perform(click());
        onView(withId(R.id.resultTextView1)).check(matches(withText("Loading Dosage 17 g/day")));
        onView(withId(R.id.resultTextView2)).check(matches(withText("Maintenance Dosage 9 g/day")));
    }

    private void onSelectCreatine() {
        onView(withId(R.id.Creatine)).perform(click());
    }
}
