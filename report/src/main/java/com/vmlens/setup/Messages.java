package com.vmlens.setup;

import java.io.File;

public class Messages {

    public static String dataRaces(int count, File reportDirectory) {
        return String.format("There are %s data races, see %s for the report.",
                count,reportDirectory);
    }


}
