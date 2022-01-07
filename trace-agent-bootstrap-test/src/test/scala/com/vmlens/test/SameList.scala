package com.vmlens.test

import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper
import gnu.trove.list.linked.TLinkedList
import org.hamcrest.{Description, TypeSafeMatcher}

class SameList[T](val expected: TLinkedList[TLinkableWrapper[T]]) extends TypeSafeMatcher[TLinkedList[TLinkableWrapper[T]]] {

  override def matchesSafely(actual: TLinkedList[TLinkableWrapper[T]]): Boolean = {
    if (expected.size() != actual.size()) {
      false
    }
    else {
      val expectedIter = expected.iterator();
      val actualIter = actual.iterator();
      var isSame = true;
      while (expectedIter.hasNext) {
        val a = actualIter.next();
        val e = expectedIter.next();

        if (a.element != e.element) {
          isSame = false;
        }
      }
      isSame;
    }
  }

  override def describeTo(description: Description): Unit = {
    description.appendValue(expected);
  }
}
