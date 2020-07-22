package com.anarsoft.race.detection.process.state

import com.anarsoft.race.detection.process.workflow.SingleStep
import java.io._;



class StepReadClassName(val eventDir : String)  extends SingleStep[ContextClassName] {
  
  
  def execute(context : ContextClassName)
  {
    val classNameArray =  Array.ofDim[String]( context.classIdMap.currentFieldOrdinal)    
    
       val dir = new File(eventDir);
       for( file <- dir.listFiles() )
      {
        if(  file.getName().contains("className") )
        {
           val in = new DataInputStream(new FileInputStream (file));
      
      
        /*
         stream.writeBoolean( hasMappedTreadId );
		stream.writeByte( mappedThreadId);
         */
           
       try{
      while( true)
      {
          val id =  in.readInt()
          val name = in.readUTF();
        
           context.classIdMap.fieldId2Ordinal.get(id) match
           {
             case None =>
               {
                 // nothing to do not access by multiple threads
               }
             
             case Some(x) =>
               {
                 classNameArray(x) = name;
               }
             
             
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
        
        context.classId2Name = classNameArray;
        
      }
    
    
    
    
    
    
    
    
  }
}