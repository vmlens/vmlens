package com.vmlens.trace.agent.bootstrap.parallize.logic;

import static org.junit.Assert.*;

import org.junit.Test;

import com.vmlens.trace.agent.bootstrap.parallize.logicState.DecisionActive;
import com.vmlens.trace.agent.bootstrap.parallize.logicState.DecisionQueue;
import com.vmlens.trace.agent.bootstrap.parallize.logicState.DecisionWaiting;

public class DecisionQueueTest {

	@Test
	public void testClone() {
		
		DecisionQueue decisionQueue = new DecisionQueue();
		
		DecisionActive first = new DecisionActive();
		DecisionWaiting second = new DecisionWaiting(1);
		
		decisionQueue.enqueue( first  );
		decisionQueue.enqueue( second  );
		
		
		DecisionQueue  clone = decisionQueue.cloneQueue();
		
		assertEquals( first ,  clone.dequeue() );
		assertEquals( second ,  clone.dequeue() );
		
		
	}

}
