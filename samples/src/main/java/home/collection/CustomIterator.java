package home.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CustomIterator implements Iterator<Employee>
{

    List<Employee> list = new ArrayList<Employee>();

    int pointer = 0;

    int lastRet = -1;

    @Override
    public boolean hasNext()
    {
        return pointer != list.size();
    }

    @Override
    public Employee next()
    {
        Employee next = (Employee) list.get(pointer);
        lastRet = pointer++;
        return next;
    }

    @Override
    public void remove()
    {
        if (-1 == lastRet)
            throw new IllegalStateException();
        list.remove(lastRet);
    }

}
