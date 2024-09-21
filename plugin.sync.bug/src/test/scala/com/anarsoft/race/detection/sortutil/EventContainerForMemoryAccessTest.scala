package com.anarsoft.race.detection.sortutil

import com.anarsoft.race.detection.sortutil.MemoryAccessEventGuineaPig.{atomic, read, write}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class EventContainerForMemoryAccessTest extends AnyFlatSpec with Matchers {

  "EventContainerForMemoryAccess" should "work with read and write" in {
    // Given
    val firstRead = read();
    val firstWrite = write();

    val secondRead = read();


    // after adding read a second read has no opposite
    var eventContainer = EventContainerForMemoryAccess(firstRead)
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

  "EventContainerForMemoryAccess" should "work with atomic" in {
    // Given
    val firstRead = read();
    val firstWrite = write();

    val firstAtomic = atomic();
    val secondAtomic = atomic();

    // After adding atomic both read and write should have an opposite
    var eventContainer = EventContainerForMemoryAccess(firstAtomic)
    oppositeCalled(eventContainer, firstRead) should be(true)
    oppositeCalled(eventContainer, firstWrite) should be(true)

    // adding read only replaces read
    eventContainer = eventContainer.put(firstRead)
    calledOpposite(eventContainer, firstRead) should be(firstAtomic)
    calledOpposite(eventContainer, firstWrite) should be(firstRead)

    // atomic replaces both
    eventContainer = eventContainer.put(secondAtomic)
    calledOpposite(eventContainer, firstRead) should be(secondAtomic)
    calledOpposite(eventContainer, firstWrite) should be(secondAtomic)

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
