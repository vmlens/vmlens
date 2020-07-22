package com.anarsoft.race.detection.process.arrayId

import com.anarsoft.race.detection.process.perEventList.PerEventListAbstract
import java.util.ArrayList;
import com.anarsoft.race.detection.process.perEventList.PerEventList4Aggregate
import com.vmlens.api.MemoryAccessType

class PerEventCallbackSetArrayOrdinal[ EVENT <: ArrayIdEvent]() 
  extends PerEventListAbstract[EVENT, ContextSetArrayOrdinal[EVENT]] {

  def getCurrentReadFields(context: ContextSetArrayOrdinal[EVENT]) = context.arrayIdEventList;

  def comparator = new ComparatorArrayIdEvent();

  

  
  

  def processEventList(list: ArrayList[_ <: EVENT], context: ContextSetArrayOrdinal[EVENT]) {

 
   val iter = list.iterator();
    
    while( iter.hasNext() )
    {
       val current = iter.next();
       
       current.classOrdinal = context.classIdMap.getOrCreate(current.classId);
    }



  }

}
//
//object PerEventCallbackSetArrayOrdinal {
//
//  def apply[ EVENT <: ArrayIdEvent]() =
//    {
//
//      new PerEventCallbackDetectRaceConditions[ID_PER_OBJECT, EVENT](defaultId, getCurrentReadFields);
//
//      //    new PerEventList4Aggregate( getCurrentReadFields , new ComparatorEventDetectRaceConditions[ID_PER_OBJECT,EVENT]() ,  defaultId ,
//      //        (context : ContextDetectRaceConditions ) =>  new PerEventCallbackDetectRaceConditions[ID_PER_OBJECT,EVENT](context) );
//    }
//
//}