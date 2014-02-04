package home.algo.tree;

public class Calculator
{

    public Integer add(int x, int y)
    {
        return x + y;
    }

    public int substract(int x, int y)
    {
        return x - y;
    }

    public float devide(float i, float j) throws Exception
    {
        if (0 == j)
        {
            throw new Exception();
        }
        return i / j;
    }

}
