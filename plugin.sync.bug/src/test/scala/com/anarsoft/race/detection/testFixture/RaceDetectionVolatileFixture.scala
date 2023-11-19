package com.anarsoft.race.detection.testFixture

import com.anarsoft.race.detection.event.syncAction.LoadedSyncActionEvent

class RaceDetectionVolatileFixture {
  def volatileReadAndWrite(): TestData = {
    val eventBuilder = new EventBuilder(5, 1);
    val eventBuilderForSyncAction = new EventBuilderForSyncAction(33, 6000, eventBuilder);
    val eventBuilderForMainThread = new EventBuilderForThread(1L);
    val eventBuilderForWorkerThread = new EventBuilderForThread(100L);

    eventBuilderForMainThread.addVolatileRead(eventBuilderForSyncAction);
    eventBuilderForWorkerThread.addVolatileWrite(eventBuilderForSyncAction);
    val data = eventBuilder.build();

    println(data.volatileAccessEvents);

    data;
  }

}
