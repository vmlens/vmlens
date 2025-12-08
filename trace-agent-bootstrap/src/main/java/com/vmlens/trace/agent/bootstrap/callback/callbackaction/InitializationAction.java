package com.vmlens.trace.agent.bootstrap.callback.callbackaction;

import com.vmlens.trace.agent.bootstrap.fieldrepository.FieldOwnerAndName;
import com.vmlens.trace.agent.bootstrap.fieldrepository.FieldUpdaterRepository;

public class InitializationAction   {

    private final Object returnValue;
    private final Object objectParam;
    private final String stringParam;
    private final FieldUpdaterRepository fieldUpdaterRepository;

    public InitializationAction(Object returnValue,
                                Object objectParam,
                                String stringParam,
                                FieldUpdaterRepository fieldUpdaterRepository) {
        this.returnValue = returnValue;
        this.objectParam = objectParam;
        this.stringParam = stringParam;
        this.fieldUpdaterRepository = fieldUpdaterRepository;
    }

    /*
     * we can be sure that we are in the correct method since all methods
     * using this callback function must create a new field updater
     */

   public void initialize() {
       String fieldName =stringParam;
       Class classOfUpdatedField = (Class) objectParam;
       Object theUpdater = returnValue;

       String className = classOfUpdatedField.getName().replace('.','/');
       FieldOwnerAndName fieldOwnerAndName = new FieldOwnerAndName(className,fieldName);
       fieldUpdaterRepository.set(theUpdater,fieldOwnerAndName);
   }
}
