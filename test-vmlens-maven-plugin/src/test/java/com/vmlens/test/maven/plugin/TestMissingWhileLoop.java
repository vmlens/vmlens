package com.vmlens.test.maven.plugin;

import com.vmlens.api.AllInterleavings;
import org.junit.jupiter.api.Test;

public class TestMissingWhileLoop {

    @Test
    public void testMissingWhileLoop() {
        try(AllInterleavings allInterleavings = new AllInterleavings("testMissingWhileLoop")) {

        }
    }

}
