package com.vmlens.trace.agent.bootstrap.interleave.createPotentialOrderList;

import static com.vmlens.trace.agent.bootstrap.interleave.createThreadIds.TestPartialOrder.READ_THREAD_0;
import static com.vmlens.trace.agent.bootstrap.interleave.createThreadIds.TestPartialOrder.READ_THREAD_1;
import static com.vmlens.trace.agent.bootstrap.interleave.createThreadIds.TestPartialOrder.WRITE_THREAD_0;
import static com.vmlens.trace.agent.bootstrap.interleave.createThreadIds.TestPartialOrder.WRITE_THREAD_1;
import static com.vmlens.trace.agent.bootstrap.interleave.syncAction.TLinkableForSyncAction.linked;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import com.vmlens.trace.agent.bootstrap.interleave.syncAction.TLinkableForSyncAction;

public class TestComparatorSyncActionByCategoryAndIndex {
	
	@Test
	public void testSort() {
		
		TLinkableForSyncAction[] array = new TLinkableForSyncAction[] {  linked(READ_THREAD_0) ,  linked(READ_THREAD_1) , linked(WRITE_THREAD_0) ,   linked(WRITE_THREAD_1)};
			
		
		Arrays.sort( array , new ComparatorSyncActionByCategoryAndIndex() );
		
		   assertEquals(  "[R_0_0, W_0_1, R_1_0, W_1_1]" ,    Arrays.toString(	array ) );
		
	}
	

}
