package home.collection;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class TestHashTableConcurrentAccess
{
    Hashtable<Integer, String> hashTable = new Hashtable<Integer, String>();

    ThreadLocal<List<String>> tl = new ThreadLocal<List<String>>();

    static int index = 0;

    public static void main(String[] args) throws InterruptedException
    {
        TestHashTableConcurrentAccess test = new TestHashTableConcurrentAccess();

        T1 t1 = new T1(test);
        T2 t2 = new T2(test);
        T3 t3 = new T3(test);

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println(test.hashTable.values());

    }

    void addToTL(String str)
    {
        if (null == tl.get())
        {
            ArrayList<String> arrayList = new ArrayList<String>();
            arrayList.add(str);
            tl.set(arrayList);
        }
        else
        {
            tl.get().add(str);
        }
    }
}

class T1 extends Thread
{

    private final TestHashTableConcurrentAccess test;

    public T1(TestHashTableConcurrentAccess test)
    {
        this.test = test;
    }

    public void run()
    {
        for (int i = 1; i < 4; i++)
        {
            String str = "X" + i;
            int key = ++TestHashTableConcurrentAccess.index;
            System.out.println("Putting " + key + " & " + str);
            test.hashTable.put(key, str);
            test.addToTL(str);
        }
        System.out.println(this.currentThread().getName() + test.tl.get());
    }
}

class T2 extends Thread
{

    private final TestHashTableConcurrentAccess test;

    public T2(TestHashTableConcurrentAccess test)
    {
        this.test = test;
    }

    public void run()
    {
        for (int i = 1; i < 4; i++)
        {
            String str = "Y" + i;
            int key = ++TestHashTableConcurrentAccess.index;
            System.out.println("Putting " + key + " & " + str);
            test.hashTable.put(key, str);
            test.addToTL(str);
        }
        System.out.println(this.currentThread().getName() + test.tl.get());
    }
}

class T3 extends Thread
{

    private final TestHashTableConcurrentAccess test;

    public T3(TestHashTableConcurrentAccess test)
    {
        this.test = test;
    }

    public void run()
    {
        for (int i = 1; i < 4; i++)
        {
            String str = "Z" + i;
            int key = ++TestHashTableConcurrentAccess.index;
            System.out.println("Putting " + key + " & " + str);
            test.hashTable.put(key, str);
            test.addToTL(str);
        }
        System.out.println(this.currentThread().getName() + test.tl.get());
    }
}