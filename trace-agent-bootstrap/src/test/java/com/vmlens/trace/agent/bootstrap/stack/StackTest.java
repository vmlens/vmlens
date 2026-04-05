package com.vmlens.trace.agent.bootstrap.stack;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;



public class StackTest {

    @Test
    public void emptyAndEnter() {
        Stack stack = new Stack();
        assertThat(stack.shouldProcess(), is(true));
        stack.pushPreAnalyzed();
        assertThat(stack.shouldProcess(), is(false));
    }

    @Test
    public void preAnalyzedAndCallback() {
        Stack stack = new Stack();
        stack.pushPreAnalyzed();
        stack.pushCallback();
        assertThat(stack.shouldProcess(), is(true));
    }

    @Test
    public void multiplePreAnalyzedAndCallback() {
        Stack stack = new Stack();
        stack.pushPreAnalyzed();
        stack.pushPreAnalyzed();
        stack.pushCallback();
        assertThat(stack.shouldProcess(), is(false));
    }

    @Test
    public void multiple() {
        Stack stack = new Stack();
        stack.pushPreAnalyzed();
        stack.pushCallback();
        stack.pushPreAnalyzed();
        stack.pushCallback();
        stack.pushCallback();
        assertThat(stack.shouldProcess(), is(true));
    }

    @Test
    public void pop() {
        Stack stack = new Stack();
        stack.pushPreAnalyzed();
        stack.popPreAnalyzed();
        assertThat(stack.size(),is(0));
    }

    @Test
    public void popWitException() {
        Stack stack = new Stack();
        stack.pushPreAnalyzed();
        stack.pushCallback();
        stack.pushCallback();
        stack.pushCallback();
        stack.popPreAnalyzed();
        assertThat(stack.size(),is(0));
    }



}
