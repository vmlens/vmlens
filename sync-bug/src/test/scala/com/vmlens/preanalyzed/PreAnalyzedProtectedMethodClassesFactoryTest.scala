package com.vmlens.preanalyzed

import com.vmlens.preanalyzed.PreAnalyzedPublicMethodClassesFactoryTest.checkClasses
import com.vmlens.preanalyzed.factory.protectedmethods.PreAnalyzedProtectedMethodClassesFactory
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.objectweb.asm.Opcodes.*

class PreAnalyzedProtectedMethodClassesFactoryTest extends AnyFlatSpec with Matchers {

  "The classes in PreAnalyzedProtectedMethodClassesFactory " should
    "exactly contain the public and protected methods from the classes in the jdk" in {
    if(Runtime.version.feature() >= 21 ) {
      checkClasses(PreAnalyzedProtectedMethodClassesFactory.classList(), take);
    }
  }

  def take(access: Int,name : String): Boolean = {
    val isPublic = (access & ACC_PUBLIC) != 0
    val isProtected = (access & ACC_PROTECTED) != 0
    val isAbstract = (access & ACC_ABSTRACT) != 0

    ( isPublic || isProtected ) && !isAbstract && ! name.startsWith("<")
  }


}
