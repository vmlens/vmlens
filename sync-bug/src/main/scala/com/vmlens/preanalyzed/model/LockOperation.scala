package com.vmlens.preanalyzed.model

sealed trait LockOperation {

}

case class LockEnter() extends LockOperation;

case class LockExit() extends LockOperation;
