package com.vmlens.nottraced.agent.classanalyzer;

import com.vmlens.nottraced.agent.classtransformer.ASMConstants;
import com.vmlens.transformed.agent.bootstrap.description.MethodDescription;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

/**
 * to get the line number of a method
 */
public class MethodVisitorAnalyze extends MethodVisitor {

    private final MethodDescription methodDescription;
    private boolean lineNumberVisited = false;

    protected MethodVisitorAnalyze(MethodVisitor methodVisitor,
                                   MethodDescription methodDescription) {
        super(ASMConstants.ASM_API_VERSION, methodVisitor);
        this.methodDescription = methodDescription;
    }


    @Override
    public void visitLineNumber(int line, Label start) {
        if(!lineNumberVisited) {
            lineNumberVisited = true;
            methodDescription.setLineNumber(line);
        }

        super.visitLineNumber(line, start);
    }
}
