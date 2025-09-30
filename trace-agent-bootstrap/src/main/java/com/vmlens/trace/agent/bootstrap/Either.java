package com.vmlens.trace.agent.bootstrap;

public class Either<LEFT,RIGHT> {

    private final LEFT left;
    private final RIGHT right;

    private Either(LEFT left, RIGHT right) {
        this.left = left;
        this.right = right;
    }

    public static<LEFT,RIGHT> Either<LEFT,RIGHT> left(LEFT left) {
        return new Either<>(left, null);
    }

    public static<LEFT,RIGHT> Either<LEFT,RIGHT> right(RIGHT right) {
        return new Either<>(null, right);
    }

    public LEFT getLeft() {
        return left;
    }

    public RIGHT getRight() {
        return right;
    }


}
