package com.anarsoft.race.detection.process.result

import com.anarsoft.config.ConfigValues
import com.anarsoft.race.detection.model.result._
import com.anarsoft.race.detection.process.detectRaceConditions.EventWrapperDetectRaceConditions
import com.anarsoft.race.detection.process.filter.FilterMap
import com.anarsoft.race.detection.process.partialOrder.RaceConditionFoundException
import com.anarsoft.race.detection.process.workflow.SingleStep
import com.anarsoft.trace.agent.runtime.util.AntPatternMatcher
import scala.collection.JavaConverters._
import scala.collection.mutable.{ArrayBuffer, HashMap, HashSet}

class StepFilterRaceConditions(val configValues: ConfigValues) extends SingleStep[ContextCreateModelFacade] {

  def getRaceFilterList() = {
    if (configValues.getSuppressIssues() == null) {
      Nil;
    } else {
      configValues.getSuppressIssues().asScala
    }
  }

  def take(name: String, firstMethodName: String, secondMethodName: String, filter: scala.collection.Seq[String]) = {

    val matcher = new AntPatternMatcher();
    var take = true;

    for (x <- filter) {

      if (x.contains('@')) {
        val filterArray = x.split("@");
        if (matcher.`match`(filterArray(0), name)) {
          if (matcher.`match`(filterArray(1), secondMethodName)) {
            take = false;
          }
          if (matcher.`match`(filterArray(1), firstMethodName)) {
            take = false;
          }

        }

      } else {
        if (matcher.`match`(x, name)) {
          take = false;
        }
      }

    }

    take
  }

  def execute(contextModelFacade: ContextCreateModelFacade) {

    val filter = FilterMap();
    val key2Race = new HashMap[String, ArrayBuffer[RaceConditionFoundException]]

    val filterList = getRaceFilterList();

    for (raceExp <- contextModelFacade.raceExceptionSet) {
      val name = raceExp.getName(contextModelFacade.fieldAndArrayFacade, contextModelFacade.stackTraceGraph);

      if (filter.take(name)) {
        val firstMethodName = raceExp.getFirstMethodName(contextModelFacade.stackTraceGraph);
        val secondMethodName = raceExp.getFirstMethodName(contextModelFacade.stackTraceGraph);

        if (take(name, firstMethodName, secondMethodName, filterList)) {
          val list = key2Race.getOrElseUpdate(name, new ArrayBuffer[RaceConditionFoundException]);
          list.append(raceExp)
          raceExp.take()

        }

      }
    }

    contextModelFacade.filteredRaces = new HashSet[RaceCondition]();

    for (elem <- key2Race) {
      val toBeAdded = new ArrayBuffer[RaceConditionFoundException]

      for (r <- elem._2) {
        var take = true;

        for (already <- toBeAdded) {
          if (already.read.stackTraceOrdinal == r.read.stackTraceOrdinal || already.write.stackTraceOrdinal == r.write.stackTraceOrdinal) {
            take = false;
          }

        }

        if (take) {
          toBeAdded.append(r);
        }

      }

      for (exp <- toBeAdded) {
        contextModelFacade.filteredRaces.add(createRace(exp, toBeAdded.size));
      }

    }


  }

  def createRaceConditionElement(event: EventWrapperDetectRaceConditions) = {
    new RaceConditionElement(event.getLocationInClass(), StackTraceOrdinal(event.stackTraceOrdinal), event.operation, event.threadId, event.stacktraceOrdinal2MonitorId);
  }

  def createRace(race: RaceConditionFoundException, countOfOther: Int) = {
    new RaceCondition(createRaceConditionElement(race.read), createRaceConditionElement(race.write), countOfOther)
  }


}