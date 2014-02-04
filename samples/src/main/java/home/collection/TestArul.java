package home.collection;

import java.util.HashSet;
import java.util.Set;

public class TestArul
{

    public static void main(String[] args)
    {
        Set<Employee> set = new HashSet<Employee>(2);

        Employee e1 = new Employee("Arul");
        Employee e2 = new Employee("Arul");

        set.add(e1);
        set.add(e2);
        System.out.println(set.size());

        Employee_Equals ee1 = new Employee_Equals("arul");
        Employee_Equals ee2 = new Employee_Equals("arul");

        Set<Employee_Equals> s = new HashSet<Employee_Equals>(2);
        s.add(ee1);
        s.add(ee2);
        System.out.println(s.size());

    }

}
