package com.vmlens.report.process;

import com.github.difflib.DiffUtils;
import com.github.difflib.patch.Patch;
import com.vmlens.report.domain.UILoop;
import org.junit.Test;

import java.io.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestIndex {

    @Test
    public void createReport() throws IOException {
        // Given
        UILoop uiLoop = mock(UILoop.class);

        when(uiLoop.getImagePath()).thenReturn("imagePath");
        when(uiLoop.getLink()).thenReturn("link");
        when(uiLoop.getName()).thenReturn("name");
        when(uiLoop.getResultText()).thenReturn("resultText");

        CreateOneReport createOneReport = new CreateOneReport("index");
        List<UILoop> list = Collections.singletonList(uiLoop);

        // When
        StringWriter writer = new StringWriter();
        createOneReport.create(list, writer);

        // Then
        BufferedReader actual = new BufferedReader(new StringReader(writer.toString()));
        BufferedReader expected = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("indexReport.txt")));

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
