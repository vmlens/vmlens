package com.vmlens.inttest;

import com.vmlens.api.AllInterleavings;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.vmlens.api.AllInterleavings.doNotTrace;
import static com.vmlens.api.Runner.runParallel;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class TestABAStack {

    @Test
    public void testUpdate() throws InterruptedException {
        AssertionError actualException = null;
        try {
        AllInterleavings testUpdate = new AllInterleavings("testABAStack");
        while (testUpdate.hasNext()) {
            List<Integer> list = new LinkedList<>();
            ABAStack abaStack = new ABAStack();
            abaStack.push(1);
            abaStack.push(2);
            runParallel(
                    () -> abaStack.push(3),
                    () -> {
                        Node result = abaStack.pop();
                        if(result != null) {
                            doNotTrace(() -> {list.add(result.getValue());});
                        }
                        Node second = abaStack.pop();
                        if(result != null) {
                            doNotTrace(() -> {list.add(second.getValue());});
                        }
                        abaStack.push(4); });
            Node result = abaStack.pop();
            while (result != null) {
                list.add(result.getValue());
                result = abaStack.pop();
            }
            assertThat(list,containsInAnyOrder(1,2,3,4));
        }
        }
        catch (AssertionError exp) {
            actualException = exp;
        }
        assertThat(actualException, instanceOf(AssertionError.class));
    }

}
