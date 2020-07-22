package com.anarsoft.race.detection.process.interleave

trait RunEndEvent extends LoopOrRunEvent with LoopOrRunEventVisitor[Int] {

  def runId: Int;

  def accept[RESULT](visitor: LoopOrRunEventVisitor[RESULT]) =
    {
      visitor.visit(this);
    }

  def compare(other: LoopOrRunEvent) =
    {
      other.accept(this);

    }

  /*
   * public static int compare(int x, int y) {
        return (x < y) ? -1 : ((x == y) ? 0 : 1);
    }

    x ist dieses event
    LoopStartEvent

    RunEndEvent

   */

  def visit(event: RunEndEvent) =
    {
      if (event.loopId != loopId) {
        Integer.compare(loopId, event.loopId);
      } else {
        Integer.compare(runId, event.runId);
      }
    
    
      Integer.compare(loopId, event.loopId);
    }

  def visit(event: RunStartEvent) =
    {
       if (event.loopId != loopId) {
        Integer.compare(loopId, event.loopId);
      } else  if (event.runId != runId)  {
        Integer.compare(runId, event.runId);
      } 
       else {
        1;
      }
    }

  def visit(event: LoopStartEvent) =
    {
      if (event.loopId != loopId) {
        Integer.compare(loopId, event.loopId);
      } else {
        1;
      }
    }

  def visit(event: LoopEndEvent) =
    {
      if (event.loopId != loopId) {
        Integer.compare(loopId, event.loopId);
      } else {
        -1;
      }

    }

}