package com.anarsoft.race.detection.testfixture

import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.VolatileAccessEventBuilder

class EventBuilderForSyncAction(val fieldId: Int, val objectHashCode: Long) {

  def setFieldValues(volatileAccessEvent: VolatileAccessEventBuilder): Unit = {
    volatileAccessEvent.setFieldId(fieldId);
    volatileAccessEvent.setObjectHashCode(objectHashCode);

  }

}
