package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertreebuilder;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.AlternativeNoOrder;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class ChoiceAlternativeTest {

    @Test
    public void lengthWhenNull() {
        // Given
        ChoiceAlternative choiceAlternative = new ChoiceAlternative();

        // When
        int length = choiceAlternative.getLength();

        // Then
        assertThat(length,is(0));
    }

    @Test
    public void lengthWhenTwo() {
        // Given
        ChoiceAlternative choiceAlternative = new ChoiceAlternative();
        choiceAlternative.either(new AlternativeNoOrder(true),new AlternativeNoOrder(true)).
        either(new AlternativeNoOrder(true),new AlternativeNoOrder(true));

        // When
        int length = choiceAlternative.getLength();

        // Then
        assertThat(length,is(2));
    }

    @Test
    public void fillWhenNull() {
        // Given
        ChoiceAlternative choiceAlternative = new ChoiceAlternative();

        // When
        choiceAlternative.fill(2);

        // Then
        assertThat(choiceAlternative.next().getNext(),notNullValue());
        assertThat(choiceAlternative.next().getNext().getNext(),nullValue());
    }

    @Test
    public void fillWhenOneValue() {
        // Given
        ChoiceAlternative choiceAlternative = new ChoiceAlternative();
        choiceAlternative.either(new AlternativeNoOrder(true),new AlternativeNoOrder(true));

        // When
        choiceAlternative.fill(2);

        // Then
        assertThat(choiceAlternative.next().getNext(),notNullValue());
        assertThat(choiceAlternative.next().getNext().getNext(),nullValue());
    }


}
