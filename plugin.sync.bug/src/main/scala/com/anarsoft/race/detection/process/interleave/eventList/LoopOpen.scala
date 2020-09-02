package com.anarsoft.race.detection.process.interleave.eventList

import java.util.ArrayList
import com.anarsoft.race.detection.process.interleave._

import com.anarsoft.race.detection.process.SortArrayList
import com.anarsoft.race.detection.process.nonVolatileField.InterleaveEventNonVolatileAccess

class LoopOpen(val loopId: Int) extends LoopState {

  var listWithEvents: Option[FirstFilledStatementList] = None;

  var runWithRace: Option[Int] = None;
  var raceHasRead = false;

  var hasWarning = false;

  var statementList = new ArrayList[InterleaveEventStatement]
  var loopEventList = new ArrayList[LoopOrRunEvent]

  var prevoiusLastRunId = -1;

  def add(event: LoopWarningEvent) {
    hasWarning = true;
  }

  def add(event: LoopOrRunEvent) {

    loopEventList.add(event);
  }

  def add(event: InterleaveEventStatement) {
    statementList.add(event);
  }

  def filterStatementList() {
    SortArrayList.sort(statementList, new ComparatorInterleaveEvent());

    val result = new ArrayList[InterleaveEventStatement]

    val iter = statementList.iterator();
    while (iter.hasNext()) {
      val current = iter.next();

      listWithEvents match {
        case None =>
          {
            val first = new FirstFilledStatementList(current.runId, current.runPosition);
            first.statementList.add(current);
            listWithEvents = Some(first);
          }

        case Some(x) =>
          {
            if (x.runId == current.runId && x.runPosition < current.runPosition) {
              x.runPosition = current.runPosition;
              x.statementList.add(current);

            }
          }

      }

      if (current.runId >= prevoiusLastRunId) {
        result.add(current);
      }
    }

    statementList = result;
  }

  def filterStatementList4OneId() {
    SortArrayList.sort(statementList, new ComparatorInterleaveEvent());

    val result = new ArrayList[InterleaveEventStatement]

    val iter = statementList.iterator();
    while (iter.hasNext()) {
      val current = iter.next();

      listWithEvents match {
        case None =>
          {
            val first = new FirstFilledStatementList(current.runId, current.runPosition);
            first.statementList.add(current);
            listWithEvents = Some(first);
          }

        case Some(x) =>
          {
            if (x.runId == current.runId && x.runPosition < current.runPosition) {
              x.runPosition = current.runPosition;
              x.statementList.add(current);

            }
          }

      }

      if (current.runId == prevoiusLastRunId) {
        result.add(current);
      }
    }

    statementList = result;
  }

  def processAtSlingWingowIdEnd() =
    {
      /*
     *  sortieren von loopEventList
     *  	merken von letztes RunStartEvent
     *    sowie letztes event
     *
     *    wenn letztes event LoopEnd -> LoopEnded
     *    runWithRace auswertung
     *
     */

      SortArrayList.sort(loopEventList, new ComparatorLoopOrRunEvent());

      val calculateLastLoopEvent = new CalculateLastLoopEvent();
      val iter = loopEventList.iterator();

      while (iter.hasNext()) {
        val current = iter.next();
        current.accept(calculateLastLoopEvent)

      }

      runWithRace match {
        case None =>
          {

            if (calculateLastLoopEvent.lastStartedRun != -1 && calculateLastLoopEvent.lastStartedRun != prevoiusLastRunId) {
              prevoiusLastRunId = calculateLastLoopEvent.lastStartedRun;
              filterStatementList();
            }

            // LoopEnded( val statementList : ArrayList[InterleaveEventStatement],val  hasWarning : Boolean,val loopEndEvent : LoopEndEvent, val lastStartedRunId : Int)

            calculateLastLoopEvent.loopEndEvent match {

              case None =>
                {
                  loopEventList = new ArrayList[LoopOrRunEvent];
                  this;
                }

              case Some(x) =>
                {
                  filterStatementList4OneId();

                  if (statementList.isEmpty()) {
                    listWithEvents match {
                      case None =>
                        {
                          new LoopEnded(statementList, hasWarning, x, prevoiusLastRunId);
                        }
                      case Some(y) =>
                        {
                          new LoopEnded(y.statementList, hasWarning, x, prevoiusLastRunId);
                        }

                    }
                  } else {
                    new LoopEnded(statementList, hasWarning, x, prevoiusLastRunId);
                  }

                }

            }

          }

        case Some(id) =>
          {

            if (calculateLastLoopEvent.lastStartedRun != -1 && calculateLastLoopEvent.lastStartedRun != prevoiusLastRunId) {
              prevoiusLastRunId = calculateLastLoopEvent.lastStartedRun;

            }

            val maxCount = prevoiusLastRunId;

            prevoiusLastRunId = id;
            filterStatementList4OneId();

            new LoopRaceOpen(statementList, raceHasRead, maxCount, prevoiusLastRunId);
          }

      }

    }

  var processingStopped = false;

  def stopProcessing() =
    {
      if (!processingStopped) {
        processingStopped = true;
        val current = processAtSlingWingowIdEnd();
        current.stopProcessing();
      } else {
        LoopResultError(statementList, prevoiusLastRunId);
      }

    }

  def addRace(reading: InterleaveEventNonVolatileAccess, writing: InterleaveEventNonVolatileAccess, hasRead: Boolean) {
    runWithRace = Some(reading.runId);
    raceHasRead = hasRead;

    if (!reading.showSharedMemory) // um es nicht doppelt hinzuzufügen
    {
      statementList.add(reading);

      if (writing.loopId == loopId) {
        statementList.add(writing);
      }
    }

  }

}