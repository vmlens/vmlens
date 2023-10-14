package com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PermutationIteratorTest {

    @Test
    public void testLenghtZero() {
        runTest(0, new HashSet<String>());
    }

    @Test
    public void testLengthOne() {
        Set<String> expected = new HashSet<>();
        addAlternatives("", 1, expected);
        runTest(1,expected);
    }

    @Test
    public void testLengthSeven() {
        Set<String> expected = new HashSet<>();
        addAlternatives("",7,expected);
        runTest(7,expected);
    }

    private void addAlternatives(String parent,int depth, Set<String> elements) {
        if( depth > 0 ) {
            addAlternatives(parent + "1" , depth - 1 , elements);
            addAlternatives(parent + "0" , depth - 1 , elements);
        }
        else {
            elements.add(parent);
        }
    }
    
    private void runTest(int length, Set<String> expected) {
        PermutationIterator permutationIterator = new PermutationIterator(length);
        Set<String> actual = new HashSet<>();
        while (permutationIterator.hasNext()) {
            String permutation="";
            for(int i = 0 ; i < length; i++) {
                if(permutationIterator.at(i)) {
                    permutation += "1";
                } else {
                    permutation += "0";
                }
            }
            actual.add(permutation);
            permutationIterator.advance();
        }
        assertThat(actual,is(expected));
    }


}
