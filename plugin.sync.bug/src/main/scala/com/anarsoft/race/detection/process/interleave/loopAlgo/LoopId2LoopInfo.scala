package com.anarsoft.race.detection.process.interleave.loopAlgo

import com.anarsoft.race.detection.process.interleave._;
import com.anarsoft.race.detection.process.nonVolatileField.InterleaveEventNonVolatileAccess
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashSet
import scala.collection.mutable.HashMap
import java.util.ArrayList
import com.anarsoft.race.detection.process.SortArrayList
import com.vmlens.trace.agent.bootstrap.util.LoopResultStatusCodes
import  com.vmlens.api.MemoryAccessType
import  com.anarsoft.race.detection.model.result.StackTraceOrdinalAndMonitor
import com.anarsoft.race.detection.process.monitor.MonitorEnterEventInterleave


class LoopId2LoopInfo {

  var endedRuns = new ArrayBuffer[LoopIdRunId];
  val loopMap = new GenericMap[LoopInfo]((id) => { new LoopInfo(id) })

  val hasRunWithEvents = new HashSet[Int]

  def processBeforeNewSlidingWindowId(
    unprocessedInterleaveEventStatements: ArrayList[InterleaveEventStatement],
    potentialInterleaveEventStatements: ArrayList[InterleaveEventStatement]) =
    {
      if (endedRuns.isEmpty) {
        unprocessedInterleaveEventStatements;

      } else {

        // create actions
        val loopIdRunId2Action = new HashMap[LoopIdRunId, Action]

        for (elem <- endedRuns) {
          val loopInfo = loopMap.getOrCreate(elem.loopId);
          val runInfo = loopInfo.runMap.getOrCreate(elem.runId);

          if (runInfo.startRaceList != None) {
            loopIdRunId2Action.put(elem, Take())
            runInfo.state = Taken();
            hasRunWithEvents.add(elem.loopId)
          } else if (runInfo.hasDeadlock) {
            loopIdRunId2Action.put(elem, Take())
            hasRunWithEvents.add(elem.loopId)
            runInfo.state = Taken();

          } else if (runInfo.eventCount > 0) {
            if (hasRunWithEvents.contains(elem.loopId)) {
              loopIdRunId2Action.put(elem, Filter())
              runInfo.state = Filtered();

            } else {
              hasRunWithEvents.add(elem.loopId)
              loopIdRunId2Action.put(elem, Take())
              runInfo.state = Taken();

            }
          } else {
            runInfo.state = Filtered();
          }


        }

        // create new  unprocessedInterleaveEventStatements
        val newList = new ArrayList[InterleaveEventStatement]();

        // sort and process unprocessedInterleaveEventStatements
        SortArrayList.sort(unprocessedInterleaveEventStatements, new ComparatorInterleaveEventByRunIdLoopId());
        val iter = unprocessedInterleaveEventStatements.iterator();
        var loopIdRunIdAndAction: Option[LoopIdRunIdAndAction] = None;

        while (iter.hasNext()) {
          val current = iter.next();

          loopIdRunIdAndAction match {
            case None =>
              {
                loopIdRunIdAndAction = Some(getNewLoopIdRunIdAndAction(current, loopIdRunId2Action));
              }

            case Some(x) =>
              {
                if (x.loopIdRunId.loopId == current.loopId && (x.loopIdRunId.runId == current.runId)) {

                } else {
                  loopIdRunIdAndAction = Some(getNewLoopIdRunIdAndAction(current, loopIdRunId2Action));
                }

              }

          }

          loopIdRunIdAndAction.get.action match {

            case None =>
              {
                newList.add(current);
              }

            case Some(action) =>
              {
                action match {
                  case Take() =>
                    {
                      potentialInterleaveEventStatements.add(current);
                    }
                  case Filter() =>
                    {
                      // nothing ToDo
                    }

                }

              }

          }

        }

        // endedRuns zurück setzen
        endedRuns = new ArrayBuffer[LoopIdRunId];

        newList;

      }

    }

  def getNewLoopIdRunIdAndAction(event: InterleaveEventStatement, loopIdRunId2Action: HashMap[LoopIdRunId, Action]) =
    {
      val loopIdRunId = new LoopIdRunId(event.loopId, event.runId);
      new LoopIdRunIdAndAction(loopIdRunId, loopIdRunId2Action.get(loopIdRunId));

    }

  def add(event: LoopWarningEvent) {
    loopMap.getOrCreate(event.loopId).hasWarning = true;

  }

  def addRace(read: InterleaveEventNonVolatileAccess) {
    loopMap.getOrCreate(read.loopId).addRace(read);
  }

  def add(event: LoopOrRunEvent) {

    loopMap.getOrCreate(event.loopId).add(event) match {

      case None => {}

      case Some(x) => {

        endedRuns.append(x)
      }

    }
  }

  def add(event: InterleaveEventStatement ) {
    
  
    
    
    loopMap.getOrCreate(event.loopId).add(event);

  }
  
    def addMonitorEvent(event: InterleaveEventStatement,  deadlockFilter : HashSet[StackTraceOrdinalAndMonitor] ) {
    loopMap.getOrCreate(event.loopId).addMonitorEvent(event,  deadlockFilter : HashSet[StackTraceOrdinalAndMonitor] );

  }
  
  
  

