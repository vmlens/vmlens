package com.anarsoft.race.detection.model


import com.anarsoft.race.detection.model.description._;

trait WithMethodId {
  
 // def getMethodName(descriptionRepository : DescriptionRepository) = descriptionRepository.getMethodName(methodId);
  def methodId : Int;

}