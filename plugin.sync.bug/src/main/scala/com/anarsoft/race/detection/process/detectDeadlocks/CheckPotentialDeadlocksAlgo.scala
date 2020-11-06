package com.anarsoft.race.detection.process.detectDeadlocks

import scala.collection.mutable.HashMap
import scala.collection.mutable.HashSet
import com.anarsoft.race.detection.model.result._;

class CheckPotentialDeadlocksAlgo(val higherMonitor: Int, val lowerMonitor: Int) {

  val parentMap = new HashMap[ThreadIdAndParentMonitors, DeadlockLocation]()
  val childMap = new HashMap[ThreadIdAndParentMonitors, DeadlockLocation]

  def add2Map(potentialDeadlock: PotentialDeadlock, map: HashMap[ThreadIdAndParentMonitors, DeadlockLocation]) {
    val threadId = potentialDeadlock.potentialDeadlockAsKey.threadId;
    val set = potentialDeadlock.potentialDeadlockAsKey.parentMonitorIds.toSet;

    map.put(new ThreadIdAndParentMonitors(threadId, set), potentialDeadlock.stackTraceOrdinalTuple);
  }

  def processPotentailDeadlock(potentialDeadlock: PotentialDeadlock) {
    if (potentialDeadlock.potentialDeadlockAsKey.higherMonitorIsChild) {
      add2Map(potentialDeadlock, childMap);

    } else {
      add2Map(potentialDeadlock, parentMap);
    }
  }

  def createDeadlockPart(parent: StackTraceOrdinalAndMonitor, child: StackTraceOrdinalAndMonitor, threadId: Long) =
    {
      new DeadlockPart(parent, child, threadId)
    }

  def addDeadlocks2Set(deadlocks: HashSet[Deadlock], deadlockFilter: HashSet[StackTraceOrdinalAndMonitor]) {

    var alreadyAdded = false;

    if (parentMap.size > 0 && childMap.size > 0) {

      for (parent <- parentMap) {
        for (child <- childMap) {

          if (parent._1.threadId != child._1.threadId) {

            var notSynchronized = true;

            for (parentMonitor <- parent._1.parentList) {
              if (child._1.parentList.contains(parentMonitor)) {
                notSynchronized = false;
              }
            }

            if (notSynchronized && !alreadyAdded) {
              // DeadlockPart( val parent : MonitorAccess , val child : MonitorAccess,val threadId : Long, isParentChild : Boolean )
              // ( val position : Int, val monitorAggregateOrdinal : Int , val stackTraceOrdinal : Int)
              val first = createDeadlockPart(parent._2.parent, parent._2.child, parent._1.threadId);
              val second = createDeadlockPart(child._2.parent, child._2.child, child._1.threadId);

              deadlockFilter.add(parent._2.parent);
              deadlockFilter.add(parent._2.child);
              deadlockFilter.add(child._2.parent);
              deadlockFilter.add(child._2.child);
              deadlocks.add(new Deadlock(first, second));

              alreadyAdded = true;

            }

          }

        }

      }

    }
  }

}