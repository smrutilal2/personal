package home.collection;

import java.util.Iterator;
import java.util.Vector;

public class TestIterator
{

    public static void main(String[] args)
    {
        Vector<Employee> v = new Vector<Employee>(3);
        v.add(new Employee("A"));
        v.add(new Employee("B"));
        v.add(new Employee("C"));

        for (Iterator<Employee> iterator = v.iterator(); iterator.hasNext();)
        {
            // Before using iterator.remove(), the Iterator should
            // point to any of its elements. The remove() removes the
            // element which the Iterator currently pointing to.
            // Otherwise it will throw IllegalStateException

            // iterator.remove();

            Employee employee = (Employee) iterator.next();

            // Next statement will throw ConcurrentModificationException.
            // Means, Iterator won't allow object modification while it is
            // getting traversed. Even in the same thread.

            // v.remove(employee);

            System.out.println(employee);
            iterator.remove();
        }
        System.out.println(v.size());
    }

}
