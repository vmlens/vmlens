package com.vmlens.inttest.projects.h2;

import com.vmlens.api.AllInterleavings;
import com.vmlens.api.AllInterleavingsBuilder;
import org.h2.mvstore.MVStore;
import org.h2.mvstore.tx.Transaction;
import org.h2.mvstore.tx.TransactionStore;
import org.junit.jupiter.api.Test;

public class TestTransactionStoreVMLens  {

    @Test
    public void testConcurrentPutAndGet() throws InterruptedException {
        try (AllInterleavings allInterleavings = new AllInterleavingsBuilder()
                .withReportAsSummaryThreshold(5)
                .build("testCacheLongKeyLIRSVMLens.testConcurrent")) {
            while (allInterleavings.hasNext()) {
                final MVStore s = new MVStore.Builder().open();
                TransactionStore ts = new TransactionStore(s);
                ts.init();
                Thread first = new Thread() {
                    @Override
                    public void run() {
                        Transaction transaction = ts.begin();
                        transaction.rollback();
                    }
                };
                first.start();
                Transaction transaction = ts.begin();
                transaction.commit();
                first.join();
                s.close();
            }
        }
    }

}
