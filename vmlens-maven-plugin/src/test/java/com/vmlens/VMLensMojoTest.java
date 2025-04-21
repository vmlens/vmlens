package com.vmlens;


import com.vmlens.report.ResultForVerify;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.vmlens.VMLensMojo.handleResult;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class VMLensMojoTest {

    @Test
    public void testFailure() {
        // Given
        ResultForVerify resultForVerify = new ResultForVerify();
        final MojoFailureException mojoFailureException = new MojoFailureException("test");
        Log log = mock(Log.class);
        // When
        MojoFailureException exception = assertThrows(MojoFailureException.class, () -> {
            handleResult(resultForVerify, new File("."), mojoFailureException,log);
        });

        // Then
        assertThat(exception.getMessage(),is("test"));
    }

}
