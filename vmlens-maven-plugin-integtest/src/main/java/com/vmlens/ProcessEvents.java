package com.vmlens;

import com.anarsoft.race.detection.process.run.ProcessRunContext;
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
        Map<String,Integer> loopNameToId = new HashMap<>();
        CheckLeftBeforeRight check = new CheckLeftBeforeRight(new TestCaseCollectionFactory().create(),
                loopNameToId);

        BuildEventListMap buildEventListMap = new BuildEventListMap();

        ResultForVerify result = new com.anarsoft.race.detection.main.ProcessEvents(eventDirectory.toPath(),
                reportDirectory.toPath(),
                new ProcessRunContext(check,
                buildEventListMap,false,false,false)).process();
        checkDataRaces(result);

        int loopId = loopNameToId.get("readWriteLockTest");
        Map<Integer, List<EventForAssertion>> loopIdToEventForAssertionList = buildEventListMap.build();
        List<EventForAssertion> list = loopIdToEventForAssertionList.get(loopId);
        new CheckLockEnterExit().check(list);
    }

    private static void checkDataRaces(ResultForVerify result ) {
        Set<String> testWithDataRace = new HashSet<>();
        testWithDataRace.add("staticField");
        testWithDataRace.add("arrayTest");
        testWithDataRace.add("hashMapTest");
        testWithDataRace.add("hashSetTest");
        testWithDataRace.add("writeWriteDataRace");
        testWithDataRace.add("putIfAbsentAndGetKeysWithSequenceNumber");
        checkDataRaces(testWithDataRace,result);
    }

    private static void checkDataRaces(Set<String> testWithDataRace, ResultForVerify result) {
        for(LoopToDataRaceCount loopToDataRaceCount : result.loopToDataRaceCountList()) {
            if(! testWithDataRace.contains(loopToDataRaceCount.loopName())) {
                throw new RuntimeException("not expected data race in " + loopToDataRaceCount.loopName() );
            }
            testWithDataRace.remove(loopToDataRaceCount.loopName());
        }
        assertThat(testWithDataRace,is(empty()));
    }
}
