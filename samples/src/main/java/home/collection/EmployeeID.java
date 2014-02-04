package home.collection;

class EmployeeID
{
    public String id;

    public EmployeeID(String id)
    {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return id;
    }

    @Override
    public boolean equals(Object obj)
    {
        return id.equals(((EmployeeID) obj).id);
    }

    @Override
    public int hashCode()
    {
        return id.hashCode();
    }
}

class EmployeeID_Equals
{
    public String id;

    public EmployeeID_Equals(String id)
    {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return id;
    }

    @Override
    public boolean equals(Object obj)
    {
        return id.equals(((Employee) obj).name);
    }
}

class EmployeeID_HashCode
{
    public String id;

    public EmployeeID_HashCode(String id)
    {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return id;
    }

    @Override
    public int hashCode()
    {
        return id.hashCode();
    }
}

class EmployeeID_Weird
{
    public String id;

    public EmployeeID_Weird(String id)
    {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return id;
    }

    @Override
    public int hashCode()
    {
        return 10;
    }

    @Override
    public boolean equals(Object obj)
    {
        return false;
    }
}