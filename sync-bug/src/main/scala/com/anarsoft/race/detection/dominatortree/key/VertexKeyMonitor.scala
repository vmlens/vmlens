package com.anarsoft.race.detection.dominatortree.key

import com.anarsoft.race.detection.dominatortree.{DefaultVertex, VertexLockOrMonitor}

case class  VertexKeyMonitor(id : Int) extends VertexKeyLockOrMonitor {

  override def create(): DefaultVertex = new VertexLockOrMonitor(this);

  override def getLabel: String = "monitor(" + id + ")";

  
  
}
