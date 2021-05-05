package com.vmlens.trace.agent.bootstrap.interleave;

import com.vmlens.trace.agent.bootstrap.interleave.syncAction.TLinkableForSyncAction;
import static com.vmlens.trace.agent.bootstrap.interleave.syncAction.TLinkableForSyncAction.linked;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import gnu.trove.list.linked.TLinkedList;
import static com.vmlens.trace.agent.bootstrap.interleave.createThreadIds.TestPartialOrder.*;


public class TestInterleaveAlgorihm {
	
	
	private TLinkedList<TLinkableForSyncAction> createSyncActionList() {
		TLinkedList<TLinkableForSyncAction> syncActionList = new TLinkedList<TLinkableForSyncAction>();
		syncActionList.add(linked(READ_THREAD_0));
		syncActionList.add(linked(READ_THREAD_1));
		syncActionList.add(linked(WRITE_THREAD_0));
		syncActionList.add(linked(WRITE_THREAD_1));
		
		return syncActionList;
	}
	
	@Test
	public void testVolatileReadWrite() {
		
		
		int count = 0;
		Set<String> result = new HashSet<String>();
		
		
		InterleaveAlgorihm interleaveAlgorihm = new InterleaveAlgorihm();
		interleaveAlgorihm.add(createSyncActionList());
		
		while( interleaveAlgorihm.hasNext() ) {
			
			result.add( Arrays.toString(	interleaveAlgorihm.next() ));
			count++;
		}
		
		assertEquals(3 , count);
		Set<String> expected = new HashSet<String>();
		expected.add("[R_0_0, W_0_1, R_1_0, W_1_1]");
		expected.add("[R_0_0, R_1_0, W_0_1, W_1_1]");
		expected.add("[R_1_0, W_1_1, R_0_0, W_0_1]");
		
		assertEquals( expected , result );
	}
	
	
	

}
