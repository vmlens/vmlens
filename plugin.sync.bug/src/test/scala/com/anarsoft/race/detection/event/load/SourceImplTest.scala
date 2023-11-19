package com.anarsoft.race.detection.event.load

import com.anarsoft.race.detection.event.gen.{SyncActionsDeSerializer, VolatileAccessEventGen}
import com.anarsoft.race.detection.event.syncAction.{LoadedSyncActionContext, LoadedSyncActionEvent}
import com.anarsoft.race.detection.testFixture.RaceDetectionVolatileFixture
import com.vmlens.trace.agent.bootstrap.event.gen.EventConstants.MAX_ARRAY_SIZE
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import java.nio.ByteBuffer
import scala.collection.mutable.ArrayBuffer


class SourceImplTest extends AnyFlatSpec with Matchers {

  def createSourceImpl():
  SourceImpl[LoadedSyncActionEvent, LoadedSyncActionContext] = {
    createSourceImpl(new LoadedSyncActionContext());
  }


  def createSourceImpl(context: LoadedSyncActionContext):
  SourceImpl[LoadedSyncActionEvent, LoadedSyncActionContext] = {
    new SourceImpl[LoadedSyncActionEvent, LoadedSyncActionContext](context, new SyncActionsDeSerializer());
  }


  "SourceImpl" should "read as byte buffer from the file system" in {

  }

  it should "deserialize the array buffer to events" in {
    // Given
    val byteArray: Array[Byte] = new Array[Byte](MAX_ARRAY_SIZE * 100)
    val byteBuffer = ByteBuffer.wrap(byteArray);
    val testData = new RaceDetectionVolatileFixture().volatileReadAndWrite();
    val events = testData.javaEvents;
    for (e <- events) {
      e.serialize(byteBuffer);
    }

    byteBuffer.flip();
    val source = createSourceImpl();

    // When
    val serializedEvents = new ArrayBuffer[LoadedSyncActionEvent]();

    for (i <- 0 until events.size) {
      serializedEvents.append(source.deSerialize(byteBuffer));
    }

    // Then
    serializedEvents should be(testData.volatileAccessEvents);

  }

  it should "distributeLoadedEvent" in {
    // Given
    val event = new VolatileAccessEventGen(1L, 0, 0, 0, 0,
      0, 0, 0, 1L, 0, 0, 0);
    val context = new LoadedSyncActionContext();
    val eventSourceImpl = createSourceImpl(context);

    // When
    eventSourceImpl.distributeLoadedEvent(event);

    // Then
    context.volatileAccessEventList.get(0) should be(event)
  }

  it should "put the resulting list in the variable map" in {

  }

}
