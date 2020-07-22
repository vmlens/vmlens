package com.anarsoft.race.detection.process.setStacktraceOrdinal


import com.anarsoft.race.detection.process.method._;
import scala.collection.mutable.HashSet
import  com.anarsoft.race.detection.model.method.StackTraceOrdinalAndMethodId


trait EventSetStacktraceOrdinal {
  
  
  
   def methodCounter: Int;
   def threadId : Long;
   
   def methodIdOption               : Option[Int];
   def isStackTraceIncompleteOption : Option[Boolean];
   def setStackTraceOrdinal(in: StackTraceOrdinalAndMethodId);
   
   def setStackTraceOrdinal(methodFlowPerThread: MethodFlowPerThread, stackTraceTree: StackTraceTree,threadOrdinalAndStackTraceSet : HashSet[ThreadOrdinalAndStackTrace],threadOrdinal : Int): Option[Int] = {

    try {

      val stackTraceOrdinal =   methodIdOption match
      {
        
        case Some(methodId) =>
          {
            val s =
            isStackTraceIncompleteOption match
            {
              
              case None =>
                {
                    methodFlowPerThread.getStackTraceOrdinal(methodCounter,
                    methodId , stackTraceTree);
                }
                
              case Some(stackTraceIncomplete) =>
                {
                    methodFlowPerThread.getStackTraceOrdinal(methodCounter,
                    methodId, stackTraceIncomplete, stackTraceTree);
                }
              
              
              
            }
            
            threadOrdinalAndStackTraceSet.add(  new ThreadOrdinalAndStackTrace(  threadOrdinal ,  s.ordinal ) );
            
            
            
            s;
            
           
          }
        
        case None =>
          {
            methodFlowPerThread.getStackTraceOrdinal(methodCounter, stackTraceTree);
          }
          
        
        
      }
      
      
      
      
    

      setStackTraceOrdinal(stackTraceOrdinal);
      stackTraceOrdinal.parallizeId;
      
    
      
      
    } catch {
      case exp: Exception =>
        {
          System.err.println(toString());
          exp.printStackTrace();
          throw exp;
        }

    }

  }
   
   
  // Felder für Statement Blöcke 
   
   
   
//   def programCounter : Int;
   
   
   
  // def accept(visitor : EventSetStacktraceOrdinalVisitor);
   
   
   
   
  
}