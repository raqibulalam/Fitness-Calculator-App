package org.lukasowy.fitnesscalculators;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainInstrumentedTest {

    public BMITest bmiTest;
    public WaterIntakeTest waterIntakeTest;
    public MaxHeartRateTest maxHeartRateTest;
    public MetabolicTest metabolicTest;
    public ProteinTest proteinTest;
    public CreatineTest creatineTest;

    @Before
    public void before() {
        bmiTest = new BMITest();
        waterIntakeTest = new WaterIntakeTest();
        maxHeartRateTest = new MaxHeartRateTest();
        metabolicTest = new MetabolicTest();
        proteinTest = new ProteinTest();
        creatineTest = new CreatineTest();
    }

    @Rule
    public ActivityTestRule mActivityRule =
            new ActivityTestRule(MainActivity.class);

    @Test
    public void mainTest() throws InterruptedException {
        bmiTest.BMITest();
        waterIntakeTest.waterIntakeTest();
        maxHeartRateTest.maxHeartRateTest();
        metabolicTest.metabolicTest();
        proteinTest.proteinTest();
        creatineTest.creatineTest();
    }


}
