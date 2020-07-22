package com.anarsoft.race.detection.process.result

import java.io._;
import com.anarsoft.race.detection.process.workflow.SingleStep
import scala.collection.mutable.ArrayBuffer
import com.anarsoft.race.detection.process.ProzessConfig


class StepReadAgentLog(val eventDir : String,val prozessConfig : ProzessConfig)  extends SingleStep[ContextReadAgentLog] {
  
  
  def execute(context : ContextReadAgentLog)
  {
      //context.threadNames = new ThreadNames();
    
       val agentLog = new ArrayBuffer[String]();
  
   
    
      
      val dir = new File(eventDir);
      
      
      for( file <- dir.listFiles() )
      {
        if(  file.getName().contains("agentLog") )
        {
           val in = new DataInputStream(new FileInputStream (file));
      
      
        /*
         stream.writeBoolean( hasMappedTreadId );
		stream.writeByte( mappedThreadId);
         */
           
       try{
      while( true)
      {
       
 
        val message = in.readUTF();
        
        
        if(message.equals("throw exception"))
        {
          throw new Exception("test message");
        }
        
        //  
        
//        if( message.startsWith("EXCEPTION:") && prozessConfig.throwExceptionFromAgent())
//        {
//          throw new RuntimeException("Exception in agent " + message);
//        }
      
        
       agentLog.append(message)
        
        
        
        
        
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
      
    
      
      
      
      context.agentLog = agentLog;
      
      
      
      
     
    
  }
  
  
  def desc = "";
  
}