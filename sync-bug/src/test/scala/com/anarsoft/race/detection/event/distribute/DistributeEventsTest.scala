package com.anarsoft.race.detection.event.distribute

import com.anarsoft.race.detection.rundata.LoopAndRunId
import org.mockito.Mockito.{mock, verify}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import java.util

class DistributeEventsTest extends AnyFlatSpec with Matchers {

  "DistributeEvents" should " process a list of one event" in {
    // Given
    val context = mock(classOf[LoadedEventContext[EventWithLoopAndRunIdGuineaPig]]);
    val distributeEvents =  new DistributeEvents[EventWithLoopAndRunIdGuineaPig](() => context);
    val list = new util.LinkedList[EventWithLoopAndRunIdGuineaPig]()
    list.add(EventWithLoopAndRunIdGuineaPig(1,1));

    // When
    distributeEvents.distribute(list,null);

    // Then
    verify(context).addToBuilder(LoopAndRunId(1,1),null);
  }

}
