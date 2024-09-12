package com.anarsoft.trace.agent.runtime;

import org.junit.Test;

public class LoadClassesAtStartTest {

    @Test
    public void allClassesCanBeLoaded() throws ClassNotFoundException {
        new LoadClassesAtStart().loadClasses();
        ;
    }


}
