package com.anarsoft.race.detection.process.monitor

import com.anarsoft.race.detection.process.interleave.InterleaveEventStatement
import com.anarsoft.race.detection.process.interleave.StatementVisitor
import com.vmlens.api.internal.IconRepository

trait MonitorEnterEventInterleave extends MonitorEnterEvent with InterleaveEventStatement {

  var hasDeadlock = false;

  def icon() =
    {
       if (hasDeadlock) {
        IconRepository.MONITOR_ENTER_DEADLOCK
      } else {
        IconRepository.MONITOR_ENTER
      }
    }

  def objectId = Some("<" + monitorId + ">");
  def isSame(InterleaveEventStatement: InterleaveEventStatement) = false;

  def acceptStatementVisitor(statementVisitor: StatementVisitor) {
    statementVisitor.visit(this);
  }

  def canStartLoop() = true;
  def isSameStatement(interleaveEventStatement: InterleaveEventStatement) =
    {
      if (interleaveEventStatement.isInstanceOf[MonitorEnterEventInterleave]) {
        val other = interleaveEventStatement.asInstanceOf[MonitorEnterEventInterleave]

        if (other.threadId == threadId && other.stackTraceOrdinal == stackTraceOrdinal && other.monitorId == monitorId) {
          true
        } else {
          false
        }
      } else {
        false;
      }

    }

}