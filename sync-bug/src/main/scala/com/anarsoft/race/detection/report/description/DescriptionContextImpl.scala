package com.anarsoft.race.detection.report.description

import com.anarsoft.race.detection.report.element.LoopRunAndThreadIndex
import com.vmlens.trace.agent.bootstrap.description.{ClassDescription, MethodDescription}

class DescriptionContextImpl(val containerMapCollection : ContainerMapCollection) extends  DescriptionContext  {

  override def threadName(key: LoopRunAndThreadIndex): String = {
    containerMapCollection.threadNames.get(key) match {
      case None => {
        notFound(key);
      }
      case Some(x) => x.threadName();
      
    }
  }

  override def methodName(key: Integer): String = {
    containerMapCollection.methodNames.get(key).flatMap(  x => x.description )
      .map( x => createMethodString(x)) match {
      case None => {
        notFound(key);
      }
      case Some(x) => x
    }
  }

  override def fieldName(key: Integer): String = {
    containerMapCollection.fieldNames.get(key).flatMap(  x => x.description )
      .map( x => x._1.name().replace('/','.') + "." + x._2.name() ) match {
      case None => {
        notFound(key);
      }
      case Some(x) => x
    }
  }

  override def loopName(key: Integer): String =  {
    containerMapCollection.loopNames.get(key) match {
      case None => {
        notFound(key);
      }
      case Some(x) => x.name();

    }
  }

  /*
   * see java.lang.StackTraceElement
   */
  private def createMethodString(tuple : Tuple2[ClassDescription, MethodDescription]): String = {
    tuple._1.name().replace('/', '.') + "." + tuple._2.name() + "("  + classSource(tuple._1.source())
       + lineNumber(tuple._2.lineNumber()) + ")"
  }

  private def classSource(source : String): String = {
    if(source.equals("")) {
      "Unknown Source"
    } else {
      source
    }
  }

  private def lineNumber(number : Int): String = {
    if( number < 0 ) {
      ""
    } else {
      ":" + number;
    }
  }

  private def notFound(key: Any) = String.format("not found (%s)", key)
  
  
  
  
}
