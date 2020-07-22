package com.anarsoft.race.detection.process.read.event

import scala.collection.mutable.HashMap
import net.liftweb.json._;
import org.apache.commons.io._;
import net.liftweb.json.Extraction._
import java.io._;


object ReadExpectedEvents {
  
  
  implicit val formats = Serialization.formats(ShortTypeHints(List(classOf[ExpectMonitorEnter], classOf[ExpectVolatileAccessEvent] , classOf[ExpectNonVolatileFieldAccessEvent]  )))
  
  def read(fileName : String) =
  {
    val expected2Found = new HashMap[ExpectedEvent,MutableBoolean]
    
    
       val expectedRunCount = new File(fileName);
         
         
         
       
             val dataStream = new FileInputStream(expectedRunCount);
     val contents =    IOUtils.toString(dataStream);
    val json = parse( contents );
    
     val   set    = json.extract[Set[ExpectedEvent]]
    
    
     for( elem <- set )
     {
       expected2Found.put( elem , new MutableBoolean() );
     }
       
     
     
    
    expected2Found;
  }
  
  
  
  def main(args : Array[String])
  {
    
    val set = Set( ExpectMonitorEnter("test")  );
    
     println( pretty(render(decompose(set))));
    
    
    
  }
  
  
  
}