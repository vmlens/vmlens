package com.anarsoft.race.detection.setstacktrace

import com.anarsoft.race.detection.stacktrace.StacktraceNode
import com.anarsoft.race.detection.util.EventArray
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.collection.mutable.HashMap

class SetStacktraceNodeInEventTest extends AnyFlatSpec with Matchers {

  "SetStacktraceNodeInEvent" should "set the stacktrace nodes" in {
    // Given
    val firstEvent = new EventWithStacktraceNodeGuineaPig(1, 3);
    val secondEvent = new EventWithStacktraceNodeGuineaPig(10, 2);
    val thirdEvent = new EventWithStacktraceNodeGuineaPig(1, 1);

    val eventArray = new EventArray[EventWithStacktraceNodeGuineaPig](Array(firstEvent, secondEvent, thirdEvent));
    val threadIdToStacktraceNodeArray = new HashMap[Long, Array[StacktraceNode]]();
    threadIdToStacktraceNodeArray.put(1L, buildStacktraceNodeArray());
    threadIdToStacktraceNodeArray.put(10L, buildStacktraceNodeArray());

    val setStacktraceNodeInEvent = new SetStacktraceNodeInEvent();

    // When
    setStacktraceNodeInEvent.process(eventArray, threadIdToStacktraceNodeArray.toMap)

    // Then
    firstEvent.node should be(new StacktraceNodeGuineaPig(3));

  }

  def buildStacktraceNodeArray(): Array[StacktraceNode] = {
    val stacktraceNodeArray = Array.ofDim[StacktraceNode](5)
    for (i <- 0 until 5) {
      stacktraceNodeArray(i) = new StacktraceNodeGuineaPig(i);
    }
    stacktraceNodeArray;
  }
}
