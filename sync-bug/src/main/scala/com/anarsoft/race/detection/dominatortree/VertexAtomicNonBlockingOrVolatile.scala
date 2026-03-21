package com.anarsoft.race.detection.dominatortree

import com.anarsoft.race.detection.report.description.{DescriptionContext, NeedsDescriptionCallback}
import com.anarsoft.race.detection.report.dominatortree.ReportCallback
import com.anarsoft.race.detection.report.element.runelementtype.memoryaccesskey.MemoryAccessKey
import com.vmlens.report.dominatortree.{UIDominatorTreeElement, UIStateElementSortKey}
import com.anarsoft.race.detection.report.element.runelementtype.dominatormemoryaccesskey.DominatorMemoryAccessKey

import scala.collection.mutable

class VertexAtomicNonBlockingOrVolatile(val memoryAccessKey: DominatorMemoryAccessKey,
                                        val uiStateElementSortKey : UIStateElementSortKey) extends LeafNode {

  val operationSet = new mutable.HashSet[Int];
  
  
  override def getLabel(descriptionContext: DescriptionContext): String ={
    memoryAccessKey.asString(descriptionContext)
  }

  override def getUIStateElementSortKey: UIStateElementSortKey = uiStateElementSortKey;

  override def addToNeedsDescription(needsDescriptionCallback: NeedsDescriptionCallback): Unit = {
    memoryAccessKey.addToNeedsDescription(needsDescriptionCallback)
  }


  private def canEqual(other: Any): Boolean = other.isInstanceOf[VertexAtomicNonBlockingOrVolatile]
  
  override def equals(other: Any): Boolean = other match {
    case that: VertexAtomicNonBlockingOrVolatile =>
      that.canEqual(this) &&
        memoryAccessKey == that.memoryAccessKey
    case _ => false
  }
  
  override def hashCode(): Int = {
    val state = Seq(memoryAccessKey)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }

  override def isMethodCall : Boolean = false;

  override def addToReport(parent: Option[UIDominatorTreeElement], level: Int, reportCallback: ReportCallback): UIDominatorTreeElement = 
    reportCallback.withReverseCallTree(this,parent,level)
  
}
