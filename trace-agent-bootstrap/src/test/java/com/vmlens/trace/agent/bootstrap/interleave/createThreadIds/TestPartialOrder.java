package com.vmlens.trace.agent.bootstrap.interleave.createThreadIds;

import static  com.vmlens.trace.agent.bootstrap.interleave.createThreadIds.TLinkableForLeftBeforeRight.linked;
import static com.vmlens.trace.agent.bootstrap.interleave.syncAction.TLinkableForSyncAction.linked;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.vmlens.trace.agent.bootstrap.callback.field.MemoryAccessType;
import com.vmlens.trace.agent.bootstrap.interleave.syncAction.VolatileFieldAccess;


public class TestPartialOrder {
	
	
	public static VolatileFieldAccess READ_THREAD_0 = new VolatileFieldAccess(MemoryAccessType.IS_READ,1,0,0);
	public static VolatileFieldAccess READ_THREAD_1 = new VolatileFieldAccess(MemoryAccessType.IS_READ,1,1,0);
	
	public static VolatileFieldAccess WRITE_THREAD_0 = new VolatileFieldAccess(MemoryAccessType.IS_WRITE,1,0,1);
	public static VolatileFieldAccess WRITE_THREAD_1 = new VolatileFieldAccess(MemoryAccessType.IS_WRITE,1,1,1);
	
	
	
	
	
	
	public static final TLinkableForLeftBeforeRight[] READ_BEFORE_WRITE = new TLinkableForLeftBeforeRight[] {
			linked(new LeftBeforeRight(READ_THREAD_0,WRITE_THREAD_1)) ,
			linked(new LeftBeforeRight( READ_THREAD_1 , WRITE_THREAD_0   ) )};
	
	
	
	
	@Test
	public void testReadBeforeWrite() {
		
		PartialOrder partialOrder = new PartialOrder(READ_BEFORE_WRITE);
		
		assertEquals( PartialOrder.UNDEFINED ,   partialOrder.getSort( linked(READ_THREAD_0) , linked(READ_THREAD_1)    ));
		assertEquals( PartialOrder.UNDEFINED ,   partialOrder.getSort( linked(WRITE_THREAD_0) , linked(WRITE_THREAD_1) ));
		
		assertEquals( PartialOrder.LEFT_COMES_BEFORE ,   partialOrder.getSort( linked(READ_THREAD_0) , linked(WRITE_THREAD_1)  ));
		assertEquals( PartialOrder.LEFT_COMES_BEFORE ,   partialOrder.getSort( linked(READ_THREAD_1) , linked(WRITE_THREAD_0)  ));
	}
	
	
	
	@Test
	public void testSameThreadId() {
		
		PartialOrder partialOrder = new PartialOrder(READ_BEFORE_WRITE);
		
		assertEquals( PartialOrder.LEFT_COMES_BEFORE ,   partialOrder.getSort( linked(READ_THREAD_0) , linked(WRITE_THREAD_0)  ));
		assertEquals( PartialOrder.LEFT_COMES_AFTER ,   partialOrder.getSort( linked(WRITE_THREAD_1) , linked(READ_THREAD_1)  ));
		assertEquals( PartialOrder.UNDEFINED ,   partialOrder.getSort( linked(READ_THREAD_1) , linked(READ_THREAD_1)  ));
	}
	
	
}
