package com.anarsoft.race.detection.event.load

import com.anarsoft.race.detection.event.gen.InterleaveDeSerializer
import com.anarsoft.race.detection.event.interleave.LoadedInterleaveActionEvent
import com.anarsoft.race.detection.testFixture.VolatileFixture
import com.vmlens.trace.agent.bootstrap.event.gen.EventConstants.MAX_ARRAY_SIZE
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import java.nio.ByteBuffer
import scala.jdk.CollectionConverters.*

class DeserializeEventsTest extends AnyFlatSpec with Matchers {

  " DeserializeEvents" should "deserialize the events from a bytebuffer" in {
    // Given
    val byteArray: Array[Byte] = new Array[Byte](MAX_ARRAY_SIZE * 100)
    val byteBuffer = ByteBuffer.wrap(byteArray);
    val testData = new VolatileFixture().volatileReadAndWrite();
    val events = testData.syncActionJavaEvents;
    for (e <- events) {
      e.serialize(byteBuffer);
    }

    byteBuffer.flip();
    val deserializeEvents = DeserializeEvents[LoadedInterleaveActionEvent]();

    // When
    val serializedEvents = deserializeEvents.deserialize(byteBuffer, new InterleaveDeSerializer());

    // Then
    serializedEvents.asScala.toList should be(testData.volatileAccessEvents);
  }
}
