package com.anarsoft.integration


import com.anarsoft.race.detection.model.description._;
import com.anarsoft.race.detection.model.description.StackTraceElementModel


trait MethodModelVisitor[T] {

   def visit(element: MethodModelFromTrace): T;
   def visit(element: StackTraceElementModel): T;
   def visit(element: MissingLink): T;
}