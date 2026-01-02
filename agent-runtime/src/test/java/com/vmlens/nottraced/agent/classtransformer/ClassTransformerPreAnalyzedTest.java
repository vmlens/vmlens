package com.vmlens.nottraced.agent.classtransformer;

import com.vmlens.transformed.agent.bootstrap.preanalyzed.model.PackageOrClass;
import com.vmlens.transformed.agent.bootstrap.preanalyzed.model.PreAnalyzedMethod;
import com.vmlens.transformed.agent.bootstrap.preanalyzed.model.classtypeimpl.PreAnalyzedAllMethods;
import com.vmlens.transformed.agent.bootstrap.util.TLinkableWrapper;
import org.junit.Test;

import java.io.IOException;

import static com.vmlens.transformed.agent.bootstrap.preanalyzed.model.methodtypeimpl.MethodToStrategy.NEW_UPDATER;
import static com.vmlens.transformed.agent.bootstrap.preanalyzed.model.methodtypeimpl.MethodToStrategy.REFLECT_FIELD_SET;


public class ClassTransformerPreAnalyzedTest {

   @Test
    public void reflectField() throws IOException {
       // Given
        PreAnalyzedMethod[] methods = new PreAnalyzedMethod[]{
                new PreAnalyzedMethod("set", "(Ljava/lang/Object;Ljava/lang/Object;)V", REFLECT_FIELD_SET)
        };
        PackageOrClass packageOrClass = new PackageOrClass("com/vmlens/test/guineapig/ReflectField", PreAnalyzedAllMethods.SINGLETON,
                methods
        );
       RunTestClassTransformer runTestClassTransformer =  RunTestClassTransformer.create(TLinkableWrapper.singleton(packageOrClass));

       // When and Then
       runTestClassTransformer.runTest("com.vmlens.test.guineapig.ReflectField", "/reflectField.txt");
   }

    @Test
    public void newUpdater() throws IOException {
        // Given
        PreAnalyzedMethod[] methods = new PreAnalyzedMethod[]{
                new PreAnalyzedMethod("newUpdater", "(Ljava/lang/Class;Ljava/lang/String;)Ljava/util/concurrent/atomic/AtomicIntegerFieldUpdater;", NEW_UPDATER)
        };
        PackageOrClass packageOrClass = new PackageOrClass("com/vmlens/test/guineapig/NewUpdater", PreAnalyzedAllMethods.SINGLETON,
                methods
        );
        RunTestClassTransformer runTestClassTransformer =  RunTestClassTransformer.create(TLinkableWrapper.singleton(packageOrClass));

        // When and Then
        runTestClassTransformer.runTest("com.vmlens.test.guineapig.NewUpdater", "/newUpdater.txt");
    }

    //@Test
    public void atomicArray() throws IOException {
        // Given
        RunTestClassTransformer runTestClassTransformer =  RunTestClassTransformer.createFromLoaded();

        // When and Then
        runTestClassTransformer.runTest("com.vmlens.test.guineapig.MethodWithIntParam", "/methodWithIntParam.txt");
    }

}
