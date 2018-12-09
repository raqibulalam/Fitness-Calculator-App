# ![icon](https://user-images.githubusercontent.com/22128178/36096709-bff91f40-0ff7-11e8-81c3-a25e57ed211a.png) Fitness Calculator

Fitness Calculator is an Android application for calculating basic values such as BMI, BMR etc. The app provides a wealth of information on both fitness training and nutrition plan.

<p align="center">
  <br><br>
  <img src="http://recordit.co/tpAqbRc7lS.gif">
</p>

## Running the tests 

The main purpose of the unit test was to verify the correctness of the implementation methods. The JUnitParamsRunner class was one of the packages used in the project.

> JUnitParams project adds a new runner to JUnit and provides much easier and readable parametrised tests for JUnit >= 4.12. [Check info here](https://github.com/Pragmatists/JUnitParams)

#### One of the examples

```java
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
```
For more check the code.

## Built With

* [ExpandableTextView](https://github.com/Manabu-GT/ExpandableTextView)


## Author

* lukasowy

## License

This project is licensed under the  GNU GENERAL PUBLIC LICENSE - see the  [LICENSE.md](https://github.com/lukasowy/FitnessCalculators/blob/master/LICENSE) file for details

