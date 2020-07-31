package com.vmlens.examples.javaMemoryModel;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;

import com.vmlens.api.AllInterleavings;

public class TestConcurrentHashMapWithoutAtomicTwoPuts {

	@Test
	public void testUpdate() throws InterruptedException {
		try (AllInterleavings allInterleavings =  AllInterleavings.builder(TestConcurrentHashMapWithoutAtomicTwoPuts.class.getName()).removeAtomicAnnotation(ConcurrentHashMap.class).build();) {
			while (allInterleavings.hasNext()) {
				final ConcurrentHashMap<Integer, Integer> map = 
						new ConcurrentHashMap<Integer, Integer>();
				Thread first = new Thread(() -> {
					map.put(1,1);
				});
				Thread second = new Thread(() -> {
					map.put(1,1);
				});
				first.start();
				second.start();
				first.join();
				second.join();
			
			}
		}
	}
}
