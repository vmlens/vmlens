package com.anarsoft.race.detection.event.control

import com.anarsoft.race.detection.warning.Warning

import scala.collection.mutable


class ProcessContext {

  val warningList = new mutable.HashSet[Warning];
  var isFailure = true;
  
}
