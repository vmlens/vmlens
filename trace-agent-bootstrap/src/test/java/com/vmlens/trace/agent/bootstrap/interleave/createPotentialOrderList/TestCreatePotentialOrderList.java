package com.vmlens.trace.agent.bootstrap.interleave.createPotentialOrderList;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import com.vmlens.trace.agent.bootstrap.interleave.createThreadIds.TestSortThreadIds;
import com.vmlens.trace.agent.bootstrap.interleave.potentialOrder.TLinkableForPotentialOrder;
import com.vmlens.trace.agent.bootstrap.interleave.syncAction.TLinkableForSyncAction;
import static com.vmlens.trace.agent.bootstrap.interleave.syncAction.TLinkableForSyncAction.linked;
import static com.vmlens.trace.agent.bootstrap.interleave.createThreadIds.TestPartialOrder.*;


public class TestCreatePotentialOrderList {
	
	@Test
	public void testReadOnly() {
		TLinkableForSyncAction[] syncActions = new TLinkableForSyncAction[] {linked(READ_THREAD_0) ,   linked(READ_THREAD_1) };
		TLinkableForPotentialOrder[] result = new CreatePotentialOrderList().create(syncActions).createPotential();
		
		assertEquals(0 ,  result.length );
		
	}
	
	
	
	@Test
	public void testReadWrite() {
		TLinkableForSyncAction[] syncActions = TestSortThreadIds.TWO_THREADS;
		TLinkableForPotentialOrder[] result = new CreatePotentialOrderList().create(syncActions).createPotential();
		
		assertEquals( "[(0,0<1,1)|(1,1<0,0), (0,1<1,0)|(1,0<0,1)]" ,  Arrays.toString(result) );
		

	}
	

}
