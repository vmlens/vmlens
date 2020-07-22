package com.anarsoft.race.detection.process.state

import com.anarsoft.race.detection.process.workflow.SingleStep
import com.anarsoft.race.detection.process._;
import java.util.ArrayList;
import java.util.Comparator;
import com.anarsoft.race.detection.process.perEventList.PerEventListAbstract


class StepBuildClassIdMap  extends SingleStep[ContextState] {
  
  
  def execute(context : ContextState)
  {
    
    if( context.stateFieldEventList != null )
    {
       val iter = context.stateFieldEventList.iterator();
    
    while( iter.hasNext() )
    {
       val current = iter.next();
       
       current.classOrdinal = context.classIdMap.getOrCreate(current.classId);
    }
    }
    
   
    
    
  }
}