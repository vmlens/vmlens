package com.anarsoft.race.detection.process.interleave.loopAlgo

import org.junit.Test;
import org.junit.Assert._;

class TestGenericClass {

  @Test
  def test() {
    val map = new GenericMap[LoopInfo]( (id) => { new LoopInfo(id) });

    val id5 = map.getOrCreate(5)

    assertEquals(5, id5.id);

    val id0 = map.getOrCreate(0)

    assertEquals(0, id0.id);

    assertEquals(6, map.list.size());

    val iter = map.list.iterator()

    assertEquals(0, iter.next().id);
    assertEquals(1, iter.next().id);
    assertEquals(2, iter.next().id);
  }

}