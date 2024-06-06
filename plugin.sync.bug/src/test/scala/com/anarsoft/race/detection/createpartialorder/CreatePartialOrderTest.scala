package com.anarsoft.race.detection.createpartialorder

import com.anarsoft.race.detection.util.EventArray
import org.mockito.Mockito.{mock, verify}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class CreatePartialOrderTest extends AnyFlatSpec with Matchers {


  "CreatePartialOrder" should "call PartialOrderBuilder for monitor enter after exit" in {
    // Given
    val monitorEnterMainThread = new SyncActionEventMonitorGuineaPig(true, 1, 1L);
    val monitorExitMainThread = new SyncActionEventMonitorGuineaPig(false, 2, 1L);
    val monitorEnterWorkerThread = new SyncActionEventMonitorGuineaPig(true, 3, 10L);

    val eventArray = new EventArray[SyncActionEventMonitorGuineaPig](Array(monitorEnterWorkerThread, monitorEnterMainThread, monitorExitMainThread));
    val partialOrderBuilderMock = mock(classOf[PartialOrderBuilder]);
    val createPartialOrder = new CreatePartialOrder[SyncActionEventMonitorGuineaPig](event => EventContainerMonitorGuineaPig(event));

    // When
    createPartialOrder.process(eventArray, partialOrderBuilderMock)

    // Then
    verify(partialOrderBuilderMock).addLeftBeforeRight(monitorExitMainThread, monitorEnterWorkerThread)

  }


}
