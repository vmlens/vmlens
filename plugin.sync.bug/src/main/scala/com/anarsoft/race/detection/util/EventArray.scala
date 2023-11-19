package com.anarsoft.race.detection.util

class EventArray[EVENT](private val array: Array[EVENT]) {

  def foreach(f: (EVENT) => Unit): Unit = {
    for (elem <- array) {
      f(elem);
    }
  }

  def sort(): Unit = {

  }

  def createWithSameSize[NEW_EVENT](): Unit = {

  }


}
