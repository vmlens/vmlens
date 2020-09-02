package com.anarsoft.race.detection.process.result

import scala.collection.mutable.HashMap;
import com.anarsoft.race.detection.model.description.ThreadNames;
import java.io._;
import com.anarsoft.race.detection.process.workflow.SingleStep
import com.vmlens.trace.agent.bootstrap.util.Constants
import com.typesafe.scalalogging.Logger

class StepReadThreadNames(val eventDir : String)  extends SingleStep[ContextReadThreadNames] {
  
  
     val logger = Logger("com.vmlens.Performance")
  
  def execute(context : ContextReadThreadNames)
  {
      //context.threadNames = new ThreadNames();
    
       val id2ThreadName = new HashMap[Long,String]();
  
   val mappedThreadId2ThreadId = new HashMap[Byte,Long]
   val shortThreadId2ThreadId = new HashMap[Short,Long]
    
   val  loopId2Name = new HashMap[Int,String] 
   
      
      val dir = new File(eventDir);
      
      
      for( file <- dir.listFiles() )
      {
        if(  file.getName().contains("threadName") )
        {
           val in = new DataInputStream(new FileInputStream (file));
      
      
        /*
         stream.writeBoolean( hasMappedTreadId );
		stream.writeByte( mappedThreadId);
         */
           
       try{
      while( true)
      {
       val typ = in.readInt();
       
       if(typ == Constants.TYPE_THREAD_NAME_EVENT)
       {
                 val threadId = in.readLong();
        val threadName = in.readUTF();
        val hasMappedThreadId = in.readBoolean() 
        val mappedThreadId = in.readByte();
        val hasShortThreadId = in.readBoolean();
        val shortThreadId = in.readShort()
        
        
      //  println(threadId + " " + threadName);
        
        
     //   PluginLogger.traceThread( threadId ,  threadName); 
        
        
       id2ThreadName.put(threadId, threadName)
        
        if(hasMappedThreadId)
        {
          
        //  println(  mappedThreadId + " <-> " + threadId );
          
           mappedThreadId2ThreadId.put(mappedThreadId, threadId);
        }
        
        if(hasShortThreadId)
        {
         shortThreadId2ThreadId.put(shortThreadId,threadId);
        }
       }
       else
       {
         val loopId = in.readInt();
         val name = in.readUTF();
         loopId2Name.put( loopId , name );
         
       }
        
        
        

        
        
        
        
      }

    }
    catch
    {
      case eof : java.io.EOFException =>  { }
    }
    finally
    {
      in.close();
    }
    
    
        }
        
        
        
      }
      
      
      val threadId2ThreadOrdinal = new HashMap[Long,Int]
      var ordinal = 0;
      
      
      for(elem <- id2ThreadName)
      {
        
        threadId2ThreadOrdinal.put(elem._1 , ordinal );
        ordinal = ordinal + 1;
      }
      
      
      logger.trace("thread.count " + id2ThreadName.size);
           
      
      context.threadNames = new ThreadNames(id2ThreadName,mappedThreadId2ThreadId,shortThreadId2ThreadId,threadId2ThreadOrdinal, ordinal)
      context.loopId2Name = loopId2Name;
      
 
     
    
  }
  
  
  def desc = "";
  
}