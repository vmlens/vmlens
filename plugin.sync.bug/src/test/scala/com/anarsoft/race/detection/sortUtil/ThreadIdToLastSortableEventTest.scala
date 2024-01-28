package com.anarsoft.race.detection.sortUtil

import com.anarsoft.race.detection.sortNonVolatileMemoryAccess.{MemoryAccessEventBuilder, NonVolatileMemoryAccessEventGuineaPig}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ThreadIdToLastSortableEventTest extends AnyFlatSpec with Matchers {

  "ThreadIdToLastSortableEvent" should "store non volatile field access events" in {
    val threadIdToLastSortableEvent = new ThreadIdToLastSortableEvent[NonVolatileMemoryAccessEventGuineaPig]();

    val memoryAccessEventBuilder = new MemoryAccessEventBuilder();

    val firstReadThreadOne = memoryAccessEventBuilder.threadId(1L).read();
    threadIdToLastSortableEvent.foreachOppositeAndPut(firstReadThreadOne, (previous) => {
      fail();
    })

    val firstReadThreadTwo = memoryAccessEventBuilder.threadId(10L).read();
    threadIdToLastSortableEvent.foreachOppositeAndPut(firstReadThreadTwo, (previous) => {
      fail();
    })


    val firstWriteThreadOne = memoryAccessEventBuilder.threadId(1L).write();
    threadIdToLastSortableEvent.foreachOppositeAndPut(firstWriteThreadOne, (previous) => {
      previous should be(firstReadThreadTwo)
    })


    val secondWriteThreadOne = memoryAccessEventBuilder.threadId(1L).write();
    threadIdToLastSortableEvent.foreachOppositeAndPut(secondWriteThreadOne, (previous) => {
      previous should be(firstReadThreadTwo)
    })

    val secondReadThreadTwo = memoryAccessEventBuilder.threadId(10L).read();
    threadIdToLastSortableEvent.foreachOppositeAndPut(secondReadThreadTwo, (previous) => {
      previous should be(secondWriteThreadOne)
    })


  }

}
