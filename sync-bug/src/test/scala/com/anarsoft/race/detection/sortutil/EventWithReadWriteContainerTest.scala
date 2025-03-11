package com.anarsoft.race.detection.sortutil

import com.anarsoft.race.detection.sortutil.MemoryAccessEventGuineaPig.{read, write}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class EventWithReadWriteContainerTest extends AnyFlatSpec with Matchers {

  "EventWithReadWriteContainer" should "work with read and write" in {
    // Given
    val firstRead = read();
    val firstWrite = write();

    val secondRead = read();

    // after adding read a second read has no opposite
    var eventContainer = EventWithReadWriteContainer(firstRead)
    oppositeCalled(eventContainer, secondRead) should be(false)

    // while read should have an opposite
    oppositeCalled(eventContainer, firstWrite) should be(true)

    // Adding read it should replace the current read
    eventContainer = eventContainer.put(secondRead)
    calledOpposite(eventContainer, firstWrite) should be(secondRead)

    // Adding write should not replace the current read
    eventContainer = eventContainer.put(firstWrite)
    calledOpposite(eventContainer, firstWrite) should be(secondRead)
  }



  def oppositeCalled(eventContainerForMemoryAccess: EventContainer[MemoryAccessEventGuineaPig],
                     event: MemoryAccessEventGuineaPig): Boolean = {
    var oppositeCalled = false;
    eventContainerForMemoryAccess.foreachOpposite(event, (opposite) => oppositeCalled = true)
    oppositeCalled
  }

  def calledOpposite(eventContainerForMemoryAccess: EventContainer[MemoryAccessEventGuineaPig],
                     event: MemoryAccessEventGuineaPig): MemoryAccessEventGuineaPig = {
    var calledOpposite: MemoryAccessEventGuineaPig = null;
    eventContainerForMemoryAccess.foreachOpposite(event, (opposite) => calledOpposite = opposite)
    calledOpposite
  }


}
