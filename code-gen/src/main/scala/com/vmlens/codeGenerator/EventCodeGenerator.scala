package com.vmlens.codeGenerator

import com.samskivert.mustache.*
import com.vmlens.codeGenerator.domain.*

import java.io.*
import java.util
import scala.collection.JavaConverters.*
import scala.collection.mutable.{ArrayBuffer, HashMap}

class EventCodeGenerator(val targetDir: String, val template: String, val fileNamePostfix: String, isScala: Boolean) extends CodeGenerator {


  def generate(eventDescList: ArrayBuffer[EventDesc], eventTypList: ArrayBuffer[EventTyp]): Unit = {
    for (eventDesc <- eventDescList) {
      val prozess = (isScala && eventDesc.typ.doProzessInScala) || !isScala;
      if (prozess) {
        val fileName = targetDir + "/" + eventDesc.name + fileNamePostfix;
        println("mustache " + fileName);
        val stream = new PrintWriter(new PrintStream(fileName));
        val mustache = Mustache.compiler().compile(asFileReader(template));
        mustache.execute(EventCodeGenerator.generateBasic(eventDesc), stream);
        stream.close()

      }
      else {
        val fileName = targetDir + "/" + eventDesc.name + fileNamePostfix;
        println("not processed " + fileName);
      }

    }
  }

}

object EventCodeGenerator {


  val EVENT_COUNT = 10000;


  def generateBasic(eventDesc: EventDesc) = {
    val map = new HashMap[String, AnyRef]();
    map.put("className", eventDesc.name)
    map.put("visitorName", eventDesc.typ.name + "Visitor")
    map.put("scalaExtends", eventDesc.scalaExtends)
    map.put("eventId", "" + eventDesc.id)
    map.put("eventTypeInvertCamelCase", Util.getInvertCamelCase(eventDesc.typ.name))
    

    map.put("typName", eventDesc.typName())

    val list = new util.LinkedList[java.util.HashMap[String, String]]();
    val fields = new java.util.HashMap[String, String]();
    fields.put("name", "test");
    fields.put("javaType", "testjavaType");
    list.add(fields) 
    map.put("javaFields", createFieldList(eventDesc.javaFields()))
    map.put("scalaFields", createFieldList(eventDesc.scalaFields()))
    map.put("javaFieldsInSend", createFieldList(eventDesc.javaFieldsInSend()))
    
    map.put("eventCount", "" + EVENT_COUNT)
    map.put("arraySize", "" + eventDesc.typ.getDataArraySize)
    map.put("writtenLength", "" + eventDesc.getByteArraySize())

    map.asJava;

  }


  private def createFieldList(origList: ArrayBuffer[FieldDesc]) = {
    val fieldList = new ArrayBuffer[java.util.Map[String, Any]]();

    var isFirst = true;
    var position = 1;

    for (field <- origList) {
      val fieldMap = new HashMap[String, Any]();
      if (isFirst) {
        fieldMap.put("comma", "");
        isFirst = false;
      }
      else {
        fieldMap.put("comma", ",");
      }


      fieldMap.put("name", field.name);
      fieldMap.put("nameCamelCase", Util.getCamelCase(field.name));
      fieldMap.put("javaType", field.typ.name);
      fieldMap.put("scalaType", Util.getCamelCase(field.typ.name));
      fieldMap.put("nameForSend", field.name);
      
      fieldMap.put("setJavaElement", "public void set" + Util.getCamelCase(field.name) + "(" + 
        field.typ.name + " " + field.name + ") { this." + field.name + " = " + field.name + "; }");
      fieldMap.put("writeJavaElement", String.format(field.typ.writeJavaElement, field.name));
      fieldMap.put("scalaFromByteBuffer", field.typ.scalaFromByteBuffer);

      fieldList.append(fieldMap.asJava);

      position = position + field.typ.size;

    }

    fieldList.asJava;
  }
  
}


