package com.vmlens.nottraced.agent.applyclasstransformer;

public class TransformerStrategyFilterInnerClasses  implements TransformerStrategy {

    private final String prefix;
    private final TransformerStrategy forAnonymousClass;

    public TransformerStrategyFilterInnerClasses(String prefix,
                                                 TransformerStrategy forAnonymousClass) {
        this.prefix = prefix;
        this.forAnonymousClass = forAnonymousClass;
    }

    @Override
    public byte[] transform(TransformerContext context) {
        String postFix = context.name().substring( prefix.length() );
        for (int i = 0; i < postFix.length(); i++) {
            if (!Character.isDigit(postFix.charAt(i))) {
                return null;
            }
        }
        return forAnonymousClass.transform(context);
    }
}
