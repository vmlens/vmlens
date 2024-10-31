package com.vmlens.test.util;

import com.github.difflib.DiffUtils;
import com.github.difflib.patch.Patch;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class DiffText {

    public void assertEquals(String pathToExpected, String actualString) throws IOException {
        InputStream stream = this.getClass().getResourceAsStream(pathToExpected);
        List<String> expected = IOUtils.readLines(stream);

        List<String> actual = new LinkedList<>();
        Scanner scanner = new Scanner(actualString);
        while (scanner.hasNextLine()) {
            actual.add(scanner.nextLine());
        }

        Patch<String> patch = DiffUtils.diff(expected, actual);
        if (patch.getDeltas().size() > 0) {
            System.err.println(patch);
            System.err.println("-----------------------------------------------");
            System.err.println(actualString);
            System.err.println("-----------------------------------------------");
            throw new RuntimeException("not the same");
        }
    }
}
