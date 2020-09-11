package com.anarsoft.race.detection.process.method

import scala.collection.mutable._
import com.anarsoft.race.detection.model.method.StackTraceOrdinalAndMethodId
import com.anarsoft.race.detection.model.method.StackTraceGraphBuilder
import com.anarsoft.race.detection.model.description._;
import com.anarsoft.race.detection.model.method.StackTraceElement2Id;
import java.util.ArrayList
import com.anarsoft.race.detection.model.result.StackTraceOrdinal
import com.anarsoft.race.detection.model.graph.ModelKey2OrdinalMap
import com.anarsoft.race.detection.model.result.MethodOrdinal

class StackTraceForestPerThreadBuilder(val threadOrdinal : Int,val threadOrdinalAndStackTraceSet : HashSet[ThreadOrdinalAndStackTrace]) {
  
   val stack = new ArrayStack[StackTraceOrdinalAndMethodId]();
   var start =  new StackTraceOrdinalAndMethodId(0 , -1 , None);
   var currentParallizeId : Option[Int] = None;
   
   
   
  def setBeginningStackTrace(stackTraceRevert :  ArrayBuffer[StackTraceElementModel],stackTraceElement2Id : StackTraceElement2Id, stackTraceTree : StackTraceTree)
  {
    val stackTrace = stackTraceRevert.reverse;
    
    for( e <- stackTrace )
    {
      
      val methodId = stackTraceElement2Id.getId(e);
     
      
      
         if( stack.isEmpty )
        {
          
          
                stackTraceTree.methodId2RootNode.get(methodId) match
                {
                  
                  case None =>
                    {
                       val newOrdinal = stackTraceTree.getStackTraceOrdinalInternal(methodId ,  StackTraceGraphBuilder.PARENT_ORDINAL_OF_ROOT  );
                       val newStackTraceOrdinalAndMethodId = new StackTraceOrdinalAndMethodId(newOrdinal , methodId,None);
                       
                       start = newStackTraceOrdinalAndMethodId;
                   
                       stack.push(newStackTraceOrdinalAndMethodId);
                       stackTraceTree.methodId2RootNode.put(methodId, newOrdinal);
                                        
                      
                      
                    }
                  
                  case Some(r) =>
                    {
                         val stackTraceOrdinalAndMethodId =  new StackTraceOrdinalAndMethodId(r , methodId,None );
                    
                           start = stackTraceOrdinalAndMethodId;
                         
                       stack.push(stackTraceOrdinalAndMethodId);
                      
                      
                    }
                  
                }
          
          
          
          
          
          
        }
        else
        {
                  val n =  stackTraceTree.getStackTraceOrdinalInternal (methodId , stack.top.ordinal  );
                   val stackTraceOrdinalAndMethodId =  new StackTraceOrdinalAndMethodId(n , methodId ,None);
                 start = stackTraceOrdinalAndMethodId;
                  stack.push(  stackTraceOrdinalAndMethodId );
        }
      
      
      
      
      
      
      
      
      
      
      
    }
    
    
  }
   
   
 // ,statementList : ArrayList[Statement] 
 
  def add2StatementListAndMethodFlow(forFlow : StackTraceOrdinalAndMethodId ,forStatements : StackTraceOrdinalAndMethodId, event : MethodEvent,methodFlow : MethodFlowBlock   )
  {
     methodFlow.add( forFlow);
   
     threadOrdinalAndStackTraceSet.add( new ThreadOrdinalAndStackTrace( threadOrdinal ,  forFlow.ordinal )  );

     
    
     
     
  }
  
  
  
  // ,statementList : ArrayList[Statement]
  
  
  
  
   
