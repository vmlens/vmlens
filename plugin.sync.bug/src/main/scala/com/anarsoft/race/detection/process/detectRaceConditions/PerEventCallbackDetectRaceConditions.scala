package com.anarsoft.race.detection.process.detectRaceConditions

import com.anarsoft.race.detection.process.perEventList.PerEventListAbstract
import java.util.ArrayList;
import com.anarsoft.race.detection.process.perEventList.PerEventList4Aggregate
import com.vmlens.api.MemoryAccessType
import com.typesafe.scalalogging.Logger

class PerEventCallbackDetectRaceConditions[ID_PER_OBJECT, EVENT <: EventDetectRaceConditions[ID_PER_OBJECT]](val defaultId: ID_PER_OBJECT, val getCurrentReadFieldsInternal: ContextDetectRaceConditions => ArrayList[EVENT]) extends PerEventListAbstract[EVENT, ContextDetectRaceConditions] {

  def getCurrentReadFields(context: ContextDetectRaceConditions) = getCurrentReadFieldsInternal(context);

  def comparator = new ComparatorEventDetectRaceConditions[ID_PER_OBJECT, EVENT]();

    val logger = Logger(classOf[PerEventCallbackDetectRaceConditions[_,_]])

  
  

  def processEventList(list: ArrayList[_ <: EVENT], context: ContextDetectRaceConditions) {

    val iter = list.iterator();
    
    var algo  : Tuple2[ ID_PER_OBJECT , Option[DetectRaceConditionsAlgo[ID_PER_OBJECT,EVENT ] ]] = Tuple2(defaultId , None  ) 
    

    while (iter.hasNext()) {
      val current = iter.next();

  
      logger.trace("" + current);
      
      if( algo._1 == current.idPerMemoryLocation )
      {
        
        
        
        algo._2 match
        {
          case Some(a) =>
            {
              if( ! a.hasRace)
              {
                  a.add(  current );
              }
              
            
            }
          
          case None =>
            {
              // nothing to do, read only
            }
          
          
        }
        
        
        
      }
      else
      {
        if( MemoryAccessType.isReadOnly(current.operation) )
        {
          algo = Tuple2(current.idPerMemoryLocation , None );
        }
        else if(  context.raceLocation2Count.getOrElse( current.getLocationInClass() , 0 ) > 10  )
        {
          algo = Tuple2(current.idPerMemoryLocation , None );
        }
        else
        {
             algo = Tuple2(current.idPerMemoryLocation , Some(  DetectRaceConditionsAlgo( context , current ) ) );
        }
        
        
        
      }
      
      
      

    }



  }

}

object PerEventCallbackDetectRaceConditions {

  def apply[ID_PER_OBJECT, EVENT <: EventDetectRaceConditions[ID_PER_OBJECT]](
    defaultId: ID_PER_OBJECT, getCurrentReadFields: ContextDetectRaceConditions => ArrayList[EVENT]) =
    {

      new PerEventCallbackDetectRaceConditions[ID_PER_OBJECT, EVENT](defaultId, getCurrentReadFields);

      //    new PerEventList4Aggregate( getCurrentReadFields , new ComparatorEventDetectRaceConditions[ID_PER_OBJECT,EVENT]() ,  defaultId ,
      //        (context : ContextDetectRaceConditions ) =>  new PerEventCallbackDetectRaceConditions[ID_PER_OBJECT,EVENT](context) );
    }

}