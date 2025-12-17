package com.anarsoft.race.detection.report.element.runelementtype.operation

import com.anarsoft.race.detection.report.element.runelementtype.{BarrierKeyTextBuilder, BarrierOperationType, ReportOperation}
import com.anarsoft.race.detection.report.description.{DescriptionContext, NeedsDescriptionCallback}
import com.anarsoft.race.detection.report.element.runelementtype.objecthashcodemap.ObjectHashCodeMap
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.barrierkey.BarrierKey


class OperationBarrier(val barrierOperationType: BarrierOperationType, val barrierKey: BarrierKey) extends ReportOperation {
  private var objectHashCodeMap: ObjectHashCodeMap = null

  override def operation: String = {
    val barrierKeyTextBuilder = new BarrierKeyTextBuilder
    barrierKey.accept(barrierKeyTextBuilder)
    barrierKeyTextBuilder.build + " " + barrierOperationType.text
  }

  override def element(context: DescriptionContext) = ""

  override def addToNeedsDescription(callback: NeedsDescriptionCallback): Unit = {

    // No description needed
  }

  override def objectString(context: DescriptionContext): String = objectHashCodeMap.asUiString(barrierKey.objectHashcode)

  override def setObjectHashCodeMap(objectHashCodeMap: ObjectHashCodeMap, threadIndex: Int): Unit = {
    this.objectHashCodeMap = objectHashCodeMap
    objectHashCodeMap.add(barrierKey.objectHashcode, threadIndex)
  }
}