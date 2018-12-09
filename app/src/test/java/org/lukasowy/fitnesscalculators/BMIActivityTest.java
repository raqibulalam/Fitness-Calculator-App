package org.lukasowy.fitnesscalculators;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(JUnitParamsRunner.class)
public class BMIActivityTest {

    BMIActivity bmiActivity;

    @Before
    public void before() {

        bmiActivity = new BMIActivity();
    }

    @Test
    @Parameters
    public void testCalculateBMI(double inWeight, double inHeight, double expected) {
        assertEquals(expected, bmiActivity.calculateBMI(inWeight, inHeight), 0.0);
    }

    private Object[] parametersForTestCalculateBMI() {
        return new Object[]{
                new Object[]{34, 193, 9.13},
                new Object[]{45, 155, 18.73},
                new Object[]{45, 178, 14.2}
        };
    }

    @Test
    @Parameters
    public void testBMICategorization(String expected, double actual){
        assertEquals(expected, bmiActivity.BMICategorization(actual));
    }

    private Object[] parametersForTestBMICategorization() {
        return new Object[]{
                new Object[]{"Very severely underweight", 15},
                new Object[]{"Severely underweight", 15.5},
                new Object[]{"Underweight", 18},
                new Object[]{"Normal (healthy weight)", 22},
                new Object[]{"Overweight", 26},
                new Object[]{"Moderately obese", 30.5},
                new Object[]{"Severely obese", 35.1},
                new Object[]{"Very severely obese", 45},
                new Object[]{"Please, check the correctness of the input data", 0}
        };
    }
}