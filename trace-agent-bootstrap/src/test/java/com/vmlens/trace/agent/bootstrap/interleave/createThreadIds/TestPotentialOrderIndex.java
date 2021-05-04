package com.vmlens.trace.agent.bootstrap.interleave.createThreadIds;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.vmlens.trace.agent.bootstrap.interleave.createThreadIds.PotentialOrderIndex.PotentialOrderIndexState;

public class TestPotentialOrderIndex {

	
	private static String asString(boolean flag) 	{
		if(flag) 	{
			return "T";
		}
		else {
			return "F";
		}
	}
	
	
	@Test
	public void testLengthTwo() {
		
		
		Set<String> resultSet = new HashSet<String>();
		
		
		PotentialOrderIndex potentialOrderIndex = new PotentialOrderIndex(2);
		
		int count = 0;
		
		while(potentialOrderIndex.hasNext()) {
			PotentialOrderIndexState index = potentialOrderIndex.next();
			
			String result = asString( index.isSet(0) ) + asString( index.isSet(1) );
			resultSet.add(result);
			
			count++;
		}
			
		Set<String> expectedSet = new HashSet<String>();
		expectedSet.add("TT");
		expectedSet.add("TF");
		expectedSet.add("FT");
		expectedSet.add("FF");
		
		assertEquals( expectedSet, resultSet );
		assertEquals(4 , count);
		
	}
	
	
}
