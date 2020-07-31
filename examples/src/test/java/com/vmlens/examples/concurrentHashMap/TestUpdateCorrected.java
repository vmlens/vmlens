package com.vmlens.examples.concurrentHashMap;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;
import com.vmlens.api.AllInterleavings;

public class TestUpdateCorrected {
	
	
	public void update(ConcurrentHashMap<Integer,Integer>  map ) {
			map.compute(1, (key, value) -> {
				if (value == null) {
					return 1;
				} 
				return value + 1;
			});
	}
	@Test
	public void testUpdate() throws InterruptedException	{
		try (AllInterleavings allInterleavings = 
				new AllInterleavings("TestUpdateCorrected");) {
			while (allInterleavings.hasNext()) {
				
				final ConcurrentHashMap<Integer,Integer>  map = new  ConcurrentHashMap<Integer,Integer>();
				
				
				
				Thread first = new Thread(() -> { update(map); 	});
				Thread second = new Thread(() -> { update(map); 	});
				first.start();
				second.start();
				
				first.join();
				second.join();

				assertEquals( 2 , map.get(1).intValue() );
				
			}
		}
	}
}
