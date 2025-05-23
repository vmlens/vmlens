package com.anarsoft.race.detection.createstacktrace

import com.anarsoft.race.detection.event.gen.{MethodEnterEventGen, MethodExitEventGen}
import com.anarsoft.race.detection.util.EventArray
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ServiceCalculateMethodCountToStacktraceNodeTest extends AnyFlatSpec with Matchers {


  "The service " should " calculate the stack trace nodes " in {
    // Expected
    val root = new StacktraceNodeRoot(1);
    val expected = Array(null, root, new StacktraceNodeIntermediate(5, root), root, null);

    // Given
    val methodEnterRoot = new MethodEnterEventGen(1, 1, 
      0, 0, 0);
    val methodEnterChild = new MethodEnterEventGen(1, 5, 1,
      0, 0);
    val methodExitChild = new MethodExitEventGen(1, 5, 2,
      0, 0);
    val methodExitRoot = new MethodExitEventGen(1, 1, 3,
      0, 0);

    // wrong order also works
    val array = Array(methodExitRoot, methodEnterRoot, methodEnterChild, methodExitChild);

    // When
    val eventArray = new EventArray(array);
    val result = new ServiceCalculateMethodCountToStacktraceNode().process(eventArray);

    // Then
    result.get(1).get should be(expected)
  }


}
