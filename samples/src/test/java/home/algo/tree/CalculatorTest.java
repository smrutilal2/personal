package home.algo.tree;

import junit.framework.TestCase;

public class CalculatorTest extends TestCase
{

    private Calculator calculator = new Calculator();

    // @Before
    public void setup()
    {
        calculator = new Calculator();
    }

    // @After
    public void teardown()
    {
        calculator = null;
    }

    // @Test
    public void testAdditionOfTwoIntegers()
    {

        Integer value = calculator.add(3, 5);
        assertNotNull("Shouldnot return null", value);
        assertTrue(8 == value);
    }

    // @Test
    public void testSubstractionOfTwoNumbers()
    {
        int value = calculator.substract(5, 2);
        assertTrue(3 == value);
    }

    // @Test
    public void testDivisionOfTwoNumbers() throws Exception
    {
        float value = calculator.devide(5, 2);
        assertTrue(2.5 == value);
    }

    // @Test
    public void testDivisionByZero()
    {
        try
        {
            calculator.devide(5, 0);
            fail("Should not devide by zero ");
        }
        catch (NumberFormatException exception)
        {
            // expected
        }
        catch (Exception e)
        {
            // expected
        }
    }

}
