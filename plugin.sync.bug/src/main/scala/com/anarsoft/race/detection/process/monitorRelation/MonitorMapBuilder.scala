package com.anarsoft.race.detection.process.monitorRelation

import scala.collection.mutable.Stack;
import java.util.ArrayList;




class MonitorMapBuilder() {
  
  
  
  val blockIdAndMethodIdList = new ArrayList[MonitorIdBlockIdStackTraceOrdinal]();
  val programPosition2Interval = new  ArrayList[ListBasedMapEntry[MonitorListInterval]]();
  
  
  val EMPTY_LIST_INTERVAL = new MonitorListInterval(0,0);
  
  
  
  
  
  
  def create() =
  {
    
    new MonitorMapPerThread( ListBasedMap(programPosition2Interval) , blockIdAndMethodIdList   );
    
    
  }
  
  
  
  
  /*
   * es wird der neue Zustand gesetzt
   * 
   * D.h. programCounter < aktuell alter stand
   * programCounter == aktuell neuer stand
   * 
   * 
   */
  
  
  def addStack(programCounter : Int,   monitorStack : Stack[BlockIdAndEvent4MonitorRelationEnter])
  {
    

    if(  monitorStack.isEmpty )
    {
      programPosition2Interval.add(new  ListBasedMapEntry(programCounter , EMPTY_LIST_INTERVAL ) );
    }
    else
    {
      
      val start = blockIdAndMethodIdList.size();
      
      for( elem <- monitorStack )
    {
      //  MonitorInfoElement( val monitorId : Int,  val blockId : Int, val methodId : Int ) 
        

        
      blockIdAndMethodIdList.add(  elem.createMonitorIdBlockIdStackTraceOrdinal()  );
      
      
    }
      
       programPosition2Interval.add( new  ListBasedMapEntry( programCounter , new MonitorListInterval(start ,  blockIdAndMethodIdList.size())  ));
      
      
      
    }
    
    
    
    
    
  }
  
  
  
}

