package com.anarsoft.race.detection.sortMemoryAccess

abstract sealed class AddResult {

}

case class AddBefore() extends AddResult;

case class AddAfter() extends AddResult;

case class ElementModified() extends AddResult;

case class AddLeadsToDataRace() extends AddResult;

