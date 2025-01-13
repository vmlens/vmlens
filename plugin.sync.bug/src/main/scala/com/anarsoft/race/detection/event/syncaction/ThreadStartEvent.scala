package com.anarsoft.race.detection.event.syncaction

import com.anarsoft.race.detection.createpartialorderthreadoperation.ThreadOperation
import com.anarsoft.race.detection.partialorder.{PartialOrderBuilder, WithPositionImpl}
import com.anarsoft.race.detection.reportbuilder.EventForReportElement
import com.anarsoft.race.detection.setstacktrace.WithSetStacktraceNode
import com.vmlens.report.element.OperationTextFactory
import com.vmlens.report.element.operationtextfactoryimpl.ThreadOperationTextFactory


trait ThreadStartEvent extends LoadedSyncActionEvent with ThreadOperation
  with EventForReportElement with WithSetStacktraceNode {

  def startedThreadIndex: Int;

  override def addToContext(context: LoadedSyncActionContext): Unit = {
    context.threadStartEvents.add(this);
  }

  def addToPartialOrderBuilder(partialOrderBuilder: PartialOrderBuilder): Unit = {
    partialOrderBuilder.addLeftBeforeRight(this, new WithPositionImpl(0, startedThreadIndex))
  }

  override def operationTextFactory: OperationTextFactory = {
    new ThreadOperationTextFactory("start (%s)", startedThreadIndex);
  }
}
