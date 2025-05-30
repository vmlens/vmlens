package com.vmlens.codeGenerator

import com.samskivert.mustache.Mustache
import com.vmlens.codeGenerator.domain.*

import java.io.*
import java.util.HashMap
import scala.collection.JavaConverters.*
import scala.collection.mutable.ArrayBuffer;


class ForAllEventsCodeGenerator(val targetDir: String, val template: String, val fileName: String) extends CodeGenerator {
  
  def generate(eventDescList: ArrayBuffer[EventDesc], eventTypList: ArrayBuffer[EventTyp]) : Unit =  {
    val eventList = new ArrayBuffer[java.util.Map[String, AnyRef]]();
    var maxArraySize = 0;


    for (eventDesc <- eventDescList) {
      eventList.append(EventCodeGenerator.generateBasic(eventDesc));
      maxArraySize = Math.max(maxArraySize, eventDesc.getByteArraySize());
    }

    val data = new HashMap[String, AnyRef]();
    data.put("maxArraySize", maxArraySize.toString());
    data.put("eventList", eventList.asJava);
    val stream = new PrintWriter(new PrintStream(targetDir + "/" + fileName));

    val mustache  =  Mustache.compiler().compile(asFileReader(template));
    mustache.execute(data,stream);
    stream.close()
    
  }
  
}