package com.anarsoft.race.detection.process.detectDeadlocks

case class ThreadIdAndParentMonitors(val threadId : Long, val parentList : Set[Int]) {
  
}