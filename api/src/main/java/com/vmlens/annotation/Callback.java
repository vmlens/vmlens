package com.vmlens.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks that a method of an atomic class calls a callback method. 
 * 
 * @see Atomic
 * 
 */


@Target(value={ElementType.METHOD})
@Retention(value=RetentionPolicy.CLASS)
public @interface Callback {

	Class value();
	
}
