package com.anarsoft.race.detection.report.description

import com.anarsoft.race.detection.report.description.container.{ContainerForField, ContainerForMethod}
import com.anarsoft.race.detection.report.element.LoopRunAndThreadIndex
import com.vmlens.trace.agent.bootstrap.description.{AutomaticTestDescription, ClassDescription, TestLoopDescription, ThreadDescription, ThreadLoopOrAutomaticTestDescription, ThreadOrLoopDescriptionVisitor}

class DescriptionCallbackImpl extends DescriptionCallback with NeedsDescriptionCallback with ThreadOrLoopDescriptionVisitor  {
  
  val containerMapCollection = new ContainerMapCollection();


  override def addClassDescription(classDescription: ClassDescription): Unit = {
    for( field <- classDescription.serializedFieldDescriptionArray() ) {
      containerMapCollection.fieldNames.get(field.id()) match {
        case None => {
        }
        case Some(x) => {
          x.fieldDescription = Some(Tuple2(classDescription,field));
        }
      }
    }


    for( method <- classDescription.methodArray() ) {
      containerMapCollection.methodNames.get(method.id()) match {
        case None => {
        }
        case Some(x) => {
          x.classAndMethod = Some(Tuple2(classDescription, method));
        }
      }
    }
  }

  override def visit(threadDescription: ThreadDescription): Unit = {
    val loopRunAndThreadIndex = new LoopRunAndThreadIndex(threadDescription.loopId() ,threadDescription.runId() , threadDescription.threadIndex());
    containerMapCollection.threadNames.put(loopRunAndThreadIndex , threadDescription);
  }

  override def visit(whileLoopDescription: TestLoopDescription): Unit = {
    containerMapCollection.loopNames.put(whileLoopDescription.loopId(), whileLoopDescription)

  }

  override def visit(automaticTestDescription: AutomaticTestDescription): Unit = {
    containerMapCollection.idToAutomaticTestClassName.put(automaticTestDescription.id(),automaticTestDescription.className())
  }

  override def addThreadOrLoopDescription(threadOrLoopDescription: ThreadLoopOrAutomaticTestDescription): Unit = {
    threadOrLoopDescription.accept(this);
  }

  override def needsField(fieldId: Int): Unit = {
    containerMapCollection.fieldNames.put(fieldId, new ContainerForField());
  }

  override def needsMethod(methodId: Int): Unit = {
    containerMapCollection.methodNames.put(methodId, new ContainerForMethod());
  }

  def build() : DescriptionContext = {
    new DescriptionContextImpl(containerMapCollection);
  }



}
