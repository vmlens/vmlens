package com.vmlens.inttest.example;

import com.vmlens.api.AllInterleavings;
import org.junit.Test;

import static com.vmlens.api.Runner.runParallel;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TestExampleForRunner {

    @Test
    public void exampleTest()  {
        try (AllInterleavings allInterleavings = new AllInterleavings("exampleRunner")) {
            while (allInterleavings.hasNext()) {
                // recreate the object at each run
                ClassUnderTest classUnderTest = new ClassUnderTest();
                // runs methodA and methodB in different threads
                runParallel(classUnderTest::methodA,
                            classUnderTest::methodB);
                // check after both threads are joined
                assertThat(classUnderTest.isValid(),is(true))  ;
            }
        }
    }

}
