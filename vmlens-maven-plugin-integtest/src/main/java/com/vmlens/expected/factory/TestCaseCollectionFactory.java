package com.vmlens.expected.factory;

import com.vmlens.expected.domain.TestCase;
import com.vmlens.expected.domain.TestCaseCollection;

import java.util.HashMap;
import java.util.Map;

public class TestCaseCollectionFactory {

    public TestCaseCollection create() {
        Map<String, TestCase> nameToTestCase = new HashMap<>();

        // volatile fields
        nameToTestCase.put("volatileFieldIntTest",new VolatileFieldIntTestFactory().create());
        nameToTestCase.put("staticVolatileFieldIntTest",new VolatileFieldIntTestFactory().create());


        // monitor
        nameToTestCase.put("monitorIntTest",new MonitorIntTestFactory().create());
        nameToTestCase.put("synchronizedMethodIntTest",new MonitorIntTestFactory().create());

        // locks
        nameToTestCase.put("readWriteLockTest",new MonitorIntTestFactory().create());

        // non volatile
        nameToTestCase.put("staticField",new EmptyIntTestFactory().create());
        nameToTestCase.put("arrayTest",new EmptyIntTestFactory().create());
        nameToTestCase.put("hashSetTest",new EmptyIntTestFactory().create());
        nameToTestCase.put("hashMapTest",new EmptyIntTestFactory().create());

        // Not Checked
        nameToTestCase.put("threadPoolInsideTest",new EmptyIntTestFactory().create());
        nameToTestCase.put("allFieldsTracedIntTest",new EmptyIntTestFactory().create());
        nameToTestCase.put("atomicIntegerTest",new EmptyIntTestFactory().create());
        nameToTestCase.put("concurrentLinkedDeque",new EmptyIntTestFactory().create());
        nameToTestCase.put("exceptionTest",new EmptyIntTestFactory().create());
        nameToTestCase.put("reentrantTryLockWithTimeout",new EmptyIntTestFactory().create());
        nameToTestCase.put("writeWriteDataRace",new EmptyIntTestFactory().create());
        nameToTestCase.put("putIfAbsentAndGetKeysWithSequenceNumber",new EmptyIntTestFactory().create());

        return new TestCaseCollection(nameToTestCase);
    }
}
