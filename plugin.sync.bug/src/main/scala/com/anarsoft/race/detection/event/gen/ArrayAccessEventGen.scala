package com.anarsoft.race.detection.event.gen;

import com.anarsoft.race.detection.event.control.*
import com.anarsoft.race.detection.event.directmemory.*
import com.anarsoft.race.detection.event.interleave.*
import com.anarsoft.race.detection.event.method.*
import com.anarsoft.race.detection.event.nonvolatilefield.*

import java.nio.ByteBuffer;


class ArrayAccessEventGen(
                           val threadIndex: Int
                           , val fieldId: Int
                           , val methodCounter: Int
                           , val operation: Int
                           , val methodId: Int
                           , val objectHashCode: Long
                           , val bytecodePosition: Int
                           , val classId: Int
                           , val loopId: Int
                           , val runId: Int
                           , val runPosition: Int
                         ) extends ArrayAccessEvent {
  override def toString() = {
    var text = "ArrayAccessEventGen"
    text = text + ", threadIndex:" + threadIndex
    text = text + ", fieldId:" + fieldId
    text = text + ", methodCounter:" + methodCounter
    text = text + ", operation:" + operation
    text = text + ", methodId:" + methodId
    text = text + ", objectHashCode:" + objectHashCode
    text = text + ", bytecodePosition:" + bytecodePosition
    text = text + ", classId:" + classId
    text = text + ", loopId:" + loopId
    text = text + ", runId:" + runId
    text = text + ", runPosition:" + runPosition
    text;
  }

  override def equals(other: Any) = {
    other match {
      case that: ArrayAccessEventGen => {
        if (threadIndex != that.threadIndex) {
          false;
        }
        else if (fieldId != that.fieldId) {
          false;
        }
        else if (methodCounter != that.methodCounter) {
          false;
        }
        else if (operation != that.operation) {
          false;
        }
        else if (methodId != that.methodId) {
          false;
        }
        else if (objectHashCode != that.objectHashCode) {
          false;
        }
        else if (bytecodePosition != that.bytecodePosition) {
          false;
        }
        else if (classId != that.classId) {
          false;
        }
        else if (loopId != that.loopId) {
          false;
        }
        else if (runId != that.runId) {
          false;
        }
        else if (runPosition != that.runPosition) {
          false;
        }
        else
          true;
      }
      case _ => false
    }
  }
}


object ArrayAccessEventGen {
  def applyFromJavaEvent(data: ByteBuffer) = {
    val result = new ArrayAccessEventGen(

      data.getInt()
      ,
      data.getInt()
      ,
      data.getInt()
      ,
      data.getInt()
      ,
      data.getInt()
      ,
      data.getLong()
      ,
      data.getInt()
      ,
      data.getInt()
      ,
      data.getInt()
      ,
      data.getInt()
      ,
      data.getInt()
    );
    result;
  }

}