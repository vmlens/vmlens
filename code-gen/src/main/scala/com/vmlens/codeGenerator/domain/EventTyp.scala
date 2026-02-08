package com.vmlens.codeGenerator.domain

import java.io.*
import scala.collection.mutable.ArrayBuffer;


class EventTyp(val name : String, eventTypList : ArrayBuffer[EventTyp], 
               val doProzessInScala : 
               Boolean, val deserializerExtends : String) {
  
  eventTypList.append(this);
  
  val eventList = new ArrayBuffer[EventDesc]();
  
  def getDataArraySize: Int = {
    var size = 0;
    for( e <- eventList ) {
      size = Math.max( size ,  e.getByteArraySize() );
    }
    size;
  }
}

