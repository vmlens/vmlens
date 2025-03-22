package com.vmlens;

import com.vmlens.report.VerifyResult;
import org.apache.maven.plugin.MojoFailureException;
import org.junit.Test;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class VerifyMojoTest {

    @Test
    public void handleResultNothing() throws MojoFailureException {
        VerifyMojo.handleResult(new VerifyResult(0,0), new File("aab/ddd"));
    }

    @Test
    public void handleResultDataRaceAndFailure() {
        try {
            VerifyMojo.handleResult(new VerifyResult(3, 6), new File("aab/ddd"));
        }
        catch (MojoFailureException exp) {
            assertThat(exp.getMessage(),
                    is("There are 3 test failures and 6 data races, see aab\\ddd for the test report."));
        }
    }

    @Test
    public void handleResultDataRace() {
        try {
            VerifyMojo.handleResult(new VerifyResult(0, 6), new File("aab/ddd"));
        }
        catch (MojoFailureException exp) {
            assertThat(exp.getMessage(),
                    is("There are 6 data races, see aab\\ddd for the report."));
        }
    }

    @Test
    public void handleResultFailure() {
        try {
            VerifyMojo.handleResult(new VerifyResult(5, 0), new File("aab/ddd"));
        }
        catch (MojoFailureException exp) {
            assertThat(exp.getMessage(),
                    is("There are 5 test failures, see aab\\ddd for the report."));
        }
    }


}
