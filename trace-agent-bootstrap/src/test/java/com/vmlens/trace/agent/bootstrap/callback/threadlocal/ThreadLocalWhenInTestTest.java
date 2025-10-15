package com.vmlens.trace.agent.bootstrap.callback.threadlocal;

import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadForParallelize;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ThreadLocalWhenInTestTest {

    @Test
    public void startDoNotTrace() {
        ThreadLocalWhenInTest threadLocalWhenInTest = new ThreadLocalWhenInTest(null , 1 ,null);
        ThreadForParallelize threadForParallelize = new ThreadForParallelize(null);
        recursive(threadForParallelize,threadLocalWhenInTest,0);
    }

    public void recursive(ThreadForParallelize threadForParallelize,
                          ThreadLocalWhenInTest threadLocalWhenInTest,
                          int count) {
        if(count == 3) {
            threadLocalWhenInTest.startDoNotTrace(threadForParallelize);
        }
        if(count == 8) {
            threadLocalWhenInTest.startDoNotTrace(threadForParallelize);
        }
        if(count == 11) {
            threadLocalWhenInTest.startDoNotTrace(threadForParallelize);
        }
        if(count < 12) {
            recursive(threadForParallelize, threadLocalWhenInTest,count+1);
        }
        if(count > 4) {
            assertThat(threadLocalWhenInTest.processAction(threadForParallelize) ,is(false));
        }


        if(count < 3) {
            assertThat(threadLocalWhenInTest.processAction(threadForParallelize) ,is(true));
        }
    }

}
