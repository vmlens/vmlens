package com.anarsoft.race.detection.process.gen;

import com.anarsoft.race.detection.process.interleave._

import java.nio.ByteBuffer
import java.util.Comparator;


class LoopEndEventGen(
                       val loopId: Int


                       , val status: Int


                     ) extends LoopEndEvent {
  override def toString() = {
    var text = "LoopEndEventGen"
    text = text + ", loopId:" + loopId
    text = text + ", status:" + status

    text;

  }



   
    
   



def accept(visitor : SchedulerVisitor)
{
visitor.visit(this);
}


 override def equals(other: Any) = {
    other match {
      case that: LoopEndEventGen => 
        {
            
             if( loopId != that.loopId )
             {
               false;
             }
             else
            
             if( status != that.status )
             {
               false;
             }
             else
             true;
        }


      case _ => false
    }
  }



}


object  LoopEndEventGen 
{

   def applyFromJavaEvent(data : ByteBuffer) =
   {
     val result = new LoopEndEventGen (
     
           
            
                data.getInt()
           
          , 
            
                data.getInt()
           
     
     
     
     
     );
     
     
     
     result;
   
   }
   
   
     def applyFromScalaEvent(data : ByteBuffer) =
   {
     val result = new LoopEndEventGen (
     
            data.getInt()
          ,  data.getInt()
     
     
     
     
     );
     
     
     
     
     result;
   
   }
   


}










class SortOrigLoopEndEventGen extends Comparator[LoopEndEventGen]
{
    def	compare(o1 :  LoopEndEventGen,  o2 : LoopEndEventGen ) =
    {
          {
            0;
          }
    
    
    }
    

}


class SortLoopEndEventGen extends Comparator[LoopEndEventGen]
{
    def	compare(o1 :  LoopEndEventGen,  o2 : LoopEndEventGen ) =
    {
          {
            0;
          }
    
    
    }
    

}




