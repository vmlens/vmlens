package com.vmlens.test.guineapig.trycatchfinally;

public class FinalBlockThree {

    public int update() {
        try {
            callback(5);
            return 4;
        }
        finally {
           return 2;
        }
    }

    protected FinalBlockThree callback(int i) {
        if(i == 66) {
            return this;
        }
        return null;
    }



}
