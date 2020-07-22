package com.vmlens.api.internal.reports

import com.anarsoft.race.detection.model.result._
import com.vmlens.api.internal.reports.element._
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashMap
import java.io._
import java.util.ArrayList
import  com.anarsoft.race.detection.process.interleave.InterleaveEventStatement
import com.anarsoft.race.detection.process.SortArrayList
import com.anarsoft.race.detection.process.interleave.ComparatorInterleaveEvent
import com.anarsoft.race.detection.process.nonVolatileField.InterleaveEventNonVolatileAccess
import com.anarsoft.race.detection.process.syncAction.SyncActionLockEnterInterleave
import com.anarsoft.race.detection.process.syncAction.SyncActionLockExitInterleave
import com.anarsoft.race.detection.process.monitor.MonitorEnterEventInterleave
import com.anarsoft.race.detection.process.monitor.MonitorExitEventInterleave
import com.anarsoft.race.detection.process.scheduler.MethodAtomicEnterEvent
import com.anarsoft.race.detection.process.scheduler.MethodAtomicExitEvent
import com.anarsoft.race.detection.process.scheduler.MethodCallbackEnterEvent
import com.anarsoft.race.detection.process.scheduler.MethodCallbackExitEvent
import com.anarsoft.race.detection.process.volatileField.VolatileAccessEventInterleave
import com.anarsoft.race.detection.process.volatileField.VolatileAccessEventStaticInterleave
import com.anarsoft.race.detection.process.volatileField.VolatileArrayAccessEventInterleave
import com.anarsoft.race.detection.process.interleave.StatementVisitor
import com.vmlens.trace.agent.bootstrap.util.Constants
import com.anarsoft.race.detection.process.interleave.StatementLoop
import com.anarsoft.race.detection.process.interleave.LoopDetectionAlgo
import com.typesafe.scalalogging.Logger
import com.anarsoft.race.detection.process.scheduler.MethodAtomicExitEvent

import com.anarsoft.race.detection.process.syncAction.StampedLockExitInterleave
import com.anarsoft.race.detection.process.syncAction.StampedLockEnterInterleave


class CreateParallizedReportAlgo {
 
    val logger = Logger(classOf[CreateParallizedReportAlgo])
  
