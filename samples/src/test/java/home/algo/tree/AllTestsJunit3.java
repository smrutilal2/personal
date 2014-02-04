package home.algo.tree;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AllTestsJunit3 extends TestCase
{

    public static Test suite()
    {
        TestSuite suite = new TestSuite(AllTestsJunit3.class.getName());
        // $JUnit-BEGIN$

        // $JUnit-END$
        suite.addTest(new CalculatorTest());
        return suite;
    }

}
