package com.vmlens.trace.agent.bootstrap.interleave.patterndetection;

import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.InterleaveAction;
import org.junit.Ignore;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static com.vmlens.trace.agent.bootstrap.interleave.InterleaveActionGuineaPig.action;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class DetectPatternTest {

    @Ignore
    @Test
    public void givenTwoElementsLoopOneElement() {
        // Given
        List<InterleaveAction> list =
                new LinkedList<>();
        list.add(action(0,"g"));
        list.add(action(0,"h"));

        for(int i = 0; i < 8; i++) {
            list.add(action(0,"a"));
            list.add(action(0,"b"));
            list.add(action(0,"c"));
        }

        list.add(action(0,"i"));
        list.add(action(0,"j"));

        InterleaveAction[] array = list.toArray(new InterleaveAction[]{});

        // When
        PatternKeyAndCount[] result = new DetectPattern(array).detect();

        // Then
        assertThat(result.length,is(3));
        assertPattern(result[0] , "abc" , 7);
        assertPattern(result[1] , "cab" , 6);
        assertPattern(result[2] , "bca" , 6);
    }

    @Ignore
    @Test
    public void givenSingleElementLoop() {
        // Given
        List<InterleaveAction> list =
                new LinkedList<>();
        list.add(action(0,"g"));
        list.add(action(0,"h"));

        for(int i = 0; i < 5; i++) {
            list.add(action(0,"a"));
            list.add(action(0,"a"));
            list.add(action(0,"a"));
        }

        list.add(action(0,"i"));
        list.add(action(0,"j"));

        InterleaveAction[] array = list.toArray(new InterleaveAction[]{});

        // When
        PatternKeyAndCount[] result = new DetectPattern(array).detect();

        // Then
        assertThat(result.length,is(5));
        assertPattern(result[0] , "aaaaa" , 6);
        assertPattern(result[1] , "aaaa" , 8);
        assertPattern(result[2] , "aaa" , 10);
    }

    @Ignore
    @Test
    public void givenMultipleLoops() {
        // Given
        List<InterleaveAction> list =
                new LinkedList<>();
        list.add(action(0,"g"));
        list.add(action(0,"h"));

        for(int i = 0; i < 5; i++) {
            list.add(action(0,"a"));
            list.add(action(0,"a"));
            list.add(action(0,"a"));
        }

        list.add(action(0,"i"));
        list.add(action(0,"j"));

        for(int i = 0; i < 12; i++) {
            list.add(action(0,"a"));
            list.add(action(0,"a"));
            list.add(action(0,"b"));
        }

        InterleaveAction[] array = list.toArray(new InterleaveAction[]{});

        // When
        PatternKeyAndCount[] result = new DetectPattern(array).detect();

        // Then
        assertThat(result.length,is(14));


        assertPattern(result[0] , "aabaabaab" , 7);
        assertPattern(result[1] , "abaabaaba" , 6);
        assertPattern(result[2] , "baabaabaa" , 6);
        assertPattern(result[3] , "aabaab" , 9);
    }

    private void assertPattern(PatternKeyAndCount patternKeyAndCount,String key, int count ) {
        assertThat(patternKeyAndCount.patternKey().toString(),is(key));
        assertThat(patternKeyAndCount.count(),is(count));
    }

}
