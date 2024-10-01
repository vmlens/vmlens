package com.vmlens.report.createreport;

import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

public class CopyDescriptionFromResourceTest {

    @Test
    public void load() throws IOException {
        CopyDescription[] array = new CopyDescriptionFromResource().load();
        assertThat(array.length, greaterThan(1));
    }

}
