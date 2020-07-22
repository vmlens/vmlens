package com.anarsoft.race.detection.process.gen;


trait StateVisitor
{  
       
def visit( in :  StateEventFieldGen);
       
def visit( in :  StateEventStaticFieldGen);
       
def visit( in :  StateEventArrayGen);
       
}