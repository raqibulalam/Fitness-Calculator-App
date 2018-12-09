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
public class MetasbolicActivityTest {

    MetabolicActivity bmrActivity;

    @Before
    public void before() {
        bmrActivity = new MetabolicActivity();
    }

    @Test
    @Parameters
    public void testCalculateBMR(double inWeight, double inHeight, int inAge, double expected) {
        assertEquals(expected, bmrActivity.calculateBMR(inWeight, inHeight, inAge), 0.0);
    }

    private Object[] parametersForTestCalculateBMR() {
        return new Object[]{
                new Object[]{100, 200, 27, 2115},
                new Object[]{75, 170, 15, 1737.5},
                new Object[]{45, 155, 15, 1343.75},
                new Object[]{0, 0, 0, 0}
        };
    }

    @Test
    @Parameters
    public void testCalculateBMRbaseOnSex(String sex, double bmr, double expected) {
        assertEquals(expected, bmrActivity.calculateBMRbaseOnSex(sex, bmr), 0.0);
    }

    private Object[] parametersForTestCalculateBMRbaseOnSex() {
        return new Object[]{
                new Object[]{"Male", 2115, 2120},
                new Object[]{"Female", 2115, 1954},
                new Object[]{"Female", 1737.5, 1576.5},
                new Object[]{"Male", 1737.5, 1742.5}
        };
    }
}