package com.anarsoft.race.detection.report.dominatortree

import com.anarsoft.race.detection.dominatortree.DominatorTreeVertex
import com.vmlens.report.dominatortree.UIDominatorTreeElement

import scala.collection.mutable.ArrayBuffer

class ReportCallbackMock extends ReportCallback {
  
  val result = new ArrayBuffer[DominatorTreeVertex]();
  
  override def withReverseCallTree(node: DominatorTreeVertex, parent: Option[UIDominatorTreeElement], level: Int): UIDominatorTreeElement = {
    result.append(node)
    new UIDominatorTreeElement("", "",null);
  }

  override def withOutReverseCallTree(node: DominatorTreeVertex, parent: Option[UIDominatorTreeElement], level: Int): UIDominatorTreeElement = {
    result.append(node)
    new UIDominatorTreeElement("", "",null);
  }
}
