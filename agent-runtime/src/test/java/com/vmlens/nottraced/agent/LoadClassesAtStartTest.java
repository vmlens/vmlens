package com.vmlens.nottraced.agent;

import org.junit.Test;

public class LoadClassesAtStartTest {

    @Test
    public void allClassesCanBeLoaded() throws ClassNotFoundException {
        new LoadClassesAtStart().loadClasses();
    }

}
