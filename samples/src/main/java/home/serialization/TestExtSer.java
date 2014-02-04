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

public class TestExtSer
{

    public static void main(String[] args) throws FileNotFoundException, IOException,
            ClassNotFoundException
    {
        Sub sub = new Sub(3139, "ck", "abc");
        System.out.println(sub);
        System.out.println("***************Started Writing***************");
        ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("TestExtSer.out"));
        o.writeObject(sub);

        System.out.println("***************Started Reading***************");
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("TestExtSer.out"));
        sub = (Sub) in.readObject();
        System.out.println(sub);
    }

}

class Super implements Serializable
{
    int id;

    private String name;

    public Super()
    {
        System.out.println("Inside Super D-Constructor");
    }

    public Super(int id, String name)
    {
        this.id = id;
        this.name = name;
        System.out.println("Inside Super P-Constructor");
    }

    private void readObject(ObjectInputStream is) throws IOException, ClassNotFoundException
    {
        System.out.println("Inside Super readObject");
        is.defaultReadObject();
    }

    private void writeObject(ObjectOutputStream os) throws IOException, ClassNotFoundException
    {
        System.out.println("Inside Super writeObject");
        os.defaultWriteObject();
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException
    {
        System.out.println("Inside Super readExternal");
        name = (String) in.readObject();
        id = in.readInt();
    }

    public void writeExternal(ObjectOutput out) throws IOException
    {
        System.out.println("Inside Super writeExternal");
        out.writeObject(name);
        out.writeInt(id);
    }

    @Override
    public String toString()
    {
        return id + " " + name;
    }
}

class Sub extends Super implements Externalizable
{
    String etc;

    public Sub()
    {
        super();
        System.out.println("Inside Sub D-Constructor");
    }

    public Sub(int id, String name, String etc)
    {
        super(id, name);
        this.etc = etc;
        System.out.println("Inside Sub P-Constructor");
    }

    private void readObject(ObjectInputStream is) throws IOException, ClassNotFoundException
    {
        System.out.println("Inside Sub readObject");
        is.defaultReadObject();
    }

    private void writeObject(ObjectOutputStream os) throws IOException, ClassNotFoundException
    {
        System.out.println("Inside Sub writeObject");
        os.defaultWriteObject();
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException
    {
        System.out.println("Inside Sub readExternal");
        // name = (String) in.readObject();
        id = in.readInt();
        etc = (String) in.readObject();
    }

    public void writeExternal(ObjectOutput out) throws IOException
    {
        System.out.println("Inside Sub writeExternal");
        // out.writeObject(name);
        out.writeInt(id);
        out.writeObject(etc);
    }

    @Override
    public String toString()
    {
        return super.toString() + " " + etc;
        // return id + " " + name + " " + etc;
    }

}

/*************************
 * NOTES************************* 1. readExternal/writeExternal is called only once throughout the
 * hierarchy, if Externalizable is implemented. But readObject/writeObject is invoked once per
 * class. 2. If Externalizable is implemented, readObject/writeObject is never invoked; i.e.
 * readExternal/writeExternal has higher precedence over readObject/writeObject. 3.
 * readObject/writeObject would never be invoked if access modifier is made public. 4.
 * readObject/writeObject don't use constructor to instantiate the object. 5.
 * readExternal/writeExternal always use the default constructor to instantiate the object. If not
 * present, will through InvalidClassException. 6. If any private field exists then
 * readExternal/writeExternal can't use it. So to hide sensitive information use Externalizable. 7.
 * While implementing readExternal/writeExternal, Capture all the fields in lowest member of the
 * class hierarchy. 8. As readObject/writeObject works in a delegation model, you can still
 * serialize private fields present in super classes of the hierarchy.
 */