    def filterAtomic( eventList :   ArrayList[InterleaveEventStatement]) =
  {
     val filtered = new ArrayList[InterleaveEventStatement]
     
     var threadId = -1L;
     
    val iter = eventList.iterator();
   
    
      var atomicCall=0;
     var callbackCall=0;
  
     
    val visitStart = new StatementVisitor()
    {
      
      
      
      
      
        def visit(statement: StampedLockExitInterleave)
        {
          
        }
 
        def visit(statement: StampedLockEnterInterleave)
        {
          
        }
  
      
      
      
      
      
        def visit(statement: InterleaveEventNonVolatileAccess)
        {
          
        }
 
        def visit(statement: SyncActionLockEnterInterleave)
        {
          
        }
  
        def visit(statement: SyncActionLockExitInterleave)
        {
          
        }
  
        def visit(statement: MonitorEnterEventInterleave)
        {
          
        }
  
        def visit(statement: MonitorExitEventInterleave)
        {
          
        }

  
        def visit(statement: MethodAtomicEnterEvent)
        {
          
        }
  
        def visit(statement: MethodAtomicExitEvent)
        {
          atomicCall = atomicCall - 1;
        }

  
        def visit(statement: MethodCallbackEnterEvent)
        {
          callbackCall = callbackCall + 1;
        }
  
        def visit(statement: MethodCallbackExitEvent)
        {
          
        }
  
        def visit(statement: VolatileAccessEventInterleave)
        {
          
        }
  
        def visit(statement: VolatileAccessEventStaticInterleave)
        {
          
        }
        
  
        def visit(statement: VolatileArrayAccessEventInterleave)
        {
          
        }
    
    
    
    };
     
    
    val visitEnd = new StatementVisitor()
    {
      def visit(statement: StampedLockExitInterleave)
        {
          
        }
 
        def visit(statement: StampedLockEnterInterleave)
        {
          
        }
      
      
      
        def visit(statement: InterleaveEventNonVolatileAccess)
        {
          
        }
 
        def visit(statement: SyncActionLockEnterInterleave)
        {
          
        }
  
        def visit(statement: SyncActionLockExitInterleave)
        {
          
        }
  
        def visit(statement: MonitorEnterEventInterleave)
        {
          
        }
  
        def visit(statement: MonitorExitEventInterleave)
        {
          
        }

  
        def visit(statement: MethodAtomicEnterEvent)
        {
           atomicCall = atomicCall + 1;
        }
  
        def visit(statement: MethodAtomicExitEvent)
        {
         
        }

  
        def visit(statement: MethodCallbackEnterEvent)
        {
          
        }
  
        def visit(statement: MethodCallbackExitEvent)
        {
          callbackCall = callbackCall - 1;
        }
  
        def visit(statement: VolatileAccessEventInterleave)
        {
          
        }
  
        def visit(statement: VolatileAccessEventStaticInterleave)
        {
          
        }
        
  
        def visit(statement: VolatileArrayAccessEventInterleave)
        {
          
        }
    
    
    
    };
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    while( iter.hasNext() )
    {
      val e = iter.next();
      
      if( e.threadId != threadId )
      {
        atomicCall=0;
        callbackCall=0;
        
        threadId = e.threadId;
        
      }
      
      
      e.acceptStatementVisitor(visitStart);
      
      
      if( atomicCall < 1 || callbackCall > 0 )
      {
        filtered.add(e);
      }
      
        e.acceptStatementVisitor(visitEnd);
      
      
    } 
    
    
    filtered;
    
  }
   
   
 
  
  
  
  def filterDoubleLockEventsAndAtomic( eventList :   ArrayList[InterleaveEventStatement]) =
  {
    
    SortArrayList.sort(eventList, new InterleaveEventStatementComparatorByThread())
    
    
    val preFiltered = new ArrayList[InterleaveEventStatement]
    
    var current : Option[InterleaveEventStatement] = None;
    val iter = eventList.iterator();
    
    while( iter.hasNext() )
    {
      val e = iter.next();
      
 
      
      current match
      {
        case None =>
          {
            preFiltered.add(e);
          }
        
        case Some(x) =>
          {
            if( ! x.isSame(e) )
            {
               preFiltered.add(e);
            }
          }
          
        
      }
      
      current = Some(e);
      
      
    }
    
   
    
    
    val filtered = filterAtomic(preFiltered);
    
    
      SortArrayList.sort( filtered, new ComparatorInterleaveEvent() );
    
    filtered
    
  }
  
  
  
