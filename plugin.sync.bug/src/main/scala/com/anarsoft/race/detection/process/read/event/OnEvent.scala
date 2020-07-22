package com.anarsoft.race.detection.process.read.event

import scala.collection.mutable.HashMap
import java.io.PrintWriter

class OnEvent(val expected2Found: HashMap[ExpectedEvent, MutableBoolean], val stream: Option[PrintWriter]) {

  def onEvent(event: Object, context: ContextReadEvent) {
    for (elem <- expected2Found) {

      if (elem._1.isExpected(event, context)) {
        if (elem._2.isSet) {
          //  throw new RuntimeException("duplicate event " + event);
        }

        elem._2.isSet = true;
      }

    }

    stream match {
      case None =>
        {

        }

      case Some(x) =>
        {

          var methodName = ""
          var fieldName = "";
          
          
          try {
            val methodId = event.getClass().getMethod("methodId");
            
            val id = methodId.invoke( event ).asInstanceOf[Int]
            methodName = "  method:" + context.methodId2Name.get(id).get
            
            
          } catch {

            case exp: NoSuchMethodException =>
              {

              }

          }
          
          try {
            val fieldId = event.getClass().getMethod("fieldId");
            
            val id = fieldId.invoke( event ).asInstanceOf[Int]
            fieldName = "  field:" + context.fieldId2Name.get(id).get
            
            
          } catch {

            case exp: NoSuchMethodException =>
              {

              }

          }
          
          
          
          
          
          x.println(event + methodName + fieldName)
          
          
          

        }

    }

  }

}