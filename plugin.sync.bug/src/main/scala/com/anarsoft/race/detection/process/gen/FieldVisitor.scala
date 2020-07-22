package com.anarsoft.race.detection.process.gen;


trait FieldVisitor
{  
       
def visit( in :  FieldAccessEventGen);
       
def visit( in :  FieldAccessEventGenInterleave);
       
def visit( in :  FieldAccessEventStaticGen);
       
def visit( in :  FieldAccessEventStaticGenInterleave);
       
def visit( in :  ArrayAccessEventGen);
       
def visit( in :  ArrayAccessEventGenInterleave);
       
}