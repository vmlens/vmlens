package com.anarsoft.race.detection.process.read




trait StreamAndStreamStatistic[EVENT] 
{
  
  def execute( readCallback : ReadCallback[EVENT] );
  
}