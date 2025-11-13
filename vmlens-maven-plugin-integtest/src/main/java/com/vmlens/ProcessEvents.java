package com.vmlens;

import com.anarsoft.race.detection.process.run.ProcessRunContext;
import com.anarsoft.race.detection.process.run.ProcessRunContextBuilder;
import com.vmlens.expected.check.BuildEventListMap;
import com.vmlens.expected.check.CheckLeftBeforeRight;
import com.vmlens.expected.check.CheckLockEnterExit;
import com.vmlens.expected.factory.TestCaseCollectionFactory;
import com.vmlens.report.LoopToDataRaceCount;
import com.vmlens.report.ResultForVerify;
import com.vmlens.report.assertion.EventForAssertion;

import java.io.File;
import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;

public class ProcessEvents {

    public static void main(String[] args) {
        process(new File("C:\\workspace\\vmlens\\test-cases\\target\\vmlens-agent\\vmlens"),
                new File("C:\\workspace\\vmlens\\test-cases\\target\\vmlens-report"));
    }

    static void process(File eventDirectory, File reportDirectory) {
        if(reportDirectory.getAbsolutePath().contains("jdk23")) {
            processJdk23(eventDirectory,reportDirectory);
        } else {
            processJdk11(eventDirectory,reportDirectory);
        }
    }

    private static void processJdk23(File eventDirectory, File reportDirectory) {
        ResultForVerify result = new com.anarsoft.race.detection.main.ProcessEvents(eventDirectory.toPath(),
                reportDirectory.toPath(),
                new ProcessRunContextBuilder().build()).process();
        checkDataRacesJdk23(result);
    }

    private static void processJdk11(File eventDirectory, File reportDirectory) {
        Map<String,Integer> loopNameToId = new HashMap<>();
        CheckLeftBeforeRight check = new CheckLeftBeforeRight(new TestCaseCollectionFactory().create(),
                loopNameToId);

        BuildEventListMap buildEventListMap = new BuildEventListMap();

        ResultForVerify result = new com.anarsoft.race.detection.main.ProcessEvents(eventDirectory.toPath(),
                reportDirectory.toPath(),
                new ProcessRunContext(check,
                        buildEventListMap,false,false,false)).process();
        checkDataRacesJdk11(result);

        int loopId = loopNameToId.get("testNoDataRace");
        Map<Integer, List<EventForAssertion>> loopIdToEventForAssertionList = buildEventListMap.build();
        List<EventForAssertion> list = loopIdToEventForAssertionList.get(loopId);
        new CheckLockEnterExit().check(list);
    }

    private static void checkDataRacesJdk11(ResultForVerify result ) {
        Set<String> testWithDataRace = new HashSet<>();
        testWithDataRace.add("staticField");
        testWithDataRace.add("arrayTest");
        testWithDataRace.add("hashMapTest");
        testWithDataRace.add("hashSetTest");
        testWithDataRace.add("writeWriteDataRace");
        testWithDataRace.add("putIfAbsentAndGetKeysWithSequenceNumber");
        testWithDataRace.add("hiero.testAdd");
        testWithDataRace.add("innerChild");
        testWithDataRace.add("whileLoop");
        testWithDataRace.add("testJacksonWithReader");
        testWithDataRace.add("testJackson");
        testWithDataRace.add("testNonVolatileField");
        testWithDataRace.add("testDataRace");

       // testWithDataRace.add("childWithProtectedFieldTest");
        checkDataRaces(testWithDataRace,new HashSet<>() , result);
    }

    private static void checkDataRacesJdk23(ResultForVerify result ) {
        Set<String> testWithDataRace = new HashSet<>();
        testWithDataRace.add("qBeanSupportConcurrentTest");
        testWithDataRace.add("loop.petersonNok");
        testWithDataRace.add("loop.nok");
        testWithDataRace.add("testCacheLongKeyLIRSVMLens.testConcurrent");
        testWithDataRace.add("jdbcJobExecutionDao");

        Set<String> filter
                = new HashSet<>();
        // not sure why this is happening
       // filter.add("jdbcJobExecutionDao");
        checkDataRaces(testWithDataRace,filter,result);
    }

    private static void checkDataRaces(Set<String> testWithDataRace,
                                       Set<String> filter,
                                       ResultForVerify result) {
        for(LoopToDataRaceCount loopToDataRaceCount : result.loopToDataRaceCountList()) {
            if(! testWithDataRace.contains(loopToDataRaceCount.loopName())) {
                if( ! filter.contains(loopToDataRaceCount.loopName())) {
                    throw new RuntimeException("not expected data race in " + loopToDataRaceCount.loopName() );

                }
            }
            testWithDataRace.remove(loopToDataRaceCount.loopName());
        }
        assertThat(testWithDataRace,is(empty()));
    }
}
