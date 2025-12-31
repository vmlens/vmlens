package com.anarsoft.race.detection.dominatortree

import com.anarsoft.race.detection.report.description.{DescriptionContext, NeedsDescriptionCallback}
import com.anarsoft.race.detection.report.element.runelementtype.memoryaccesskey.MemoryAccessKey
import com.vmlens.report.dominatortree.UIStateElementSortKey

import scala.collection.mutable

case class VertexAtomicNonBlockingOrVolatile(memoryAccessKey: MemoryAccessKey,
                                             operationSet : Set[Int],
                                             uiStateElementSortKey : UIStateElementSortKey) extends LeafNode {

  override def getLabel(descriptionContext: DescriptionContext): String ={
    memoryAccessKey.asString(descriptionContext)
  }

  override def getUIStateElementSortKey: UIStateElementSortKey = uiStateElementSortKey;

  override def addToNeedsDescription(needsDescriptionCallback: NeedsDescriptionCallback): Unit = {
    memoryAccessKey.addToNeedsDescription(needsDescriptionCallback)
  }
}
