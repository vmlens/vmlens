package com.vmlens.codeGenerator

import com.vmlens.codeGenerator.domain.*

import java.io.InputStreamReader
import scala.collection.mutable.ArrayBuffer;


trait CodeGenerator {
  
  def asFileReader(name : String): InputStreamReader ={
    new InputStreamReader( getClass.getResourceAsStream("/" + name))
  }
  
  def generate( eventDescList : ArrayBuffer[EventDesc] ,  eventTypList : ArrayBuffer[EventTyp] )  : Unit;
  
}