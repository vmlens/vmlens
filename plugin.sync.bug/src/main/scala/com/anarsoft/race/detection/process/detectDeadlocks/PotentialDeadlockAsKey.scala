package com.anarsoft.race.detection.process.detectDeadlocks

case class PotentialDeadlockAsKey(val higherMonitorId : Int, val lowerMonitorId : Int , 
    val higherMonitorIsChild : Boolean , val threadId : Long  , val parentMonitorIds : List[Int] ) {
  
}