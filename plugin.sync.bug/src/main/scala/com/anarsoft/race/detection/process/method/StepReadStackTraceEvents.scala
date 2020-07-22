package com.anarsoft.race.detection.process.method


import java.io._
import scala.collection.mutable.ArrayBuffer;
import com.anarsoft.race.detection.model.description._;
import com.anarsoft.race.detection.process.workflow.SingleStep


class StepReadStackTraceEvents(val eventDir : String) extends SingleStep[ContextMethodData] {
  
  def execute(context : ContextMethodData)
  {
        val dir = new File(eventDir);
   
    
    for(  file <- dir.listFiles() )
    {
      if( file.getName.startsWith("stackTrace_") )
      {
        val stream = new DataInputStream(new FileInputStream(file));
        
        try{
          
          while(true)
          {
            val stackTrace = new ArrayBuffer[StackTraceElementModel]();
          
          val threadId = stream.readLong();
          val size = stream.readInt();
          
          var takeNow = false;
          
          
          for( i <- 0 until size )
          {
            val className = stream.readUTF();
            val methodName = stream.readUTF();
            val fileName = stream.readUTF();
            val lineNumber = stream.readInt();
            
            
            val name = className + "." + methodName;
            
//            if( context.filterList.onlyTraceIn.take(  name ) )
//            {
//              takeNow = true;
//            }
//            
            
            
 //           if(takeNow)
            {
  
              // wird aktuell nicht hinzugefügt
              //              stackTrace.append(  new StackTraceElementModel(className ,methodName , fileName , lineNumber  )  )
            }
            
            
          }
          
       val     currentBuilder = context.threadId2StackTraceForestPerThreadBuilder.get(threadId) match
      {
        case Some(x) => x;
        
        case None =>
          {
            val x = new StackTraceForestPerThreadBuilder( context.threadNames.getThreadOrdinal(threadId)  , context.threadOrdinalAndStackTraceSet );
             context.threadId2StackTraceForestPerThreadBuilder.put(threadId,x) 
            
            x;
          }
        
        
      }
       
       currentBuilder.setBeginningStackTrace(stackTrace, context.stackTraceElement2Id, context.stackTraceTree)
          
          } 
        }
        catch
        {
          
          case exp : EOFException =>
            {
              
            }
          
        }
        
       
        /*
         * stream.writeLong ( threadId );
		stream.writeInt( stackTraceElementArray.length   - MIN_LENGTH );
		
		
		for( int i = MIN_LENGTH ; i <  stackTraceElementArray.length  ; i++)
		{
			stream.writeUTF(convertNullToEmptyString (stackTraceElementArray[i].getClassName()));
			stream.writeUTF(convertNullToEmptyString(stackTraceElementArray[i].getMethodName()));
			stream.writeUTF(convertNullToEmptyString(stackTraceElementArray[i].getFileName()));
			stream.writeInt(stackTraceElementArray[i].getLineNumber()); //stackTraceElementArray[i].getLineNumber()
			
			
		}
         */
        
        
        
        
      }
      
    }
  }
  
  
  def desc = "";
  
  
}