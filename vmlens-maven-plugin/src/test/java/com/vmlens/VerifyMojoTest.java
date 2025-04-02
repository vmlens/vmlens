package com.vmlens;

import com.vmlens.report.ResultForVerify;
import org.apache.maven.plugin.MojoFailureException;
import org.junit.Test;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class VerifyMojoTest {

    @Test
    public void handleResultNothing() throws MojoFailureException {
        VerifyMojo.handleResult(new ResultForVerify(), new File("aab/ddd"));
    }

    @Test
    public void handleResultDataRaceAndFailure() {
        try {
            ResultForVerify resultForVerify = mock(ResultForVerify.class);
            when(resultForVerify.dataRaceCount()).thenReturn(6);
            when(resultForVerify.failureCount()).thenReturn(3);

            VerifyMojo.handleResult(resultForVerify, new File("aab/ddd"));
        }
        catch (MojoFailureException exp) {
            assertThat(exp.getMessage(),
                    is("There are 3 test failures and 6 data races, see aab\\ddd for the test report."));
        }
    }

    @Test
    public void handleResultDataRace() {
        try {
            ResultForVerify resultForVerify = mock(ResultForVerify.class);
            when(resultForVerify.dataRaceCount()).thenReturn(6);

            VerifyMojo.handleResult(resultForVerify, new File("aab/ddd"));
        }
        catch (MojoFailureException exp) {
            assertThat(exp.getMessage(),
                    is("There are 6 data races, see aab\\ddd for the report."));
        }
    }

    @Test
    public void handleResultFailure() {
        try {
            ResultForVerify resultForVerify = mock(ResultForVerify.class);
            when(resultForVerify.failureCount()).thenReturn(5);

            VerifyMojo.handleResult(resultForVerify, new File("aab/ddd"));
        }
        catch (MojoFailureException exp) {
            assertThat(exp.getMessage(),
                    is("There are 5 test failures, see aab\\ddd for the report."));
        }
    }


}
