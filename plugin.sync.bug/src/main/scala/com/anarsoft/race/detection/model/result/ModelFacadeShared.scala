package com.anarsoft.race.detection.model.result

import scala.collection.mutable.HashMap

trait ModelFacadeShared extends ModelFacade {
   def  ownerId2Name : HashMap[Int,String];
}