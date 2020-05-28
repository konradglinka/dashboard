package WeatherAppDashboard.ForMapper.GIOS.Stations;


public class City
{
    private Commune commune;

    private String name;

    private String id;

    public Commune getCommune ()
    {
        return commune;
    }

    public void setCommune (Commune commune)
    {
        this.commune = commune;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [commune = "+commune+", name = "+name+", id = "+id+"]";
    }
}