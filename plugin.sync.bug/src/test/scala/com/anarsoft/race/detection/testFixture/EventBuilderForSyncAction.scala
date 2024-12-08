package com.anarsoft.race.detection.testFixture

import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.VolatileAccessEventBuilder

class EventBuilderForSyncAction(val fieldId: Int, val objectHashCode: Long) {

  var order = 0;

  def setFieldValues(volatileAccessEvent: VolatileAccessEventBuilder): Unit = {
    volatileAccessEvent.setFieldId(fieldId);
    volatileAccessEvent.setOrder(order);
    volatileAccessEvent.setObjectHashCode(objectHashCode);
    order = order + 1;
  }

}
