package com.anarsoft.race.detection.dominatortree.key

import com.anarsoft.race.detection.dominatortree.{DefaultVertex, VertexLockOrMonitor}
import com.anarsoft.race.detection.report.element.runelementtype.ReportLockType

case class VertexKeyLock(lockType : ReportLockType, id : Int) extends VertexKeyLockOrMonitor {

  override def create(): DefaultVertex = new VertexLockOrMonitor(this);

  override def getLabel: String =  lockType.text +  "(" + id + ")";
}
