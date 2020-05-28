package WeatherAppDashboard.ForMapper.GIOS.Stations;

public class GiosStationForMapper {
    private City city;

    private String addressStreet;

    private String gegrLon;

    private String stationName;

    private String id;

    private String gegrLat;

    public City getCity ()
    {
        return city;
    }

    public void setCity (City city)
    {
        this.city = city;
    }

    public String getAddressStreet ()
    {
        return addressStreet;
    }

    public void setAddressStreet (String addressStreet)
    {
        this.addressStreet = addressStreet;
    }

    public String getGegrLon ()
    {
        return gegrLon;
    }

    public void setGegrLon (String gegrLon)
    {
        this.gegrLon = gegrLon;
    }

    public String getStationName ()
    {
        return stationName;
    }

    public void setStationName (String stationName)
    {
        this.stationName = stationName;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getGegrLat ()
    {
        return gegrLat;
    }

    public void setGegrLat (String gegrLat)
    {
        this.gegrLat = gegrLat;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [city = "+city+", addressStreet = "+addressStreet+", gegrLon = "+gegrLon+", stationName = "+stationName+", id = "+id+", gegrLat = "+gegrLat+"]";
    }
}
