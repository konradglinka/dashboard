package WeatherAppDashboard.DbFinalObjects;

public final class AppSettings { //Obiekty tej klasy odwzorowują rekord z bazy danych z tabeli appsettings dlatego jej
    // pola mogą być jedynie ustawiane przy tworzeniu obiektu

    private final double minTemp;
    private final double maxTemp;
    private final double minWind;
    private final double maxWind;
    private final double minPressure;
    private final double maxPressure;


    public AppSettings(double minTemp, double maxTemp, double minWind, double maxWind, double minPressure, double maxPressure) {
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.minWind = minWind;
        this.maxWind = maxWind;
        this.minPressure = minPressure;
        this.maxPressure = maxPressure;
    }

    public double getMinTemp() {
        return minTemp;
    }
    public double getMaxTemp() {
        return maxTemp;
    }
    public double getMinWind() {
        return minWind;
    }
    public double getMaxWind() {
        return maxWind;
    }
    public double getMinPressure() {
        return minPressure;
    }
    public double getMaxPressure() {
        return maxPressure;
    }



}
