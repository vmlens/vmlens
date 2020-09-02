package com.anarsoft.race.detection.process.detectRaceConditions

import com.vmlens.api.MemoryAccessType
import com.anarsoft.race.detection.process.partialOrder.RaceConditionFoundException
import com.typesafe.scalalogging.Logger
import com.anarsoft.race.detection.process.gen.FieldAccessEventGenInterleave

class DetectRaceConditionsAlgo[ID_PER_OBJECT, EVENT <: EventDetectRaceConditions[ID_PER_OBJECT]](val context: ContextDetectRaceConditions, var tail: DoubleLinkedListElement[ID_PER_OBJECT, EVENT], var currentStart: DoubleLinkedListElement[ID_PER_OBJECT, EVENT]) {

  val logger = Logger(classOf[DetectRaceConditionsAlgo[_, _]])

  var hasRace = false;

  //
  //  def debug(event : EVENT, text : String)
  //  {
  //    if( event.isInstanceOf[FieldAccessEventGenInterleave] )
  //    {
  //      println(event +  " " +  text);
  //    }
  //
  //
  //  }
  //

  def add(event: EVENT) {

    if (currentStart.element.threadId != event.threadId || currentStart.element.operation != event.operation) {
      currentStart = tail;

    }

    var current: Option[DoubleLinkedListElement[ID_PER_OBJECT, EVENT]] = Some(currentStart);
    var stop = false;

    while (!current.isEmpty && !stop) {
      var ce = current.get;

      if (ce.element.threadId == event.threadId) {

        if (ce.element.programCounter == event.programCounter) {

          stop = true;
        } else if (event.programCounter < ce.element.programCounter) {

          insertAt(ce, event);
          stop = true;
        }

      } else {
      
        if (context.partialOrder.isLeftBeforeRight(event, ce.element)) {

          insertAt(ce, event);
          stop = true;
        } else {
          if (!context.partialOrder.isLeftBeforeRight(ce.element, event)) {

            if (context.partialOrder.isLeftBeforeRightTransetive(event, ce.element)) {

              insertAt(ce, event);
              stop = true;
            } else if (!context.partialOrder.isLeftBeforeRightTransetive(ce.element, event)) {

              isRace(ce, event);
              stop = true;
            }

          }

        }

      }

      current = ce.next;

      if (current.isEmpty) {

        if (MemoryAccessType.containsWrite(event.operation)) {
          val c = new DoubleLinkedListElement[ID_PER_OBJECT, EVENT](event);
          ce.next = new Some(c);
          c.previous = Some(ce);

        }

      }

    }

  }

  def isRace(position: DoubleLinkedListElement[ID_PER_OBJECT, EVENT], event: EVENT) {

    event.isRace = true;
    position.element.isRace = true;
    hasRace = true;

    val race = new RaceConditionFoundException(event, position.element);

    logger.trace("race for " + event + " " + position.element);

    context.interleaveEventList.addRace(race);
    context.raceExceptionSet.add(race)

    val count = context.raceLocation2Count.getOrElse(event.getLocationInClass(), 0);
    context.raceLocation2Count.put(event.getLocationInClass(), count + 1);
  }

  def insertAt(position: DoubleLinkedListElement[ID_PER_OBJECT, EVENT], event: EVENT) {
    position.previous match {

      case Some(x) =>
        {
          if (x.element.threadId == event.threadId) {

          } else {
            if (!context.partialOrder.isLeftBeforeRight(x.element, event) && !context.partialOrder.isLeftBeforeRightTransetive(x.element, event)) // offen ob das notwendig ist
            {
              isRace(position, event);
            } else {
              if (MemoryAccessType.containsWrite(event.operation)) {

                val n = new DoubleLinkedListElement[ID_PER_OBJECT, EVENT](event);

                x.next = Some(n);
                position.previous = x.next;

                n.next = Some(position);
                n.previous = Some(x)

              }
            }
          }

        }

      case None =>
        {
          if (MemoryAccessType.containsWrite(event.operation)) {
            val n = new DoubleLinkedListElement[ID_PER_OBJECT, EVENT](event);
            position.previous = Some(n);

            n.next = Option(position);

            tail = n;
            currentStart = n;
          }
        }

    }
  }

}

object DetectRaceConditionsAlgo {
  def apply[ID_PER_OBJECT, EVENT <: EventDetectRaceConditions[ID_PER_OBJECT]](context: ContextDetectRaceConditions, event: EVENT) =
    {
      val tail = new DoubleLinkedListElement[ID_PER_OBJECT, EVENT](event);

      new DetectRaceConditionsAlgo(context, tail, tail);
    }

}







