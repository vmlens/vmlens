package com.anarsoft.race.detection.dominatortree.key

import com.anarsoft.race.detection.dominatortree.{DefaultVertex, VertexMethod}

case class  VertexKeyMethod(methodId : Int) extends DefaultVertexKey {

  override def create(): DefaultVertex = new VertexMethod(this);
}
