package com.anarsoft.race.detection.process.method

import java.util._
import com.anarsoft.race.detection.model.method.StackTraceOrdinalAndMethodId
import com.anarsoft.race.detection.model.method._;

/**
 * 
 *  last und first zeitlich gesehen
 *  
 *  d.h
 *  
 *  detLat das letzte hinzugegügte element
 *  
 * 
 * 
 */



class MethodFlowPerThreadImpl  extends MethodFlowPerThread {
  
  
  val fifo : LinkedList[MethodFlowBlock] = new LinkedList[MethodFlowBlock]();
  
  var lastReturnedStackTraceId = new StackTraceOrdinalAndMethodId(0,0,None);
  
  
 // var lastPutBlockLastElementId : Option[Int] = None;
  
  
  /*
   *  	add(e)
   *    remove()
   *   peek()
   */
  
  def createMethodFlowBlock( start : StackTraceOrdinalAndMethodId ) =
  {
    
  
    
    val block =
    
        if( fifo.isEmpty() )
        {
           val n =   new MethodFlowBlock( 0 );
      
            n.add( start );
            
            n;
        }
        else
        {
           new MethodFlowBlock( fifo.getLast().lastElementId()   );
          
      
      }
      
      
      
    
    
    fifo.addLast( block );
    
    
    /**
     * 
     * ich lese einen block vorher und behalte den vorherigen Block, 
     * so das die events in der mitte liegen daher 3 + 1 da ich volatile 
     * und monitore vorher prozessiere
     * 
     */
    
    if( fifo.size() > 4 )
    {
      fifo.removeFirst();
    }
    
    
    block;
    
  }
  
  
  
  
  
  def hasStackTraceOrdinal(methodCounter : Int) =
  {
     methodCounter <  fifo.getLast.lastElementId() && fifo.getFirst.start <= methodCounter;
  }
  
  
  
  def getStackTraceOrdinalAndMethodId(methodCounter : Int ,  stackTraceTree : StackTraceTree) =
  {
      val iter = fifo.iterator();
      
      var current = iter.next()
      
      while( iter.hasNext()  && current.lastElementId() <= methodCounter )
      {
       //  println("iter lastElementId " + current.lastElementId()  + " "  + current.start);
        current = iter.next();
             
      }
      
        try{
      
     lastReturnedStackTraceId = current.get(methodCounter);
      
     lastReturnedStackTraceId;
     
       }
    catch
    {
      case exp : java.lang.IndexOutOfBoundsException =>
        {
         
//            val iter = fifo.iterator();
//          
//             println(" requesting " +  methodCounter) ;
//             
//           
//            
//             while( iter.hasNext()  )
//      {
//        val current = iter.next()       
//               
//         println("lastElementId " + current.lastElementId()  + " "  + current.start);
//             
//      }
            
            lastReturnedStackTraceId;
          
        }
    }
      
      
  }
  
   def getStackTraceOrdinal(methodCounter : Int ,  methodId : Int,  stackTraceIncomplete : Boolean , stackTraceTree : StackTraceTree  )  =
   {
     
     if(stackTraceIncomplete)
     {
       val stackTraceOrdinalAndMethodId =  getStackTraceOrdinalAndMethodId(methodCounter , stackTraceTree );
        val intermediate =   stackTraceTree.getStackTraceOrdinalInternal( StackTraceElement2Id.MISSING_LINK_ID , stackTraceOrdinalAndMethodId.ordinal );
           new StackTraceOrdinalAndMethodId(     stackTraceTree.getStackTraceOrdinalInternal( methodId , intermediate ) , methodId , None  ) ;
     }
     else
     {
       getStackTraceOrdinal(methodCounter : Int , methodId : Int, stackTraceTree : StackTraceTree  );
     }
     
     
     
   }
   
  
  
    def getStackTraceOrdinal(methodCounter : Int , stackTraceTree : StackTraceTree  ) =
      getStackTraceOrdinalAndMethodId(methodCounter : Int ,  stackTraceTree : StackTraceTree);
  
  
  
    def getStackTraceOrdinal(methodCounter : Int , methodId : Int, stackTraceTree : StackTraceTree  ) =
    {
    
     val elem =  getStackTraceOrdinalAndMethodId(methodCounter : Int ,  stackTraceTree : StackTraceTree);
      
      if( elem.methodId ==  methodId )
      {
        elem;
      }
      else
      {
       new StackTraceOrdinalAndMethodId(  stackTraceTree.getStackTraceOrdinalInternal(methodId, elem.ordinal) , methodId , elem.parallizeId );
        
        
      }
      
      
      
    }
  
}