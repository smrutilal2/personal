package home.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestThreadPoolExecuter
{
    int poolSize = 2;

    int maxPoolSize = 2;

    long keepAliveTime = 10;

    ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(5);

    ThreadPoolExecutor executor =
            new ThreadPoolExecutor(poolSize, maxPoolSize, keepAliveTime, TimeUnit.SECONDS, queue);

    private ExecutorService pool;

    public TestThreadPoolExecuter()
    {
        pool = Executors.newFixedThreadPool(2);
    }

    // Runnable runnable = getRunnable();

    private Future<?> runTask()
    {
        // executor.execute(getRunnable());
        Future<?> submit = pool.submit(getRunnable());
        return submit;
        // pool.execute(getRunnable());
    }

    private Runnable getRunnable()
    {
        return new Runnable()
        {

            @Override
            public void run()
            {
                for (int i = 0; i < 10; i++)
                {
                    System.out.println(i);
                    try
                    {
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        };

    }

    public static void main(String[] args)
    {
        List<Future> results = new ArrayList<Future>();
        TestThreadPoolExecuter threadPoolExecuter = new TestThreadPoolExecuter();
        results.add(threadPoolExecuter.runTask());
        results.add(threadPoolExecuter.runTask());
        results.add(threadPoolExecuter.runTask());
        for (Future future : results)
        {
            try
            {
                System.out.println(future.get());
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            catch (ExecutionException e)
            {
                e.printStackTrace();
            }
        }
    }

}
