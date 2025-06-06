package com.vmlens.codeGenerator

import com.vmlens.codeGenerator.domain.*
import org.apache.commons.io.FileUtils

import java.io.*
import scala.collection.mutable.ArrayBuffer;

object Generate {

  private def generate() : Unit = {
    val generatorList = new ArrayBuffer[CodeGenerator]();

    FileUtils.cleanDirectory(new File("trace-agent-bootstrap/src/main/java/com/vmlens/trace/agent/bootstrap/event/gen"));
    FileUtils.cleanDirectory(new File("sync-bug/src/main/scala/com/anarsoft/race/detection/event/gen"));

    generatorList.append(new EventCodeGenerator("trace-agent-bootstrap/src/main/java/com/vmlens/trace/agent/bootstrap/event/gen",
      "templates/JavaEvent.mustache", ".java", false));
    generatorList.append(new ForAllEventsCodeGenerator("trace-agent-bootstrap/src/main/java/com/vmlens/trace/agent/bootstrap/event/gen",
      "templates/EventConstants.mustache", "EventConstants.java"));
    generatorList.append(new EventCodeGenerator("sync-bug/src/main/scala/com/anarsoft/race/detection/event/gen",
      "templates/ScalaEvent.mustache", ".scala", true));
    generatorList.append(new EventTypCodeGenerator("sync-bug/src/main/scala/com/anarsoft/race/detection/event/gen",
      "templates/ScalaEventDeSerializer.mustache", "DeSerializer"));

    val eventTypList = new ArrayBuffer[EventTyp]();
    val eventDescList = EventDesc.getEventList(eventTypList);

    for (generator <- generatorList) {
      generator.generate(eventDescList, eventTypList);
    }
  }


  def main(args: Array[String]) : Unit = {
    generate();
  }

}