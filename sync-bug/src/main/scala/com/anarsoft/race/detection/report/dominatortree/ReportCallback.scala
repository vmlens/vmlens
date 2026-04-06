package com.anarsoft.race.detection.report.dominatortree

import com.anarsoft.race.detection.dominatortree.DominatorTreeVertex
import com.vmlens.report.dominatortree.UIDominatorTreeElement

trait ReportCallback {

  def withReverseCallTree(node : DominatorTreeVertex, parent: Option[UIDominatorTreeElement], level: Int) : UIDominatorTreeElement;
  def withOutReverseCallTree(node : DominatorTreeVertex, parent: Option[UIDominatorTreeElement], level: Int) : UIDominatorTreeElement;

}
