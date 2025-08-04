package com.vmlens.trace.agent.bootstrap.fieldrepository;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

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

    @Test
    public void firstGetIdThanSetStrategy() {
        // Given
        FieldRepositoryImpl fieldRepository = new FieldRepositoryImpl();

        // When
        int isAsInt = fieldRepository.asInt(normalField);
        int idSetStrategy = fieldRepository.getIdAndSetFieldIsNormal(normalField);

        // Then
        assertThat(isAsInt,is(idSetStrategy));
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
        assertThat(fieldRepository.get(volatileFieldId), is(FieldRepositoryImpl.VOLATILE_FIELD_STRATEGY));
        assertThat(fieldRepository.get(staticVolatileFieldId), is(FieldRepositoryImpl.VOLATILE_FIELD_STRATEGY));
        assertThat(fieldRepository.get(normalFieldId), is(FieldRepositoryImpl.NORMAL_FIELD_STRATEGY));
        assertThat(fieldRepository.get(staticFieldId), is(FieldRepositoryImpl.NORMAL_FIELD_STRATEGY));
    }

}