  def transformStatementEventList( unfiltered  :   ArrayList[InterleaveEventStatement] , modelFacade : ModelFacadeAll) =
  {


    
    var take = true;
    
    
      val visitAtomic = new StatementVisitor()
    {
        
        def visit(statement: StampedLockExitInterleave)
        {
          
        }
 
        def visit(statement: StampedLockEnterInterleave)
        {
          
        }
        
        
        def visit(statement: InterleaveEventNonVolatileAccess)
        {
          
        }
 
        def visit(statement: SyncActionLockEnterInterleave)
        {
          
        }
  
        def visit(statement: SyncActionLockExitInterleave)
        {
          
        }
  
        def visit(statement: MonitorEnterEventInterleave)
        {
          
        }
  
        def visit(statement: MonitorExitEventInterleave)
        {
          
        }

  
        def visit(statement: MethodAtomicEnterEvent)
        {
          
        }
  
        def visit(statement: MethodAtomicExitEvent)
        {
          
          if( statement.hasCallback == Constants.FALSE )
          {
            take = false;
          }
          
          
        }

  
        def visit(statement: MethodCallbackEnterEvent)
        {
          
        }
  
        def visit(statement: MethodCallbackExitEvent)
        {
          
        }
  
        def visit(statement: VolatileAccessEventInterleave)
        {
          
        }
  
        def visit(statement: VolatileAccessEventStaticInterleave)
        {
          
        }
        
  
        def visit(statement: VolatileArrayAccessEventInterleave)
        {
          
        }
    
    
    
    };
     
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    val eventList = filterDoubleLockEventsAndAtomic(unfiltered);
    
    val context = new ContextLastRun();
    
   val filteredStatementList = new ArrayList[InterleaveEventStatement]
   val threadId2MonitorCount = new HashMap[Long,Int]
    
   //   val threadId2Stream = new HashMap[Long,PrintStream]
    
    val iter = eventList.iterator();
    
    while( iter.hasNext() )
    {
      
      val elem = iter.next();
      
    
      
      
          context.addThreadId( elem.threadId )
      
      
      var  count = threadId2MonitorCount.getOrElseUpdate(  elem.threadId , 0);
      
   
    
          
      
     
     take = true;
     
     elem.acceptStatementVisitor(visitAtomic);
     
     
     if( take )
     {
      filteredStatementList.add(elem);
     
     }
     
      
    }
    
    
    val listWithLoops = new  ArrayList[Either[InterleaveEventStatement,StatementLoop]]
    
    LoopDetectionAlgo.detectLoop(filteredStatementList,listWithLoops);
      
     // result.append(  new ReportParallizedStatementBlock(elem,  modelFacade , context) )   
     
//     for( elem <- threadId2Stream)
//     {
//       elem._2.close();    
//     }
//    
    
  // replaceAtomic(result)
   
     val result = new ArrayBuffer[ReportLoopOrStatement]
     
     val listWithLoopsIter = listWithLoops.iterator();
     
     while( listWithLoopsIter.hasNext() )
     {
        val current = listWithLoopsIter.next();
        
        current match
        {
          case Left(x) =>
            {
                result.append( new ReportLoopOrStatement(None ,  Some(new ReportStatementBlock(x,  modelFacade , context))) )
            }
          
          case Right(x) =>
            {
             val stList = new ArrayBuffer[ReportStatementBlockInLoop]
             
           //  val i = x.statements.iterator();
             
            for( i <- 0 until x.statements.size() )
             {
               val s = x.statements.get(i)
               
               val bordertop    = (i == 0)
               val borderBottom = ( i == ( x.statements.size() -1 ))
               
               
               stList.append(new ReportStatementBlockInLoop(s,  modelFacade , context, bordertop , borderBottom))
             }
              
              result.append( new ReportLoopOrStatement(Some(new ReportLoop(x.loopCount , stList.toList) ) , None)) 
              
            }
          
          
        }
       
       
     }
     
     
     
     
    result;
   
  }
  
  
//  def replaceAtomic(in : ArrayBuffer[ReportParallizedStatementBlock]) =
//  {
//      val result = new ArrayBuffer[ReportParallizedStatementBlock]
//      
//      var prevoius : Option[ReportParallizedStatementBlock] = None;
//      
//      
//      for( elem <- in)
//      {
//        if(  elem.atomicMethod.isEmpty )
//        {
//          prevoius.foreach(  x =>  result.append(x) )
//          prevoius = None;
//          
//          result.append(  elem )
//        }
//        else
//        {
//          
//          prevoius match
//          {
//            case None =>
//              {
//                prevoius = Some(elem);
//              }
//            
//            case Some(x) =>
//              {
//                
//                val a = elem.atomicMethod.get;
//                
//              result.append(  new   ReportParallizedStatementBlock(None, None,Some(new ReportAtomicMethod(  a.method, a.threadId , new AtomicCall() , a.prefixCount  , a.context )  )  , None , elem.stackTraceOrdinal , elem.modelFacade ) );
//                
//               prevoius = None; 
//              }
//            
//            
//          }
//          
//        }
//        
//      }
//      
//       prevoius.foreach(  x =>  result.append(x) )
//      
//      result;
//  }
//  
  


  

  
}



  
  
 
  
  