  def stopProcessing(
    unprocessedInterleaveEventStatements: ArrayList[InterleaveEventStatement],
    potentialInterleaveEventStatements: ArrayList[InterleaveEventStatement]) =
    {
      val id2Result = new HashMap[Int, LoopResult]
      val loopId2TakeFrom = new HashMap[Int, TakeFrom];

      /*
       * pro loopid durchlaufen
       * take  from potential oder open
       *
       *
       *
       */

      val loopIter = loopMap.list.iterator();

      while (loopIter.hasNext()) {
        val loopInfo = loopIter.next();
        val runIter = loopInfo.runMap.list.iterator();

        var takeFrom: Option[TakeFrom] = None;

        while (runIter.hasNext()) {
          val runInfo = runIter.next();

          
         

          runInfo.state match {
            case Open() =>
              {
                takeFrom = Some(TakeWithError(runInfo.id, loopInfo));
              }

            case Taken() =>
              {
                takeFrom match {
                  case None =>
                    {
                      takeFrom = Some(createTakeFrom(runInfo, loopInfo));

                    }

                  case Some(take) =>
                    {

                      take match {

                        case TakeSuccess(runId, count) =>
                          {
                         
                             takeFrom = Some(createTakeFrom(runInfo, loopInfo));
                            
                          }
                          
                         case TakeIssue(runId, issueType, count) =>
                          {
                         
                              // nothing todo
                            
                          }  
                          
                          

                        case TakeWithError(runId, count ) =>
                          {
                            // nothing todo
                          }

                      }

                    }

                }
              }

            case Filtered() =>
              {

              }

          }

        }
        
      

        takeFrom match {

          case None =>
            {
              //   loopId2TakeFrom.append( new LoopId2TakeFrom( loopInfo.id , None) );

              id2Result.put(loopInfo.id, new LoopEndedNormally(new ArrayList[InterleaveEventStatement](), 0, LoopResultStatusCodes.OK, false));
            }

          case Some(take) =>
            {
              loopId2TakeFrom.put(loopInfo.id, take);
            }

        }

      }

      /*
       * beide listen sortieren und prozessieren
       *
       *
       */

      stopProcessingList(unprocessedInterleaveEventStatements, id2Result, loopId2TakeFrom);
      stopProcessingList(potentialInterleaveEventStatements, id2Result, loopId2TakeFrom);

      id2Result;

    }
  
  /*
   * case class TakeSuccess(val runId : Int ,  val count : Int) extends TakeFrom;

case class TakeIssue(val runId : Int , val hasIssue : Boolean, val count : Int) extends TakeFrom;

   * 
   */
  
  
  def createTakeFrom(runInfo : RunInfo, loopInfo : LoopInfo) =
  {
    var race : Option[RaceCondition] = None;
    var current = runInfo.startRaceList;
    
    while(  current != None && race == None)
    {
      if( current.get.readEvent.raceTaken )
      {
        race = Some(RaceCondition(MemoryAccessType.containsRead( current.get.readEvent.operation)) );
      }
      
      
      current = current.get.next
    }
    
    
    if(runInfo.hasDeadlock)
    {
      TakeIssue(runInfo.id , Deadlock()  ,   loopInfo   );
    }
    else {
      
      race match
      {
        case None =>
          {
             TakeSuccess( runInfo.id ,   loopInfo  );
          }
        case Some(x) =>
          {
              TakeIssue(runInfo.id , x  ,   loopInfo   );
          }
        
      }
      
      
      
    }
    
  }
  
  
  /*
   * if(runInfo.startRaceList != None)
    {
      
    }
    else
    {
     
    }
   */
  
  
  
  
  
  
  
  
  
  

  def stopProcessingList(list: ArrayList[InterleaveEventStatement], id2Result: HashMap[Int, LoopResult],
    loopId2TakeFrom: HashMap[Int, TakeFrom]) {
    
    var takeRunId: Option[TakeRunIdForLoopId] = None;
    var currentList = new ArrayList[InterleaveEventStatement];

    SortArrayList.sort(list, new ComparatorInterleaveEventByRunIdLoopId());

    val iter = list.iterator();

    while (iter.hasNext()) {
      val current = iter.next()


      takeRunId match {
        case None =>
          {
            takeRunId = Some(new TakeRunIdForLoopId(current.loopId, loopId2TakeFrom.get(current.loopId).map(x => x.runId)));
          }

        case Some(x) =>
          {
            if (x.loopId == current.loopId) {

            } else {
              fillResult(x, id2Result,
                loopId2TakeFrom, currentList);

              takeRunId = Some(new TakeRunIdForLoopId(current.loopId, loopId2TakeFrom.get(current.loopId).map(x => x.runId)));

              if (!currentList.isEmpty()) {
                currentList = new ArrayList[InterleaveEventStatement];
              }
            }

          }

      }

      takeRunId.get.runId match {
        case None =>
          {

          }
        case Some(id) =>
          {
            if (id == current.runId) {
              currentList.add(current);
            }

          }

      }

    }

    
   
    
    takeRunId match {
      case None =>
        {

        }

      case Some(x) =>
        {

          fillResult(x, id2Result,
            loopId2TakeFrom, currentList);
        }

    }

  }

  def fillResult(x: TakeRunIdForLoopId, id2Result: HashMap[Int, LoopResult],
    loopId2TakeFrom: HashMap[Int, TakeFrom], currentList: ArrayList[InterleaveEventStatement]) {
    
    
    
    id2Result.get(x.loopId) match {

      case None =>
        {
           id2Result.put(x.loopId,   loopId2TakeFrom.get(x.loopId).get.loopResult(currentList)  );
            

          
        }

      case Some(result) =>
        {
          result.list.addAll(currentList)
        }

    }
  }

}