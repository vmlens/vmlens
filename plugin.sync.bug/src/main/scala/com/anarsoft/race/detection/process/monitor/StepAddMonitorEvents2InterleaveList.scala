package com.anarsoft.race.detection.process.monitor

import com.anarsoft.race.detection.process.workflow.SingleStep
import com.anarsoft.race.detection.process.interleave.InterleaveEventStatement

class StepAddMonitorEvents2InterleaveList extends SingleStep[ContextMonitor] {

  def execute(context: ContextMonitor) {

    if (context.monitorEventList != null) {

      val iter = context.monitorEventList.iterator();
      while (iter.hasNext()) {
        val current = iter.next();

        if (current.isInstanceOf[InterleaveEventStatement]) {
          context.interleaveEventList.addMonitorEvent(current.asInstanceOf[InterleaveEventStatement], context.deadlockFilter);
        }

      }

    }

  }

}