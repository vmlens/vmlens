package com.anarsoft.race.detection.process.read.event

import scala.collection.mutable.HashMap
import com.anarsoft.race.detection.process.workflow.SingleStep
import com.anarsoft.race.detection.model.description._
import java.io._
import com.anarsoft.race.detection.model.graph._
import scala.collection.mutable.HashMap
import com.anarsoft.race.detection.model._
import com.anarsoft.race.detection.model.field.ArrayAndFieldOrdinalMap;


class ReadDescriptionInMap(val eventDir : String) extends SingleStep[ContextReadEvent] {
  
  val methodId2Name = new HashMap[Int,String]();
  
  
  def execute(context : ContextReadEvent)
  {
      val in = new DataInputStream(new BufferedInputStream( new FileInputStream(new File( eventDir + "description_0.vmlens" ) )) );
    
      
  val methodId2Name = new HashMap[Int,String]();    
  val fieldId2Name = new HashMap[Int,String]();    
  val visitor = new    DescVisitorFillMap(methodId2Name,fieldId2Name);
      
      
    val serializeDescription = new DeSerializeDescription();
    try{
      while( true)
      {
        serializeDescription.deserialize(visitor , in);
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
    
    
    context.methodId2Name = methodId2Name;
    
    
    ArrayAndFieldOrdinalMap.setTemplateFields(  ( id , model ) => {   fieldId2Name.put( id , model.getDisplayName()  )     } );
    
    
    
    
    
    
    
    context.fieldId2Name = fieldId2Name;
  }
  
  
  
  def desc = "";
  
}