package com.vmlens.report.createreport;

import com.github.difflib.DiffUtils;
import com.github.difflib.patch.Patch;
import com.vmlens.report.uielement.UITestLoop;
import com.vmlens.report.uielement.UITestLoopOrWarning;

import java.io.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.fail;

public class IndexTest {

    //@Test
    public void createReport() throws IOException {
        // Given
        UITestLoop uiLoop = new UITestLoop("name", 0, "resultText");
        uiLoop.setLink("link");

        CreateOneReport createOneReport = new CreateOneReport("index");
        List<UITestLoopOrWarning> list = Collections.singletonList(uiLoop);

        // When
        StringWriter writer = new StringWriter();
        createOneReport.createUITestLoop(list, writer);

        // Then
        BufferedReader actual = new BufferedReader(new StringReader(writer.toString()));
        BufferedReader expected = new BufferedReader(new InputStreamReader(getClass()
                .getClassLoader()
                .getResourceAsStream("indexReport.txt")));

        List<String> expectedList = toLineList(expected);
        List<String> actualList = toLineList(actual);

        Patch<String> diff = DiffUtils.diff(expectedList, actualList);
        expected.close();

        assertNoDiff(diff, writer.toString());
    }

    private void assertNoDiff(Patch<String> diff, String actual) {
        if (!diff.getDeltas().isEmpty()) {
            System.out.println(diff.getDeltas());
            System.out.println(actual);
            fail();
        }
    }

    private List<String> toLineList(BufferedReader reader) throws IOException {
        List<String> list = new LinkedList<String>();
        String line;
        while ((line = reader.readLine()) != null) {
            list.add(line.trim());
        }
        return list;
    }
}
