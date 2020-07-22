package com.anarsoft.race.detection.process.interleave

import java.util.ArrayList
import scala.collection.mutable.HashSet
import com.anarsoft.race.detection.model.result.LocationInClass
import com.vmlens.trace.agent.bootstrap.util.LoopResultStatusCodes
import com.vmlens.api.internal.IconRepository
import com.vmlens.api.Icon
import com.vmlens.api.MemoryAccessType

abstract sealed class LoopResult {
  
  def list :  ArrayList[InterleaveEventStatement];
  def toDisplayText() : String;
  def count : Int;
 
  def icon : Icon;
  
}


case class LoopResultError( val list : ArrayList[InterleaveEventStatement],val count : Int) extends LoopResult {
    def toDisplayText()  = "Error";   
    def icon  = IconRepository.INTERLEAVE_ERROR;
    
}


case class LoopResultRace( val list : ArrayList[InterleaveEventStatement] , val raceHasRead : Boolean,var count : Int) extends LoopResult {
   def toDisplayText()  = "Data Race";
   
    def icon = { if(raceHasRead)
           {
              IconRepository.getImageForField(   new  MemoryAccessType( MemoryAccessType.IS_READ_WRITE ) ,false, false , true  )
           }
           else
           {
              IconRepository.getImageForField(   new  MemoryAccessType( MemoryAccessType.IS_WRITE ) ,false, false , true  )
           }
    }
   
}
/*
 * 	public static final int OK = 0;
	public static final int MAXIMUM_RUN_COUNT_REACHED = 1;
	public static final int MAXIMUM_OPERATION_COUNT_REACHED = 2;
 */

case class LoopEndedNormally( val list : ArrayList[InterleaveEventStatement] ,val count : Int,val status : Int,val hasWarning : Boolean)  extends LoopResult {
    def toDisplayText()  = 
      if(status == LoopResultStatusCodes.OK)
      {
        if(hasWarning)
        {
          "Not all threads were stopped inside the loop.";
        }
        else
        {
            "Success"
        }
        
        
       
      }
      else
      if(status == LoopResultStatusCodes.MAXIMUM_RUN_COUNT_REACHED)
      {
        "Maximum run count reached"
      }
      else
      {
          "Maximum operation count reached"
      }
          
      
    def icon =  
       if(status == LoopResultStatusCodes.OK)
      {
         if(hasWarning)
         {
            IconRepository.INTERLEAVE_WARN;
         }
         else
         {
           IconRepository.INTERLEAVE_OK;
         }
         
          
       
      }
      else
      {
           IconRepository.INTERLEAVE_WARN;
      }
        
      
   
     
}



