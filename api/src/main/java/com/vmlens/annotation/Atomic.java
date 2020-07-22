package com.vmlens.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * Marks a class as atomic. This means that all methods of this class are atomic.
 * A method is atomic when the method call appears to take effect instantaneously. So other threads either see the state before or after the method call but no intermediate state.
 * 
 * 
 * This annotation tells the class {@link com.vmlens.api.AllInterleavings} that it does not look inside the methods of this class when calculating all thread interleavings.
 * This reduces the amount of thread interleavings generated, often dramatically.
 * 
 * @see Callback
 * 
 * 
 *
 */


@Target(value={ElementType.TYPE})
@Retention(value=RetentionPolicy.CLASS)
public @interface Atomic {

}
