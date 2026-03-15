# Problem

new start try
existing byte code
new end try


# Solution

the new end try is only reached when an exception leaves the method block
so do not trace throw statements in the existing byte code

We can not use asm computeFrames see https://stackoverflow.com/questions/35692336/how-to-override-class-files-asm-classwriter-getcommonsuperclass

Type org/apache/maven/surefire/api/booter/MasterProcessChannelDecoder not present
org.objectweb.asm.ClassWriter.getCommonSuperClass(ClassWriter.java:1068)
org.objectweb.asm.SymbolTable.addMergedType(SymbolTable.java:1286)
org.objectweb.asm.Frame.merge(Frame.java:1311)
org.objectweb.asm.Frame.merge(Frame.java:1208)
org.objectweb.asm.MethodWriter.computeAllFrames(MethodWriter.java:1612)
org.objectweb.asm.MethodWriter.visitMaxs(MethodWriter.java:1548)
org.objectweb.asm.MethodVisitor.visitMaxs(MethodVisitor.java:787)
org.objectweb.asm.MethodVisitor.visitMaxs(MethodVisitor.java:787)
org.objectweb.asm.MethodVisitor.visitMaxs(MethodVisitor.java:787)
org.objectweb.asm.MethodVisitor.visitMaxs(MethodVisitor.java:787)
org.objectweb.asm.MethodVisitor.visitMaxs(MethodVisitor.java:787)
org.objectweb.asm.ClassReader.readCode(ClassReader.java:2664)
org.objectweb.asm.ClassReader.readMethod(ClassReader.java:1512)
org.objectweb.asm.ClassReader.accept(ClassReader.java:745)
org.objectweb.asm.ClassReader.accept(ClassReader.java:425)
com.vmlens.nottraced.agent.classtransformer.ClassTransformer.transform(ClassTransformer.java:44)
com.vmlens.nottraced.agent.classtransformer.TransformerStrategyForClassTransformer.transform(TransformerStrategyForClassTransformer.java:35)
com.vmlens.nottraced.agent.AgentClassFileTransformer.transform(AgentClassFileTransformer.java:76)
java.instrument/java.lang.instrument.ClassFileTransformer.transform(ClassFileTransformer.java:257)
java.instrument/sun.instrument.TransformerManager.transform(TransformerManager.java:188)
java.instrument/sun.instrument.InstrumentationImpl.transform(InstrumentationImpl.java:594)
java.base/java.lang.ClassLoader.defineClass1(Native Method)