package com.vmlens.examples.doNotCombine;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.vmlens.api.AllInterleavings;


public class TestCounter {

	volatile int i = 0;

	@Test
	public void testUpdate() throws InterruptedException {
		try (AllInterleavings allInterleavings = new AllInterleavings("TestCounter");) {
			while (allInterleavings.hasNext()) {

				i = 0;

				Thread first = new Thread(() -> {
					i++;
				});
				Thread second = new Thread(() -> {
					i++;
				});
				first.start();
				second.start();
				first.join();
				second.join();

				assertEquals(2, i);

			}
		}

	}

}
