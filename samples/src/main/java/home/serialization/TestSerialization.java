package home.serialization;

import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class TestSerialization
{

    public static void main(String[] args)
    {

        TestSerialization serialization = new TestSerialization();

        serialization.store(new Serialization_S(10), Serialization_S.fileName);
        System.out.println(((Serialization_S) serialization.load(Serialization_S.fileName))
                .getValue());

        System.out.println("****************************************************");

        serialization.store(new Serialization_E(20), Serialization_E.fileName);
        System.out.println(((Serialization_E) serialization.load(Serialization_E.fileName))
                .getValue());

        System.out.println("****************************************************");

        SimpleSub_S obj = new SimpleSub_S(20);
        obj.showValue();
        serialization.store(obj, SimpleSub_S.fileName);
        ((SimpleSub_S) serialization.load(SimpleSub_S.fileName)).showValue();

        System.out.println("****************************************************");

        SerializableSub_S ssub = new SerializableSub_S(20);
        ssub.showValue();
        serialization.store(ssub, SerializableSub_S.fileName);
        ((SerializableSub_S) serialization.load(SerializableSub_S.fileName)).showValue();

        System.out.println("****************************************************");

        Serializable_Composed_Good scg = new Serializable_Composed_Good(20);
        scg.showValue();
        serialization.store(scg, Serializable_Composed_Good.fileName);
        ((Serializable_Composed_Good) serialization.load(Serializable_Composed_Good.fileName))
                .showValue();

        System.out.println("****************************************************");

        Serializable_Composed_Bad sc = new Serializable_Composed_Bad(20);
        sc.showValue();
        serialization.store(sc, Serializable_Composed_Bad.fileName);
        ((Serializable_Composed_Bad) serialization.load(Serializable_Composed_Bad.fileName))
                .showValue();

    }

    private void store(Object obj, String fileName)
    {
        try
        {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(obj);
            out.close();
            fileOut.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private Object load(String fileName)
    {
        Object obj = null;
        try
        {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            obj = in.readObject();
            in.close();
            fileIn.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return obj;
    }

}

class Serialization_S implements Serializable
{
    static final String fileName = Serialization_S.class.getSimpleName() + ".ser";

    private static final long serialVersionUID = 1L;

    private int value;

    int getValue()
    {
        return value;
    }

    public Serialization_S(int value)
    {
        this.value = value;
    }

    // private void writeObject(java.io.ObjectOutputStream out) throws IOException
    // {
    // System.out.println("Object being Saved");
    // }
    //
    // private void readObject(java.io.ObjectInputStream in) throws IOException,
    // ClassNotFoundException
    // {
    // System.out.println("Object being read");
    // }

}

class Serialization_E implements Externalizable
{
    static final String fileName = Serialization_E.class.getSimpleName() + ".ser";

    private int value;

    public Serialization_E()
    {
    }

    public Serialization_E(int value)
    {
        this.value = value;
    }

    int getValue()
    {
        return value;
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException
    {
        value = in.readInt();
        System.out.println("Object loaded!!");
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException
    {
        out.writeInt(value);
        System.out.println("Object stored!!");
    }
}

class SimpleSuper
{

    int value_super;

    public SimpleSuper()
    {
    }

    public SimpleSuper(int value_super)
    {
        this.value_super = value_super;
    }

}

class SerializableSuper implements Serializable
{
    private static final long serialVersionUID = 1L;

    int value_super;

    public SerializableSuper()
    {
    }

    public SerializableSuper(int value_super)
    {
        this.value_super = value_super;
    }
}

class SimpleSub_S extends SimpleSuper implements Serializable
{
    static final String fileName = SimpleSub_S.class.getSimpleName() + ".ser";

    private static final long serialVersionUID = 1L;

    private final int value;

    public SimpleSub_S(int value)
    {
        super(value + 1);
        this.value = value;
    }

    void showValue()
    {
        System.out.println(value_super);
        System.out.println(value);
    }
}

class SerializableSub_S extends SerializableSuper implements Serializable
{
    static final String fileName = SerializableSub_S.class.getSimpleName() + ".ser";

    private static final long serialVersionUID = 1L;

    private final int value;

    public SerializableSub_S(int value)
    {
        this.value = value;
        this.value_super = value + 1;
    }

    void showValue()
    {
        System.out.println(value_super);
        System.out.println(value);
    }
}

class Serializable_Composed_Bad implements Serializable
{

    private static final long serialVersionUID = 1L;

    static final String fileName = Serializable_Composed_Bad.class.getSimpleName() + ".ser";

    private SimpleSuper simpleSupre;

    private int value;

    public Serializable_Composed_Bad(int value)
    {
        this.value = value;
        simpleSupre = new SimpleSuper(value + 2);
    }

    void showValue()
    {
        System.out.println(simpleSupre.value_super);
        System.out.println(value);
    }
}

class Serializable_Composed_Good implements Serializable
{

    private static final long serialVersionUID = 1L;

    static final String fileName = Serializable_Composed_Good.class.getSimpleName() + ".ser";

    private SerializableSuper serializableSuper;

    private int value;

    public Serializable_Composed_Good(int value)
    {
        this.value = value;
        serializableSuper = new SerializableSuper(value + 3);
    }

    void showValue()
    {
        System.out.println(serializableSuper.value_super);
        System.out.println(value);
    }
}
