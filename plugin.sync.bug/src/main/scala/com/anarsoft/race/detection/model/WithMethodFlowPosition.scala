package com.anarsoft.race.detection.model



trait WithMethodFlowPosition {
  
  // def getMethodFlowId() = new Map2ListIdImpl(threadId,methodCounter);

     def  methodCounter : Int; 
     def  threadId : Long;
   
}

class WithMethodFlowPositionImpl(val methodCounter : Int , val  threadId : Long) extends WithMethodFlowPosition
{
  
}