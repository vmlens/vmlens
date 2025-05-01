package com.vmlens.test.maven.plugin;

import static com.vmlens.test.maven.plugin.TestRunnable.STATIC_FIELD;

public class UpdateVolatileField implements Runnable  {
    @Override
    public void run() {
        STATIC_FIELD++;
    }
}
