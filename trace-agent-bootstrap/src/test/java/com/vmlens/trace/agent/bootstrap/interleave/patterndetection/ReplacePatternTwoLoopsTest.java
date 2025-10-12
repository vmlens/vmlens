package com.vmlens.trace.agent.bootstrap.interleave.patterndetection;

import com.vmlens.trace.agent.bootstrap.interleave.AbstractInterleaveActionBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.InterleaveAction;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static com.vmlens.trace.agent.bootstrap.interleave.InterleaveActionGuineaPig.action;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayContaining;

public class ReplacePatternTwoLoopsTest extends AbstractInterleaveActionBuilder {

    @Test
    public void oneElementTwoLoops() {
        // Given
        List<InterleaveAction> list =
                new LinkedList<>();
        list.add(action(0,"g"));
        for(int i = 0; i < 7; i++) {
            list.add(action(0,"a"));
        }
        list.add(action(0,"i"));
        for(int i = 0; i < 7; i++) {
            list.add(action(0,"a"));
        }

        InterleaveAction[] array = list.toArray(new InterleaveAction[]{});
        PatternKeyAndCount[] patternKeys = new PatternKeyAndCount[1];
        // PatternKeyAndCount{patternKey=abc, count=7}
        patternKeys[0] =  new PatternKeyAndCount(new PatternKey(array, 0, 1 , 1) , 12);
        // PatternKeyAndCount{patternKey=cab, count=6}


        // When
        InterleaveAction[] result = new ReplacePattern(array,patternKeys).replace();

        // Then
        InterleaveAction[] expected = buildArray();
        assertThat(result, arrayContaining(expected));
    }


    @Override
    protected void addActions() {
        guineaPig(0,"g");
        guineaPig(0,"a");
        noOp(0);
        noOp(0);
        noOp(0);
        noOp(0);
        noOp(0);
        guineaPig(0,"a");
        guineaPig(0,"i");
        guineaPig(0,"a");
        noOp(0);
        noOp(0);
        noOp(0);
        noOp(0);
        noOp(0);
        guineaPig(0,"a");
    }

}