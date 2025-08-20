in ClassVisitorApplyMethodVisitor is currently a filter to filter out all constructors:

```Java
        if ("<init>".equals(name)) {
            return previous;
        }
```


This class uses FactoryCollectionAdapter to get the MethodVisitor for transformation
and analyzing:

In this FactoryCollectionAdapter add a method getForConstructor

the class FactoryCollectionAdapterForAnalyze should return an empty list,
e.g. no need to analyze when contructor

FactoryCollectionAdapterForTransform should call getTransformForConstructorAndSetStrategy
from FactoryCollection

FactoryCollection is implemented by 5 classes but the only classes need to transform the constructor is 
FactoryCollectionAll and perhaps FactoryCollectionDoNotTrace 


The method getTransformForConstructorAndSetStrategy should return a list of methodvisitors to 
transform the constructor.

There are four method visitors which apply to constructor:
MethodEnterExitTransform
AddMonitorCall
AddFieldAccessCall
AddArrayAccessAccessCall

The only class with needs to be changed is MethodEnterExitTransform
at the enter and exit we should not load the existing class:
```Java
 public void visitCode() {
    super.visitCode();
    if (isStatic) {
        super.visitLdcInsn(Type.getType("L" + className + ";"));
    } else {
        super.visitVarInsn(ALOAD, 0);
    }
    methodCallbackFactory.methodEnter(inMethodId);

}
```
insted call a static method with out any argument





