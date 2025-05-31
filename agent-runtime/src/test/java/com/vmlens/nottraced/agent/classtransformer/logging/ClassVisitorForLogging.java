package com.vmlens.nottraced.agent.classtransformer.logging;

import com.vmlens.nottraced.agent.classtransformer.ASMConstants;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import java.io.PrintWriter;

public class ClassVisitorForLogging extends ClassVisitor {

    private final PrintWriter printWriter;

    public ClassVisitorForLogging(PrintWriter printWriter) {
        super(ASMConstants.ASM_API_VERSION);
        this.printWriter = printWriter;
    }

    @Override
    public void visit(int version,
                      int access,
                      String name,
                      String signature,
                      String superName,
                      String[] interfaces) {
        printWriter.println("class:" + name);
    }

    @Override
    public MethodVisitor visitMethod(int access,
                                     String name,
                                     String descriptor,
                                     String signature,
                                     String[] exceptions) {
        printWriter.println("method:" + name + descriptor);
        return new MethodVisitorForLogging(printWriter);
    }
}
