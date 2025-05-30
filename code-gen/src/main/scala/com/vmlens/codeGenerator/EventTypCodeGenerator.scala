package com.vmlens.codeGenerator

import com.samskivert.mustache.Mustache
import com.vmlens.codeGenerator.domain.*

import java.io.*
import scala.collection.JavaConverters.*
import scala.collection.mutable.{ArrayBuffer, HashMap}


class EventTypCodeGenerator(val targetDir: String, val template: String, val classPostFix: String) extends CodeGenerator {


  def generate(eventDescList: ArrayBuffer[EventDesc], eventTypList: ArrayBuffer[EventTyp]) : Unit = {
    for (eventTyp <- eventTypList) {
      if (eventTyp.doProzessInScala) {
        val stream = new PrintWriter(new PrintStream(targetDir + "/" + eventTyp.name + classPostFix + ".scala"));
        val mustache  =  Mustache.compiler().compile(asFileReader(template));
        mustache.execute( generateMap(eventTyp) , stream);
        stream.close()
      }
    }
  }


  def generateMap(eventTyp: EventTyp) = {
    val map = new HashMap[String, AnyRef]();
    map.put("className", eventTyp.name + classPostFix)
    map.put("arraySize", "" + eventTyp.getDataArraySize)
    map.put("eventCount", "" + EventCodeGenerator.EVENT_COUNT)
    map.put("eventList", createEventlist(eventTyp.eventList))
    map.put("deserializerExtends", eventTyp.deserializerExtends)
    map.asJava;
  }


  def createEventlist(eventDesc: ArrayBuffer[EventDesc]) = {
    val result = new ArrayBuffer[java.util.Map[String, AnyRef]]

    for (event <- eventDesc) {
      val map = new HashMap[String, AnyRef]();
      map.put("eventId", "" + event.id);
      map.put("eventClass", event.name)
      result.append(map.asJava);
    }
    result.asJava;
  }
}