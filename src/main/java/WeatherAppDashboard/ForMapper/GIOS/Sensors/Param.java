package WeatherAppDashboard.ForMapper.GIOS.Sensors;

public class Param
{
    private String paramFormula;

    private String paramCode;

    private String idParam;

    private String paramName;

    public String getParamFormula ()
    {
        return paramFormula;
    }

    public void setParamFormula (String paramFormula)
    {
        this.paramFormula = paramFormula;
    }

    public String getParamCode ()
    {
        return paramCode;
    }

    public void setParamCode (String paramCode)
    {
        this.paramCode = paramCode;
    }

    public String getIdParam ()
    {
        return idParam;
    }

    public void setIdParam (String idParam)
    {
        this.idParam = idParam;
    }

    public String getParamName ()
    {
        return paramName;
    }

    public void setParamName (String paramName)
    {
        this.paramName = paramName;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [paramFormula = "+paramFormula+", paramCode = "+paramCode+", idParam = "+idParam+", paramName = "+paramName+"]";
    }
}