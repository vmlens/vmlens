package com.anarsoft.race.detection.testFixture

import com.vmlens.trace.agent.bootstrap.event.impl.VolatileAccessEventBuilder

class EventBuilderForSyncAction(val fieldId: Int, val objectHashCode: Long, val eventBuilder: EventBuilder) {

  var order = 0;

  def add(volatileAccessEvent: VolatileAccessEventBuilder): Unit = {
    volatileAccessEvent.setFieldId(fieldId);
    volatileAccessEvent.setOrder(order);
    volatileAccessEvent.setObjectHashCode(objectHashCode);
    eventBuilder.add(volatileAccessEvent);
    order = order + 1;
  }

}
