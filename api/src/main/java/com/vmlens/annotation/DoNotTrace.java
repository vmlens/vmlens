package com.vmlens.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;



/**
 * 
 * The com.vmlens.annotation.DoNotTrace annotation lets you define methods that should not be traced by vmlens. 
 * This allows you to enforce a specific ordering of your thread without influencing the data race detection of vmlens. 
 * In the following example, the CountDownLatch creates a happens-before relation between the i++ in the methods updateBefore and updateAfter:
 *  <pre>
 public class UpdateWithDoNotTrace {
    private int i = 0;
    final   CountDownLatch countDownLatch = 
        new CountDownLatch(1);  
    &#64;DoNotTrace
    private void signal() {
        countDownLatch.countDown();
    }
    &#64;DoNotTrace
    private void wait4Signal() 
            throws InterruptedException {
        countDownLatch.await();
    }
    public void updateBefore() {
        i++;
        signal();
    }
    public void updateAfter() 
            throws InterruptedException {
        wait4Signal();
        i++;
    }
}
 *</pre>
 * 
 *
 */


@Target(value={ElementType.METHOD})
@Retention(value=RetentionPolicy.CLASS)
public @interface  DoNotTrace {

}
