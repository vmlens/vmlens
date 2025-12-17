package com.anarsoft.race.detection.report.description

import com.anarsoft.race.detection.report.description.container.{ContainerForField, ContainerForMethod}
import com.vmlens.trace.agent.bootstrap.description.{TestLoopDescription, ThreadDescription}
import com.anarsoft.race.detection.report.element.LoopRunAndThreadIndex

import scala.collection.mutable

class ContainerMapCollection {

  val threadNames = new mutable.HashMap[LoopRunAndThreadIndex, ThreadDescription]();
  val methodNames = new mutable.HashMap[Integer,ContainerForMethod]();
  val fieldNames = new mutable.HashMap[Integer,ContainerForField]();
  val loopNames = new mutable.HashMap[Integer,TestLoopDescription]();
  

}
