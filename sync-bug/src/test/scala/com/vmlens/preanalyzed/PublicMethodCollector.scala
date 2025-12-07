package com.vmlens.preanalyzed

import org.objectweb.asm._
import org.objectweb.asm.Opcodes._

import scala.collection.mutable

class PublicMethodCollector(notTraced : Set[String]) extends ClassVisitor(ASM9) {
  
  private val methods = mutable.HashSet.empty[NameAndDescription]

  def getMethods: Set[NameAndDescription] = methods.toSet

  override def visitMethod(
                            access: Int,
                            name: String,
                            descriptor: String,
                            signature: String,
                            exceptions: Array[String]
                          ): MethodVisitor = {
    val isPublic = (access & ACC_PUBLIC) != 0
    val isAbstract = (access & ACC_ABSTRACT) != 0
    
    if (isPublic && ! isAbstract) {
      val nameAndDescription = NameAndDescription(name, descriptor);
      if(! notTraced.contains(nameAndDescription.name)) {
        methods += nameAndDescription
      }
    
    }
    super.visitMethod(access, name, descriptor, signature, exceptions)
  }
}

