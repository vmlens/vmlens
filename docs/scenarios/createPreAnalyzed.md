# Problem

How to create a configuration for the PreAnalyzedClasses


# Solution (New Structure)

ClassWithMethodToMethodType -> Name of the class , List[MethodToMethodType]

MethodToMethodType -> name : String, val desc : String, val abstractMethodType : AbstractMethodType

MethodToStrategy -> AbstractMethodType

