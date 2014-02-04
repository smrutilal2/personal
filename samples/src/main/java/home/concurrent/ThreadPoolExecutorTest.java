package home.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPoolExecutorTest
{

    public static void main(String[] args) throws InterruptedException, ExecutionException
    {
        List<Integer> list = prepare();
        RejectedExecutionHandler executionHandler = new MyRejectedExecutionHandelerImpl();
        ExecutorService pool = Executors.newFixedThreadPool(3);
        ((ThreadPoolExecutor) pool).setRejectedExecutionHandler(executionHandler);
        List<Future> results = new ArrayList<Future>();
        for (Integer integer : list)
        {
            TPRunner task = new TPRunner(integer);
            Future<?> res = pool.submit(task);
            results.add(res);
        }
        Thread.sleep(1000);
        for (Future future : results)
        {
            System.out.println("Is Completed?[null means completed]: " + future.get());
        }
    }

    private static List<Integer> prepare()
    {
        List<Integer> list = new ArrayList<Integer>();
        list.add(5);
        list.add(7);
        list.add(9);
        list.add(10);
        return list;
    }
}

class TPRunner implements Runnable
{

    private final Integer value;

    public TPRunner(Integer integer)
    {
        this.value = integer;
    }

    @Override
    public void run()
    {
        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println("Running with value=" + value);
    }
}

class MyRejectedExecutionHandelerImpl implements RejectedExecutionHandler
{
    @Override
    public void rejectedExecution(Runnable runnable, ThreadPoolExecutor executor)
    {
        System.out.println(runnable.toString() + " : I've been rejected ! ");
    }
}
