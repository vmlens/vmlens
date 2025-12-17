package com.anarsoft.race.detection.report.description.container

import com.vmlens.trace.agent.bootstrap.description.{ClassDescription, MethodDescription}

class ContainerForMethod {

  var description: Option[Tuple2[ClassDescription, MethodDescription]] = None
  
}
