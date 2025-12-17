package com.anarsoft.race.detection.report.element.runelementtype.operation

import com.anarsoft.race.detection.report.element.runelementtype.ReportOperation
import com.anarsoft.race.detection.report.description.{DescriptionContext, NeedsDescriptionCallback}
import com.vmlens.trace.agent.bootstrap.MemoryAccessType
import com.anarsoft.race.detection.report.element.runelementtype.memoryaccesskey.MemoryAccessKey
import com.anarsoft.race.detection.report.element.runelementtype.objecthashcodemap.ObjectHashCodeMap

class OperationNonVolatileAccess(val memoryAccessKey: MemoryAccessKey, val memoryAccess : Int, val isDataRace: Boolean) extends ReportOperation {

  private var objectHashCodeMap: ObjectHashCodeMap = null

  override def operation: String = modifier + MemoryAccessType.asString(memoryAccess)

  override def element(context: DescriptionContext): String = memoryAccessKey.asString(context)

  override def addToNeedsDescription(callback: NeedsDescriptionCallback): Unit = {
    memoryAccessKey.addToNeedsDescription(callback)
  }

  private def modifier: String = {
    if (isDataRace) return "<span style=\"color: red;\">Data Race</span> "
    ""
  }

  override def objectString(context: DescriptionContext): String = {
    memoryAccessKey.objectHashCodeOption match {
      case Some(x) => {
        objectHashCodeMap.asUiString(x)
      }

      case None => {
        "static"
      }
    }
    
  }

  override def setObjectHashCodeMap(objectHashCodeMap: ObjectHashCodeMap, threadIndex: Int): Unit = {
    memoryAccessKey.objectHashCodeOption match {
      case Some(x) => {
        this.objectHashCodeMap = objectHashCodeMap
        objectHashCodeMap.add(x, threadIndex)
      }

      case None => {
        
      }
    }
    
  }
}
