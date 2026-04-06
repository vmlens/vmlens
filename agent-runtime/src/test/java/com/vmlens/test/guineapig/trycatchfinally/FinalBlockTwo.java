package com.vmlens.test.guineapig.trycatchfinally;

public class FinalBlockTwo {

    private int i = 0;

    public void update() {
        try {
            callback(5);
            i = 6;
        }
        finally {
            i = 9;
        }
    }

    protected FinalBlockTwo callback(int i) {
        if(i == 66) {
            return this;
        }
        return null;
    }

}
