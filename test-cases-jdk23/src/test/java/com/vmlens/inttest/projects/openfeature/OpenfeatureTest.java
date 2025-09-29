package com.vmlens.inttest.projects.openfeature;

import com.vmlens.api.AllInterleavings;
import dev.openfeature.sdk.*;
import dev.openfeature.sdk.exceptions.FatalError;
import org.awaitility.Awaitility;
import org.awaitility.core.ConditionTimeoutException;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static dev.openfeature.sdk.ProviderState.FATAL;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class OpenfeatureTest {

    @Test
    public void setProviderAndGetState() throws Exception {
        try {
            try (AllInterleavings allInterleavings =
                         new AllInterleavings("OpenfeatureTest.setProviderAndGetState")) {
                while (allInterleavings.hasNext()) {
                    EventProvider mockProvider = spy(EventProvider.class);
                    ProviderState providerState = FATAL;
                    switch (providerState) {
                        case NOT_READY:
                            doAnswer(invocationOnMock -> {
                                while (true) {
                                }
                            })
                                    .when(mockProvider)
                                    .initialize(any());
                            break;
                        case FATAL:
                            doThrow(new FatalError("Error")).when(mockProvider).initialize(any());
                            break;
                    }
                    // Configure all evaluation methods with a single helper
                    configureMockEvaluations(mockProvider, ErrorCode.PROVIDER_FATAL, "error");

                    OpenFeatureAPI.getInstance().setProvider(providerState.name(), mockProvider);
                    Client client = OpenFeatureAPI.getInstance().getClient(providerState.name());
                    System.out.println(client.getProviderState());
                    ProviderEventDetails details =
                            ProviderEventDetails.builder().errorCode(ErrorCode.PROVIDER_FATAL).build();
                    switch (providerState) {
                        case FATAL:
                        case ERROR:
                            mockProvider.emitProviderReady(details);
                            mockProvider.emitProviderError(details);
                            break;
                        case STALE:
                            mockProvider.emitProviderReady(details);
                            mockProvider.emitProviderStale(details);
                            break;
                        default:
                    }
                    System.out.println(client.getProviderState());
                    Awaitility.await().pollDelay(Duration.ofMillis(1)).atMost(Duration.ofMillis(100)).until(() -> {
                        ProviderState providerState1 = client.getProviderState();
                        return providerState1 == providerState;
                    });
                }
            }
            // Fixme should fail everytime
            //fail("No ConditionTimeoutException");
        }
        catch(ConditionTimeoutException exp) {

        }
    }

    private void configureMockEvaluations(FeatureProvider mockProvider, ErrorCode errorCode, String errorMessage) {
        // Configure Boolean evaluation
        when(mockProvider.getBooleanEvaluation(anyString(), any(Boolean.class), any()))
                .thenAnswer(invocation -> createProviderEvaluation(invocation.getArgument(1), errorCode, errorMessage));

        // Configure String evaluation
        when(mockProvider.getStringEvaluation(anyString(), any(String.class), any()))
                .thenAnswer(invocation -> createProviderEvaluation(invocation.getArgument(1), errorCode, errorMessage));

        // Configure Integer evaluation
        when(mockProvider.getIntegerEvaluation(anyString(), any(Integer.class), any()))
                .thenAnswer(invocation -> createProviderEvaluation(invocation.getArgument(1), errorCode, errorMessage));

        // Configure Double evaluation
        when(mockProvider.getDoubleEvaluation(anyString(), any(Double.class), any()))
                .thenAnswer(invocation -> createProviderEvaluation(invocation.getArgument(1), errorCode, errorMessage));

        // Configure Object evaluation
        when(mockProvider.getObjectEvaluation(anyString(), any(Value.class), any()))
                .thenAnswer(invocation -> createProviderEvaluation(invocation.getArgument(1), errorCode, errorMessage));
    }
    private <T> ProviderEvaluation<T> createProviderEvaluation(
            T defaultValue, ErrorCode errorCode, String errorMessage) {
        return ProviderEvaluation.<T>builder()
                .value(defaultValue)
                .errorCode(errorCode)
                .errorMessage(errorMessage)
                .reason(Reason.ERROR.toString())
                .build();
    }

}
