package com.anarsoft.race.detection.util

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.collection.mutable.ArrayBuffer

class EventArrayTest extends AnyFlatSpec with Matchers {

  "EventArrayTest" should "be iterable through a for expression" in {
    // Given
    val stringArray = Array[String]("eins", "zwei");
    val eventArray = new EventArray[String](stringArray);

    val result = new ArrayBuffer[String]();

    // When
    for (elem <- eventArray) {
      result += elem;
    }

    // Then
    result should contain("eins")
    result should contain("zwei")

  }

}
