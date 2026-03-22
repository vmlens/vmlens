package com.anarsoft.race.detection.dominatortree

import com.anarsoft.race.detection.dominatortree.key.StateVertexKey
import com.anarsoft.race.detection.report.description.{DescriptionContext, NeedsDescriptionCallback}
import com.anarsoft.race.detection.report.dominatortree.ReportCallback
import com.vmlens.report.dominatortree.UIDominatorTreeElement

import scala.collection.mutable

class VertexState(val stateVertexKey : StateVertexKey) extends DominatorTreeVertex {

  
  val operationSet = new mutable.HashSet[Int];
  
  
  override def getLabel(descriptionContext: DescriptionContext): String ={
    stateVertexKey.memoryAccessKey.asString(descriptionContext) + stateVertexKey.objectHashCode.idLabel() 
  }
  
  override def addToNeedsDescription(needsDescriptionCallback: NeedsDescriptionCallback): Unit = {
    stateVertexKey.memoryAccessKey.addToNeedsDescription(needsDescriptionCallback)
  }
  
  override def isMethodCall : Boolean = false;

  override def addToReport(parent: Option[UIDominatorTreeElement], level: Int, reportCallback: ReportCallback): UIDominatorTreeElement = 
    reportCallback.withReverseCallTree(this,parent,level)
  
  
}
