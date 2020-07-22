package com.anarsoft.race.detection.process.interleave



trait LoopStartEvent extends LoopOrRunEvent with LoopOrRunEventVisitor[Int] {
  
   def compare( other :  LoopOrRunEvent) =
   {
       other.accept(this);
     
   }
   
   
  def accept[RESULT](  visitor : LoopOrRunEventVisitor[RESULT] ) =
  {
    visitor.visit(this);
  }
  
  /*
   * public static int compare(int x, int y) {
        return (x < y) ? -1 : ((x == y) ? 0 : 1);
    }
    
    x ist dieses event
    
   */
  
   def visit(event : LoopStartEvent) =
   {
     Integer.compare( loopId , event.loopId );
   }
   
  def visit(event : LoopEndEvent) =
  {
    if( event.loopId != loopId )
    {
       Integer.compare( loopId , event.loopId );
    }
    else
    {
      -1;
    }
  }
  
  def visit(event : RunEndEvent) =
  {
     if( event.loopId != loopId )
    {
       Integer.compare( loopId , event.loopId );
    }
    else
    {
      -1;
    }
    
  }
  
  def visit(event : RunStartEvent) =
  {
     if( event.loopId != loopId )
    {
       Integer.compare( loopId , event.loopId );
    }
    else
    {
      -1;
    }
  }
  
  
  
  
  
  
  
  
  
  
  
  
}