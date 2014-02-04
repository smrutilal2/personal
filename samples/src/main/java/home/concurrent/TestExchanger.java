package home.concurrent;

import java.util.concurrent.Exchanger;

public class TestExchanger
{
    public static void main(String[] args)
    {
        Buffer source = new Buffer("Source");
        Buffer destination = new Buffer("Destination");

        Exchanger<Buffer> exchanger = new Exchanger<Buffer>();

        new PrepareString(source, exchanger);
        new ConsumeString(destination, exchanger);
    }
}

class PrepareString implements Runnable
{
    private Exchanger<Buffer> exchanger;

    private Buffer source;

    public PrepareString(Buffer source, Exchanger<Buffer> exchanger)
    {
        this.source = source;
        this.exchanger = exchanger;
        new Thread(this).start();
    }

    @Override
    public void run()
    {
        char ch = 'A';
        for (int i = 0; i < 3; i++)
        {
            String str = new String();
            System.out.println("Filling Buffer : " + source.getId());
            for (int j = 0; j < 5; j++)
                str += ch++;
            source.setValue(str);
            try
            {
                source = exchanger.exchange(source);
            }
            catch (InterruptedException exception)
            {
                exception.printStackTrace();
            }
        }

    }
}

class ConsumeString implements Runnable
{
    private Exchanger<Buffer> exchanger;

    private Buffer destination;

    public ConsumeString(Buffer destination, Exchanger<Buffer> exchanger)
    {
        this.destination = destination;
        this.exchanger = exchanger;
        new Thread(this).start();
    }

    @Override
    public void run()
    {
        for (int i = 0; i < 3; i++)
        {
            try
            {
                System.out.println("Consumming Buffer : " + destination.getId());
                System.out.println("Got : " + destination.getValue());
                destination = exchanger.exchange(destination);
            }
            catch (InterruptedException exception)
            {
                exception.printStackTrace();
            }
        }
    }

}

class Buffer
{
    private String id;

    private String value;

    public Buffer(String id)
    {
        this.id = id;
        this.value = "";
    }

    public String getId()
    {
        return id;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

}
