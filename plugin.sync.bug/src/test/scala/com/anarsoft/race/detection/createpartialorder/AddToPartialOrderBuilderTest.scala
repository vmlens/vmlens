package com.anarsoft.race.detection.createpartialorder

import com.anarsoft.race.detection.util.EventArray
import org.mockito.Mockito.{mock, verify}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class AddToPartialOrderBuilderTest extends AnyFlatSpec with Matchers {


  "AddToPartialOrderBuilder" should "call PartialOrderBuilder for monitor enter after exit" in {
    // Given
    val monitorEnterMainThread = new SyncActionEventMonitorGuineaPig(true, 1, 1);
    val monitorExitMainThread = new SyncActionEventMonitorGuineaPig(false, 2, 1);
    val monitorEnterWorkerThread = new SyncActionEventMonitorGuineaPig(true, 3, 10);

    val eventArray = new EventArray[SyncActionEventMonitorGuineaPig](Array(monitorEnterWorkerThread, monitorEnterMainThread, monitorExitMainThread));
    val partialOrderBuilderMock = mock(classOf[PartialOrderBuilder]);
    val createPartialOrder = new AddToPartialOrderBuilder[SyncActionEventMonitorGuineaPig](event => EventContainerMonitorGuineaPig(event));

    // When
    createPartialOrder.process(eventArray, partialOrderBuilderMock)

    // Then
    verify(partialOrderBuilderMock).addLeftBeforeRight(monitorExitMainThread, monitorEnterWorkerThread)

  }


}
