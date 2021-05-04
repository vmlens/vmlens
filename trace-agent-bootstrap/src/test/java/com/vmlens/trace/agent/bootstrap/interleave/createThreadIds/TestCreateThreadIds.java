package com.vmlens.trace.agent.bootstrap.interleave.createThreadIds;

import static com.vmlens.trace.agent.bootstrap.interleave.createThreadIds.TestPartialOrder.READ_THREAD_0;
import static com.vmlens.trace.agent.bootstrap.interleave.createThreadIds.TestPartialOrder.READ_THREAD_1;
import static com.vmlens.trace.agent.bootstrap.interleave.createThreadIds.TestPartialOrder.WRITE_THREAD_0;
import static com.vmlens.trace.agent.bootstrap.interleave.createThreadIds.TestPartialOrder.WRITE_THREAD_1;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.vmlens.trace.agent.bootstrap.interleave.potentialOrder.PotentialOrder;
import com.vmlens.trace.agent.bootstrap.interleave.potentialOrder.PotentialOrderSingle;
import com.vmlens.trace.agent.bootstrap.interleave.syncAction.TLinkableForSyncAction;

public class TestCreateThreadIds {
	
	final PotentialOrder[] potentialOrderArray = new PotentialOrder[] { new PotentialOrderSingle( new LeftBeforeRight(READ_THREAD_0,WRITE_THREAD_1) ,  new LeftBeforeRight(WRITE_THREAD_1,READ_THREAD_0)) ,
			new  PotentialOrderSingle(new LeftBeforeRight( WRITE_THREAD_0 , READ_THREAD_1    ) , new LeftBeforeRight(   READ_THREAD_1 , WRITE_THREAD_0   ) )  };
	
	@Test
	public void testVolatileReadWrite() {
	
		
		CreateThreadIds createThreadIds = new CreateThreadIds(TestSortThreadIds.TWO_THREADS,potentialOrderArray);
		
		int count = 0;
		Set<String> result = new HashSet<String>();
		
		while(  createThreadIds.hasNext() )
		{
			TLinkableForSyncAction[] array = createThreadIds.next();
			count++;
			result.add( Arrays.toString( array ) );
		}
		
		assertEquals(3 , count);
		Set<String> expected = new HashSet<String>();
		expected.add("[R_0_0, W_0_1, R_1_0, W_1_1]");
		expected.add("[R_0_0, R_1_0, W_0_1, W_1_1]");
		expected.add("[R_1_0, W_1_1, R_0_0, W_0_1]");
		
		assertEquals( expected , result );
	} 
	
	@Test
	public void testEqualsAndHashCode()
	{
		CreateThreadIds firstCreateThreadIds = new CreateThreadIds(TestSortThreadIds.TWO_THREADS,potentialOrderArray);
		
		 PotentialOrder[] secondPotentialOrderArray = new PotentialOrder[] { new PotentialOrderSingle( new LeftBeforeRight(READ_THREAD_0,WRITE_THREAD_1) ,  new LeftBeforeRight(WRITE_THREAD_1, READ_THREAD_0)  ) ,
					new  PotentialOrderSingle(new LeftBeforeRight( WRITE_THREAD_0 , READ_THREAD_1    ), new LeftBeforeRight(  READ_THREAD_1  , WRITE_THREAD_0  ) )  };
		
		CreateThreadIds secondCreateThreadIds = new CreateThreadIds(TestSortThreadIds.TWO_THREADS,secondPotentialOrderArray);
		
		assertEquals( firstCreateThreadIds , secondCreateThreadIds  );
		assertEquals( firstCreateThreadIds.hashCode() , secondCreateThreadIds.hashCode()  );
		
	}
	
	
}
