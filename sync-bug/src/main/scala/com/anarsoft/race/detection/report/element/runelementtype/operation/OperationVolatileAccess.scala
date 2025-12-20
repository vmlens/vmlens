package com.anarsoft.race.detection.report.element.runelementtype.operation

import com.anarsoft.race.detection.report.element.runelementtype.ReportOperation
import com.anarsoft.race.detection.report.description.{DescriptionContext, NeedsDescriptionCallback}
import com.anarsoft.race.detection.report.element.runelementtype.memoryaccesskey.MemoryAccessKey
import com.anarsoft.race.detection.report.element.runelementtype.objecthashcodemap.ObjectHashCodeMap
import com.vmlens.trace.agent.bootstrap.MemoryAccessType

class OperationVolatileAccess(val memoryAccessKey: MemoryAccessKey, val memoryAccess: Int) extends ReportOperation {
  private var objectHashCodeMap: ObjectHashCodeMap = null

  override def operation: String = "Volatile " + MemoryAccessType.asString(memoryAccess)

  override def element(context: DescriptionContext): String = memoryAccessKey.asString(context)

  override def addToNeedsDescription(callback: NeedsDescriptionCallback): Unit = {
    memoryAccessKey.addToNeedsDescription(callback)
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

  override def take(): Boolean = memoryAccessKey.objectHashCodeOption match {
    case Some(x) => {
      !objectHashCodeMap.isSingleThreaded(x)
    }

    case None => {
      true;
    }
  }

  override def isDataRace: Boolean = false;

  private def canEqual(other: Any): Boolean = other.isInstanceOf[OperationVolatileAccess]
  
  override def equals(other: Any): Boolean = other match {
    case that: OperationVolatileAccess =>
      that.canEqual(this) &&
        memoryAccessKey == that.memoryAccessKey &&
        memoryAccess == that.memoryAccess
    case _ => false
  }
  
  override def hashCode(): Int = {
    val state = Seq(memoryAccessKey, memoryAccess)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}
