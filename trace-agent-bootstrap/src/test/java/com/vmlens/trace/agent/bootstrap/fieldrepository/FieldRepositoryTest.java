package com.vmlens.trace.agent.bootstrap.fieldrepository;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class FieldRepositoryTest {

    private static final FieldOwnerAndName volatileField =
            new FieldOwnerAndName("owner.test.Test", "volatile");
    private static final FieldOwnerAndName staticVolatileField =
            new FieldOwnerAndName("owner.test.Test", "staticVolatile");
    private static final FieldOwnerAndName normalField =
            new FieldOwnerAndName("owner.two.Test", "normal");
    private static final FieldOwnerAndName staticField =
            new FieldOwnerAndName("owner.two.Test", "static");

    private int volatileFieldId = -1;
    private int staticVolatileFieldId = -1;
    private int normalFieldId = -1;
    private int staticFieldId = -1;

    @Before
    public void setFieldsToMinusOne() {
        volatileFieldId = -1;
        staticVolatileFieldId = -1;
        normalFieldId = -1;
        staticFieldId = -1;
    }

    @Test
    public void analyzeTransformUse() {
        FieldRepository fieldRepository = new FieldRepository();
        analyze(fieldRepository);
        transform(fieldRepository);
        use(fieldRepository);
    }

    @Test
    public void transformAnalyzeUse() {
        FieldRepository fieldRepository = new FieldRepository();
        transform(fieldRepository);
        analyze(fieldRepository);
        use(fieldRepository);
    }

    private void analyze(FieldRepository fieldRepository) {
        fieldRepository.setFieldIsVolatile(volatileField);
        fieldRepository.setFieldIsVolatileStatic(staticVolatileField);
        fieldRepository.setFieldIsNormal(normalField);
        fieldRepository.setFieldIsStatic(staticField);
    }

    private void transform(FieldRepository fieldRepository) {
        volatileFieldId = fieldRepository.asInt(volatileField);
        staticVolatileFieldId = fieldRepository.asInt(staticVolatileField);
        normalFieldId = fieldRepository.asInt(normalField);
        staticFieldId = fieldRepository.asInt(staticField);
    }

    private void use(FieldRepository fieldRepository) {
        assertThat(fieldRepository.get(volatileFieldId), is(FieldRepository.VOLATILE_FIELD_STRATEGY));
        assertThat(fieldRepository.get(staticVolatileFieldId), is(FieldRepository.STATIC_VOLATILE_FIELD_STRATEGY));
        assertThat(fieldRepository.get(normalFieldId), is(FieldRepository.NORMAL_FIELD_STRATEGY));
        assertThat(fieldRepository.get(staticFieldId), is(FieldRepository.STATIC_FIELD_STRATEGY));
    }

}
