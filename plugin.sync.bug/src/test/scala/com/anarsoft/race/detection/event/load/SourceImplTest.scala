package com.anarsoft.race.detection.event.load

import com.anarsoft.race.detection.event.gen.VolatileAccessEventGen
import com.anarsoft.race.detection.event.syncAction.{LoadedSyncActionContext, LoadedSyncActionEvent}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class SourceImplTest extends AnyFlatSpec with Matchers {

  "SourceImpl" should "read as byte buffer from the file system" in {

  }

  it should "deserialize the array buffer to events" in {

  }

  it should "distributeLoadedEvent" in {
    // Given
    val event = new VolatileAccessEventGen(1L, 0, 0, 0, 0,
      0, 0, 0, 0, 1L, 0, 0, 0);
    val context = new LoadedSyncActionContext();
    val eventSourceImpl = new SourceImpl[LoadedSyncActionEvent, LoadedSyncActionContext](context);

    // When
    eventSourceImpl.distributeLoadedEvent(event);

    // Then
    context.volatileAccessEventList.get(0) should be(event)
  }

  it should "put the resulting list in the variable map" in {

  }

}
