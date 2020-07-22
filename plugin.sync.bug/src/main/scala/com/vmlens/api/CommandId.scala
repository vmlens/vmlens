package com.vmlens.api

abstract sealed class CommandId {
  
}

case class TOGGLE_WAITPOINT() extends CommandId;
case class OPEN_DECLARATION() extends CommandId;
case class DELETE_WAITPOINT() extends CommandId;
case class DELETE_RACE_CONDITION() extends CommandId;