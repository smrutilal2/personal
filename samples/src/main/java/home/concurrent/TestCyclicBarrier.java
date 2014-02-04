package home.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class TestCyclicBarrier
{

    private int[][] seeds;

    private int[] sum;

    public TestCyclicBarrier(int[][] seeds)
    {
        this.seeds = seeds;
        sum = new int[seeds.length];
    }

    public void process()
    {
        Runnable merger = new Runnable()
        {

            @Override
            public void run()
            {
                for (int i = 0; i < sum.length; i++)
                {
                    System.out.println(sum[i]);
                }
            }
        };
        CyclicBarrier barrier = new CyclicBarrier(seeds.length, merger);
        for (int row = 0; row < seeds.length; row++)
        {
            new Thread(new Worker(row, barrier)).start();
        }
    }

    public static void main(String[] args)
    {
        int[][] ss = new int[][] { { 1 }, { 2, 2 }, { 3, 3, 3 }, { 3, 3, 4 } };
        new TestCyclicBarrier(ss).process();
    }

    class Worker implements Runnable
    {

        private int[] seed;

        private final CyclicBarrier barrier;

        private final int row;

        public Worker(int row, CyclicBarrier barrier)
        {
            this.row = row;
            this.seed = seeds[row];
            this.barrier = barrier;
        }

        @Override
        public void run()
        {
            int sum = 0;
            for (int s : seed)
            {
                sum += s;
            }
            TestCyclicBarrier.this.sum[row] = sum;
            try
            {
                barrier.await();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            catch (BrokenBarrierException e)
            {
                e.printStackTrace();
            }
        }
    }
}
