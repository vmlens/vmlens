package com.anarsoft.race.detection.process.mode.state

import com.anarsoft.race.detection.model.result._
import java.util.StringTokenizer


class NameAndState(val sharedState : SharedState , val tokenizer: StringTokenizer) {
  
  
  def hasNext() = tokenizer.hasMoreTokens();
  def next() = tokenizer.nextToken();
  
  
 
}


object NameAndState {
  
  def apply(sharedState : SharedState) =
  {
    new NameAndState(  sharedState, new  StringTokenizer(sharedState.classOrPackageName , ".") );
  }
  
  
}