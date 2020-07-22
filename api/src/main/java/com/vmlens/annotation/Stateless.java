package com.vmlens.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * Marks this class as stateless.
 * 
 * 
 */


@Target(value={ElementType.TYPE})
@Retention(value=RetentionPolicy.CLASS)
public @interface Stateless {
	
	String[]  value() default {};
	

}
