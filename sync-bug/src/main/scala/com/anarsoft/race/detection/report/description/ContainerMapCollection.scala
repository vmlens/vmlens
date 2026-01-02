package com.anarsoft.race.detection.report.description

import com.anarsoft.race.detection.report.description.container.{ContainerForField, ContainerForMethod}
import com.vmlens.trace.agent.bootstrap.description.{TestLoopDescription, ThreadDescription}
import com.anarsoft.race.detection.report.element.LoopRunAndThreadIndex

import scala.collection.mutable

class ContainerMapCollection {

  val threadNames = new mutable.HashMap[LoopRunAndThreadIndex, ThreadDescription]();
  val methodNames = new mutable.HashMap[Int,ContainerForMethod]();
  val fieldNames = new mutable.HashMap[Int,ContainerForField]();
  val loopNames = new mutable.HashMap[Int,TestLoopDescription]();
  

}
