package com.vmlens.trace.agent.bootstrap.interleave.patterndetection;

import com.vmlens.trace.agent.bootstrap.interleave.AbstractInterleaveActionBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.InterleaveAction;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static com.vmlens.trace.agent.bootstrap.interleave.InterleaveActionGuineaPig.action;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayContaining;

public class ReplacePatternTest extends AbstractInterleaveActionBuilder {

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

        PatternKeyAndCount[] patternKeys = new PatternKeyAndCount[2];
        // PatternKeyAndCount{patternKey=abc, count=7}
        patternKeys[0] =  new PatternKeyAndCount(new PatternKey(array, 0, 2 , 3) , 7);
        // PatternKeyAndCount{patternKey=cab, count=6}
        patternKeys[1] = new PatternKeyAndCount(new PatternKey(array, 0, 4 , 3) , 6);

        // When
        InterleaveAction[] result = new ReplacePattern(array,patternKeys).replace();

        // Then
        InterleaveAction[] expected = buildArray();
        assertThat(result, arrayContaining(expected));
    }


    @Override
    protected void addActions() {
        guineaPig(0,"g");
        guineaPig(0,"h");
        guineaPig(0,"a");
        guineaPig(0,"b");
        guineaPig(0,"c");
        noOp(0);
        noOp(0);
        noOp(0);
        noOp(0);
        noOp(0);
        noOp(0);
        guineaPig(0,"a");
        guineaPig(0,"b");
        guineaPig(0,"c");
        noOp(0);
        noOp(0);
        noOp(0);
        noOp(0);
        noOp(0);
        noOp(0);
        noOp(0);
        noOp(0);
        noOp(0);
        guineaPig(0,"a");
        guineaPig(0,"b");
        guineaPig(0,"c");
        guineaPig(0,"i");
        guineaPig(0,"j");
    }
}
