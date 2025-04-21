package com.vmlens.test.maven.plugin;

import static com.vmlens.test.maven.plugin.RunnableCT.STATIC_FIELD;

public class UpdateVolatileField implements Runnable  {
    @Override
    public void run() {
        STATIC_FIELD++;
    }
}