   def visit(event : MethodEvent,methodFlow : MethodFlowBlock , stackTraceTree : StackTraceTree ,methodId2Ordinal :  ModelKey2OrdinalMap[Int] ,
       context : ContextMethodData )
   {
     
 
     
     if( event.isMethodEnter )
     {

 
          if( event.isParallized() )
                  {
            
                    currentParallizeId = event.getParallizeId();
                    
                   
                     val  parallizedMethodEnterEvent =  event.asInstanceOf[ParallizedMethodEnterEvent] ;
                    
                     parallizedMethodEnterEvent.methodOrdinal =  methodId2Ordinal.getOrAddOrdinal(  event.methodId() );
                     
                     
          
                    
                    
                  }
       
          if( stack.isEmpty )
        {
          
            
            
                stackTraceTree.methodId2RootNode.get(event.methodId) match
                {
                  
                  case None =>
                    {
                       val newOrdinal = stackTraceTree.getStackTraceOrdinalInternal(event.methodId ,  StackTraceGraphBuilder.PARENT_ORDINAL_OF_ROOT  );
                       val newStackTraceOrdinalAndMethodId = new StackTraceOrdinalAndMethodId(newOrdinal , event.methodId,None);
                       
                       
//                       methodFlow.add( newStackTraceOrdinalAndMethodId);
                       
                       add2StatementListAndMethodFlow( newStackTraceOrdinalAndMethodId , newStackTraceOrdinalAndMethodId , event , methodFlow  );
                       
                       
                       stack.push(newStackTraceOrdinalAndMethodId);
                       stackTraceTree.methodId2RootNode.put(event.methodId, newOrdinal);
                       
                       if( ! event.isParallized())
                       {
                         currentParallizeId = None;
                       }
                      
                                     
                      
                    }
                  
                  case Some(r) =>
                    {
                         val stackTraceOrdinalAndMethodId =  new StackTraceOrdinalAndMethodId(r , event.methodId , None );
//                       methodFlow.add(   stackTraceOrdinalAndMethodId);
                       
                       add2StatementListAndMethodFlow( stackTraceOrdinalAndMethodId , stackTraceOrdinalAndMethodId , event , methodFlow   );
                       stack.push(stackTraceOrdinalAndMethodId);
                       
                       if( ! event.isParallized())
                       {
                         currentParallizeId = None;
                       }
                      
                      
                      
                    }
                  
                }
          
          
          
          
          
          
        }
        else
        {
                  val n =  stackTraceTree.getStackTraceOrdinalInternal ( event.methodId , stack.top.ordinal  );
                  
               
                  
                  
                  
                  val stackTraceOrdinalAndMethodId =  new StackTraceOrdinalAndMethodId(n , event.methodId , currentParallizeId);
//                  methodFlow.add (    stackTraceOrdinalAndMethodId);
                   add2StatementListAndMethodFlow( stackTraceOrdinalAndMethodId ,stackTraceOrdinalAndMethodId ,  event , methodFlow    );
                  stack.push(  stackTraceOrdinalAndMethodId );
                 
//                currentParallizeId match
//                {
//                  case None =>
//                    {
//                      
//                    }
//                 
//                  case Some(x) =>
//                    {
//                      additionalOpOnMethodInParallizeBlock( event , x , context );
//                    }
//                  
//                  
//                }
                  
                  
                  
        }
      
       
       
     }
     else
     {
//           currentParallizeId match
//                {
//                  case None =>
//                    {
//                      
//                    }
//                 
//                  case Some(x) =>
//                    {
//                      additionalOpOnMethodInParallizeBlock( event , x , context );
//                    }
//                  
//                  
//                }
//      
       
       
        if( stack.isEmpty )
     {
          
          val empty =  new StackTraceOrdinalAndMethodId( StackTraceGraphBuilder.UNKNOWN_METHOD_ID  ,StackTraceGraphBuilder.PARENT_ORDINAL_OF_ROOT, None ) 
          
       //    methodFlow .add(   new StackTraceOrdinalAndMethodId( StackTraceGraphBuilder.UNKNOWN_METHOD_ID  ,StackTraceGraphBuilder.PARENT_ORDINAL_OF_ROOT )) ;//stackTraceForest.notTracedRoot;
               add2StatementListAndMethodFlow( empty , empty , event , methodFlow    );
                currentParallizeId = None;
       
     }
     else
     {
       
      
       
        if(  ! stack.isEmpty && stack.top.methodId   > 0 && stack.top.methodId != event.methodId() )
        {
//           println(  stack.size  );
//          println( stack.top.methodId  );
//          println( event );
              methodFlow.add(   stack.top);
             //add2StatementListAndMethodFlow(   stack.top , current , event , methodFlow  , statementList );
  
          //  throw new RuntimeException(event + " " +    stack.top.methodId);
        }
        else
        {
                 
         var current = stack.pop();
      

      
      if( stack.isEmpty )
      { 
      //  methodFlow .add(   new StackTraceOrdinalAndMethodId( StackTraceGraphBuilder.UNKNOWN_METHOD_ID  ,StackTraceGraphBuilder.PARENT_ORDINAL_OF_ROOT )) ;//stackTraceForest.notTracedRoot;
        
          add2StatementListAndMethodFlow(   new StackTraceOrdinalAndMethodId( StackTraceGraphBuilder.UNKNOWN_METHOD_ID  ,StackTraceGraphBuilder.PARENT_ORDINAL_OF_ROOT , None) , current , event , methodFlow    );
           currentParallizeId = None;

     
      }  
      else
      {
      
      //   methodFlow.add ( stack.top);
         
           add2StatementListAndMethodFlow(   stack.top , current , event , methodFlow   );
         
      }  
         
         if( event.isParallized() )
       {
           
              currentParallizeId = None
       }
       
     }
     
     }
     
     
     }
   
   }
  
  
  
}