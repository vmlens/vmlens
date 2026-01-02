package com.anarsoft.race.detection.report.description.container

import com.vmlens.trace.agent.bootstrap.description.{ClassDescription, FieldInClassDescription}

class ContainerForField {

  var description: Option[Tuple2[ClassDescription,FieldInClassDescription]] = None
  
}
