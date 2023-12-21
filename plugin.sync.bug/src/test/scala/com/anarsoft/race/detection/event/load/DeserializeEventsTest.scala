package com.anarsoft.race.detection.event.load

import com.anarsoft.race.detection.event.gen.SyncActionsDeserializer
import com.anarsoft.race.detection.event.syncAction.LoadedSyncActionEvent
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import com.vmlens.trace.agent.bootstrap.event.gen.EventConstants.MAX_ARRAY_SIZE

import java.nio.ByteBuffer
import com.anarsoft.race.detection.testFixture.RaceDetectionVolatileFixture

class DeserializeEventsTest extends AnyFlatSpec with Matchers {

  " DeserializeEvents" should "deserialize the events from a bytebuffer" in {
    // Given
    val byteArray: Array[Byte] = new Array[Byte](MAX_ARRAY_SIZE * 100)
    val byteBuffer = ByteBuffer.wrap(byteArray);
    val testData = new RaceDetectionVolatileFixture().volatileReadAndWrite();
    val events = testData.javaEvents;
    for (e <- events) {
      e.serialize(byteBuffer);
    }

    byteBuffer.flip();
    val deserializeEvents = DeserializeEvents[LoadedSyncActionEvent]();

    // When
    val serializedEvents = deserializeEvents.deserialize(byteBuffer, new SyncActionsDeserializer());

    // Then
    serializedEvents should be(testData.volatileAccessEvents);
  }
}
