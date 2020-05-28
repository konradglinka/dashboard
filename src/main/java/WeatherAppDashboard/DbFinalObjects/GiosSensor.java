package WeatherAppDashboard.DbFinalObjects;

public final class GiosSensor { //Obiekty tej klasy odwzorowują rekord z bazy danych z tabeli giossensors dlatego jej
    // pola mogą być jedynie ustawiane przy tworzeniu obiektu
    private final int IDSensor;
    private final int IDStation;
    private final String nameOfSensor;
    private final String shortNameOfSensor;
    private final String stationName;

    public GiosSensor(int IDSensor, int IDStation, String nameOfSensor, String shortNameOfSensor, String stationName) {
        this.IDSensor = IDSensor;
        this.IDStation = IDStation;
        this.nameOfSensor = nameOfSensor;
        this.shortNameOfSensor = shortNameOfSensor;
        this.stationName = stationName;
    }

    public int getIDStation() {
        return IDStation;
    }
    public int getIDSensor() {
        return IDSensor;
    }
    public String getNameOfSensor() {
        return nameOfSensor;
    }
    public String getShortNameOfSensor() {
        return shortNameOfSensor;
    }

    public String getStationName() {
        return stationName;
    }
}

