package com.anarsoft.race.detection.testFixture

import com.anarsoft.race.detection.event.interleave.LoadedInterleaveActionEvent

class VolatileFixture {
  def volatileReadAndWrite(): TestData = {
    val eventBuilder = new EventBuilder(5, 1, 0);
    val eventBuilderForSyncAction = new EventBuilderForSyncAction(33, 6000);
    val eventBuilderForMainThread = new EventBuilderForThread(1, eventBuilder);
    val eventBuilderForWorkerThread = new EventBuilderForThread(100, eventBuilder);

    eventBuilderForMainThread.addVolatileRead(eventBuilderForSyncAction);
    eventBuilderForWorkerThread.addVolatileWrite(eventBuilderForSyncAction);
    val data = eventBuilder.build();

    data;
  }

}
