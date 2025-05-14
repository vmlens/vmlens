package com.vmlens.setup;

import java.io.File;

public class EventDirectoryAndArgLine {

    private final File eventDirectory;
    private final String argLine;

    public EventDirectoryAndArgLine(File eventDirectory, String argLine) {
        this.eventDirectory = eventDirectory;
        this.argLine = argLine;
    }

    public File eventDirectory() {
        return eventDirectory;
    }

    public String argLine() {
        return argLine;
    }
}
