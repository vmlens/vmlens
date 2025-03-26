package com.anarsoft.race.detection.event.control

import scala.collection.mutable


class ProcessContext {

  val warningList = new mutable.HashSet[Int];
  var isFailure = true;
  
}
