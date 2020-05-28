package WeatherAppDashboard.DbFinalObjects;

public final class MeasureFromUser { //Obiekty tej klasy odwzorowują rekord z bazy danych z tabeli measuresfromusers dlatego jej
    // pola mogą być jedynie ustawiane przy tworzeniu obiektu
    private final int ID;
    private final int IDUSER;
    private final int IDUSERSTATION;
    private final String date;
    //OBJECT JEST NIEZBĘDNY BO WARTOŚĆ NULL JEST W TABLE VIEW WYŚWIETLANA JAKO 0 CO JEST NIE PRAWDĄ
    private final Object temp;
    private final Object windspeed;
    private final Object humidity;
    private final String cloudiness;
    private final Object pressure;
    //pola z innych tabel
    private final String email;
    private final String stationName;

    public MeasureFromUser(int ID, int IDUSER, int IDUSERSTATION, String date, Object temp, Object windspeed, Object humidity, String cloudiness, Object pressure, String email, String stationName) {
        this.ID = ID;
        this.IDUSER = IDUSER;
        this.IDUSERSTATION = IDUSERSTATION;
        this.date = date;
        this.temp = temp;
        this.windspeed = windspeed;
        this.humidity = humidity;
        this.cloudiness = cloudiness;
        this.pressure = pressure;
        this.email = email;
        this.stationName = stationName;
    }

    public String getEmail() {
        return email;
    }

    public String getStationName() {
        return stationName;
    }

    public int getID() {
        return ID;
    }

    public int getIDUSER() {
        return IDUSER;
    }

    public int getIDUSERSTATION() {
        return IDUSERSTATION;
    }

    public String getDate() {
        return date;
    }

    public Object getTemp() {
        return temp;
    }

    public Object getWindspeed() {
        return windspeed;
    }

    public Object getHumidity() {
        return humidity;
    }

    public String getCloudiness() {
        return cloudiness;
    }

    public Object getPressure() {
        return pressure;
    }
}
