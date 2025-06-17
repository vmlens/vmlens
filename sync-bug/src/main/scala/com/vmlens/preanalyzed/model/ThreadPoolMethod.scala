package com.vmlens.preanalyzed.model

sealed trait ThreadPoolMethod {

}

case class ThreadStart(name: String, desc: String) extends ThreadPoolMethod;

case class JoinAll(name: String, desc: String) extends ThreadPoolMethod;

case class JoinTask(name: String, desc: String) extends ThreadPoolMethod;
