package com.anarsoft.race.detection.process.gen;


trait MethodVisitor
{  
       
def visit( in :  MethodEnterEventGen);
       
def visit( in :  MethodExitEventGen);
       
def visit( in :  ParallizedMethodEnterEventGen);
       
def visit( in :  ParallizedMethodExitEventGen);
       
def visit( in :  MethodEnterSmallThreadIdEventGen);
       
def visit( in :  MethodExitSmallThreadIdEventGen);
       
def visit( in :  MethodEnterShortThreadIdEventGen);
       
def visit( in :  MethodExitShortThreadIdEventGen);
       
}