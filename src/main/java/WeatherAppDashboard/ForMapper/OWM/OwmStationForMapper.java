package WeatherAppDashboard.ForMapper.OWM;

public class OwmStationForMapper
{
    private String country;

    private Coord coord;

    private String name;

    private String id;

    private String state;

    public String getCountry ()
    {
        return country;
    }

    public void setCountry (String country)
    {
        this.country = country;
    }

    public Coord getCoord ()
    {
        return coord;
    }

    public void setCoord (Coord coord)
    {
        this.coord = coord;
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

    public String getState ()
    {
        return state;
    }

    public void setState (String state)
    {
        this.state = state;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [country = "+country+", coord = "+coord+", name = "+name+", id = "+id+", state = "+state+"]";
    }
}
