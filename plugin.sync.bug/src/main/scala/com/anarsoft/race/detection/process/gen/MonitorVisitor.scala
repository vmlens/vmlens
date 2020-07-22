package com.anarsoft.race.detection.process.gen;


trait MonitorVisitor
{  
       
def visit( in :  MonitorEnterEventGen);
       
def visit( in :  MonitorExitEventGen);
       
def visit( in :  MonitorEnterEventGenInterleave);
       
def visit( in :  MonitorExitEventGenInterleave);
       
}