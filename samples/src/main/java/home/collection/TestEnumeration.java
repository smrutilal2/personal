package home.collection;

import java.util.Enumeration;
import java.util.Vector;

public class TestEnumeration
{

    public static void main(String[] args)
    {
        Vector<Employee> v = new Vector<Employee>(3);
        v.add(new Employee("A"));
        v.add(new Employee("B"));
        v.add(new Employee("C"));
        Enumeration<Employee> elements = v.elements();
        while (elements.hasMoreElements())
        {
            Employee employee = elements.nextElement();
            System.out.println(employee);
        }

        elements = v.elements();
        while (elements.hasMoreElements())
        {
            Employee employee = elements.nextElement();
            v.remove(employee);
        }
        System.out.println(v.size());
        System.out.println(v.get(0));
    }

}
