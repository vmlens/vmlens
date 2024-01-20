package com.anarsoft.race.detection.process.load

import com.anarsoft.race.detection.event.loadAndDistribute.LoadAndDistributeOneFilePositionImpl
import com.anarsoft.race.detection.process.loopAndRunData.RunData
import com.anarsoft.race.detection.stacktrace.MethodEvent
import com.anarsoft.race.detection.testFixture.{EventBuilder, EventBuilderForThread, MethodFixture, TestData}
import com.vmlens.trace.agent.bootstrap.event.{AbstractStreamWrapper, StreamWrapperWithSlidingWindow}
import gnu.trove.list.linked.TLinkedList
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import java.nio.file.Paths
import scala.collection.mutable.ArrayBuffer

class LoadEventsImplIntTest extends AnyFlatSpec with Matchers {

  "LoadEventsImpl" should "the load the events saved by tha agent" in {
    // Given
    val streamList = new TLinkedList[AbstractStreamWrapper]();
    val tempDir = Paths.get(System.getProperty("java.io.tmpdir"))
    val methodStream = new StreamWrapperWithSlidingWindow(tempDir.toString, "method", streamList)
    val testDataOne = methodCall(1, 0);
    val testDataTwo = methodCall(2, 1);

    val loadStatisticAndSerializeOneFilePositionList = List(LoadStatisticAndDistributeOneFilePosition(
      LoadAndDistributeOneFilePositionImpl.method(tempDir)));
    val loadEventsImpl = new LoadEventsImpl(loadStatisticAndSerializeOneFilePositionList);

    // When
    for (event <- testDataOne.methodJavaEvents) {
      event.serialize(methodStream)
    }
    for (event <- testDataTwo.methodJavaEvents) {
      event.serialize(methodStream)
    }
    methodStream.close();

    // Then
    val result = new ArrayBuffer[RunData]();
    loadEventsImpl.foreach(data => result.append(data));

    result(0).loopAndRunId.runId should be(1)

    val methodsList = new ArrayBuffer[MethodEvent]();
    result(0).methodEventArray.foreach(event => methodsList.append(event))
    methodsList.toList should be(testDataOne.methodEvents)
  }


  private def methodCall(runId: Int, slidingWindowId: Int): TestData = {
    val eventBuilder = new EventBuilder(runId, 1, slidingWindowId);
    val eventBuilderForMainThread = new EventBuilderForThread(1L, eventBuilder);
    eventBuilderForMainThread.addMethodEnterEvent(5);
    eventBuilder.build();
  }

}
