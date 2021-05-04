package com.vmlens.trace.agent.bootstrap.interleave.createThreadIds;

import static com.vmlens.trace.agent.bootstrap.interleave.syncAction.TLinkableForSyncAction.linked;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Arrays;
import static com.vmlens.trace.agent.bootstrap.interleave.createThreadIds.TestPartialOrder.*;

import static  com.vmlens.trace.agent.bootstrap.interleave.createThreadIds.TLinkableForLeftBeforeRight.linked;
import org.junit.Test;

import com.vmlens.trace.agent.bootstrap.callback.field.MemoryAccessType;
import com.vmlens.trace.agent.bootstrap.interleave.syncAction.TLinkableForSyncAction;
import com.vmlens.trace.agent.bootstrap.interleave.syncAction.VolatileFieldAccess;

import gnu.trove.list.linked.TLinkedList;

public class TestSortThreadIds {
	
	
	public static final TLinkableForSyncAction[] TWO_THREADS =  new TLinkableForSyncAction[] {  linked(READ_THREAD_0) ,  linked(WRITE_THREAD_0) ,   linked(READ_THREAD_1) , linked(WRITE_THREAD_1)};
	
	
	public static VolatileFieldAccess READ_THREAD_2 = new VolatileFieldAccess(MemoryAccessType.IS_READ,1,2,0);
	public static VolatileFieldAccess WRITE_THREAD_2 = new VolatileFieldAccess(MemoryAccessType.IS_WRITE,1,2,1);
	
	
	private static final TLinkableForSyncAction[] THREE_THREADS =  new TLinkableForSyncAction[] {  linked(READ_THREAD_0) ,  linked(WRITE_THREAD_0) ,   linked(READ_THREAD_1) , linked(WRITE_THREAD_1) , linked(READ_THREAD_2) ,  linked(WRITE_THREAD_2) };
	
	
	
	@Test
	public void testReadBeforeWrite() {
		
		PartialOrder partialOrder = new PartialOrder(READ_BEFORE_WRITE);
		
		
			
		TLinkableForSyncAction[] result = new SortThreadIds().sort(partialOrder, TWO_THREADS);
		
	    assertEquals(  "[R_0, R_1, W_0, W_1]" ,    Arrays.toString(	result) );
		
	}
	
	
	public static final TLinkableForLeftBeforeRight[] THREAD_0_BEFORE_THREAD_1 = new TLinkableForLeftBeforeRight[] {
			linked( new LeftBeforeRight(READ_THREAD_0,WRITE_THREAD_1) ) ,
			linked( new LeftBeforeRight( WRITE_THREAD_0 , READ_THREAD_1 )   ) };
	
	
	
	// Test add after
	
	@Test
	public void testThread0BeforeThread1() {
		
		PartialOrder partialOrder = new PartialOrder(THREAD_0_BEFORE_THREAD_1);
	
			
        TLinkableForSyncAction[] result = new SortThreadIds().sort(partialOrder, TWO_THREADS);
		
	    assertEquals(  "[R_0, W_0, R_1, W_1]" ,    Arrays.toString(	result ) );
		
	}
	
	
	
	// Test Loop
	
	public static final TLinkableForLeftBeforeRight[]  LOOP_BOTH_READS_AFTER_WRITE = new TLinkableForLeftBeforeRight[] {
			linked( new LeftBeforeRight(WRITE_THREAD_1 , READ_THREAD_0)) ,
			linked(new LeftBeforeRight( WRITE_THREAD_0 , READ_THREAD_1)) };
	
	@Test
	public void testLoop() {
		
		PartialOrder partialOrder = new PartialOrder(LOOP_BOTH_READS_AFTER_WRITE);
		
			
		  TLinkableForSyncAction[]  result = new SortThreadIds().sort(partialOrder, TWO_THREADS);
		
	    assertNull(   result);
		
	}
	
	
	
	
	
	// Test three indices
	
	public static final TLinkableForLeftBeforeRight[] THREAD_1_AFTER_THREAD_0_AND_THREAD_2 = new TLinkableForLeftBeforeRight[] {
			linked(new LeftBeforeRight(READ_THREAD_0,WRITE_THREAD_1)) ,
			linked(new LeftBeforeRight( WRITE_THREAD_0 , READ_THREAD_1)    ),
			linked(new LeftBeforeRight(READ_THREAD_2,WRITE_THREAD_1)) ,
			linked(new LeftBeforeRight( WRITE_THREAD_2 , READ_THREAD_1)  ) };
	
	
	@Test
	public void testThreeThreads() {
		
		PartialOrder partialOrder = new PartialOrder(THREAD_1_AFTER_THREAD_0_AND_THREAD_2);
		
			
		  TLinkableForSyncAction[]  result = new SortThreadIds().sort(partialOrder, THREE_THREADS);
		
		  assertEquals(  "[R_0, W_0, R_2, W_2, R_1, W_1]" ,    Arrays.toString(	result ) );
		
	}
	
	
	
	
}
