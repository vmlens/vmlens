package com.anarsoft.race.detection.sortutil

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class EventWithReadWriteTest extends AnyFlatSpec with Matchers {

  " EventWithReadWrite" should "work for write" in {
    // Given
    val access = MemoryAccessEventGuineaPig.write();

    // Then
    access.isRead should be(false)
    access.isWrite should be(true)
  }

  " EventWithReadWrite" should "work for read" in {
    // Given
    val access = MemoryAccessEventGuineaPig.read();

    // Then
    access.isRead should be(true)
    access.isWrite should be(false)
  }

  " EventWithReadWrite" should "work for readWrite" in {
    // Given
    val access = MemoryAccessEventGuineaPig.readWrite();

    // Then
    access.isRead should be(true)
    access.isWrite should be(true)
  }

}
