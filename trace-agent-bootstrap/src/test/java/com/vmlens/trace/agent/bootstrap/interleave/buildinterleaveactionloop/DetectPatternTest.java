package com.vmlens.trace.agent.bootstrap.interleave.buildinterleaveactionloop;

import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.InterleaveAction;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static com.vmlens.trace.agent.bootstrap.interleave.buildinterleaveactionloop.InterleaveActionGuineaPig.action;

public class DetectPatternTest {

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
        //assertThat(map.size(),is(1));
        for( PatternKeyAndCount entry :result) {
            System.out.println(entry);
        }
    }

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
        //assertThat(map.size(),is(1));
        for( PatternKeyAndCount entry :result) {
            System.out.println(entry);
        }
    }

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
        //assertThat(map.size(),is(1));
        for( PatternKeyAndCount entry :result) {
            System.out.println(entry);
        }
    }

}
