package com.anarsoft.race.detection.event.distribute

import com.anarsoft.race.detection.event.distribute.DistributeEvents
import com.anarsoft.race.detection.event.gen.MethodEnterEventGen
import com.anarsoft.race.detection.event.method.{LoadedMethodEvent, LoadedMethodEventContext}
import com.anarsoft.race.detection.process.loopAndRunData.{LoopAndRunId, RunDataListBuilder}
import com.anarsoft.race.detection.stacktrace.MethodEvent
import com.anarsoft.race.detection.util.EventArray
import org.mockito.ArgumentCaptor
import org.mockito.Mockito.{mock, times, verify}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import java.util.ArrayList;

class DistributeEventsTest extends AnyFlatSpec with Matchers {

  "DistributeEvents" should "distribute method events based on loop and run id" in {
    // Mocks
    val loopAndRunDataBuilderMock = mock(classOf[RunDataListBuilder]);
    val captureLoopAndRunId = ArgumentCaptor.forClass(classOf[LoopAndRunId]);
    val captureEventArray = ArgumentCaptor.forClass(classOf[EventArray[MethodEvent]]);

    // Expected
    val expectedEventArrayOne = new EventArray[MethodEvent](Array(event(1, 1), event(1, 1)));

    // Given
    val methodEvents = new ArrayList[LoadedMethodEvent]();
    methodEvents.add(event(2, 2));
    methodEvents.add(event(1, 1));
    methodEvents.add(event(2, 2));
    methodEvents.add(event(1, 1));

    // When
    val distribute = new DistributeEvents[LoadedMethodEvent]();
    distribute.distribute(methodEvents, () => new LoadedMethodEventContext(), loopAndRunDataBuilderMock);

    // Then
    verify(loopAndRunDataBuilderMock, times(2)).add(captureLoopAndRunId.capture(), captureEventArray.capture());
    captureLoopAndRunId.getAllValues.get(0) should be(LoopAndRunId(1, 1))
    captureEventArray.getAllValues.get(0) should be(expectedEventArrayOne)

    captureLoopAndRunId.getAllValues.get(1) should be(LoopAndRunId(2, 2))
  }

  private def event(loopId: Int, runId: Int) = new MethodEnterEventGen(1L, 1, 0,
    loopId, runId, 0);

}
