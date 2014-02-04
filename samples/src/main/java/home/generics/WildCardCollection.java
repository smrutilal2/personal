package home.generics;

import java.util.ArrayList;
import java.util.List;

public class WildCardCollection
{
    public static void main(String[] args)
    {

        List<? extends Number> list = getList();
        List<? super Integer> list1 = getList();
        System.out.println(list);
        // list.add(new Integer(1)); // Won't compile
        list1.add(new Integer(7));
        System.out.println(list1);

        List<Integer> list2 = new ArrayList<Integer>();
        add(list2);
        System.out.println(list2);

        List list3 = new ArrayList();
        add(list3);

        List<Number> list4 = new ArrayList<Number>();
        list4.add(new Integer(13));
        test(list4);
        System.out.println(list4);
    }

    private static List<Integer> getList()
    {
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        return list;
    }

    private static void add(List<? super Integer> list)
    {
        list.add(11);
    }

    private static void test(List<Number> list4)
    {
        list4.add(new Integer(15));
        list4.add(new Float(13.6));
    }
}
