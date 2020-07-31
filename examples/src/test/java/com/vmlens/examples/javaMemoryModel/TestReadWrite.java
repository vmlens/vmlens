package com.vmlens.examples.javaMemoryModel;

import org.junit.Test;
import com.vmlens.api.AllInterleavings;
public class TestReadWrite {
 volatile int v = 0;
 int i = 0;
 @Test
 public void testUpdate() throws InterruptedException {
   try (AllInterleavings allInterleavings =
     new AllInterleavings(TestReadWrite.class.getName());) {
	 while (allInterleavings.hasNext()) {
	   Thread first = new Thread(() -> {
		 i = 5;
		 v = 7;
	   });
	   Thread second = new Thread(() -> {
		 int x = v;
		 int y = i;
	   });
	   first.start();
	   second.start();

	   first.join();
	   second.join();
	   }
    }
 }
}
