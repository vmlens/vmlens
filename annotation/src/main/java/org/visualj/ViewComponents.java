package org.visualj;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
public @interface ViewComponents {
    ViewComponent[] value();
}
