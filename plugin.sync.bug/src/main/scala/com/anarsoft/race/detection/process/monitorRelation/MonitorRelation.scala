package com.anarsoft.race.detection.process.monitorRelation

case  class MonitorRelation( val higherMontorId : Int, val lowerMonitorId : Int , 
    val higherMonitorIsChild : Boolean , val threadId : Long ) {
  
}


