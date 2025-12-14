package com.vmlens.report.input;

import com.vmlens.report.description.DescriptionContext;
import com.vmlens.report.input.run.RunElementType;
import com.vmlens.report.input.run.VolatileAccess;
import com.vmlens.report.input.run.memoryaccesskey.FieldIdAndObjectHashcode;
import com.vmlens.trace.agent.bootstrap.MemoryAccessType;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class VolatileAccessTest {

    @Test
    public void readObjectField() {
        // Given
        RunElementType runElementType = new VolatileAccess(new FieldIdAndObjectHashcode(5,657L),
                MemoryAccessType.IS_WRITE);
        DescriptionContext descriptionContext = mock(DescriptionContext.class);
        when(descriptionContext.fieldName(anyInt())).thenReturn("field");

        // When
        String text = runElementType.element(descriptionContext);

        // Then
        assertThat(text,is("field"));
    }

}
