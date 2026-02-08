package com.vmlens.test.maven.plugin.automatictest;

import java.util.concurrent.atomic.AtomicInteger;

public class CompareAndSwap {

  AtomicInteger atomicInteger = new AtomicInteger(1);

  public void multiply(int value) {
      int current = atomicInteger.get();
      boolean success = atomicInteger.compareAndSet(current,current*value);
      while(! success) {
           current = atomicInteger.get();
           success = atomicInteger.compareAndSet(current,current*value);
      }
  }

    public int intValue() {
        return atomicInteger.intValue();
    }
}
