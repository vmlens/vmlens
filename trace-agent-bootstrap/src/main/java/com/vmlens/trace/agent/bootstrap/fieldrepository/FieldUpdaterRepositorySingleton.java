package com.vmlens.trace.agent.bootstrap.fieldrepository;

public class FieldUpdaterRepositorySingleton {

    public static final FieldUpdaterRepository INSTANCE = new FieldUpdaterRepository(FieldRepositorySingleton.INSTANCE);

}
