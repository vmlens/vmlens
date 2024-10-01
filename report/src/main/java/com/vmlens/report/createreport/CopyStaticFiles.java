package com.vmlens.report.createreport;

import org.apache.commons.io.IOUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

public class CopyStaticFiles {

    private final Path reportDir;

    public CopyStaticFiles(Path reportDir) {
        this.reportDir = reportDir;
    }

    public void copy() throws IOException {
        CopyDescription[] descriptions = new CopyDescriptionFromResource().load();

        for (CopyDescription description : descriptions) {
            copy(description);
        }
    }

    private void copy(CopyDescription description) throws IOException {
        Path dir = reportDir.resolve(description.dir());
        dir.toFile().mkdir();
        InputStream input = this.getClass().getResourceAsStream("/" + description.path());
        Path filePath = dir.resolve(description.name());
        FileOutputStream output = new FileOutputStream(filePath.toFile());
        IOUtils.copy(input, output);

        input.close();
        output.close();

    }

}
