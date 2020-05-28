package WeatherAppDashboard.ForMapper.GIOS.Stations;


public class Commune
{
    private String districtName;

    private String communeName;

    private String provinceName;

    public String getDistrictName ()
    {
        return districtName;
    }

    public void setDistrictName (String districtName)
    {
        this.districtName = districtName;
    }

    public String getCommuneName ()
    {
        return communeName;
    }

    public void setCommuneName (String communeName)
    {
        this.communeName = communeName;
    }

    public String getProvinceName ()
    {
        return provinceName;
    }

    public void setProvinceName (String provinceName)
    {
        this.provinceName = provinceName;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [districtName = "+districtName+", communeName = "+communeName+", provinceName = "+provinceName+"]";
    }
}
