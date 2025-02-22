package org.visualj;


import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Target;

import static org.visualj.ComponentType.DEFAULT;

@Target(ElementType.TYPE)
@Repeatable(ViewComponents.class)
public @interface ViewComponent {

    String value();

    ComponentType type() default DEFAULT;

}
