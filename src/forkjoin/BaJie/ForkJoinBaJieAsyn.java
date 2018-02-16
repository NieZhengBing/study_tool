package forkjoin.BaJie;

import forkjoin.MakePanTaoArray;
import forkjoin.SunWuKong.impl.WuKongPickImpl;
import forkjoin.SunWuKong.impl.WuKongProcessImpl;
import forkjoin.service.IPickTaoZi;
import forkjoin.service.IProcessTaoZi;
import forkjoin.vo.PanTao;

import java.sql.Time;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

/**
 * @author M
 * @create 2018/2/16
 */
public class ForkJoinBaJieAsyn {
    private static class XiaoBaJie extends RecursiveAction {
        private final static int THRESHOLD = 100;
        private PanTao[] src;
        private int fromIndex;
        private int toIndex;
        private IPickTaoZi pickTaoZi;

        public XiaoBaJie(PanTao[] src, int fromIndex, int toIndex, IPickTaoZi pickTaoZi) {
            this.src = src;
            this.fromIndex = fromIndex;
            this.toIndex = toIndex;
            this.pickTaoZi = pickTaoZi;
        }

        @Override
        protected void compute() {
            if (toIndex - fromIndex < THRESHOLD) {
                System.out.println(" from index= " + fromIndex + " toIndex= " + toIndex);
                int count = 0;
                for (int i = fromIndex; i < toIndex; i++) {
                    if (pickTaoZi.pick(src, i)) {
                        count++;
                    }
                }
            } else {
                int mid = (fromIndex + toIndex) / 2;
                XiaoBaJie left = new XiaoBaJie(src, fromIndex, mid, pickTaoZi);
                XiaoBaJie right = new XiaoBaJie(src, mid + 1, toIndex, pickTaoZi);
                invokeAll(left, right);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ForkJoinPool pool = new ForkJoinPool();
        PanTao[] src = MakePanTaoArray.makeArray();
        IProcessTaoZi processTaoZi = new WuKongProcessImpl();
        WuKongPickImpl pickTaoZi = new WuKongPickImpl(processTaoZi);

        long start = System.currentTimeMillis();

        XiaoBaJie xiaoBaJie = new XiaoBaJie(src, 0, src.length - 1, pickTaoZi);

        pool.execute(xiaoBaJie);
        System.out.println("BaJie is picking......");

        Thread.sleep(2);
        System.out.println("please waiting......");

        while (!xiaoBaJie.isDone()) {
            showLog(pool);
            TimeUnit.MILLISECONDS.sleep(100);
        }

        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.DAYS);
        showLog(pool);

        xiaoBaJie.join();
        System.out.println(" spend time: " + (System.currentTimeMillis() - start) + "ms");
    }

    private static void showLog(ForkJoinPool pool) {
        System.out.printf("***************************\n");

        System.out.printf("thread pool worker count:%d\n", pool.getPoolSize());

        System.out.printf("current execute thread count:%d\n", pool.getActiveThreadCount());

        System.out.printf("not blocked working thread:%d\n", pool.getRunningThreadCount());

        System.out.printf("already submit to thread pool but not execute task:%d\n", pool.getQueuedSubmissionCount());

        System.out.printf("already submit to thread pool but executing task:%d\n", pool.getQueuedTaskCount());

        System.out.printf("thread steal task is: %d\n", pool.getStealCount());

        System.out.printf("pool is stoped:%d\n", pool.isTerminated());

        System.out.printf("******************************************\n");
    }

}
