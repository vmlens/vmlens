package com.anarsoft.race.detection.report.description

import com.anarsoft.race.detection.report.element.LoopRunAndThreadIndex

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
      .map( x => x._1.name() + "." + x._2.name() ) match {
      case None => {
        notFound(key);
      }
      case Some(x) => x
    }
  }

  override def fieldName(key: Integer): String = {
    containerMapCollection.fieldNames.get(key).flatMap(  x => x.description )
      .map( x => x._1.name() + "." + x._2.name() ) match {
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


  private def notFound(key: Any) = String.format("not found (%s)", key)
  
  
  
  
}
