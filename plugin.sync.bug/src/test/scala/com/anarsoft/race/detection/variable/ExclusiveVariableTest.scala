package com.anarsoft.race.detection.variable

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ExclusiveVariableTest extends AnyFlatSpec with Matchers {

  "An exclusive variable" should "only be available to one consumer at a time" in {
    // Constants
    val first = new VariableId()
    val second = new VariableId();

    // Given
    val builder = new ExclusiveVariableBuilder[String]();
    val firstExtractor = builder.createExtractor(first);
    val secondExtractor = builder.createExtractor(second);
    val firstPublisher = builder.createPublisher(first);
    val secondPublisher = builder.createPublisher(second);

    // When
    firstPublisher.publish("hallo");

    // Then
    firstExtractor.isAvailable() should be(true)
    secondExtractor.isAvailable() should be(false)
    firstExtractor.take() should be("hallo");

    // When
    secondPublisher.publish("hallo");

    // Then
    secondExtractor.isAvailable() should be(true)

    // When
    secondExtractor.take() should be("hallo");

    // Then
    secondExtractor.isAvailable() should be(false)
  }

}
