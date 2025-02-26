package com.vmlens.trace.agent.bootstrap.fieldrepository;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class FieldRepositoryImplTest {

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
        FieldRepositoryImpl fieldRepository = new FieldRepositoryImpl();
        analyze(fieldRepository);
        transform(fieldRepository);
        use(fieldRepository);
    }

    @Test
    public void transformAnalyzeUse() {
        FieldRepositoryImpl fieldRepository = new FieldRepositoryImpl();
        transform(fieldRepository);
        analyze(fieldRepository);
        use(fieldRepository);
    }

    private void analyze(FieldRepositoryImpl fieldRepository) {
        fieldRepository.getIdAndSetFieldIsVolatile(volatileField);
        fieldRepository.getIdAndSetFieldIsVolatile(staticVolatileField);
        fieldRepository.getIdAndSetFieldIsNormal(normalField);
        fieldRepository.getIdAndSetFieldIsNormal(staticField);
    }

    private void transform(FieldRepositoryImpl fieldRepository) {
        volatileFieldId = fieldRepository.asInt(volatileField);
        staticVolatileFieldId = fieldRepository.asInt(staticVolatileField);
        normalFieldId = fieldRepository.asInt(normalField);
        staticFieldId = fieldRepository.asInt(staticField);
    }

    private void use(FieldRepositoryImpl fieldRepository) {
        assertThat(fieldRepository.get(volatileFieldId), Matchers.is(FieldRepositoryImpl.VOLATILE_FIELD_STRATEGY));
        assertThat(fieldRepository.get(staticVolatileFieldId), Matchers.is(FieldRepositoryImpl.VOLATILE_FIELD_STRATEGY));
        assertThat(fieldRepository.get(normalFieldId), Matchers.is(FieldRepositoryImpl.NORMAL_FIELD_STRATEGY));
        assertThat(fieldRepository.get(staticFieldId), Matchers.is(FieldRepositoryImpl.NORMAL_FIELD_STRATEGY));
    }

}
